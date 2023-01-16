package BST;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Random;
/**
 * 
 * @author katerina tsimpirdoni.
 * This class contains methods  that  print the average of comparison per insert,the average of comparison of 100 search and of 100 search of range.
 * Also the  total time that  take the n insert and the 100 search in milliseconds.
 * the member variable of this class is an instance of a BinarySearchTree a Filename  and an instance of Random.
 *
 */
public class CountingOfBinarySearchTree {
	private BinarySearchTree bst = new BinarySearchTree();
	private String Filename;
	Random rand = new Random();
	
	
	
	
	/**
	 * class construction
	 * @param filename
	 */
	public CountingOfBinarySearchTree(String filename) {
		super();
		Filename = filename;
	}
	
/**
 * read the file with the number and insert them in the BinaryTree, also print the average of comparisons for the  inserts and the time in milliseconds.
 * @throws FileNotFoundException 
 * 
 */
	public void avarageComparison() throws FileNotFoundException {
		 
		
		
		
		
		RandomAccessFile raf = new RandomAccessFile(Filename, "r");  // create a new RandomAccessFile with filename test
		
	
  byte[] number =new  byte[4]; //make a byte array that has size 4 
 int hasNext = 0;

		try {
			hasNext = raf.read(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		System.out.println("I can read it");
		} //read the first 4 bytes from the file.
	long start = System.nanoTime(); // start to count the time
	
 while( hasNext!=-1) {
	 int key=ByteBuffer.wrap(number).getInt();// take the number as demical.
		bst.insertrec(key); // insert  the  elements 
		
		
		
		try {
			hasNext=raf.read(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("I can read it");
		} 
		// take the next  4 bytes.
	
		
	}
long end = System.nanoTime(); // end the counting

 
 //finding the time difference
   long nsec = (end-start); 
  
  
  System.out.println( "time for "+ bst.getNumberofinsert()+" insert in ms:" +nsec/1000000.0);  // make the nanoseconds to milliseconds
  
 System.out.println( "avarage of comparisons per insert:"+bst.getCounter()/(float)bst.getNumberofinsert());
	}
	/** make 100 search  and print the average of comparisons for the  100 search and the time in milliseconds
	 * 
	 */
	public void avarageof100search() {
		long start = System.nanoTime();// start to count the time
		for(int i=0;i<100;i++) {
		int rand_int1 = rand.nextInt(); // take a random  integer
		bst.search(rand_int1); //search it
		
		
		
		}
		long end = System.nanoTime();// end the counting

		 
		 //finding the time difference
		   long nsec = (end-start);
		  
		   
		System.out.println( "time for 100 search in ms:" +nsec/1000000.0);  // make the nanoseconds to milliseconds
	    System.out.println("avarage of comparisons per search:"+bst.getCounterSearch()/100.0);
	

}
	
		
		
	
	/**
	 * make 100 search of range and print the average of comparisons for the  100 search of range.
	 * @param n is the size of the range.
	 */
	public void AvarageOfRangeSearch( int n) {
		bst.setCounterRange(0);
		for(int i=0;i<100;i++) {
		 int random = rand.nextInt(); // take a random  integer
		bst.range(random, random+n);
		
		}
		
		System.out.println("The range for k="+ n);
		
		System.out.println("avarage of comparisons per search:"+bst.getCounterRange()/100.0);
		
		
		
		
		
	
		
	}
	
	
}
