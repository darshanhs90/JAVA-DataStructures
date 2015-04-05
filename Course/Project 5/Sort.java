import java.util.ArrayList;
import java.util.Random;


/**
 * A class that contains several sorting routines,
 * implemented as static methods.
 * Arrays are rearranged with smallest item first,
 * using compares.
 * @author Mark Allen Weiss
 */
public final class Sort
{
	public static void bucketSort( Integer[] a)
	{
		Integer sorted[]=new Integer[1000];
		for (int i = 0; i < a.length; i++) {
			if(sorted[a[i]]==null){
				sorted[a[i]]=0;
			}
			sorted[a[i]]+=1;
		}
		/*for (int i = 0; i < sorted.length; i++) {
			if(sorted[i]!=null && sorted[i]!=0 ){
				for (int j = 0; j < sorted[i]; j++) {
					System.out.print(i+",");
				}
				System.out.println("");
			}

		}*/
	}
	
	public static void radixSort( Integer[] a)
	{
		StringBuilder[] sortedLSB=new StringBuilder[1000];//Least Significant Bit
		StringBuilder sortedNSB[]=new StringBuilder[1000];//Next Significant Bit
		//Integer sortedMSB[]=new Integer[1000];//Most significant Bit

		for (int i = 0; i < a.length; i++) {
			int j=a[i]%100%10;
			if(sortedLSB[j]==null){
				sortedLSB[j]=new StringBuilder("");
				//sortedLSB[j].append(str);
				sortedLSB[j].append(" ");
			}
			sortedLSB[j].append(a[i]+"/");
		}
		for (int i = 0; i < sortedLSB.length; i++) {
			if(sortedLSB[i]!=null){
				String str=sortedLSB[i].toString().trim();
				int x=0,z=0;
				while(str.indexOf("/",x)<str.lastIndexOf("/")){
					z=Integer.parseInt(str.substring(x,str.indexOf("/", x)));
					int z1=z%100/10;
					if(sortedNSB[z1]==null){
						sortedNSB[z1]=new StringBuilder("");
						sortedNSB[z1].append("");
					}
					sortedNSB[z1].append(z+"/");
					x=str.indexOf("/", x);
					x=x+1;
				}
				z=Integer.parseInt(str.substring(x,str.indexOf("/", x)));
				int z1=z%100/10;
				if(sortedNSB[z1]==null){
					sortedNSB[z1]=new StringBuilder("");
					sortedNSB[z1].append("");
				}
				sortedNSB[z1].append(z+"/");
			}
		}

		sortedLSB=new StringBuilder[1000];
		for (int i1 = 0; i1 < sortedNSB.length; i1++) {
			if(sortedNSB[i1]!=null){
				String str=sortedNSB[i1].toString().trim();
				int x=0,z=0;
				while(str.indexOf("/",x)<str.lastIndexOf("/")){
					z=Integer.parseInt(str.substring(x,str.indexOf("/", x)));
					int z1=z/100;
					if(sortedLSB[z1]==null){
						sortedLSB[z1]=new StringBuilder("");
						sortedLSB[z1].append("");
					}
					sortedLSB[z1].append(z+"/");
					x=str.indexOf("/", x);
					x=x+1;
				}
				z=Integer.parseInt(str.substring(x,str.indexOf("/", x)));
				int z1=z/100;
				if(sortedLSB[z1]==null){
					sortedLSB[z1]=new StringBuilder("");
					sortedLSB[z1].append("");
				}
				sortedLSB[z1].append(z+"/");
			}	
		}
		/*System.out.println("Sorted Order is:");
		for (int i2 = 0; i2 < sortedLSB.length; i2++) {
			if(sortedLSB[i2]!=null)
				System.out.println(sortedLSB[i2].substring(0));
		}*/
	}
	public static void main(String[] args) {
		Integer a[]=new Integer[20];
		for (int i = 0; i < a.length; i++) {
			Random random=new Random();
			a[i]=random.nextInt(1000);
		}
		bucketSort(a);
		radixSort(a);


		//size=100000
		a=new Integer[1000000];
		for (int i = 0; i < a.length; i++) {
			Random random=new Random();
			a[i]=random.nextInt(1000);
		}
		long initTime=System.currentTimeMillis();
		bucketSort(a);
		long finalTime=System.currentTimeMillis();
		System.out.println("Time required by bucket sort for size 1000000 is :"+(finalTime-initTime)+"ms");
		initTime=System.currentTimeMillis();
		radixSort(a);
		finalTime=System.currentTimeMillis();
		System.out.println("Time required by Radix sort for size 1000000 is :"+(finalTime-initTime)+"ms");

		//size=200000
		a=new Integer[200000];
		for (int i = 0; i < a.length; i++) {
			Random random=new Random();
			a[i]=random.nextInt(1000);
		}
		initTime=System.currentTimeMillis();
		bucketSort(a);
		finalTime=System.currentTimeMillis();
		System.out.println("Time required by bucket sort for size 200000 is :"+(finalTime-initTime)+"ms");
		initTime=System.currentTimeMillis();
		radixSort(a);
		finalTime=System.currentTimeMillis();
		System.out.println("Time required by Radix sort for size 200000 is :"+(finalTime-initTime)+"ms");



		//size=1000000
		a=new Integer[100000];
		for (int i = 0; i < a.length; i++) {
			Random random=new Random();
			a[i]=random.nextInt(1000);
		}
		initTime=System.currentTimeMillis();
		bucketSort(a);
		finalTime=System.currentTimeMillis();
		System.out.println("Time required by bucket sort for size 1000000 is :"+(finalTime-initTime)+"ms");
		initTime=System.currentTimeMillis();
		radixSort(a);
		finalTime=System.currentTimeMillis();
		System.out.println("Time required by Radix sort for size 1000000 is :"+(finalTime-initTime)+"ms");



	}
}