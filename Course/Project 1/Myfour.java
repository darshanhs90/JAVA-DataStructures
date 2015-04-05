import java.util.Comparator;


public class Myfour<T> implements Comparator<T> {
	private T item1,item2,item3,item4;
	public Myfour(T item1,T item2,T item3,T item4) {
		this.item1=item1;
		this.item2=item2;
		this.item3=item3;
		this.item4=item4;
	}
	public boolean allEqual(){
		if(compare(item1, item2)==1 && compare(item1, item3)==1 && compare(item1, item4)==1 ){
			System.out.println("The elements are equal");
			return true;}
		else{
			System.out.println("The elements are not equal");
			return false;}
	}
	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		if(o1.equals(o2))
			return 1;
		else
			return 0;
	}
	public void shiftLeft(){
		T temp;
		temp=item1;
		item1=item2;
		item2=item3;
		item3=item4;
		item4=temp;	
	}
	@Override
	public String toString() {
		return "("+item1.toString()+","+item2.toString()+","+item3.toString()+","+item4.toString()+")";
	}


	public static void main(String[] args) {
		Myfour<String> m1=new Myfour<String>("asdf","asdf","asdf","asdf");
		System.out.println(m1.toString());
		m1.allEqual();
		//System.out.println(m1.allEqual());
		Myfour<Integer> m2=new Myfour<Integer>(1,2,3,4);
		System.out.println(m2.toString());
		m2.allEqual();
		//System.out.println(m2.allEqual());
		m2.shiftLeft();
		System.out.println(m2.toString());
	}

}
