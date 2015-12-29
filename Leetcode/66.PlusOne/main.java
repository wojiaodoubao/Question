import java.util.*;
/**
 * 2016-1-1
 * 状态不好了，思路理不清了，烦
 */
public class main{
    public static int[] plusOne(int[] digits) {
    	if(digits==null || digits.length==0)
    		return null;
    	int plus = 1;
 		for(int i=digits.length-1;i>-1;i--){
 			if(plus==1 && digits[i]==9)
 				digits[i] = 0;
 			else{
 				digits[i]+=plus;
 				plus = 0;
 			}
 		}
 		if(digits[0]==0 && plus==1){
 			int[] finalresult = new int[digits.length+1];
 			finalresult[0] = 1;
 			for(int i=0;i<digits.length;i++)
 				finalresult[i+1] = digits[i];
 			return finalresult;
 		}
 		else
 			return digits;

    }
	public static void main(String args[]){
		int[] digits = {9,9,9};
		digits = plusOne(digits);
		for(int i:digits)
			System.out.print(i);
		System.out.println();
	}
}