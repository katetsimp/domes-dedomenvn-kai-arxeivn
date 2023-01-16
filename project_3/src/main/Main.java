package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import counting.Counting;
import hashing.LinearHashing;

public class Main {
	

		public static void main(String args[]) throws IOException, ClassNotFoundException {
			int  initPages = 100;
			int pageSize = 10;
			LinearHashing hash=new LinearHashing(pageSize, initPages);
		String Filename=args[0];
			
			Counting c= new Counting(Filename);
			Counting c1= new Counting(Filename);
			System.out.println("-----------------------------------------------the load factor is:"+hash.getMaxLoadFactor()+"-------------------------------------------------------------------------");
			try {
			c.insertion(hash.getMaxLoadFactor(),100,0);
			}catch(FileNotFoundException e){
				System.out.println("I can find it");
				
			}
			 System.out.println("------------------------------------------------the load factor is:"+hash.getMinLoadFactor()+"----------------------------------------------------------------------------");
			 
			 
			 try {
			
			c1.insertion(hash.getMinLoadFactor(),100,0);
			
			 }catch(FileNotFoundException e){
					System.out.println("I can find it");
					
				}
			
			
		}}