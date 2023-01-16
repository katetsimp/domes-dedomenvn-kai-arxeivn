package binarySearchTree;



public class BNode {
	private int key; 
	   private  BNode left, right;
	   /**
		 * class constructor
		 */
		public BNode(int key) {
			super();
			this.key = key;
			this.left = null;
			this.right = null;
		}
		/**
		 *  it get the content of node.
		 * @return key 
		 */
		public int getKey() {
			return key;
		}
		/**
		 *  * it change the  content of the method
		 * @param key it is the content 
		 */
		public void setKey(int key) {
			this.key = key;
		}
		/** it get the left  of the node.
		 *  
		 * @return left
		 */
		public BNode getLeft() {
			return left;
		}
		/**
		 * it  change the left of the node.
		 * @param the left of the node.
		 */
		public void setLeft(BNode left) {
			this.left = left;
		}
		/** it get the right  of the node.
		 *  
		 * @return right
		 */
		public BNode getRight() {
			return right;
		}
		/**
		 * it  change the right of the node.
		 * @param the right of the node.
		 */
		public void setRight(BNode right) {
			this.right = right;
		}
		
	     

	}
