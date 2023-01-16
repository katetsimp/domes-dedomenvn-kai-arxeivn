package search;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;

import Sorting.Copy;
import compare.CompareInteger;
/**
 * This class contains 2 methods:
 * 1)serial search is a method that search the word in the file serially.
 * 2)binary search that is a method  search the word in the middle of the list until find it.
 * it is implements a interface  copy.
 * @author katerina 
 *
 */
public class Search  implements Copy{
private String FileName;

private int Buffersize;
private int maxWordSize;

	/**
	 * class constructor
	 * @param fileName is the name of the file that  read.
	 * @param buffersize is the size of the byte array (page).
	 * @param maxWordSize  maximum word size =20.
	 */
	public Search(String fileName, int buffersize, int maxWordSize) {
	super();
	FileName = fileName;
	Buffersize = buffersize;
	this.maxWordSize = maxWordSize;
}

/**
 * this method search the word ,for the beginning until the end, and print the line that exists.
 * if the word do not exist in the list then the method print a message.
 * @param wordSearch
 */
	public void serialSearch(String wordSearch) {
		try {
	        // create a new RandomAccessFile with filename test
	        RandomAccessFile raf = new RandomAccessFile(FileName, "r");//open the file for reading.
	        int counterDisk=0;// counter for count the disk accesses.
	        int counter=0;
	        String p;
	        ArrayList<Integer> l=new ArrayList<Integer>(); // in that ArrayList save the lines of word that it search.
	        byte[] page =new  byte[Buffersize];
	        int hasNext = 0;
	        hasNext = raf.read(page);// read the first bytes(first page) from the file 
	         //hasNext contains the number of bytes that read
	        counterDisk++;
	        
			 byte[] word = new byte[maxWordSize];
			 byte[] line = new byte[4];
			 if(hasNext==-1) {  // empty file
				 System.out.println("the file is empty");
				 return;
			 }
			  p=new String(page);
			 while(!p.contains(wordSearch) && hasNext!=-1) {
				 
				 hasNext = raf.read(page); // read the next page
				 p=new String(page);
				
		       	 counterDisk++;
				 
			 }
			 p=new String(page);
			 while(p.contains(wordSearch) && hasNext!=-1) { // if file  is not empty
				
	        	
	      

	       	 
	       	 for(int i=0; i<hasNext/(maxWordSize+4);i++) { 
	       	 
	       		  

	       		 
	       		 copy(page , word , (i*(maxWordSize+4))+4);//copy from page to  word byte array
        		 copy(page , line , (i*(maxWordSize+4))); //copy from page to line byte array.
         		
	       		 
	       		 
	       		String w=new String(word); //it transform the byte array into string.
	       		
	       		
	       	 
	       		if(w.contains(wordSearch)) {// if the string is the word that search 
	       			
	       		int line1=ByteBuffer.wrap(line).getInt();
	       		l.add(line1); //put the line that the word exists into  an ArrayList.
	       		
	       		
	       		}
	       	 }
	        	
	        	
	       			
	       	 hasNext = raf.read(page); // read the next page
	       	
	         p=new String(page);
	       	 counterDisk++;
	        	
	       		
	       	 }
			 if(!l.isEmpty()) { 
		     Collections.sort(l,new CompareInteger());//sort the Array
			 System.out.println( wordSearch+" : is in lines:"+l);
			 System.out.println("Disk accesses:" +counterDisk); 
			 }
	        if (l.isEmpty()) {
	        	System.out.println("the word does not exist in the list");
	        }
	        
	       
	       		
	       	 
	       	
	       		
	       		
	       		 
	       		
	       		
	       	 
	       	
	       	
	       	

	        
	  
	        
	  
		 }catch (IOException e ) {
			 System.out.println("I can read the file");
			 
		 
		 }
		
	}
	
/**
 * this method compares lexicographically last word of each page  with the word that we search.
 *  Each time when I don't find the word go to the middle of the file,until find it.
 *  When The word is found then we search in both sides of the file to find  the others are the same with the word that it search.
 *  Then it save the line into an ArrayList.
 *  if the Word does not exist in the folder then it print a message.
 * @param  last it is the size of the file.the position of the last element.
 * @param first  the position of the first element.
 * @param WordSearch  the  word that it search 
 * @throws IOException
 */

public  void binarySearch(int last,int first,String WordSearch ) throws IOException {
	int counterDisk = 0;// counter for count the disk accesses.
	
	int pmid=0;
	 ArrayList<Integer> lines =new ArrayList<Integer>();// in that ArrayList save the lines of word that it search.
	 // create a new RandomAccessFile with filename test
     RandomAccessFile raf = new RandomAccessFile(FileName, "r");//open the file for reading.
		 byte[] word = new byte[maxWordSize];
		 byte[] line = new byte[4];
       
		 String p;
        byte[] page =new  byte[Buffersize];
        int hasNext = 0;
       
        boolean next = true; 
        
       
        while(next==true)	{  //while the  next is true
        	
		 
			int  mid= (last+first)/2; // the middle of the list
			pmid=mid; // pmid keeps the    middle of the list.
			
			 
					
					
						raf.seek(mid*Buffersize); //  it begins to read the file from the mid*128
						 hasNext=raf.read(page); //it reads the first bytes(first page) from the file 
				         //hasNext contains the number of bytes that read
						 counterDisk++;
						
						
						 
						
					
				
				
				
				 p=new String(page);
				 
				 if(p.contains(WordSearch)) { // if that page contains the Word that search
					 while(p.contains(WordSearch) && hasNext!=-1) { // while that page contains the Word that search
						 raf.seek(mid*Buffersize);// it begin to read from mid*Buffersize
						 
		        			hasNext=raf.read(page); //it reads the bytes from the file 
					         //hasNext contains the number of bytes that read
		        			counterDisk++;
		        			 
		        			 
		        			
						
					
					
				
        
        		
        		 
        		
				
              	 for(int i=0; i<hasNext/(maxWordSize+4);i++) {
              	
              		
        			
              		 
              		 copy(page , word , (i*(maxWordSize+4))+4);//copy from page to  word byte array
            		 copy(page , line , (i*(maxWordSize+4))); //copy from page to line byte array.
              	    String w=new String(word); //it transform the byte array into string.
              	  
              	
              	if(w.contains(WordSearch)) {// if the string contains the word that search 
              	
        			
              		
              		int line1=ByteBuffer.wrap(line).getInt();
              	
               		lines.add(line1);//put the line that the word exists into  an ArrayList.
               		
               		
               	
               		
              	}
              
              	
              		
              	 }
              	
              	
              	
				 mid=mid+1; // add to mid one to go to the next page.
				 p=new String(page);
              
              
              	
				 
        		
        		
				 
        }
		  
        		
					
                   	pmid=pmid-1;// we take the previous  page.
                    if(pmid!=-1) {// if the  word is not  the first one.
                   	raf.seek(pmid*Buffersize);// it begin to read from mid*Buffersize.
        			hasNext=raf.read(page);//it reads the bytes from the file 
			         //hasNext contains the number of bytes that read
        			counterDisk++;
        			p=new String(page);
        			
        			
        			
        		while(p.contains(WordSearch)) {  // while that page contains the Word that search. 
        		
        			raf.seek(pmid*Buffersize); 
        			hasNext=raf.read(page);
        			
        			counterDisk++;
        			 
        			p=new String(page);
        		
        			 for(int i=0; i<hasNext/(maxWordSize+4);i++) {
        	              	

                  		 
                   		copy(page , word , (i*(maxWordSize+4))+4);
                   		copy(page , line , (i*(maxWordSize+4)));
                   	    String w=new String(word);
                   	  
                   	
                   	if(w.contains(WordSearch)) {
                   		
                   		
                   		int line2=ByteBuffer.wrap(line).getInt();
                   	
                   		
					lines.add(line2); //put the line that the word exists into  an ArrayList.
        			 }
        		}
        		pmid=pmid-1;
        		}
        		
                    }
        		Collections.sort(lines,new CompareInteger());//sort the Array
   			  System.out.println(WordSearch+": is in lines:"+lines); 
   			System.out.println("Disk accesses:" +counterDisk); 
   			  
        		next=false; 
        		
				 }
              	 if(lines.isEmpty() && hasNext!=-1) { //if the ArrayList lines is empty and it is not in the last element.
			 copy(page,word,(((hasNext/(maxWordSize+4))-1)*(maxWordSize+4))+4);// copy the last word of each page.
			 
			 
           
        	  String lastword=new String(word);
        	// it compares lexicographically last word with the word that we search
				if(lastword.compareTo(WordSearch)>0) { //if the last word is before the wordSearch.
              		last=mid-1;
              		
              		
              	}if(lastword.compareTo(WordSearch)<0) {//if the last word is after the wordSearch.
              		first=mid+1;
              		
              		
              	}
              	next=true; 
              	if(first > last ) { 
              		System.out.println("the Word does not exist int the list");
              		
              		next=false;
              	}
              	
              }
				if (hasNext==-1) {
					System.out.println("the Word does not exist int the list");
					
              		next=false;
					
				}
              	
					 
				
        }

		
		
			 
		 
		 
		 return;
			 
		 
		 
				
		 
        		
              

}
/**
 * it is a method that copies from a byte array  to another from a specific beginning.
 */
	
	public void copy(byte[] source, byte[] destination, int ofset) {
		for(int i=0; i < destination.length ; i++) {
			destination[i] = source[i+ofset];
		}
		
	}



		
	}


