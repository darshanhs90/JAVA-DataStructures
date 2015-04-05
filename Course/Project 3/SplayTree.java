import java.util.ArrayList;

public class SplayTree {
	public int elementsCount,levelCounter;
	private Node root;
	private String str="";
	private boolean boolChecker=false;
	private int counter=0;
	private int lastAccessedItem;
	private ArrayList<Integer> elemInitialList=new ArrayList<Integer>();
	private ArrayList<Integer> elemFinalList=new ArrayList<Integer>();
	public class Node {
		private Node left;
		private Node right;
		private int data;
		private Node parent;
		private int root;
		public Node(int data,Node left, Node right,Node parent) {
			this.left = left;
			this.right = right;
			this.data = data;
			this.parent=parent;
		}
	}
	public void add(int data){
		if(this.elementsCount==0){
			Node node=new Node(data,null,null,null);
			this.root=node;
			this.elementsCount++;
		}
		else{
			if(this.findElement(data)==false){
				Node node1=add(data,root,null);		//next step,move it to top
				accessElement(data);
			}
			else{
				accessElement(data);
			}
		}
	}
	private void accessElement(int data) {//check for parent and grandparent null condition,if both not null,call rotationmethod		
		Node datanode=getNode(data);
		if(root.data==data||datanode.parent==null){//like base case
			root=datanode;
			return;
		}
		else {
			if(datanode!=null){
				if(datanode.parent!=null){
					if(datanode.parent.parent!=null){//perform zig-zag or zig-zig rotation
						rotation(data);
					}
					else{
						singleParentNull(data);
					}
				}
			}
		}
	}
	public void prePostOrder(){
		System.out.println("Pre Order is ");
		preOrder(root);
		System.out.println();
		System.out.println("Post Order is ");
		postOrder(root);
		System.out.println();		
	}
	private void postOrder(Node t) {
		if( t != null )
		{
			postOrder( t.left );
			postOrder( t.right );
			System.out.print( t.data+" " );
		}
	}
	private void preOrder( Node t )
	{	
		if( t != null )
		{
			System.out.print( t.data+" " );
			preOrder( t.left );
			preOrder( t.right );
		}
	}
	private void rotation(int data){//System.out.println("rotation Method");
		//determine the rotation required
		//call single rotation or doubleRotation method or singleRotation
		Node node=getNode(data);
		Node parent=node.parent;
		Node grandParent=parent.parent;
		if((node.data>parent.data && node.data>grandParent.data)||(node.data<parent.data && node.data<grandParent.data))
		{
			singleRotation(data);
		}
		else if((node.data>parent.data && node.data<grandParent.data)||(node.data<parent.data && node.data>grandParent.data))
		{
			doubleRotation(data);
		}
	}
	private void singleParentNull(int data){//System.out.println("SingleParentNull Method");
		//call leftSingleParentNull or rightSingleParentNull method
		Node node=getNode(data);
		Node parent=node.parent;
		if(node.data>parent.data){
			leftSingleNull(data);
		}
		else if(node.data<parent.data){
			rightSingleNull(data);
		}
	}
	private void singleRotation(int data){//System.out.println("singleRotation Method");//call left single or right single 
		Node node=getNode(data);
		Node parent=node.parent;
		Node grandParent=parent.parent;
		if((node.data>parent.data && node.data>grandParent.data))
		{
			leftSingle(data);
		}
		else if((node.data<parent.data && node.data<grandParent.data)){
			rightSingle(data);
		}
	}
	private void doubleRotation(int data){//System.out.println("doubleRotation Method");//call leftdouble or rightdouble method		
		Node node=getNode(data);
		Node parent=node.parent;
		Node grandParent=parent.parent;
		if((node.data>parent.data && node.data<grandParent.data))
		{
			leftDouble(data);
		}
		else if((node.data<parent.data && node.data>grandParent.data)){
			rightDouble(data);
		}
	}
	private void leftSingle(int data){//System.out.println("leftSingle Method");//rotating from right side
		Node c=getNode(data);
		Node b=c.parent;
		Node a=b.parent;
		Node e=b.left;
		Node f=c.left;
		Node z=a.parent;//perform rotations from next step
		a.right=e;
		if(e!=null)e.parent=a;
		a.parent=b;
		b.right=f;
		b.left=a;
		if (f!=null)f.parent=b;
		b.parent=c;
		c.left=b;
		c.parent=z;
		if(z!=null){
			//System.out.println("z not null");
			if(z.data>c.data)
				z.left=c;
			else
				z.right=c;
		}
		if(z==null){
			root=c;
			return;
		}
		accessElement(data);
	}
	private void rightSingle(int data){//System.out.println("rightSingle Method");//rotating from left side
		Node c=getNode(data);
		Node b=c.parent;
		Node a=b.parent;
		Node f=b.right;
		Node e=c.right;
		Node z=a.parent;//perform rotations
		a.left=f;
		if(f!=null)f.parent=a;
		a.parent=b;
		b.left=e;
		b.right=a;
		if(e!=null)e.parent=b;
		b.parent=c;
		c.right=b;
		c.parent=z;
		if(z!=null){
			if(z.data>c.data)
				z.left=c;
			else
				z.right=c;
		}
		if(z==null){
			root=c;
			return;
		}
		//System.out.println();
		accessElement(data);
	}
	private void leftDouble(int data){//System.out.println("leftDouble Method");// "C" type rotation
		Node a=getNode(data);
		Node b=a.parent;
		Node c=b.parent;
		Node e=a.left;
		Node f=a.right;
		Node h=c.parent;//do rotations
		a.left=b;
		a.right=c;
		c.parent=a;
		b.parent=a;
		b.right=e;
		if(e!=null)e.parent=b;
		c.left=f;
		if(f!=null)f.parent=c;
		a.parent=h;
		if(h!=null){
			if(h.data>a.data)
				h.left=a;
			else
				h.right=a;
		}
		if(h==null){
			a.parent=null;
			root=a;
			return;
		}	//System.out.println();
		accessElement(data);
	}
	private void rightDouble(int data){//System.out.println("rightDouble Method");// ")" type rotation
		Node c=getNode(data);
		Node b=c.parent;
		Node a=b.parent;
		Node f=c.left;
		Node g=c.right;
		Node h=a.parent;//perform rotations
		c.left=a;
		c.right=b;
		a.parent=c;
		b.parent=c;
		a.right=f;
		if(f!=null)f.parent=a;
		b.left=g;
		if(g!=null)g.parent=b;
		c.parent=h;
		if(h!=null){
			if(h.data>c.data)
				h.left=c;
			else
				h.right=c;
		}
		if(h==null){
			c.parent=null;
			root=c;
			return;
		}	//System.out.println();
		accessElement(data);
	}
	private void leftSingleNull(int data){//System.out.println("leftSingleNull Method");//rotating from right side
		Node b=getNode(data);
		Node d=b.left;
		Node a=b.parent;//perform rotations
		a.right=d;
		if(d!=null)d.parent=a;
		a.parent=b;
		b.left=a;
		b.parent=null;
		root=b;//System.out.println();
		return;
	}
	private void rightSingleNull(int data){//System.out.println("rightSingleNull Method");//rotating from left side
		Node a=getNode(data);
		Node e=a.right;
		Node b=a.parent;
		b.left=e;
		if(e!=null)e.parent=b;
		b.parent=a;
		a.right=b;
		a.parent=null;
		this.root=a;//System.out.println(root.data+","+root.left+","+root.right.data);
		return;
	}
	private Node add(int data, Node node,Node parent) {
		//to add an element if there is an existing root
		if(node==null){
			Node node1=new Node(data,null,null,parent);
			elementsCount++;
			return node1;
		}
		if(data>node.data){//add to the right
			node.right=add(data,node.right,node);   
		}
		else if(data<node.data){
			node.left=add(data,node.left,node);
		}
		else{
			return null;
		}
		return node;
	}
	public boolean find(int data){
		boolChecker=false;
		boolean b1=(find(root,data));
		if(b1){//element found,move it to top||not found,use the last element accessed and move it to top
			accessElement(data);
		}
		else{//new method of find to get the last accessed element
			data=getLastAccessedElement(data);
			//System.out.println("data is "+data);
			accessElement(data);//here data is the lastaccessed element data
		}
		return b1;
	}
	private int getLastAccessedElement(int data) {//preOrder last item
		return(findLast(root, data));
	}
	public boolean findElement(int data){
		boolChecker=false;
		boolean b1=(find(root,data));
		//System.out.println("Root data is: "+root.data+"  Boolean value is "+b1);
		return b1;
	}
	private boolean find(Node node, int data) {
		// TODO Auto-generated method stub
		if (node==null){
			if(boolChecker==true)
				return true;
			else{
				return false;
			}
		}
		else{
			if(data>node.data){
				return find(node.right,data);
			}
			else if(data<node.data){
				return find(node.left,data);
			}
			else{
				boolChecker=true;
				return true;
			}
		}
	}	
	private int findLast(Node node, int data) {
		// TODO Auto-generated method stub
		int n;
		if (node==null){
			if(boolChecker==true)
				return 0;
			else{
				return -1;
			}
		}
		else{
			if(data>node.data){
				n= findLast(node.right,data);
				if(n==-1)
				{return node.data;}
			}
			else if(data<node.data){
				n= findLast(node.left,data);
				if(n==-1)return node.data;
			}
			else{
				boolChecker=true;
				return 0;
			}
		}
		return n;
	}
	public Node getNode(int data){
		Node n=getNode(root, data) ;
		return n;
	}
	private Node getNode(Node node, int data) {
		// TODO Auto-generated method stub
		if (node==null){
			//do nothing as data is not there
			if(boolChecker==true)
				return null;
			else{
				return null;
			}
		}
		else{
			if(data>node.data){
				return getNode(node.right,data);
			}
			else if(data<node.data){
				return getNode(node.left,data);
			}
			else{
				boolChecker=true;
				return node;
			}
		}
	}
	public int leafCount(){
		counter=0;
		return Integer.parseInt(leafCounter(root));
	}
	public int treeSum(){
		counter=0;
		return calculateTreeSum(root);
	}
	private int calculateTreeSum( Node t )
	{
		if( t != null )
		{
			calculateTreeSum( t.left );
			counter+=t.data;
			calculateTreeSum( t.right );
		}
		return counter;
	}
	@Override
	public String toString() {
		str="Inorder is :";
		return printTree(root);
	}
	private String printTree( Node t )
	{
		if( t != null )
		{
			printTree( t.left );
			str+=" "+t.data;
			printTree( t.right );
		}
		return str;
	}
	private String leafCounter( Node t )  
	{
		if( t.left != null || t.right!=null )
		{
			if(t.left!=null){
				leafCounter(t.left);
			}
			if(t.right!=null){
				leafCounter(t.right);
			}
		}
		else if(t.left==null && t.right==null){
			counter++;
		}
		return ""+counter;
	}	
	public void printElementsByLevel(){//modify the code//write own code using BFS
		//add elements to arrayList
		//find the left element and right element of the corresponding node
		//and add to the arrayList
		levelCounter=1;
		if(root!=null){
			elemInitialList.add(root.data);
			System.out.println("Level "+levelCounter);

			System.out.println(root.data);
			while(elemInitialList.size()!=0){
				elemFinalList=new ArrayList<Integer>();

				for(int i=0;i<elemInitialList.size();i++){
					populateList(elemInitialList.get(i));
				}
				//copy from final list to initial list
				levelCounter++;
				if(elemFinalList.size()!=0){
					System.out.println("Level "+levelCounter);
				}
				elemInitialList=new ArrayList<Integer>();
				for (int i = 0; i < elemFinalList.size(); i++) {
					System.out.println(elemFinalList.get(i));
					elemInitialList.add(elemFinalList.get(i));
				}
			}
		}
	}
	public void populateList(int data){
		Node n=getNode(data);
		if(n!=null){
			if(n.left!=null)
				elemFinalList.add(n.left.data);
			if(n.right!=null)
				elemFinalList.add(n.right.data);
		}
	}

}
class Stage2OfCopyOfTestSplayTree{
	public static void main(String[] args) {
		SplayTree splayTree=new SplayTree();
		System.out.println("Elements added in sequence are :9 2 90 53 4 64 95 59");
		splayTree.add(9);//System.out.println(splayTree.toString()+"  Add 10");splayTree.prePostOrder();
		splayTree.add(2);//System.out.println(splayTree.toString()+"  Add 2");splayTree.prePostOrder();
		splayTree.add(90);//System.out.println(splayTree.toString()+"  Add 53");splayTree.prePostOrder();
		splayTree.add(53);//System.out.println(splayTree.toString()+"  Add 4");splayTree.prePostOrder();
		splayTree.add(4);//System.out.println(splayTree.toString()+"  Add 64");splayTree.prePostOrder();
		splayTree.add(64);//System.out.println(splayTree.toString()+"  Add 95");splayTree.prePostOrder();
		splayTree.add(95);//System.out.println(splayTree.toString()+"  Add 59");splayTree.prePostOrder();
		splayTree.add(59);
		System.out.println();
		System.out.println(splayTree.toString());
		System.out.println();
		splayTree.prePostOrder();
		System.out.println();
		System.out.println("leaf count is "+splayTree.leafCount());
		System.out.println();
		System.out.println("TreeSum is "+splayTree.treeSum());
		System.out.println();
		System.out.println("Elements by Level is");splayTree.printElementsByLevel();
		System.out.println();
		System.out.println("Usage of find method:Case 1,when element is not found,last accessed element 95 is moved to root");
		System.out.println("Element 100 is found? "+splayTree.find(100));System.out.println(splayTree.toString());splayTree.prePostOrder();
		System.out.println();
		System.out.println("Usage of find method:Case 2,when element is found,element 59 is moved to root");
		System.out.println("Element 59 is found? "+splayTree.find(59));	System.out.println(splayTree.toString());splayTree.prePostOrder();
	}
}
