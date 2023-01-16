package TextEditor;
/**
 * make the Node for use it in double linked list.
 * @author katerina tsimpirdoni.
 * 
 *
 */
public class Node {
	//make the variables that I need.
		private String line; //line is the content of node.
		private Node next; // is the next node of the current
		 private Node prev; // is the previous node of the current
		public Node(String line) {
			
			this.line = line;
			
		}
		/**
		 *  it get the content of node.
		 * @return line 
		 */
		public String getLine() { 
			return line; // 
		}
		/**
		 * it change the  content of the method
		 * @param line it is the content 
		 */
		public void setLine(String line) {
			this.line = line;
		}
		/** it get the next  of the node.
		 *  
		 * @return next 
		 */
		public Node getNext() {
			return next; 
		}
		/**
		 * it  change the next  of the node.
		 * @param the next of the node.
		 */
		public void setNext(Node next) {
			this.next = next;
		}
		/** it get the previous  of the node.
		 *  
		 * @return previous 
		 */
		public Node getPrev() {
			return prev; // get the previous node.
		}
		/**
		 * it change the previous  of the node.
		 * @param the previous of the node.
		 */
		public void setPrev(Node prev) {
			this.prev = prev;
		}
		/**
		 *it print the content  of the node
		 */
		public void print() { 
			System.out.println(line); //print the content of node.
		}
		
		

	}



