package BST;

import MethodsBinarysearchTree.BinarySearchtreemethods;
/**
 * 
 * @author katerina tsimpirdoni
 * That class  has method for insert ,search ,print the data of the nodes in  order and search a range of  prices.
 * the member variable of that class is the root of the tree and some counter that use for count the comparison that has the  methods.
 *
 */

public class BinarySearchTree  implements BinarySearchtreemethods {
	 private Node root;
	 private int counterInsert;
	 private  int counterSearch;
	 private int  counterRange;
	private int numberofinsert;
	/** 
	 * class constructor 
	 */
	public BinarySearchTree() {  
        setRoot(null);  
        
        
    }
/** 
 * get the root of the tree
 * @return root
 */
	public Node getRoot() {
		return root;
	}
/**
 * make chances in the root of the tree
 * @param root
 */
	public void setRoot(Node root) {
		this.root = root;
	}
	/** 
	 * get the counter that counts the comparison of the method insertRec.
	 * @return counterInsert
	 */
	
	public int getCounter() {
		return counterInsert;
	}
	/**
	 * it make changes in the counter 
	 * @param counter
	 */

	public void setCounter(int counter) {
		this.counterInsert = counter;
	}
	/** 
	 * get the counter that counts the element of the tree.
	 * @return numberofinsert
	 */
	public int getNumberofinsert() {
		return numberofinsert;
	}
	/**
	 * it make changes in the counter 
	 * @param numberofinsert
	 */

	public void setNumberofinsert(int numberofinsert) {
		this.numberofinsert = numberofinsert;
	}
/**
 * this method insert a new node. 
 * Compares the new number that you want to insert with the root.
 * if the number is less than root make the left of the root as root and recall the method with the new root.
 * if the number is bigger than root make the right of the root as root and recall the method with the new root.
 * if the root is null make the new node of the tree and call the method with the new root. 
 * @param root  is the fist element in the tree
 * @param key is the data of the new node 
 * @return  root 
 */
	private Node insertRec(Node root, int key) { 
		  
        /* If the tree is empty, return a new node */
		counterInsert++;
        if (root == null) { 
        	counterInsert++;
            root = new Node(key); 
            return root; 
        } 
  
        /* Otherwise, recur down the tree */
        counterInsert++;
        if (key < root.getKey()) {
        	counterInsert++;
            root.setLeft( insertRec(root.getLeft(), key)); 
        }
        counterInsert++;
        if (key > root.getKey()) {
        	counterInsert++;
            root.setRight( insertRec(root.getRight(), key)); 
        
        }
        /* return the (unchanged) node pointer */
       
        return root; 
    }
	/**
	 * This method search a number that user inserts.
	 * if the data of the root of the tree is equal to key then print the data of the node .
	 * if the number is less than root make the left of the root as root and return this node.
     * if the number is bigger than root make the right of the root as root and return this node.
	 * @param root
	 * @param key
	 * @return 
	 */
	private Node search(Node root, int key) 
	{
		counterSearch++;
		if( root==null ) {
			return null;
		}
	    // Base Cases: root is null or key is present at root 
		counterSearch++;
	    if ( root.getKey()==key) {
	    	System.out.println("I find the element:"+root.getKey());
	        return root; 
	    }
	  
	    // val is greater than root's key 
	    counterSearch++;
	    if (root.getKey() > key) 
	        return search(root.getLeft(), key); 
	  
	    // val is less than root's key 
	    return search(root.getRight(), key); 
	} 
	/**
	 * this method print all the nodes of the tree in ascending order
	 * if root is not null then it cross the left side of the tree and print all the values ,then print the root,and after cross the right side of tree and print all the values.
	 * the crossing became with the use of the inorderRec and as variable the left or right of the root.
	 * @param root
	 */
	
	
	 private void inorderRec(Node root) { 
	        if (root != null) { 
	            inorderRec(root.getLeft()); 
	            System.out.println(root.getKey()); 
	            inorderRec(root.getRight()); 
	        } 
	    } 
	 /**
	  * this method  print all the values of the nodes that is bigger than k1 and less than k2.
	  * if the value of the root is bigger than k1 then call the Range again with variable the left of the root.
	  * if the value of the root is bigger than k1 and less than k2  then print the values.
	  * if the value of the root is less than k2 then call the Range again with variable the right of the root.
	  * @param root
	  * @param k1
	  * @param k2
	  */
	private void  Range (Node root,int k1,int k2 ) {
		 
         counterRange++;
		if(root==null)
			
			return ;
		counterRange++;
		if(root.getKey()>k1)
			Range(root.getLeft(),k1,k2);
		counterRange++;
		if(root.getKey()>k1 && root.getKey()<k2)
			//System.out.println(root.getKey());
		counterRange++;
		
		if(root.getKey()<k2) {
			Range(root.getRight(),k1,k2);
			
		}
		
		return;
		
		
	}
	/**
	 * this method call the insertrec that is private
	 */
	@Override
	public void insertrec(int key) {
		
		numberofinsert++;
		root=insertRec(root, key);
		
		
	}
	/**
	 * this method call the search that is private
	 */

	@Override
	public void search(int key) {
		search(root, key);
		
	}
	/**
	 * this method call the range that is private
	 */

	@Override
	public void range(int k1, int k2)  {
	
	Range(root, k1, k2);
	 
	 
	  
	}
	/**
	 * this method call the inorderRec that is private
	 */
	@Override
	public void inorder() {
		inorderRec(root);;
		
	}
	/** 
	 * get the counter that counts the comparison of the method search.
	 * @return counterSearch
	 */

	public int getCounterSearch() {
		return counterSearch;
	}
	/**
	 * it make changes in the counter 
	 * @param  counterSearch
	 */
	

	public void setCounterSearch(int counterSearch) {
		this.counterSearch = counterSearch;
	}
	/** 
	 * get the counter that counts the comparison of the method range..
	 * @return counterRange
	 */


	public int getCounterRange() {
		return counterRange;
	}
	/**
	 * it make changes in the counter 
	 * @param  counterRange
	 */

	public void setCounterRange(int counterRange) {
		this.counterRange = counterRange;
	}

	

	
	

}
