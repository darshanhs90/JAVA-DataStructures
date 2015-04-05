import java.util.Scanner;


public class Scores {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String[] names=new String[10];
		int[][] scores=new int[10][5];
		double[] average=new double[10];
		for(int i=0;i<10;i++){
			System.out.println("Enter the name of the "+(i+1)+"th person");
			names[i]=scanner.next();
			double temp=0;
			for(int j=0;j<5;j++){
				System.out.println("Enter the "+(j+1)+"th score of " +names[i]);
				scores[i][j]=scanner.nextInt();
				temp=temp+scores[i][j];
			}
			average[i]=temp/5;
		}
		for (int i = 0; i < names.length; i++) {
			System.out.println("The average Score of "+names[i] +" is :"+ average[i]);
		}
	}
}
