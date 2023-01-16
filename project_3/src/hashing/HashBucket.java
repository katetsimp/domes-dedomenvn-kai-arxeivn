package hashing;
/**
 * this class makes the buckets of hash table and the overflows. this class has member variables  a integer that  is the number of the elements that it is in the bucket,
 * an array of integers and a Hashbucket that it is the overflow.
 * @author USER
 *
 */

public class HashBucket {
	private int Nkey;
	private int[]keys;
	private HashBucket overflows;

 
  /**
   * class constructor
   * @param bucketSize
   */
	public HashBucket(int bucketSize) {		// Constructor: initialize variables
        
		Nkey = 0;
		keys = new int[bucketSize];
		overflows = null;
	}
	public HashBucket() {	
		
	}
	/**
	 * this method insert a key to the hashbucket.if the key it is already in the bucket the method terminate if the bucket  is not full  insert the new key
	 * else if it is full,look for an overflow if it exists the key writes in this overflow ,if it is not exist create a new overflow and write the new key.
	 * 
	 * @param key
	 * @param lh
	 */
	public void insertKey(int key, LinearHashing lh) { // inserts a key to the node


		int i;
		
		int bucketSize = lh.getBucketsize();
		
		int keysNum = lh.getNkey();
		int keySpace = lh.getKeySpace();
		lh.setInsertion(lh.getInsertion()+3);
		for (i = 0; (i < this.Nkey) && (i < bucketSize); i++){
			lh.setInsertion(lh.getInsertion()+2);
		   if (this.keys[i] == key){
			  //key already here. Ignore the new one
			
		     return  ;
		   }
		}
		lh.setInsertion(lh.getInsertion()+1);
		if (i < bucketSize){	
			// bucket not full write the new key
			lh.setInsertion(lh.getInsertion()+1);
		  keys[i] = key;
		  this.Nkey++;
		  keysNum++;
		 
		  lh.setInsertion(lh.getInsertion()+1);
		  lh.setNkey(keysNum); 
		  
		  
		}
		else {
		    
			lh.setInsertion(lh.getInsertion()+1);
		    if (this.overflows != null){	
		    	// pass key to the overflow
		      this.overflows.insertKey(key, lh);
		      
		    }
		    else {	
		    	lh.setInsertion(lh.getInsertion()+1); // create a new overflow and write the new key
			this.overflows = new HashBucket(bucketSize);
			lh.setInsertion(lh.getInsertion()+1);
			keySpace += bucketSize;
			lh.setInsertion(lh.getInsertion()+1);
		        lh.setKeySpace(keySpace);		// update linear hashing class.
			this.overflows.insertKey(key, lh);
		    }
		}
		
		
		
	}
	/**
	 * in this method firstly search the key that we want to delete in the hashbucket if it  exists in the hashbucket  then delete it if it is not exist in the bucket ,search
	 *  look at the overflow for the key to be deleted if one exists.if the overflow it is empty then free it. 
	
	 * @param key
	 * @param lh
	 */
	public void deleteKey(int key, LinearHashing lh) { 
  
		int i;
		int bucketSize = lh.getBucketsize();
		int keysNum = lh.getNkey();
		int keySpace = lh.getKeySpace();
		lh.setDelete(lh.getDelete()+3);
		
		for (i = 0; (i < this.Nkey) && (i < bucketSize); i++) {
			
			
			lh.setDelete(lh.getDelete()+2);
		   if (this.keys[i] == key) {
			   
			  
			  
			   lh.setDelete(lh.getDelete()+1);
		     if (this.overflows == null) {		// no overflow
		    	 lh.setDelete(lh.getDelete()+1);
			 this.keys[i] = this.keys[this.Nkey-1];
			 
			 this.Nkey--;
			 keysNum--;
			 lh.setDelete(lh.getDelete()+1);
			 lh.setNkey(keysNum);	
			 
			  // update linear hashing class.
		     }
		     else 
		    	{	// bucket has an overflow so remove a key from there and bring it here
		    	 lh.setDelete(lh.getDelete()+1);
			 this.keys[i] = this.overflows.removeLastKey(lh);
			 lh.setDelete(lh.getDelete()+lh.getC2());
			 
			 keysNum--;
			 lh.setDelete(lh.getDelete()+1);
			 lh.setNkey(keysNum);// update linear hashing class.
			 lh.setDelete(lh.getDelete()+1);
			 
			 if (this.overflows.Nkey == 0) { // overflow empty free it
				 lh.setDelete(lh.getDelete()+1);
				 
			   this.overflows = null;
			   lh.setDelete(lh.getDelete()+1);
			   keySpace -= bucketSize;
			   lh.setDelete(lh.getDelete()+1);
		         lh.setKeySpace(keySpace);
		        
		         // update linear hashing class.
			 }
		     }
		   
		     return;
		   }
		}
		lh.setDelete(lh.getDelete()+1);
		if (this.overflows != null) {			// look at the overflow for the key to be deleted if one exists
		  this.overflows.deleteKey(key, lh);
		  
		  lh.setDelete(lh.getDelete()+1);
		  if (this.overflows.Nkey == 0) {
			                  // overflow empty free it
			  lh.setDelete(lh.getDelete()+1);
		    this.overflows = null;
		    lh.setDelete(lh.getDelete()+1);
		    keySpace -= bucketSize;
		    lh.setDelete(lh.getDelete()+1);
		    lh.setKeySpace(keySpace);				// update linear hashing class.
		  }
	      }
		
		return;
	}
	/**
	 * this method remove bucket last key if it is not exist overflow return remove the last key of bucket if exist remove overflows last key.if the overflow is empty then free it.
	 * @param lh
	 * @return  the new last key
	 */
	 public int removeLastKey(LinearHashing lh) {	
 
		int retval;
		int bucketSize = lh.getBucketsize();
		int keySpace = lh.getKeySpace();
		lh.setC2(lh.getC2()+4);
        
		if (this.overflows == null) {
			lh.setC2(lh.getC2()+1);
		  if (this.Nkey!= 0){
		    this.Nkey--;
		    return this.keys[this.Nkey];
		  }
		 
		  return 0;
		}
		else {
			lh.setC2(lh.getC2()+1);
		  retval = this.overflows.removeLastKey(lh);
		  lh.setC2(lh.getC2()+1);
		  if (this.overflows.Nkey == 0) {	// overflow empty free it
			  lh.setC2(lh.getC2()+1);
		    this.overflows = null;
		    lh.setC2(lh.getC2()+1);
		    keySpace -= bucketSize;
		    lh.setC2(lh.getC2()+1);
		    lh.setKeySpace(keySpace);			// update linear hashing class.
		  }
		  
		  return retval;
		}
	}
	 /**
	  * in this method firstly search the key in the bucket.if it is not exists there look at the overflow for the key.
	  * @param key
	  * @param lh
	  * @return boolean return true if it exists in the bucket or he overflow
	  */
	 public boolean searchKey(int key, LinearHashing lh) {

			int i;
			lh.setSearch(lh.getSearch()+1);
			int bucketSize = lh.getBucketsize();

			for (i = 0; (i < this.Nkey) && (i < bucketSize); i++) {
				lh.setSearch(lh.getSearch()+2);
			   if (this.keys[i] == key) {	//key found
			     return true;
			   }
			}
			lh.setSearch(lh.getSearch()+1);
			if (this.overflows != null) {				//look at the overflow for the key if one exists
			  return this.overflows.searchKey(key,lh);
		      }
		      else {
			  return false;
		      }
		} 
	 /**
	  * this method splits the current bucket.if the mod of the key with the 2*the place of the hashtable it is not the equal with the pointer that shows the bucket 
	  * that may be split it, then the key split it and goes to new bucket and split the overflow too if one exists.If an overflow it is empty then free it.
	  * @param lh
	  * @param n place of the bucket that hash function use
	  * @param bucketPos pointer of the bucket
	  * @param newBucket the new bucket that create
	  */
		public void splitBucket(LinearHashing lh, int n, int bucketPos, HashBucket newBucket) {	
     
			int i;
			int bucketSize = lh.getBucketsize();
			int keySpace = lh.getKeySpace();
			int keysNum = lh.getNkey();
			lh.setSplit(lh.getSplit()+3);
			for (i = 0; (i < this. Nkey) && (i < bucketSize);) {
				lh.setSplit(lh.getSplit()+2);
			   if ((this.keys[i]%n) != bucketPos){	//key goes to new bucket
			    newBucket.insertKey(this.keys[i], lh);
			   
			     this.Nkey--;
			     lh.setSplit(lh.getSplit()+1);
			     keysNum = lh.getNkey();
			     keysNum--;
			     lh.setSplit(lh.getSplit()+1);
			     lh.setNkey(keysNum);		// update linear hashing class.
			     //System.out.println("HashBucket.splitBucket.insertKey: KeysNum = " + keysNum );
			     lh.setSplit(lh.getSplit()+1);
			     this.keys[i] = this.keys[this.Nkey];
			   }
			   else {				// key stays here
			     i++;
			   }
			}
			lh.setSplit(lh.getSplit()+1);
			if (this.overflows != null) {	// split the overflow too if one exists
			  this.overflows.splitBucket(lh, n, bucketPos, newBucket);
			}
			while (this.Nkey != bucketSize) {
				lh.setSplit(lh.getSplit()+2);
			     if (this.overflows == null) {
			    	// System.out.println( "split:"+lh.getSplit());
			    	
				 return;
			     }
			     lh.setSplit(lh.getSplit()+1);
			     if (this.overflows.Nkey != 0) {
			    	 lh.setSplit(lh.getSplit()+1);
			       this.keys[this.Nkey] = this.overflows.removeLastKey(lh);
			    
			       lh.setSplit(lh.getSplit()+lh.getC2());
			       
			       if (this.overflows.Nkey == 0) {	// overflow empty free it
			    	   lh.setSplit(lh.getSplit()+1);
				 this.overflows = null;
				 lh.setSplit(lh.getSplit()+1);
				 keySpace -= bucketSize;
				 lh.setSplit(lh.getSplit()+1);
				 lh.setKeySpace(keySpace);      // update linear hashing class.
			       }
			       this.Nkey++;
			     }
			     else {	
			    	 lh.setSplit(lh.getSplit()+1);;// overflow empty free it
				 this.overflows = null;
				 lh.setSplit(lh.getSplit()+1);
				 keySpace -= bucketSize;
				 lh.setSplit(lh.getSplit()+1);
			         lh.setKeySpace(keySpace);	// update linear hashing class.
			     }
		 	}
			
			return;
		}
/**
 * this method merges the current bucket. if the oldBucket has element inside  then insert the key in the bucket that pointer show.
 * @param lh
 * @param oldBucket
 */
		public void mergeBucket(LinearHashing lh, HashBucket oldBucket) {	
       
       
		
			

			while (oldBucket.Nkey != 0) {
				 lh.setMerge(lh.getMerge()+1);
			  this.insertKey(oldBucket.removeLastKey(lh), lh);
			  this.Nkey--;
			    
			  lh.setNkey(lh.getNkey()-1);
			    
			     
			  lh.setMerge(lh.getMerge()+lh.getInsertion()+lh.getC2());
			  
			  
			     
			}
			return ;
			
		}

	
		
		
		
	
		
	
	
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
