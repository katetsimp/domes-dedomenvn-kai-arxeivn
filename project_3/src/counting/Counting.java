package counting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Random;

import binarySearchTree.BinarySearchTree;
import hashing.LinearHashing;
/**
 * this class do the countings that it is necessary.
 * @author USER
 *
 */
public class Counting {
	private String Filename;
	private  static int  initPages = 100, pageSize = 10;
	private BinarySearchTree bst = new BinarySearchTree();
	private int c;
	private int c1;
	private  int search;
	private int min;
	private int max;
	private int search2;
	private int delete;
	private int delete2;
	private int k1;
	private int k2;
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getK1() {
		return k1;
	}
	public void setK1(int k1) {
		this.k1 = k1;
	}
	
	LinearHashing Hash1;

	public Counting(String filename) {
		super();
		Filename = filename;
		Hash1 = new LinearHashing(pageSize, initPages);
		
		min=0;
		max=400;
		
	
	}
	/**
	 *it counts the average of comparisons of insertions and calls the  other two method for the average of comparisons of search and delete.
	 * @param lF
	 * @param k
	 * @param i
	 * @throws FileNotFoundException
	 */
	
	public void insertion ( float lF, int k,int i) throws FileNotFoundException {
		int hasnext=0;
	int j=0;
	
	

	

	RandomAccessFile raf = new RandomAccessFile(Filename, "r"); // create a new RandomAccessFile with filename test
	 byte[] number =new  byte[4]; //make a byte array that has size 4 
	 
	 int counter=0;
	 int counter2=0;
	
	
	
	 try {
		raf.seek(i);
		
		 hasnext=raf.read(number);
		while(j<k && hasnext!=-1) {
			
		int key=ByteBuffer.wrap(number).getInt();
		
		
			
		counter=Hash1.insertKey(key, lF);
		counter2=bst.insertrec(key);
		
		c+=counter;
		c1+=counter2;
		
		
		
		j++;
			hasnext=raf.read(number);
			
		}
		
	
	} catch (IOException e) {
		System.out.println("I CAN FIND IT");
		
	}
	 if(hasnext==-1) {
		 k1+=k;
		 k2+=50;
		 
		System.out.println("the average comparison for "+k1+" insertions  is :"+c/(float)k1 );	
		System.out.println("Bst:the average comparison for "+bst.getNumberofinsert()+" insertion  is :"+c1/(float)bst.getNumberofinsert() );	
		 search(min,max);
		 delete(lF,min,max);
		 return;
	 }
	 else {
		 k1+=k;
		 k2+=50;
		
		System.out.println("the average comparison for "+k1+" insertions  is :"+c/(float)k1 );	
		System.out.println("Bst:the avarage comparison for "+bst.getNumberofinsert()+" insertion  is :"+c1/(float)bst.getNumberofinsert() );	
		
		search(min,max);
		delete(lF,min,max);
		
		
	System.out.println("---------------------------------------------------------------------------------" );	
		
		max+=400;
		insertion(lF, k, i+400);
	 }
	 }
		
	 
	 
	 
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	/**
	 * it counts the average of comparisons of searches.
	 * @param min
	 * @param max
	 * @throws FileNotFoundException
	 */
	public void search(int min, int max) throws FileNotFoundException {
		RandomAccessFile raf = new RandomAccessFile(Filename, "r");
		int k = 0;
		
		int counter=0;
		int counter2=0;
		
		byte[] number =new  byte[4]; //make a byte array that has size 4 
		Random r=  new Random ();
		
		 while(k!=50 ) {
		 int pos =r.nextInt((max - min)+1) + min; 
		 
		 if(pos%4==0) {
			
			 k++;
			 try {
				raf.seek(pos);
				int hasnext=raf.read(number);
				int key=ByteBuffer.wrap(number).getInt(); // take the number as demical.
				 counter= Hash1.searchKey(key);
				 counter2=bst.search(key);
				 search+=counter;
				 search2+=counter2;
				  
				  
				
			} catch (IOException e) {
				
				System.out.println("I CAN FIND IT");
			}
			 
		 }
			 
		 }
		
		System.out.println("the average comparison for 50 search  is :"+search/(float) k2);
	 System.out.println("bst:the Average comparison for 50 search  is :"+search2/(float) k2);
	}
	/**
	 * it counts the average of comparisons of deletions.
	 * @param lF
	 * @param min
	 * @param max
	 * @throws FileNotFoundException
	 */
	public void delete ( float lF, int min,int max) throws FileNotFoundException {
		
		RandomAccessFile raf = new RandomAccessFile(Filename, "r");
		int k = 0;
		int c=0;
		int c1=0;
		
		
		byte[] number =new  byte[4]; //make a byte array that has size 4 
		Random r=  new Random ();
		
		 while(k!=50 ) {
			 
		 int pos =r.nextInt((max - min)+1) + min; 
		
		 if(pos%4==0) {
			
			 k++;
			 try {
				raf.seek(pos);
				int hasnext=raf.read(number);
				int key=ByteBuffer.wrap(number).getInt(); // take the number as demical.
				
				 c=Hash1.deleteKey(key, lF);
				
				
				c1=bst.deleteKey(key);
				
				
				  delete+=c;
				  delete2+=c1;
				  
				
			} catch (IOException e) {
				System.out.println("I CAN FIND IT");
			}
			 
		 }
			 
		 }
		 
		System.out.println("the average comparison for 50 delete  is :"+delete/(float) k2);
		 System.out.println("bst:the average comparison for 50 delete  is :"+delete2/(float) k2);
		
	}
		
	}
	
	
	

