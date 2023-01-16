package main;


import java.io.FileNotFoundException;
import java.io.IOException;

import BST.CountingOfBinarySearchTree;
import BSTWithArray.BinarySearchTreeWithArray;
import BSTWithArray.CountingOfbinarySearchTreeArray;
/**
 * 
 * @author katerina tsimpirdoni
 * this class contain the Main.
 *
 */

public class Ìain {
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		String filename=args[0];
		
		
		CountingOfBinarySearchTree cbst=  new CountingOfBinarySearchTree (filename);
		CountingOfbinarySearchTreeArray cbsta=new CountingOfbinarySearchTreeArray (filename);
		
		 System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		 System.out.println("binary search tree"); 
		 // Call all the methods that concern binary search tree. 
		try {
			
			cbst.avarageComparison();
			System.out.println("---------------------------------------");
			 cbst.avarageof100search(); 
			 System.out.println("---------------------------------------");
			cbst.AvarageOfRangeSearch(100);
			 System.out.println("---------------------------------------");
			 cbst.AvarageOfRangeSearch(1000);
			 System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			 System.out.println("binary search tree in array");
			 System.out.println("---------------------------------------");
		
		
		// Call all the methods that concern the array Nx3. 
		
			cbsta.avarageComparisonAndTime();
			 System.out.println("---------------------------------------");
			    cbsta.avarageof100searchandTime();
			    System.out.println("---------------------------------------");
			    cbsta.AvarageOfRangeSearch(100);
			    System.out.println("---------------------------------------");
			    cbsta.AvarageOfRangeSearch(1000);
			    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			    System.out.println("ArrayNx1");
			   // Call all the methods that concern the array Nx1. 
			    cbsta.AvarageOfSearchAndTimeArray();
			    System.out.println("---------------------------------------");
			    cbsta.AvarageOfRangeArray(100);
			    System.out.println("---------------------------------------");
			    cbsta.AvarageOfRangeArray(1000);
			    
		} catch (FileNotFoundException e) {
			System.out.println("I can not find the File");
		

		}
		
	    
	    
		
		 
		
    
		
	}

}