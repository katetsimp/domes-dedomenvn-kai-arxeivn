package Sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;


import compare.Compare;


/**
 *  this class contains methods:
 *  1)read a file and make array that it is sort.
 *  2)Create a byte array and write it in a file .ndx.
 *  3)Read from the file and print the words and the lines.
 *  it implements a interface Copy 
 *  
 * @author katerina tsimpirdoni
 *
 */

public class SortingAndSaveToDisk implements Copy   {
	
	private int minwordsize;
	private int maxwordsize;
	private int buffersize;
	
	/**
	 * class constructor
	 * @param minwordsize minimum word size =5.
	 * @param maxwordsize maximum word size =20.
	 * @param buffersize   is the size of the byte array (page).
	 */
	
	public SortingAndSaveToDisk(int minwordsize, int maxwordsize, int buffersize) {
		super();
		this.minwordsize = minwordsize;
		this.maxwordsize = maxwordsize;
		this.buffersize = buffersize;
	}

	/**
	 * read a file and make an array that contains all the words of each line and the number of line that has length bigger than 5 and smaller than 20.
	 * @param Filename the name of the text file
     * @return ArrayList<ArrayLine> that is the final array after sorting.
	 */

	
public ArrayList<ArrayLine> readFileAndMakeArray(String Filename) {
	 ArrayList<ArrayLine> sorting = new ArrayList<ArrayLine>();
	 String w=null;
	File file = new File(Filename);
    try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file))); //open a file for reading.
	
	try {
	String line;
	int lineCount=0;
     while( (line = reader.readLine())!= null ){  //read each line
        lineCount++;
        String [] word = line.split("\\s+"); //split the words between the space.
        
     for(int i=0;i<word.length;i++) {
    	w = word[i].replaceAll("[^a-zA-Z0-9]+","");
        	
        	   
        		if(w.length()>maxwordsize) {          //if the word has more than 20 characters.
        		w=w.substring(0,maxwordsize-1); //it cut the word to has 20 characters.
        		}
        		if(w.length()>=minwordsize) { // if the word length is more than 5 characters
        			
        			
        		sorting.add(new ArrayLine (w,lineCount));//add the word and the line into an ArrayList 
         		
         		
         		
        		}
        	
        	   }
         	
     }
		Collections.sort(sorting,new Compare()); //sort the Array

     
     
       } catch (IOException e) {
		// TODO Auto-generated catch block
    	   System.out.println("I can read the file");
	}
	 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I can find the file");
	 	 
}
	return sorting;
    
}

	/**
	 * 
	 * this method create bytes array that have sizes=buffersize from the array above. we take each element(word,line) and make them size maxwordzize+4.then put them in a byte array that has size buffersize.
	 *  the byte array can put buffersize/maxwordzize+4.
	 *  Each one of them  is copied to a file.
	 * The creation and the Copy stops when all elements finished.
	 * it has 3 case:
	 * 1)when the size of sorted array is less than buffersize/(maxwordsize+4 (pageline) and the byte array has size (maxwordsize+4) multiplying with size of sorted array.
	 * 2)when the array size modulo buffersize/(maxwordsize+4)(page line) is 0
	 * 3)when the array size modulo buffersize/(maxwordsize+4)(page line) is not 0.In that case it take  the integral multiple of array size with (page line)and the rest of it separately.
	 * @param myArray is the sorted ArrayListe
	 * @param filepath the name of the file it will be written inside.
	 * @throws IOException
	 */
	

public void CreateByteArray( ArrayList<ArrayLine> myArray,String filepath) throws IOException {
	  
	int counter=0;
	int i;
	int j;
	int n=0;
	 int pageline=buffersize/(maxwordsize+4);
	
	int d=myArray.size()%pageline;
	
	 
	//initialize arrays.
	  byte[] p=new byte[buffersize];
	  byte[]b=new byte[(maxwordsize+4)*d];
	  byte[]a=new byte[(maxwordsize+4)*myArray.size()];
	
	 
	  
	if(myArray.size()!=0 && myArray.size()<=pageline) { //if my array size<pageline then:
		 // create a new RandomAccessFile with filename test
    	RandomAccessFile file = new RandomAccessFile(filepath, "rw");//open a file for reading and writing.
		for(i=0;i<myArray.size();i++){ //i=myArray.size()
			//we get the i elements of the arrayList.
			int someInt = myArray.get(i).getNumline(); 
			String someString = myArray.get(i).getWord();
		    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(maxwordsize+4); // we use a byte  buffer  save 24 byte each time.
			/*
			 * the first element that put in the buffer is the integer because  we want to have size only 4 bytes.
			 * with this trick can assure that the integer has size 4 byte and the integer the rest of it.
			 */
		    bb.putInt(someInt);
			 bb.put(someString.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
			 
	    	for( j=0;j<maxwordsize+4;j++) {  //j is the each byte that copies.
		 

	    		a[i*(maxwordsize+4)+j]=bb.get(j); // put the element that we have in the buffer  in a byte array.
	          
	    	}
		}

	           
	    		file.write(a); //write in the file the byte array.
	    		file.close();// close the file.
	    		
	    		
	         
		
		
	
		
		System.out.println("ok.data page size of "+buffersize+ "bytes:1") ;  	
	}
	else {
		
		 // create a new RandomAccessFile with filename test
	    RandomAccessFile file = new RandomAccessFile(filepath, "rw"); //open a file for reading and writing.
	    if(d==0) { // if myArraysize  is integral multiple when it was divided with pageline.
	    	for(int k=0;k<myArray.size();k=k+pageline) {  // k became each time  +pageline.
	    		
	    	    for(i=0;i<pageline;i++) { // i became each time +1 until be equal with  pageline.
	    	    
	    	    	java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(maxwordsize+4);// we use a byte  buffer  save 24 byte each time
	    	    	//we get the k+i elements of the arrayList.
	    	    	ArrayLine al = myArray.get(k+i); 
	    		    int someInt = al.getNumline();
	    			String someString = al.getWord();
	    			/*
	    			 * the first element that put in the buffer is the integer because  we want to have size only 4 bytes.
	    			 * with this trick can assure that the integer has size 4 byte and the integer the rest of it.
	    			 */
	    			bb.putInt(someInt);
	    			bb.put(someString.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
	    			
	    	    	for( j=0;j<maxwordsize+4;j++) {//j is the each byte that copies.
	    			
	    			    
	    				 p[(i*(maxwordsize+4))+j]=bb.get(j); // put the element that we have in the buffer  in a byte array.
	    		    }
	    	    
	    	    }

	       
	           
	    		file.write(p);//write in the file the byte array.
	    		counter++;
	    		
	    }
	    }
	    
	    if(d!=0) {//if myArraysize  is not integral multiple when it was divided with pageline.
		for(int k=0;k<myArray.size()-pageline;k=k+pageline) { //we remove the  last   elements that do the size array do not be integral multiple when it was divided with pageline.
			// k became each time  +pageline.
	    for(i=0;i<pageline;i++) { //i became each time +1 until be equal with  pageline.
	    
	    	java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(maxwordsize+4);// we use a byte  buffer  save 24 byte each time
	    	//we get the k+i elements of the arrayList.
	    	ArrayLine al = myArray.get(k+i); 
		    int someInt = al.getNumline();
			String someString = al.getWord();
			/*
			 * the first element that put in the buffer is the integer because  we want to have size only 4 bytes.
			 * with this trick can assure that the integer has size 4 byte and the integer the rest of it.
			 */
			bb.putInt(someInt);
			bb.put(someString.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
			
	    	for( j=0;j<maxwordsize+4;j++) {//j is the each byte that copies.
			
			    
				 p[(i*(maxwordsize+4))+j]=bb.get(j); // put the element that we have in the buffer  in a byte array.
		    }
	    
	    }

   
       
		file.write(p);//write in the file the byte array.
		counter++;
		n=n+5; // this is a counter that helps to take the rest of the  array size.
		 
		 
		}
	

		
			
			for(int l=0;l<myArray.size()-n;l++) { //we take the rest of it.
				
				java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(maxwordsize+4);// we use a byte  buffer  save 24 byte each time
		    	//we get the n+l elements of the arrayList.
			    int someInt = myArray.get(n+l).getNumline(); 
				String someString = myArray.get(n+l).getWord();
				/*
				 * the first element that put in the buffer is the integer because  we want to have size only 4 bytes.
				 * with this trick can assure that the integer has size 4 byte and the integer the rest of it.
				 */
				bb.putInt(someInt);
				bb.put(someString.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
				
		    	for( j=0;j<maxwordsize+4;j++) {//j is the each byte that copies.
				
				    
					 b[(l*(maxwordsize+4))+j]=bb.get(j);// put the element that we have in the buffer  in a byte array.
			    }
		    
		    
	
		   
			}
		  
		   
				file.write(b);//write in the file the byte array.
				counter++;
				
			
			}
		
		file.close(); // close the file.
	
			
	
	

  
		    
	    
	  
	    
	  System.out.println("ok.data page size of "+buffersize+ " bytes:"+counter) ;		
  }
	 
}	
	
		
	
  
	
	  
	  
  
    	
    	

	
	

	 


	
   /**
    * This is a method that reads from a file  the content.it reads page by page the file(page is a byte array  that has size =biffersize).
    * Then it copy them  in others smaller byte arrays and finally print them.
    * @param myArray it is a sorted array.
    * @param FileName it is the filename that it reads.
    */


public void ReadFromDisk( ArrayList<ArrayLine> myArray,String FileName) {
	 try {
		 
	 
         // create a new RandomAccessFile with filename test
         RandomAccessFile raf = new RandomAccessFile(FileName, "r");//open the file for reading.
         int size=buffersize/(maxwordsize+4);
         byte[] page =new  byte[buffersize]; 
         int hasNext = 0;
         hasNext = raf.read(page); // read the first bytes(first page) from the file 
         //hasNext contains the number of bytes that read.
		 byte[] word = new byte[maxwordsize]; 
		 byte[] line = new byte[4];
		
			 
		 
         while(hasNext != -1) {  //when hasNext=-1 than it has no more byte to read.
        	
        	 
        	 
        
        	 
        	 
        	
        	 
    		 

        	 
        	 for(int i=0; i < (hasNext/(maxwordsize+4)) ; i++) {

        		 
        		 copy(page , word , (i*(maxwordsize+4))+4); //copy from page to  word byte array
        		 copy(page , line , (i*(maxwordsize+4))); //copy from page to line byte array.
        		
        		 
        		 System.out.println(new String(word) + "," + ByteBuffer.wrap(line).getInt()); //we create a String that it is the word and we create the line that needs wrap because it is number and the print them.
        		 
        	 }
        	 
        	 
        	 hasNext = raf.read(page); // read the next page 
        	 
        	 
         }
         
         
	 }catch (IOException e ) {
		 System.out.println("I can read it");
		 
	 }
	

}


/**
 * it is a method that copies from a byte array  to another from a specific beginning.
 */
@Override
public void copy(byte[] source, byte[] destination, int ofset) { 
	for(int i=0; i < destination.length ; i++) {
		destination[i] = source[i+ofset];
	}
	
}
	



			
				
        	
        	
        
        	
       
    	  
          	 

        

        	
        	
        

       
	
        
	

  


	
	





}

 