package MethodsBinarysearchTree;
/**
 * interface that contain 4 method :  1)insertrec
 *                                    2) search
 *                                    3)range
 *                                    4)inorder
 * @author USER
 *
 */
public interface BinarySearchtreemethods {
	/**
	 * the method that take an integer  and insert it
	 * @param key this integer
	 */
	void insertrec(int key);
	/**
	 * take an integer and search it 
	 * @param key this integer
	 */
	void search(int key);
	/**
	 * take a range of integer between k1 and k2 and search this range.
	 * @param k1 the minimum
	 * @param k2 the maximun
	 */
	void range(int k1,int k2);
	/**
	 * put in order  the elements
	 */
	void inorder();
	

}
