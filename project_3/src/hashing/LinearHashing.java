package hashing;
/**
 * this is the  class of the linear hashing.
 * @author USER
 *
 */

public class LinearHashing {
	
	private HashBucket[] hb;
	private float MaxLoadFactor;
	private float MinLoadFactor;
	private int insertion;
	private int Bucketsize;
	private int Nkeys;
	private int keySpace;		// total space the hash table has for keys
	private int p;				// pointer to the next bucket to be split
	private int n;				// current number of buckets
	private int j;				// the n used for the hash function
	private int minBuckets;  // minimum number of buckets this hash table can have
	private int delete;
	  private int search;
	  private int split;  
	  private int merge;
	  private int c;
	  private int c2;
	public LinearHashing( int bucketsize, int unpage) {
		delete=0;
		split=0;
		search=0;
		insertion=0;
		merge=0;
		 c=0;
		 setC2(0);
		Bucketsize = bucketsize;
		Nkeys = 0;
		MaxLoadFactor = (float) 0.8;
		MinLoadFactor = (float) 0.5;
		this.p = 0;
		this.n = unpage;
		this.j = unpage;
		this.keySpace = n*bucketsize;
		this.minBuckets =unpage ;
		if ((Bucketsize == 0) || (n == 0)) {
			  System.out.println("error: space for the table cannot be 0");
			  System.exit(1);
		}
			  hb = new HashBucket[n];
				for ( int i = 0; i < n; i++) {
				   hb[i] = new HashBucket(Bucketsize);
			}
				
		
	}
	
	public int getMerge() {
		return merge;
	}

	public void setMerge(int merge) {
		this.merge = merge;
	}

	public HashBucket[] getHb() {
		return hb;
	}
	public void setHb(HashBucket[] hb) {
		this.hb = hb;
	}
	public float getMaxLoadFactor() {
		return MaxLoadFactor;
	}
	public void setMaxLoadFactor(float maxLoadFactor) {
		MaxLoadFactor = maxLoadFactor;
	}
	public float getMinLoadFactor() {
		return MinLoadFactor;
	}
	public void setMinLoadFactor(float minLoadFactor) {
		MinLoadFactor = minLoadFactor;
	}
	public int getBucketsize() {
		return Bucketsize;
	}
	public void setBucketsize(int bucketsize) {
		Bucketsize = bucketsize;
	}
	public int getNkey() {
		return Nkeys;
	}
	public void setNkey(int nkey) {
		Nkeys = nkey;
	}
	public int getKeySpace() {
		return keySpace;
	}
	public void setKeySpace(int keySpace) {
		this.keySpace = keySpace;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public int getMinBuckets() {
		return minBuckets;
	}
	public void setMinBuckets(int minBuckets) {
		this.minBuckets = minBuckets;
	}
	/**
	 *  Returns the current load factor of the hash table.
	 * @return
	 */
	private float loadFactor() {		

		return ((float)this.Nkeys)/((float)this.keySpace);
		
	}
	/**
	 *  Returns a hash based on the key
	 * @param key
	 * @return
	 */
	private int hashFunction(int key){	

		int retval;
            c++;
		retval = key%this.j;
		 c++;
		if (retval < 0) {
			 c++;
			retval *= -1;
		}
		c++;
		if (retval >= p){
		  
		  return retval;
		}
		else {
			c++;
			 retval = key%(2*this.j);
			 c++;
			 if (retval < 0) {
				 c++;
				 retval *= -1;
			 }
			 
		         return retval;
		}
	}
	/**
	 * it create a bigger hash table with one hash bucket more.Copy the old hash table to the new one and then split the bucket that pointer shows
	 * @return split a counter 
	 */
	private int  bucketSplit() {	
		split=0;
		c2=0;
		   
		
		int i;
		HashBucket[] newHashBuckets;
       split++;
		newHashBuckets= new HashBucket[n+1];
		for (i = 0; i < this.n; i++){
			
			split++;
			split++;
		   newHashBuckets[i] = this.hb[i];
		}
		
		
		hb = newHashBuckets;
		split++;
		hb[this.n] = new HashBucket(this.Bucketsize);
		
		this.keySpace += this.Bucketsize;
		
		this.hb[this.p].splitBucket(this, 2*this.j, this.p, hb[this.n]);
		
		this.n++;
		
		if (this.n == 2*this.j) {
			split++;
		  this.j = 2*this.j;
		  split++;
		  this.p = 0;
		}
		else {
		    this.p++;
		}
		
		return split;
		
	}
	/**
	 * // Merges the last bucket that was split. it create a smaller hash table with one hash bucket less.And the merge the last split bucket with the bucket that the pointer show.
	 * @return
	 */
	private int bucketMerge() { 	
	merge=0;
	c2=0;
      
		int i;

		HashBucket[] newHashBuckets;
		merge++;
		newHashBuckets= new HashBucket[n-1];
		for (i = 0; i < this.n-1; i++) {
			merge++;
		   newHashBuckets[i] = this.hb[i];
		}
		merge++;
		if (this.p == 0) {
			merge++;
		  this.j = (this.n)/2;
		  merge++;
		  this.p = this.j-1;
		}
		else {
		  this.p--;
		}
		this.n--;
		merge++;
		this.keySpace -= this.Bucketsize;
		this.hb[this.p].mergeBucket(this, hb[this.n]);
		hb = newHashBuckets;
		return merge;
	
	}
	/**
	 * this  method  Insert a new key in the hash table and if the load factor became more than ld then split the bucket.
	 * @param key
	 * @param ld
	 * @return a counter
	 */

	public int insertKey(int key, float ld) {	
	insertion=0;
   
	c=0;
		
     
		
     this.hb[this.hashFunction(key)].insertKey(key, this);
     insertion+=c;
    
		
		insertion++;
		if (this.loadFactor() > ld){
			
		  
		insertion+=this.bucketSplit();
		
	
		  
		}
		

		
		
		return insertion;
		
	}
	/**
	 * this method delete a key from  hash table .if the load factor became less than MinLoadFactor then merge the bucket.
	 * @param key
	 * @param ld
	 * @return
	 */
	public int deleteKey(int key, float ld) {	// Delete a key.
		c=0;
		c2=0;
		delete=0;
		
		

		this.hb[this.hashFunction(key)].deleteKey(key, this);
		 
		delete+=c;
		
		delete++;
		 if ((this.loadFactor() < MinLoadFactor) && (this.n > this.minBuckets)){
			
			
			
			delete+=this.bucketMerge();
			
			
		
			  
		}
		
		
		return delete;
		
	}
	/**
	 * this method search a key in  hash table .
	 * @param key
	 * @return
	 */

	public int searchKey(int key) {	// Search for a key.
		search=0;
		c=0;
		

	this.hb[this.hashFunction(key)].searchKey(key, this);
		search+=c;
		
		return search ;
	
	}
	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	
	public int getInsertion() {
		return insertion;
	}
	public void setInsertion(int insertion) {
		this.insertion = insertion;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public int getSearch() {
		return search;
	}
	public void setSearch(int search) {
		this.search = search;
	}
	public int getSplit() {
		return split;
	}
	public void setSplit(int split) {
		this.split = split;
	}

	public int getC2() {
		return c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}



}
