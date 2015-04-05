
public class TestSquare {
	public static void main(String[] args) {
		Square square1=new Square();
		System.out.println("The area of the square using default constructor(length ="+square1.getLength()+") is "+square1.getArea());
		Square square2=new Square(10);
		System.out.println("The area of the square using parameterised constructor(length ="+square2.getLength()+") is " +square2.getArea());
	}
}
