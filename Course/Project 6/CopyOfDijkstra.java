package newPkg;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CopyOfDijkstra {
	private String path="";
	private static String str1="";
	private static ArrayList<String> pathList;
	
	private static ArrayList<Vertex> vertexList;
	public static void main(String[] args) throws Exception {
		//Phase 1
		FileReader fileReader=new FileReader(new File("Project6-airports.txt"));
		BufferedReader bufferedReader=new BufferedReader(fileReader);
		String str=bufferedReader.readLine();
		vertexList=new ArrayList<Vertex>();
		while(str!=null){
			StringTokenizer stringTokenizer=new StringTokenizer(str," ");
			int j=stringTokenizer.countTokens()-1;
			String vertexName=stringTokenizer.nextToken();
			//System.out.println(str);
			String token="";
			List<String> edges=new ArrayList<String>();
			List<Integer> costs=new ArrayList<Integer>();
			int cost=0;
			while(j!=0){
				j=j-2;
				token=stringTokenizer.nextToken();
				cost=Integer.parseInt(stringTokenizer.nextToken());
				edges.add(token);
				costs.add(cost);
			}
			Vertex vertex=new Vertex(vertexName,edges,costs);
			vertexList.add(vertex);
			str=bufferedReader.readLine();
		}
		System.out.println();
		Scanner scanner=new Scanner(System.in);
		
		while(true){
			System.out.println("Enter departure airport:");
			String depAirport=scanner.next();
			depAirport=depAirport.toUpperCase();
			System.out.println("Enter arrival airport:");
			String arrAirport=scanner.next();
			arrAirport=arrAirport.toUpperCase();
			shortestPath(depAirport,arrAirport,vertexList);
			System.out.println("Check another route (Y/N)?");
			if(!(scanner.next().equalsIgnoreCase("Y"))){
				break;
			}
		}
	}

	private static void shortestPath(String depAirport, String arrAirport,ArrayList<Vertex> vertexList) {
		// TODO Auto-generated method stub
		//TreeSet<String> visitedElements =new TreeSet<String>();
		Vertex startVertex=null,endVertex=null;
		String v[]=new String[vertexList.size()];
		boolean known[]=new boolean[vertexList.size()];
		int dv[]=new int[vertexList.size()];
		String pv[]=new String[vertexList.size()];
		for (int i = 0; i < vertexList.size(); i++) {
			Vertex vertex=vertexList.get(i);
			v[i]=vertex.name;
			known[i]=false;
			dv[i]=0;
			pv[i]=depAirport;
			if(vertex.name.contentEquals(depAirport.toUpperCase()))
				startVertex=vertex;
			if(vertex.name.contentEquals(arrAirport.toUpperCase()))
				endVertex=vertex;			
		}		
		if(startVertex==null ||endVertex==null){
			System.out.println("Invalid Airport Names");
			return;
		}
		/*visitedElements.add(startVertex.name);

*/		int initSize=startVertex.adj.size();
		List<Integer> costStart=startVertex.cost;
		List<String> adjElements=startVertex.adj;
		int destPortIndex=0;
		for (int i = 0; i < pv.length; i++) {
			for (int j = 0; j < adjElements.size(); j++) {
				if(v[i].contentEquals(adjElements.get(j))){
					dv[i]=costStart.get(j);
				}
				if(v[i].contentEquals(depAirport)){
					dv[i]=-1;
					known[i]=true;
					pv[i]="FIRST";
				}
				if(v[i].contentEquals(arrAirport)){
					destPortIndex=i;
				}

			}
		}

		//checking for all elements within the main list
		//check for true count
		//calculate distances
		//if dist is less,replace with newer dist
		//print path along with cost
		boolean knownFlag=false;
		int min=999999999;
		int minIndex=-1;
		while(knownFlag!=true){
			min=999999999;
			List<Integer> costStartNeighbor=null;
			List<String> adjElementsNeighbor=null;
			//find minimum element by checking for index not false and min distance-done
			//select the minimum element index as minIndex-done
			//set its known as true-done
			//for its neighbors,check for distance by adding previous distance,if distance is less,update value,if not dont
			//repeat the same procedure

			/*find minimum element*/

			for (int i = 0; i < pv.length; i++) {
				if(known[i]==false && dv[i]!=0){
					if(dv[i]<min){
						min=dv[i];
						minIndex=i;
					}

				}
			}
			//System.out.println(v[minIndex]+" "+min+" "+minIndex);
			//System.out.println(v[minIndex]);
			known[minIndex]=true;
			//get vertex
			String vertexName=v[minIndex];
			//get its neighbors
			//get its neighbors cost
			for (int i = 0; i < vertexList.size(); i++) {
				if(vertexList.get(i).name.contentEquals(vertexName)){
					costStartNeighbor=vertexList.get(i).cost;
					adjElementsNeighbor=vertexList.get(i).adj;
				}				
			}

			//add cost if presentcost+its cost less than earlier cost
			for (int i = 0; i < adjElementsNeighbor.size(); i++) {
				if(!(adjElementsNeighbor.get(i).contentEquals(depAirport))){
					//get dv[current index]
					int dist=0;
					int index=-1;
					for (int j = 0; j < dv.length; j++) {
						if(adjElementsNeighbor.get(i).contentEquals(v[j])){
							dist=dv[j];
							index=j;
							break;
						}
					}
					if((dv[minIndex]+costStartNeighbor.get(i)<dist)){
						//System.out.println(v[minIndex]+" "+dv[minIndex]);
						dv[index]=dv[minIndex]+costStartNeighbor.get(i);
						//set previous vertex of the present vertex
						pv[index]=vertexName;
					}
				}
			}
			//get path and set path




			int j=0;
			for (int i = 0; i < dv.length; i++) {
				if(known[i]==false)
					j++;
			}
			if(j>dv.length-adjElements.size()-1){
				knownFlag=false;
			}
			else{
				knownFlag=true;
			}
		}
		/*for (int i = 0; i < pv.length; i++) {
			System.out.println(v[i]+" "+dv[i]+" "+pv[i]);
		}*/
		
		//print the output
		//find the destination port,if distance =0,no connection
		if(dv[destPortIndex]==0){
			System.out.println("Connections : 0");
		}
		else{
			System.out.println("Price : "+dv[destPortIndex]);
			System.out.println("Connections : 1");
			str1="";
			str1+=arrAirport;
			/*for (int i = 0; i < pv.length; i++) {
				System.out.println(v[i]+" "+known[i]+" "+dv[i]+" "+pv[i]);
			}*/
			String str1=printPath(v,pv,arrAirport);
			/*System.out.println(str1);
			System.out.println("Route : "+str1.substring(0,str1.length()-8));
			str1="";
			System.out.println("Answer");*/
			String path="";
			for (int i = pathList.size()-1; i >=0; i--) {
				path+=pathList.get(i)+"-->";
			}
			System.out.println("");
			System.out.println(path.substring(0,path.length()-3));
			pathList=null;
			//System.out.println(str1);
		}
	}
	private static String printPath(String[] v, String[] pv, String arrAirport) {
		// TODO Auto-generated method stub
		/*System.out.println(str1);*/
		if(arrAirport.contentEquals("FIRST")){
			return "FIRST";
		}
		else{
			for (int i = 0; i < v.length; i++) {
				if(v[i].contentEquals(arrAirport)){
					arrAirport=pv[i];
					if(pathList==null){
						pathList=new ArrayList<String>();
					}
					pathList.add(v[i]);
				}
			}
			/*System.out.println("initial sting"+str1);
			str1+="<--"+arrAirport;
			System.out.println(arrAirport);*/
			arrAirport=printPath(v, pv, arrAirport);
			return str1;
		}


	}

	/*private static Vertex getVertex(String smallestWeightPort) {
		// TODO Auto-generated method stub
		for (int i = 0; i < vertexList.size(); i++) {
			Vertex vertex=vertexList.get(i);
			if(vertex.name.contentEquals(smallestWeightPort.toUpperCase()))
				return vertex;		
		}
		return null;
	}*/

	/*private static int findMin(List<Integer> costStart) {
		// TODO Auto-generated method stub
		int min=costStart.get(0);
		for (int i = 1; i < costStart.size(); i++) {
			if(costStart.get(i)<min)
				min=costStart.get(i);
		}
		return min;
	}*/
}
class Vertex
{
	public String     name;   // Vertex name
	public List<String> adj;    // Adjacent vertices
	public List<Integer> cost;   // Cost
	public Vertex     prev;   // Previous vertex on shortest path
	public int        scratch;// Extra variable used in algorithm

	public Vertex( String nm,List<String> adj,List<Integer> cost){
		this.name = nm; 
		reset( );
		this.adj=adj;
		this.cost=cost;

	}

	public void reset( ){ 
		cost = null; 
		prev = null; 
		scratch = 0;
	}    

	//public PairingHeap.Position<Path> pos;  // Used for dijkstra2 (Chapter 23)
}
