package compare;

import java.util.Comparator;
/**
 * it is a class that  implement an Comparator an it has a method that compares two integers.
 * @author katerian
 *
 */

public class CompareInteger  implements Comparator<Integer>{

		
		/**
		 * this method compares tow integer
		 * if arg0==arg1 then a=0;
		 * if arg0>arg1 then a=1
		 * if arg0<arg1 then a=-1
		 * return a
		 */

		@Override
		public int compare(Integer arg0, Integer arg1) {
			int a = 0;
			if(arg0==arg1) {
				
			a=0;
			
			}
			
			if(arg0>arg1) {
				
			
			a=1;
			}
			if(arg0<arg1) {
				a=-1;
			}
			return a;
		
	}
	


}