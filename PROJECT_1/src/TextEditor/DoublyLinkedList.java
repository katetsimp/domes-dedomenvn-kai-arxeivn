package TextEditor;

public class DoublyLinkedList {
	
		private Node head;
		
		

		public DoublyLinkedList() {
			super();
		}

		public Node getHead() {
			return head;
		}

		public void setHead(Node head) {
			this.head = head;
		}
		/**
		 * 
		 * @param last
		 * @param data
		 * @return Node
		 * Add  after the last node  a new node that contains a string.
		 */
		public Node Addlast( Node last,String data) {
			
			    /*  allocate node  
			     *  put in the data */
			
			    Node new_node = new Node(data); 
			    
			     
			    
			    /*  This new node is going to be the last node, so 
			     * make next of it as NULL*/
			    new_node.setNext(null); 
			    
			    /*  If the Linked List is empty, then make the new 
			     * node as head */
			    if (head == null) { 
			    	
			        new_node.setNext(null); 
			        
			        head = new_node; 
			        
			        return head; 
			    } 
			  
			  
			    
			    	
			      
			        
			    
			    /*  Change the next of last node */
			    
			      last.setNext(new_node); 
			     
			    /* Make last node as previous of new node */
			    new_node.setPrev(last);
			      last=new_node;
			      return last;
			   
			    } 
			
			/**
			 * add a new node after a specific Node in the list.
			 * @param current is that specific node.
			 * @param new_data is the content of the new node.
			 * @return   return the position of the new node;
			 * 
			 */
		public Node AddAfter(Node current ,String new_data ) {
			Node new_node=new Node(new_data);
			 /*  If the Linked List is empty, then make the new 
		     * node as head */
			if (head==null) {
				

		       new_node.setNext(null); 
		        
		        head = new_node; 
				return head;
				}
			 /*  If the current node is null return null*/
			if(current==null) {
				
				
				return null;
				
		}
			
			/* if  it is between  two node */
			
			new_node.setNext(current.getNext()); // the next of new node become the next of current node. 
			current.setNext(new_node); // the new node enter after the new node.
			new_node.setPrev(current); //the previous  of the new node become equals of new node.
			current=new_node;
			/* if the current node is the latest.*/
			if (new_node.getNext() != null) 
			new_node.getNext().setPrev(new_node);
			return current; // return the current(position).
			
		}
		/**
		 * add a new node before a specific Node in the list.
		 * @param current is that specific node.
		 * @param new_data is the content of the new node.
		 * @return   return the position of the new node;
		 * 
		 */
		
		
			public Node Addbefore(Node current ,String new_data ) {
				Node new_node=new Node(new_data);
				/*if current is in the first element in the list.*/
				if(current.getPrev()==null) {
					new_node.setNext(head); 
					new_node.setPrev(null);
					/* If the Linked List is empty, then make the new 
				     * node as head */
					if(head!=null) {
						head.setPrev(new_node);
					}
					head=new_node;
					
					
					return head;
					
			}
				
				
				/* if  it is between  two node */
				new_node.setPrev(current.getPrev()); // the  previous  of new node become the previous  of current node. 
				current.setPrev(new_node);// the new node enter before the new node.
				new_node.setNext(current);
				new_node.getPrev().setNext(new_node);
				current=current.getPrev();
				return current;
			
			
				
				
		}
			/**
			 * delete a Node from the list
			 * @param del
			 * @return return the position of the deleted Node
			 */
			 /*  If the Linked List is empty, then make the new 
		     * node as head */
					public Node deleteNode(Node del) {
						if(head==null) {
							System.out.println("the list is empty");
						}
						/*if head is in the only element in the list.*/
						else if(head.getNext()==null) {
							this.head=null;
							del=null;
						}
						/*if it delete the first element of the list*/
							else if(del==head) {
								
								head=head.getNext();
								head.setPrev(null);
								del=head;
								
						/*if it delete the last element of the list*/
							}else if(del.getNext()==null) {
								del=del.getPrev();
								del.setNext(null);
						/* if  the node that I want to delete is between  two node */
						} else {
							Node p=del.getPrev();//p Node become equals to the previous of the node that i want to delete.
							Node n=del.getNext();//p Node become equals to the next of the node that i want to delete.
							del=n;
							p.setNext(n);//the next of p became the n
							n.setPrev(p);// the previous of n became the p
							del=del.getPrev(); //del became equal of the previous of it.
							
							
						}
						return del;
						
							
						}
					}
					


