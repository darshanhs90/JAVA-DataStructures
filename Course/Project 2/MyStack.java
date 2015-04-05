import java.util.Iterator;


public class MyStack<AnyType> extends MyLinkedList<AnyType>{

	public void push(AnyType x){
		add(0,x);
	}
	public AnyType pop(){
		return remove(0);
	}
	public boolean checkBalance(MyStack<AnyType> list){
		MyStack<String> stack=new MyStack<>();
		for (int i = 0; i < list.size(); i++) {
			if ((list.get(i).equals("{"))||(list.get(i).equals("["))||(list.get(i).equals("("))) {
				//System.out.println(list.get(i));
				//stack.push(String.valueOf(list.get(i)));
				//System.out.println("popping"+stack.pop());
				stack.push(String.valueOf(list.get(i)));
			}
			else{
				if(stack.size()!=0){
					String str=stack.pop();
					if(str.equals("(")){
						//System.out.println("( match found");
						if(!(list.get(i).equals(")")))
						{
							//System.out.println("( false");
							return false;
						}

					}
					else if(str.equals("[")){
						//System.out.println("[ match found");
						if(!(list.get(i).equals("]")))
						{
							//System.out.println("[ false");
							return false;
						}
					}
					else if(str.equals("{")){
						//System.out.println("{ match found");
						if(!(list.get(i).equals("}")))
						{
							//System.out.println("{ false");
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
class TestStack
{
	public static void main( String [ ] args )
	{
		MyStack<String> myStack=new MyStack<>();
		String str="[{({}())}]";
		for (int i = 0; i < str.length(); i++) {
			myStack.add(String.valueOf(str.charAt(i)));
		}
		Iterator<String> itr = myStack.iterator( );
		while( itr.hasNext( ) )
		{
			System.out.print( itr.next( ));
		}
		System.out.println();
		if(myStack.checkBalance(myStack))
			System.out.println("The Nested symbols are Valid");
		else
			System.out.println("The Nested symbols are invalid");

		myStack=new MyStack<>();
		str="[{({)}())}]";
		for (int i = 0; i < str.length(); i++) {
			myStack.add(String.valueOf(str.charAt(i)));
		}
		itr = myStack.iterator( );
		while( itr.hasNext( ) )
		{
			System.out.print( itr.next( ));
		}
		System.out.println();
		if(myStack.checkBalance(myStack))
			System.out.println("The Nested symbols are Valid");
		else
			System.out.println("The Nested symbols are invalid");

	}

}