import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FinalCopyOfDictionary
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
		LinearProbingHashTable<String> hashTable=new LinearProbingHashTable<String>();
		BufferedReader br_new = new BufferedReader(new FileReader("nosilverbullet.txt"));
		String x=br.readLine();
		System.out.println("Dictionary Copy in Progress");
		Long start = System.currentTimeMillis( );
		File old=new File("dictionary.txt");
		File tempNew=new File("newtextdictionary.txt");
		copyFile(old,tempNew);
		System.out.println("Dictionary Copy complete");
		System.out.println("Dictionary Read in Progress");
		Long end = System.currentTimeMillis( );
		System.out.println( "Elapsed time for dictionary copy: " + (end-start) );
		List<String> adjacentWordsList=new ArrayList<String>();
		int count=0;
		start = System.currentTimeMillis( );
		while(x!=null){
			adjacentWordsList.add(x);
			System.out.println(x);
			hashTable.insert(x);
			x=br.readLine();
			count++;
		}
		end = System.currentTimeMillis( );
		System.out.println( "Elapsed time for dictionary read: " + (end-start) );
		System.out.println("Dictionary Read Complete");
		System.out.println("NoSilverbullet read in progress");
		String y=br_new.readLine();
		StringBuilder builder=new StringBuilder();
		start = System.currentTimeMillis( );
		while(y!=null){
			String a= y.replaceAll("[^\\w\\s]"," ");
			String b[]=a.split(" ");
			for (int i = 0; i < b.length; i++) {
				if(b[i].trim().length()>0){
					builder.append(b[i]+" ");
					System.out.print(".");
				}
			}
			y=br_new.readLine();
		}
		String[] str=builder.toString().split(" ");

		WordLadder ladder=new WordLadder();
		List<String> words=new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			if(!hashTable.contains(str[i].toLowerCase())){
				words.add(str[i]);
			}
		}	
		end = System.currentTimeMillis( );
		System.out.println( "Elapsed time for nosilverbullet read: " + (end-start) );

		System.out.println("NoSilverbullet read complete");
		Writer output;
		System.out.println("Adjacent words calculation in progress");
		start = System.currentTimeMillis( );
		for (int i = 0; i < words.size(); i++) {
			System.out.println(".");
			output = new BufferedWriter(new FileWriter(("dictionary.txt"),true));
			output.append("\n"+words.get(i));
			output.close();
			Map<String,List<String>> adjacentWords;
			adjacentWordsList=new ArrayList<String>();
			br = new BufferedReader(new FileReader("dictionary.txt"));
			x=br.readLine();
			while(x!=null){
				adjacentWordsList.add(x);
				x=br.readLine();
			}
			adjacentWords = ladder.computeAdjacentWords( adjacentWordsList );
			System.out.println( "Alternatives computed for" );
			Set<String> keys=adjacentWords.keySet();
			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println(string+" is misspelled ");
				System.out.println("Alternatives are:");
				System.out.print(adjacentWords.get(string));
			}
			System.out.println();
		}
		end = System.currentTimeMillis( );
		System.out.println( "Elapsed time for alternatives calculation: " + (end-start) );

		copyFile(tempNew, old);

		System.out.println("Alternatives computation Complete");
	}
	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}









}


class WordLadderNew
{
	public static List<String> readWords( BufferedReader in ) throws IOException
	{
		String oneLine;
		List<String> lst = new ArrayList<String>( );

		while( ( oneLine = in.readLine( ) ) != null )
			lst.add( oneLine );

		return lst;
	}    

	// Returns true is word1 and word2 are the same length
	// and differ in only one character.

	private static <KeyType> void update( Map<KeyType,List<String>> m, KeyType key, String value )
	{
		List<String> lst = m.get( key );
		if( lst == null )
		{
			lst = new ArrayList<String>( );
			m.put( key, lst );
		}

		lst.add( value );
	}

	// Computes a map in which the keys are words and values are Lists of words
	// that differ in only one character from the corresponding key.
	// Uses a quadratic algorithm (with appropriate Map).

	// Computes a map in which the keys are words and values are Lists of words
	// that differ in only one character from the corresponding key.
	// Uses a quadratic algorithm (with appropriate Map), but speeds things up a little by
	// maintaining an additional map that groups words by their length.



	// Computes a map in which the keys are words and values are Lists of words
	// that differ in only one character from the corresponding key.
	// Uses an efficient algorithm that is O(N log N) with a TreeMap, or
	// O(N) if a HashMap is used.
	public static Map<String,List<String>> computeAdjacentWords( List<String> words )
	{
		Map<String,List<String>> adjWords = new HashMap<String,List<String>>( );
		Map<Integer,List<String>> wordsByLength = new HashMap<Integer,List<String>>( );
		List<String> wordsNew=new ArrayList<String>();
		for (int i = 0; i < words.size(); i++) {
			if(words.get(i).length()==words.get(words.size()-1).length()){
				wordsNew.add(words.get(i));
			}
		}
		words=wordsNew;
		// Group the words by their length
		for( String w : words )
			update( wordsByLength, w.length( ), w );

		// Work on each group separately
		for( Map.Entry<Integer,List<String>> entry : wordsByLength.entrySet( ) )
		{    
			List<String> groupsWords = entry.getValue( );
			int groupNum = entry.getKey( );
			// Work on each position in each group
			for( int i = 0; i < groupNum; i++ )
			{
				// Remove one character in specified position, computing representative.
				// Words with same representative are adjacent, so first popular
				// a map
				Map<String,List<String>> repToWord = new HashMap<String,List<String>>( );

				for( String str : groupsWords )
				{
					String rep = str.substring( 0, i ) + str.substring( i + 1 );
					update( repToWord, rep, str );
				}

				// and then look for map values with more than one string
				for( List<String> wordClique : repToWord.values( ) )
					if( wordClique.size( ) >= 2 )
						for( String s1 : wordClique )
							for( String s2 : wordClique )
								if( s1 != s2 )
									update( adjWords, s1, s2 );
			}
		}
		Map<String,List<String>> adjWordsNew = new HashMap<String,List<String>>( );
		adjWordsNew.put(words.get(words.size()-1), adjWords.get(words.get(words.size()-1)));
		return adjWordsNew;
	}

	// Find most changeable word: the word that differs in only one

}