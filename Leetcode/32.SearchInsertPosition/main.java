import java.util.*;
/**
 * 2015-12-31
 */
public class main{
	public static int searchInsert(int[] nums, int target) {
        if(nums==null || nums.length==0)
        	return 0;
        return search(nums,0,nums.length-1,target);
    }
    public static int search(int[] nums,int start,int end,int target){
    	if(start==end){
    		if(nums[start]==target)
    			return start;
    		else if(nums[start]>target)
    			return start;
    		else if(nums[start]<target)
    			return start+1;
    		else//impossible
    			return 0;
    	}
    	if(nums[(start+end)/2]<target)
    		//System.out.println((start+end)/2+" "+end);
    		return search(nums,(start+end)/2+1,end,target);
    	else if(nums[(start+end)/2]>target)
    		return search(nums,start,(start+end)/2,target);
    	else if(nums[(start+end)/2]==target)
    		return (start+end)/2;
    	else//impossible
    		return 0;
    }
    public static void main(String args[]){
    	int[] nums = {1,3,5,6};
    	int target = Integer.parseInt(args[0]);
    	System.out.println(searchInsert(nums,target));
    }
}