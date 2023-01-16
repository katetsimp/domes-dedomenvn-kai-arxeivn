package BSTWithArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author katerina tsimpirdoni.
 * This class contains methods  that  print the average of comparison per insert,the average of comparison of 100 search and of 100 search of range.
 * Also the  total time that  take the n insert and the 100 search in milliseconds.
 * the member variable of this class is an instance of a BinarySearchTreeArray a Filename  and an instance of Random.
 *
 */
public class CountingOfbinarySearchTreeArray {
	 private BinarySearchTreeWithArray bsta= new BinarySearchTreeWithArray();
	private String Filename ;
	Random rand = new Random();
	/**
	 * class construction
	 * @param filename
	 */
	public CountingOfbinarySearchTreeArray(String filename) {
		super();
		Filename = filename;
	}
	/**
	 * read the file with the number and insert them in the BinaryTreeArray, also print the average of comparisons for the  inserts and the time in milliseconds.
	 * @throws FileNotFoundException 
	 * 
	 */
	public void avarageComparisonAndTime() throws FileNotFoundException {
		
		
	
		
		
		RandomAccessFile raf = new RandomAccessFile(Filename, "r"); // create a new RandomAccessFile with filename test
			try {
				bsta.initializearray(Filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //initialize array
			
			
			  byte[] number =new  byte[4]; //make a byte array that has size 4 
			 int hasNext = 0;

					try {
						hasNext = raf.read(number);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//read the first 4 bytes from the file.
					long start =System.nanoTime();// start to count the time
					
				

			 while( hasNext!=-1) {
				 int key=ByteBuffer.wrap(number).getInt(); // take the number as demical.
				 
			   bsta.insertrec(key);// insert  the  elements 
					
					
					
			 try {
				hasNext=raf.read(number);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // take the next  4 bytes.

				}
			long end = System.nanoTime();// end the counting

			 
			 //finding the time difference 
			   long nsec = (end-start);
			  
			System.out.println( "time for "+bsta.getCounter()+" insert in ms:" +nsec/1000000.0);// make them milliseconds.




			 System.out.println("avarage of comparisons per insert :"+bsta.getCounterInsert()/ (float)bsta.getCounter());
			 
				
		
		
	}
	/** 
	 * make 100 search in the array Nx3  and print the average of comparisons for the  100 search and the time in milliseconds
	 * 
	 */
	public void avarageof100searchandTime() {
		long start =System.nanoTime();// start to count the time
		for(int i=0;i<100;i++) {
		int rand_int1 = rand.nextInt(); // take a random  integer
		bsta.search(rand_int1);  //search it
		
		}
		
		long end = System.nanoTime();// end to count the time
		//finding the time difference 
		long nsec = (end-start);
		
		
		System.out.println( "time for 100 search in ms:" + (nsec/1000000.0));
		
		 
	
		 System.out.println("avarage of comparisons per search:"+bsta.getCounterSearch()/100.0);
	

}
	/**
	 * make 100 search of range in the array Nx3  and print the average of comparisons for the  100 search of range.
	 * @param n is the size of the range.
	 */
	public void AvarageOfRangeSearch( int n) {
		bsta.setCounterRangå(0);
		for(int i=0;i<100;i++) {
		 int random = rand.nextInt();  // take a random  integer
		bsta.range(random, random+n);
		
		}
	
		System.out.println("The range for k="+ n);
		System.out.println("avarage of comparisons per search:"+bsta.getCounterRangå()/100.0);
		
		
		
		
		
	
		
	}
	/** 
	 * make 100 binarysearch in the array Nx1  and print the average of comparisons for the  100 search and the time in milliseconds
	 * 
	 */
	public void AvarageOfSearchAndTimeArray() {
		bsta.inorder(); //  put the element into the array.
		bsta.transfromArrayListarray();// transform the arrayList to array
		long start =System.nanoTime();// start to count the time
		for(int i=0;i<100;i++) {
			 int random = rand.nextInt(); // take a random  integer
		bsta.binarySearch(random); //search it
		}
		long end = System.nanoTime();// end to count the time
		//finding the time difference 
		long nsec = (end-start);
		
	
		System.out.println( "time for 100 search in ms:" +nsec/1000000.0);// make the nanoseconds to milliseconds
		
		System.out.println("avarage of comparisons per search:"+bsta.getCounterbs()/100.0);
			 
}
	/** 
	 * make 100 binarysearch of range in the array Nx1  and print the average of comparisons for the  100 search and the time in milliseconds
	 * @param k is the size of the range.
	 */
	public void AvarageOfRangeArray(int k) {
		
		bsta.setCounterbs(0);
		for(int i=0;i<100;i++) {
			int random = rand.nextInt(); // take a random  integer
		
			bsta.binarySearchRange(random,random+k);
		}
		
		System.out.println("The range for k="+ k);
		
		System.out.println("avarage of comparisons per search:"+bsta.getCounterbs()/100.0);
			 
}
	
	}
