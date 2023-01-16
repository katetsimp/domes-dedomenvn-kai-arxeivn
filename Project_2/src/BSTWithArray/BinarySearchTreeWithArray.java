package BSTWithArray;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import BST.Node;
import MethodsBinarysearchTree.BinarySearchtreemethods;
/**
 * 
 * @author katerina tsimpirdoni
 * That class  has method for insert ,search ,print the data of the nodes in  order and search a range of  prices.
 * the member variable of that class is a integer  Root , some counter that use for count the comparison that has the  methods , two array one that it is Nx3 and
 * one that it is Nx1 and an integer that it call avail  that it gives the available lines in the list Nx3  and an ArrayList that helps to save temporarily the elements of the Nx1 array.
 *
 *
 */
public class BinarySearchTreeWithArray implements BinarySearchtreemethods {
	  private int Root;
	  private  int counter;
	  private int counterInsert;
	  private int counterSearch;
	  private int line;
	  private int[][] bstarray;
	  private ArrayList<Integer> array;
	  private Integer[] arrayNx1;
	  private int avail;
	  private int  counterRange;
	  private int counterbs;
	  

	  /** 
		 * class constructor 
		 */
	
	public BinarySearchTreeWithArray() {
		super();
		this.Root=-1;
		this.counter=0;
		bstarray= new int[counter][3];
		this.avail = 0;
		array= new ArrayList<Integer>();
		
		
	}
	public Integer[] getArrayNx1() {
		return arrayNx1;
	}
	public void setArrayNx1(Integer[] arrayNx1) {
		this.arrayNx1 = arrayNx1;
	}
	public ArrayList<Integer> getArray() {
		return array;
	}
	public void setArray(ArrayList<Integer> array) {
		this.array= array;
	}
	public int getRoot() {
		return Root;
	}
	public void setRoot(int root) {
		Root = root;
	}
	
	public int getAvail() {
		return avail;
	}
	public void setAvail(int avail) {
		this.avail = avail;
	}
	

	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int[][] getBstarray() {
		return bstarray;
	}
	public void setBstarray(int[][] bstarray) {
		this.bstarray = bstarray;
	}
	/**
	 * Initialize the array Nx3 and put in the  third column of each line the  number of next available line.
	 * it reads from a file all the number and with a counter counts them .
	 *  Then put in the third Column of each  line the number of the next line.
	 * @param Filename the  name of file that it reads.
	 * @return counter the number of the element that exist in the file.
	 * @throws IOException
	 */
	public int initializearray(String Filename) throws IOException  {
		
		 RandomAccessFile raf = null;
		
			try {
				raf = new RandomAccessFile(Filename, "r");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
			}
		
         byte[] number =new  byte[4]; 
        int hasNext = 0;
	
			hasNext = raf.read(number);
		
        while( hasNext!=-1) {
        	counter++;
        	
      
			hasNext=raf.read(number);
		
        }
        bstarray=new int[counter][3];
        for(int i=0;i<counter;i++) {
        	bstarray[i][2]=i+1;
        	
        }
		return counter;
	
		}
/**
 * this method take the next available  line.
 * 
 * @return
 */

	private int getnode() {
		int fN;
		if(avail==counter) {
			System.out.println("the list is full");
			return 0;
		}
		else 
			fN=avail;
		avail=bstarray[avail][2];
		return fN;
	}
	/**
	 * this method insert a element in the list. 
	 * Compares the new number that you want to insert with the number in the first line , first column.
	 * if line is -1 ,the array is empty put the first element
	 * if key is less than the first element of the array in line and if the second element of this line is -1 (empty) then  put the element in this line.
	 * else recall the method and in place of line put the second element of the line.
	 * if key is bigger than the first element of the array in line and if the third element of this line is -1 (empty) then  put the element in this line.
	 * else recall the method and in place of line put the third element of the line
	 * @param line  is the fist element in the array
	 * @param key is the data of the line
	 * @return   array[][]
	 */
	
	private int[][] insertRec(int line, int key) { 
		
		  
		  
        /* If the array is empty, return a new line */
		counterInsert++;
        if (line ==-1) {
        	
        	      counterInsert++;
    			 int temp_line = getnode();
    			 counterInsert++;
    			bstarray[temp_line][0] = key;
    			counterInsert++;
    			bstarray[temp_line][1] = -1;
    			counterInsert++;
    			bstarray[temp_line][2] = -1;
    			counterInsert++;
    			this.Root = temp_line;
    			
       
    			
        	
        } else {
        	counterInsert++;
        	if (key < bstarray[line][0]) {
        		
        	counterInsert++;
        	if(bstarray[line][1]==-1) {
        		counterInsert++;
        	int temp_line = getnode();
        	counterInsert++;
        	bstarray[line][1] = temp_line;
        	counterInsert++;
			bstarray[temp_line][0] = key;
			counterInsert++;
			bstarray[temp_line][1] = -1;
			counterInsert++;
			bstarray[temp_line][2] = -1;
        	
        	}else {
        		 insertRec( bstarray[line][1],  key);
        	}
        	
        	} 
        	counterInsert++;
        	if (key > bstarray[line][0]) {
        		
           counterInsert++;
        if(bstarray[line][2] == -1) {
        	counterInsert++;
			int temp_line = getnode();
			counterInsert++;
			bstarray[line][2] = temp_line;
			counterInsert++;
			bstarray[temp_line][0] = key;
			counterInsert++;
			bstarray[temp_line][1] = -1;
			counterInsert++;
			bstarray[temp_line][2] = -1;
     
           
           
        }    else {
        	insertRec( bstarray[line][2],  key);	 
        	
        }
        	}
        	
        }
        
        return bstarray;
	}      	
                
	/**
	 * This method search a number that user inserts.
	 * if the first element in this line  of the array is equal to key then print the data of the node .
	 * if the number is less than first element in this line recall the  method in place of line put the second element of the line.
     * if the number is bigger than  first element in this line recall the  method in place of line put the third element of the line.
	 * @param line
	 * @param key
	 * @return 
	 */
  
       
	private int search(int line, int key) 
	{
		counterSearch++;
		if( line==-1 ) {
			return 0;
		}
	    //  key is present at the content of first line.
		counterSearch++;
	    if ( bstarray[line][0]==key) {
	    	
		   System.out.println("I find the element:"+bstarray[line][0]);
	        return bstarray[line][0];

	    }
	    // val is greater than the content of first line.
	     counterSearch++;
	    if ( bstarray[line][0] > key) 
	        return search( bstarray[line][1], key); 
	  
	    
	    return search(bstarray[line][2], key); 
	}
	/**
	 * this method add all the first element  of each element of the array in ascending order into an ArrayList.
	 * if line is not -1 then take the second column of each element take content that exists inside and go in the line that content show you, 
	 * add all elements in the array ,
	 * then add the first element of the array ,
	 * and after take the third column of each element take the content that exists inside and go in the line that content show you and add all elements in the array  .
	 * the crossing became with the use of the inorderRec and as variable the second  or third column.
	 * @param line
	 * @return  the ArrayList
	 */
	
	 private ArrayList<Integer> inorderRec(int line) { 
		
		   
	        if (line != -1) { 
	        
	         inorderRec(bstarray[line][1]); 
	            array.add(bstarray[line][0]);
	       
	        inorderRec(bstarray[line][2]); 
	           
	            
	        }
	        return array;
	       
	        
	        
	    } 
	 /**
	  * * this method  print all the values of the array that is bigger than k1 and less than k2.
	  * if the first element of the line is bigger than k1 then call the Range again with variable the content of the second element of the line.
	  * if the first element of the line is bigger than k1 and less than k2  then print the values.
	  * if the first element of the line is less than k2 then call the Range again with variable the content of the third element of the line.
	  * @param line
	  * @param k1
	  * @param k2
	  */
	private void Range (int line,int k1,int k2) {
		counterRange++;
			if(line==-1)
				return;
			counterRange++;
			if(bstarray[line][0]>k1)
				Range(bstarray[line][1],k1,k2);
			counterRange++;
			if(bstarray[line][0]>k1 && bstarray[line][0]<k2) {
				
				//System.out.println(bstarray[line][0]);
			}
			counterRange++;
			if(bstarray[line][0]<k2) {
				Range(bstarray[line][2],k1,k2);
			}
	 }
	 

	/**
	 * this method call the insertrec that is private
	 */
@Override
public void insertrec(int key) {
	 
	insertRec(Root, key);
	
	
}
/**
 * this method call the search that is private
 */
@Override
public void search(int key) {
search(Root, key);
	
}
/**
 * this method call the range that is private
 */
@Override
public void range(int k1, int k2) {
	Range(line, k1, k2);
	
}
/**
 * this method call the inorderRec that is private
 */
@Override
public void inorder() {
array=inorderRec(line);

	
	
}
public int getCounterInsert() {
	return counterInsert;
}
public void setCounterInsert(int counterInsert) {
	this.counterInsert = counterInsert;
}
public int getCounterSearch() {
	return counterSearch;
}
public void setCounterSearch(int counterSearch) {
	this.counterSearch = counterSearch;
}
public int getCounterRangå() {
	return counterRange;
}
public void setCounterRangå(int counterRangå) {
	this.counterRange = counterRangå;
}
/**
 * transform the arrayList that inorderRec create to array[]; 
 */
public void transfromArrayListarray() {
	arrayNx1 = new Integer[getArray().size()];
	arrayNx1 = getArray().toArray(arrayNx1);
   
	
}
/**
 * take an array and search an element doing binary search.
 * if in the middle of the array is the element that it search then return the position of this .
 * if the middle of  array is  bigger than element ,that it search ,return the  method binarySearch  that  the middle is the middle of the left smaller array. 
 *  if the middle of  array is  less than element ,that it search, return the  method binarySearch  that  the middle this time  is  the  middle  of the right smaller array. 
 * if the l>r the binarySearchRange  return -1.
 * @param arr array that it search the element.
 * @param l the  position of the first element
 * @param r the  position of the last element
 * @param x the  element that it search
 * @return the position 
 */
 private int binarySearch(Integer arr[], int l, int r, int x) {
	setCounterbs(getCounterbs() + 1);
if (r >= l) { 
	setCounterbs(getCounterbs() + 1);
    int mid = l + (r - l) / 2; 

    // If the element is present at the middle 
    // itself 
    setCounterbs(getCounterbs() + 1);
    if (arr[mid] == x) {
        return mid; 
    }
    // If element is smaller than mid, then 
    // it can only be present in left subarray 
    setCounterbs(getCounterbs() + 1);
    if (arr[mid] > x) 
        return binarySearch(arr, l, mid - 1, x); 

    // Else the element can only be present 
    // in right subarray 
    return binarySearch(arr, mid + 1, r, x); 
}

return -1; 
 }
 /**
  * this method  print all the values of the array that is bigger than k1 and less than k2 this time doing binary search.
  * if the middle of array is bigger k1 then recall the  method binarySearchRange  that  the middle is the middle of the left smaller array 
  * if in the middle of the array between k1 and k2 then print it.
  * if the middle of array is less k2 then recall the  method binarySearchRange  that  the middle is the middle of the right smaller array  
  * if the l>r the binarySearchRange  return.
  * @param arr
  * @param l
  * @param r
  * @param k1
  * @param k2
  */
 private void binarySearchRange(Integer arr[], int l, int r, int k1,int k2) {
	
		setCounterbs(getCounterbs() + 1);
		if (l>r) {
			return;
		}
			setCounterbs(getCounterbs() + 1);
		    int mid = l + (r - l) / 2; 
		    setCounterbs(getCounterbs() + 1);
		    if(arr[mid]>k1) {
		    	binarySearchRange(arr, l, mid-1, k1, k2);
		    }
		    setCounterbs(getCounterbs() + 1);
		    if(arr[mid]<=k2 &&arr[mid]>=k1) {
		    	
		    	//System.out.println(arr[mid]);
		    }
		    setCounterbs(getCounterbs() + 1);
		    if(arr[mid]<k2) {
		    	binarySearchRange(arr, mid+1, r, k1, k2);
		    }
		    
		    return;
		    

		
		 }
 /**
  * this method call the binarySearch that is private
  */
 public void binarySearch( int key) {
	binarySearch(getArrayNx1() ,0,getArrayNx1().length-1 ,key );
	 
 }
 /**
  * this method call the binarySearchRange that is private
  */
 public void binarySearchRange(int k1, int k2) {
	 binarySearchRange(getArrayNx1(), 0, getArrayNx1().length-1, k1, k2);
 }
				
 
			    
			
		
		
		
	

 
 
public int getCounterbs() {
	return counterbs;
}
public void setCounterbs(int counterbs) {
	this.counterbs = counterbs;
}

	  
}
