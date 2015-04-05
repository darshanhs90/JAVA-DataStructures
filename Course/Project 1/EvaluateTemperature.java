import java.util.Scanner;


public class EvaluateTemperature {
	private static int count=1;
	public static void main(String[] args) {
		System.out.println("Enter the temperature in Celsius or Fahrenheit");
		Scanner scanner=new Scanner(System.in);
		String textTemperature=scanner.next();
		int temperature=0;
		char identifier=textTemperature.charAt(textTemperature.length()-1);
		try{
			if(!(identifier=='F' || identifier=='C')){
				System.out.println("Illegal Temperature Format Entered,Specify 'C' or 'F'");
				count++;
			}
			String str=textTemperature.substring(0,textTemperature.indexOf(identifier));
			temperature=Integer.parseInt(str);
		}
		catch(Exception e){
			System.out.println("Illegal Temperature Entered");
			count++;
		}
		if(identifier=='C'){
			temperature=(int)(32+Math.floor(((double)5/9)*(double)temperature));
		}
		if(count==1){
			if(temperature<0)
				System.out.println("The temperature :"+temperature + " is Extremely Cold");
			else if(temperature>=0 && temperature<=32)
				System.out.println("The temperature :"+temperature + " is Very Cold");
			else if(temperature>=33 && temperature<=50)
				System.out.println("The temperature :"+temperature + " is Cold");
			else if(temperature>=51 && temperature<=70)
				System.out.println("The temperature :"+temperature + " is Mild");
			else if(temperature>=71 && temperature<=90)
				System.out.println("The temperature :"+temperature + " is Warm");
			else if(temperature>=90 && temperature<=100)
				System.out.println("The temperature :"+temperature + " is Hot");
			else
				System.out.println("The temperature :"+temperature + " is Very Hot");	
		}
	}
}
