package myEditor;


	
	
import java.io.IOException;
import java.util.ArrayList;

import Sorting.ArrayLine;
import Sorting.SortingAndSaveToDisk;
import TextEditor.Editing;
import TextEditor.StandardInputRead;
import search.Search;
/**
 * The class MyEditor it is the main:
 * it print a menu and the user give the choice that want.the choices has to do with the editing of a file .txt and the  of it  in .ndx .
 * @author USER
 *
 */
	public class MyEditor {
		
		/**
		 * that is the main.
		 * @param args
		 */
			public static void main(String[] args) {
				// that is our variables.
				int maxwordsize=20; 
				int minwordsize=5;
				int buffersize=128;
				int maxwordinline=80;
				
				String filename=args[0];
				int i=1;
				ArrayList<ArrayLine> myArray=new ArrayList<ArrayLine>(); 
				
				
				boolean exit=false;
				//we need access in this  class
				SortingAndSaveToDisk s=new SortingAndSaveToDisk(minwordsize,maxwordsize,buffersize); 
				Search se=new Search(filename+".ndx",buffersize,maxwordsize);
				Editing file =new Editing();
				StandardInputRead r=new StandardInputRead();
				 
				
			    file.setP( file.Addelement(file.getP(),filename,maxwordinline)); //it add the element from the .txt file in the doubly linked list. 
				file.setCurrent(file.getList().getHead()); //put the current in the first element
				while(exit==false) {
					boolean chances=false;	
				
				 /**
				  * this is our switch/case with all the choices.
				  */
				
				file.printmenu();// print the menu
				String c=r.readString("Give me your choice:");
				switch(c) {
				
				 // case: go to the first line 
				 //type:^
				 
				case "^":
					file.GoToThefirstline();
					chances=true;
					break;
					
					 // case: go to the last line 
					 //type:$
					 
				
			    case "$":
				file.GoToThelastline();
				chances=true;
				break;
				
				// case: go up one line
				//  type:-
				
			
			case "-":
				file.Gouponeline();
				chances=true;
				break;
				
				 // case: go down one line
				 // type:+
				 
			case "+":
				file.Godownoneline();
				chances=true;
				break;
				
				 //case:add a new line after
				// type:a
				
			case "a":
				String data=r.readString("please type the line content:\n");
				file.setCurrent(file.AddANewLineAfterCurrentLine(file.getCurrent(), data));
				chances=true;
				break;
				//case:add a new line before
				 // type:t
				 
			case "t":
				String data1=r.readString("please type the line content:\n");
				file.setCurrent(file.AddANewLineBeforeCurrentLine(file.getCurrent(), data1));
				chances=true;
				break;
				
				 // case:delete current
				 // type:d
				
			
			case "d":
				file.setCurrent(file.DeleteACurrentline(file.getCurrent()));
				chances=true;
				break;
				
				 // case:print all line
				 //type:l
				 
			case "l":
			int	n=i%2;
				if (n==1)
				file.PrintAllLinesWithLineNumber(file.getList().getHead(), 1);
				if(n==0)
					file.PrintAllLines(file.getList().getHead() );
				chances=true;
				break;
				
				 // case:toggle Whether line number are displayed when  printing all lines.
				  //type:n
				 
			case "n":
				i++;
				chances=true;
				
				break;
			
				  //case:print current line
				 // type:p
				 
			case "p":
				file.PrintCurrentline();
				chances=true;
				break;
				
				 // case:exit without saving
				 // type:q
				 
			case "q":
				System.out.println("Exit");
				exit=true;
				chances=true;
				break ;
				
				 // case:write to disk
				 // type:w
				 
			
			case "w":
					try {
						file.SaveThechanges(filename, file.getList().getHead());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("I can save it,try again ");
					}
					chances=true;
				break ;
				
				 //case:exit with save
				 // type:x
				 
				case "x":
					try {
						file.SaveThechanges(filename, file.getList().getHead());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("I can save it,try again ");
						
					}
					chances=true;
					System.out.println("Exit with save");
					exit=true;
					break ;
					
					 //case:print current line  number
					 // type:=
					
				case"=":
					file.PrintCurrentLineNumber();
					chances=true;
					break;
					
					 //case:print number of lines and characters
					 //type:#
					 
				case"#":
					file.PrintNumofLinesAndCharacters();
					chances=true;
					break;
					
					 //case:creat .ndx file and print the  page number
					 //type:c
					 
				case"c":
					
					
					myArray=s.readFileAndMakeArray(filename);
					
					
					
					
					
					
						
						
					 try {
						s.CreateByteArray(myArray,filename+".ndx");
					} catch (IOException e) {
						
						System.out.println("I can read it");
					}
					
					
					 chances=true;
				break;
                  
                   // case:For Print index(word,line)
                   // type:v
                   
				case"v":
					
					s.ReadFromDisk(myArray,filename+".ndx");
					chances=true;
				break;
				 
                  //case:For serial search
                 // type:s
                 
				case"s":
					String search=r.readString("please type the word that you want to search:\n");
					se.serialSearch(search);
					chances=true;
					break;
					
	                // case:For binary  search
	               // type:b
	                 
			    
				case"b":
					int d=buffersize/(maxwordsize+4);
					
					String search1=r.readString("please type the word that you want to search:\n");
					
					
					int last = 0;
					
						last = ((myArray.size()/d)+(myArray.size()%d)-1);
						
						
						try {
						se.binarySearch(last, 0, search1);
						} catch (IOException e) {
							System.out.println("I can't read it");
							
						}
						chances=true;
					break;
				
					}
				
				if(chances==false) {
					System.out.println("Bad Command");
					
				}
				}
			
			}

	}



