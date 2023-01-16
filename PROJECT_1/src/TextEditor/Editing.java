package TextEditor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContextPackage.NotFound;
/**
 * it is a class that implement all the methods for edit our file.
 * @author katerina tsimpirdoni
 *
 */
public class Editing {
	private DoublyLinkedList list = new DoublyLinkedList(); //Create a doubly linked list that called "list".
	private Node current; //current  Node is our position in the list. 
	private Node p; 
	
	/**
	 * class constructor
	 */
	public Editing() {
		super();
	
	}
	
	public Node getCurrent() {
		return current;
	}
	public void setCurrent(Node current) {
		this.current = current;
	}
	
	
	public Node getP() {
		return p;
	}
	public void setP(Node p) {
		this.p = p;
	}
	public DoublyLinkedList getList() {
		return list;
	}
	public void setList(DoublyLinkedList list) {
		this.list = list;
	}
	/**
	 * it is a method that Save the list on a text file .
	 * @param filename
	 * @param n
	 * @throws IOException
	 */
	public void SaveThechanges(String filename,Node n) throws IOException {
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(filename)); // open a file for writing
	    while(n!=null) {  
	    writer.write(n.getLine()); // write in the file the data of the elements on the list.
	    writer.newLine();     
	    n=n.getNext(); //get the next one.
	    }
	    writer.close();
	   
	}
	/**
	 * it reads the lines of a text file and put them in the doubly linked list.
	 * that method returns the position of the last element that it has on  the list.
	 * @param n
	 * @param filename
	 * @param maxwordinline
	 * @return node the position of the last node in the list.
	 */
	public Node Addelement( Node n, String filename, int maxwordinline)   {
		File file = new File(filename); 
		/**the basic reader**/
		BufferedReader reader;
		try {
			
			reader= new BufferedReader(new InputStreamReader(new FileInputStream(file))); // it open a file for reading
			
		  try {
			 
		  String line; 
		  
		  while ((line = reader.readLine()) != null) { // it read each line
			
			  if(line.length()>maxwordinline) { //if the letters of a line are more than 80.
				  
				  line=line.substring(0, maxwordinline-1); // it cut the line to have 80 letters
				  
		  }
			  
			n=list.Addlast ( n,line); //add the line as element of the list
			
			 
		  }
		  } catch (IOException e) {
				System.out.println("Cant read it");
				
		  }
			  
		  } catch (FileNotFoundException e) {
			  System.out.println("Cant find it,i have creat one"); //if the text file does not exist create one.
			  try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Cant create it");
			}
		
		}
		return n; 
			
		
			   
			    } 
			 
		   
		  
		 
        
		/**
		 * this method go to the first Node of the list(the first line of the file).
		 */
	
	public void GoToThefirstline() {
		current=list.getHead(); // get the first element of the list
		System.out.println("task completed");
		
	}
	/**
	 * this method go to the last Node of the list(the last line of the file).
	 */
	
	public void GoToThelastline() {
		current=list.getHead();
		while(current.getNext()!=null) {
			current=current.getNext();
			
		}
		System.out.println("task completed");
	
	}
	/**
	 * this method go on the next mode of the current one (down one line).
	 */
	public void Godownoneline(){
		if(current.getNext()!=null)
		current=current.getNext(); //get the next of the current Node.
		else {
			System.out.println("it is in the last line");
		}
		
		
	}
	/**
	 * this method  go on the previous Node of the current(up one line) .
	 */

	public void Gouponeline() {
		current=current.getPrev(); //get the next of the current Node.
		if(current==null) {
			System.out.println("it is the first line");
		}
	}
	/**
	 * this method add a new Node after the current Node(add a new line after the current line)  .
	 * @param n
	 * @param data   the content of the new node
	 * @return Node  that is the position of the new node. 
	 */
		public Node AddANewLineAfterCurrentLine(Node n,String data){
		 n = list.AddAfter(n, data);
		 System.out.println("Added after a current line");
		return n;
		
		
		
	}
		/**
		 * this method add a new Node before the current Node(add a new line before the current line)  .
		 * @param current
		 * @param data   the content of the new node
	     * @return Node  that is the position of the new node.
		 */
		public Node AddANewLineBeforeCurrentLine(Node current,String data){
			 current=list.Addbefore(current, data);
			 System.out.println("Added Before a current line");
			return current;
			 
			 
	
		
		}
/**
 * this method delete a Node from the list 
 * @param n it is the position of the Node that we want to  delete.
 * @return Node
 */
		public Node DeleteACurrentline(Node n) {
			return n=list.deleteNode(n);
				
			}
		
		/**
		 * 	this method print the all the elements of the list with their number.
		 * @param node  this node it is the next element each time, beginning with the first one. 
		 * @param n it is counter
		 */
			
		
		public void  PrintAllLinesWithLineNumber(Node node,int n) {
			while(node!=null) {
				System.out.println( n+")"+ node.getLine());
				node=node.getNext();
				n=n+1;
				
			}
		}
		/**
		 * 	this method print the all the elements 
		 * @param node  this node it was became the next element each time, beginning with the first one. 
		 * 
		 */
			
			public void  PrintAllLines(Node node) {
				while(node!=null) {
					System.out.println(node.getLine());
					node=node.getNext();
					
					
				}
		}
			/**
			 * this method print the current node
			 */
		public void PrintCurrentline() {
			if(current!=null)
			current.print();
		}
		
		/**
		 * this method print the current  line number.
		 */
		public void PrintCurrentLineNumber() {
			Node n=list.getHead(); // initialize n to head
			int num=1;
			while(n!=current ) {
				// it start from the begining to find the current node.
				n=n.getNext();
				num++;
			}
		   System.out.println("The current line number is:"+num);
		}
		/**
		 *  this method print the number of the elements and the characters of the data.
		 */
		public void PrintNumofLinesAndCharacters(){
			
			Node n=list.getHead();
			
			int count=0; //it is the counter for the elements.
			
			int count1=0;// it is the counter for the characters.
			
			while(n!=null) {
		  for(int i=0;i<n.getLine().length() ;i++) {
			 
			  if(n.getLine().charAt(i) != ' ')    //it get all the characters that is not space.
	                count1++;    
	        }    
				
				n=n.getNext();
			   count++;
			}
			System.out.println("characters: "+count1+",lines:"+count);
				
			}
			
			
		
			
			
				
			
			
			/**
			 * the menu of the choices.
			 */
			
		
         public void printmenu() {
        	 System.out.println("---------------------------------MENU----------------------------------------");
        	 System.out.println("-----------------------------------------------------------------------------");
        	 System.out.println("For go to the first line press:^");
        	 System.out.println("For go to the last line press:$");
        	 System.out.println("For go up one line press:-");
        	 System.out.println("For go down one line press:+");
        	 System.out.println("For add a new line after current line press:a");
        	 System.out.println("For add a new line before current line press:t");
        	 System.out.println("For delete a current line press:d");
        	 System.out.println("For print all lines press:l");
        	 System.out.println("For toggle wether line numbers are displayed when printing all line press:n");
        	 System.out.println("For print current line press:p");
        	 System.out.println("For quite without save press:q");
        	 System.out.println("For write file to disk press:w");
        	 System.out.println("For exit with save press:x");
        	 System.out.println("For print current line number press:=");
        	 System.out.println("For print number of lines and characters:#");
        	 System.out.println("For creat .ndx file and print the  page number:c");
        	 System.out.println("For Print index(word,line):v");
        	 System.out.println("For serial search:s");
        	 System.out.println("For binary search:b");
        	 System.out.println("------------------------------------------------------------------------------");
        	 System.out.println("------------------------------------------------------------------------------");
         }

        	 

        	 
         
	}






