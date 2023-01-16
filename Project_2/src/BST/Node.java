package BST;
/**
 * 
 * @author USER
 * this class has as member variable a String which is the data and two others node that  it is the left and the right of it.
 *
 */
public class Node {
	private int key; 
   private Node left, right;
   /**
	 * class constructor
	 */
	public Node(int key) {
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
	public Node getLeft() {
		return left;
	}
	/**
	 * it  change the left of the node.
	 * @param the left of the node.
	 */
	public void setLeft(Node left) {
		this.left = left;
	}
	/** it get the right  of the node.
	 *  
	 * @return right
	 */
	public Node getRight() {
		return right;
	}
	/**
	 * it  change the right of the node.
	 * @param the right of the node.
	 */
	public void setRight(Node right) {
		this.right = right;
	}
	
     

}
