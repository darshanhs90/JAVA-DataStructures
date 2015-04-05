import java.util.ArrayList;
public class LinearProbingHashTable<AnyType> {
	public int tableSize=11;//initial size=11
	public int filledSize=0;
	public boolean initial=true;
	//private <Anytype>[];
	private ArrayList<AnyType> hashTable=new ArrayList<AnyType>();
	public LinearProbingHashTable() {
		for (int i = 0; i < tableSize; i++) {
			hashTable.add(i,null);
		}
	}
	public boolean contains( AnyType x ){
		//rehash the element(not needed),as the table size is dynamic,therefore check sequentially from that position		
		for (int i = 0; i < hashTable.size(); i++) {
			if (hashTable.get(i)!=null) {
				if(hashTable.get(i).equals(x)){
					return true; 
				}
			}
		}
		return false;
	}
	public void expandSize(){
		tableSize=tableSize*2+1;
		ArrayList<AnyType> newHashTable=new ArrayList<AnyType>();
		for (int i = 0; i < tableSize; i++) {
			newHashTable.add(i,null);
		}//initialise all elements to null for the hashtable size// helps in confining the table size
		for (int i = 0; i < hashTable.size(); i++) {
			newHashTable.add(hashTable.get(i));			
		}//copy the elements from the hashtable to the new hashtable
		hashTable=newHashTable;
	}
	public boolean insert( AnyType x ){
		if(filledSize>.5*tableSize){
			expandSize();
		}
		int val=applyHash(x);//applyhash function
		if(val<=0){
			val*=-1;
		}
		val=val%tableSize;
		if(contains(x)){
			return false;
		}//check for duplicates before inserting
		boolean b=true;
		while(b==true){
			if(hashTable.get(val)==null){
				hashTable.add(val,x);
				b=false;
				filledSize++;//after successfull insertion,increase the filledSize element
			}
			else{
				val++;
				val=val%tableSize;
			}
		}//insert the element according to the val
		return true;
	}
	private int applyHash(AnyType x) {
		int y=(((Object)x).hashCode());
		return y;
	}
}
