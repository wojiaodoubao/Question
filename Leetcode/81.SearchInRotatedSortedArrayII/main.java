import java.util.*;
/**
 * 2015-12-31
 * Would this affect the run-time complexity? How and why?
 */
public class main{
	public static boolean search(int[] nums, int target) {
		if(nums==null || nums.length==0)
			return false;
        return searchArray(nums,0,nums.length-1,target);
    }
    //with duplicate
    private static boolean searchArray(int[] nums,int start,int end,int target){
    	if(start==end){
    		if(nums[start]==target)
    			return true;
    		else
    			return false;
    	}
    	if(nums[start]<nums[end]){//单调递增
            if(target<nums[start] || target>nums[end])
                return false;
            if(target>nums[(start+end)/2])
                return searchArray(nums,(start+end)/2+1,end,target);
            else if(target<nums[(start+end)/2])
                return searchArray(nums,start,(start+end)/2,target);
            else//target==(start+end)/2
                return true;
    	}
    	else {
            boolean result_l = searchArray(nums,start,(start+end)/2,target);
            boolean result_r = searchArray(nums,(start+end)/2+1,end,target);
            return result_l?result_l:result_r;
    	}
    }
	public static void main(String args[]){
        int target = Integer.parseInt(args[0]);
        int[] nums = {1,2,1};
        System.out.println(search(nums,target));
	}
}