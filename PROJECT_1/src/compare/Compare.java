package compare;

import java.util.Comparator;

import Sorting.ArrayLine;
/**
 *  it is a class that  implement an Comparator an it has a method that compares two ArrayLine.
 * @author katerina
 *
 */
public class Compare implements Comparator<ArrayLine>{
	 /**this method compares tow String
	 * if String1==String2 then return 0
	 * if String1>String2  then return 1
	 * if String1<String2  then return -1
	 **/
	@Override
	public int compare(ArrayLine o1, ArrayLine o2) {
		return o1.getWord().compareTo(o2.getWord());
		
	}

}
