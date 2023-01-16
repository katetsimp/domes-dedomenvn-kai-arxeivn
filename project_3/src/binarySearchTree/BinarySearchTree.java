package binarySearchTree;

;

public class BinarySearchTree  {
	 private BNode root;
	 private int counterInsert;
	 private  int counterSearch;
	 private int counterdelete;
	 private int countermin;
	
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
	public BNode getRoot() {
		return root;
	}
/**
* make chances in the root of the tree
* @param root
*/
	public void setRoot(BNode root) {
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
	private BNode insertRec(BNode root, int key) { 
		  
       /* If the tree is empty, return a new node */
		counterInsert++;
       if (root == null) { 
       	counterInsert++;
           root = new BNode(key); 
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
	private BNode search(BNode root, int key) 
	{
		counterSearch++;
		if( root==null ) {
			
			return null;
		}
	    // Base Cases: root is null or key is present at root 
		counterSearch++;
	    if ( root.getKey()==key) {
	   // System.out.println("I find the element:"+root.getKey());
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
	 * this method call the insertrec that is private
	 */
	public int insertrec(int key) {
		counterInsert=0;
		numberofinsert++;
		root=insertRec(root, key);
		return counterInsert;
		
		
	}
	/**
	 * this method call the search that is private
	 */

	public int search(int key) {
		counterSearch=0;
		search(root, key);
		return counterSearch;
		
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
	

public int deleteKey(int key)
    { counterdelete=0;
     countermin=0;
        root = deleteRec(root, key);
		return counterdelete; 
    } 
  
    /* A recursive function to insert a new key in BST */
	private BNode deleteRec(BNode root, int key)
    { 
        /* Base Case: If the tree is empty */
		counterdelete++;
        if (root == null)  return root; 
  
        /* Otherwise, recur down the tree */
        counterdelete++;
        if (key < root.getKey() ) {
        	counterdelete++;
            root.setLeft(deleteRec(root.getLeft(), key));
        }
        counterdelete++;
       if (key > root.getKey()) {
        	counterdelete++;
            root.setLeft(deleteRec(root.getRight(), key));
        }
        
        // if key is same as root's key, then This is the node 
        // to be deleted 
        else
        { 
            // node with only one child or no child
        	counterdelete++;
            if (root.getLeft() == null) 
                return root.getRight();
            if (root.getRight() == null) 
                return root.getLeft(); 
            counterdelete++;
            // node with two children: Get the inorder successor (smallest 
            // in the right subtree) 
            counterdelete++;
            root.setKey(minValue(root.getRight()));
            counterdelete+=countermin;
            // Delete the inorder successor 
            counterdelete++;
            root.setRight( deleteRec(root.getRight(), root.getKey())) ;
            
        } 
  
        return root; 
    }
	

	int minValue(BNode root) 
    { countermin++;
        int minv = root.getKey(); 
        countermin++;
        while (root.getLeft()!= null) 
        { 
            minv = root.getLeft().getKey();
            root = root.getLeft() ;
            countermin+=2;
        } 
        return minv; 
    }
	public int getCounterdelete() {
		return counterdelete;
	}
	public void setCounterdelete(int counterdelete) {
		this.counterdelete = counterdelete;
	} 
	

}

