import java.util.*;
/**
 * 2016-1-1
 */
public class main{
	public static int removeDuplicates(int[] nums) {
        if(nums==null || nums.length==0)
        	return 0;
        int location = 0;
        for(int i=1;i<nums.length;i++){
        	if(nums[i] == nums[location])
        		continue;
        	nums[++location] = nums[i];
        }
        return location+1;
    }
    public static void main(String args[]){
    	int[] nums = {1,1,2,2,2,3,4,5,5,5,6,6,6,7,7};
    	System.out.println(removeDuplicates(nums));
    	for(int i:nums)
    		System.out.print(i);
    	System.out.println();
    }
}