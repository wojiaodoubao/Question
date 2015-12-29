import java.util.*;
/**
 * 2015-12-31
 * 记得分治的时候start-(start+end)/2;(start+end)/2+1-end
 * '有断'里面虽然左右都找了，但是有一个会因为区间检查不对而返回-1,所以不是O(n)
 */
public class main{
	public static int search(int[] nums, int target) {
		if(nums==null || nums.length==0)
			return -1;
        return searchArray(nums,0,nums.length-1,target);
    }
    //no duplicate
    private static int searchArray(int[] nums,int start,int end,int target){
    	if(start==end){
    		if(nums[start]==target)
    			return start;
    		else
    			return -1;
    	}
    	if(nums[start]<nums[end]){//单调递增
            if(target<nums[start] || target>nums[end])
                return -1;
            if(target>nums[(start+end)/2])
                return searchArray(nums,(start+end)/2+1,end,target);
            else if(target<nums[(start+end)/2])
                return searchArray(nums,start,(start+end)/2,target);
            else//target==(start+end)/2
                return (start+end)/2;
    	}
    	else if(nums[start]>nums[end]){//有断
            if(target>nums[end] && target<nums[start])
                return -1;
            int result_l = searchArray(nums,start,(start+end)/2,target);
            int result_r = searchArray(nums,(start+end)/2+1,end,target);
            return result_l!=-1?result_l:result_r;
    	}
    	else//it's impossible because 'no duplicate'
    		return -1;
    }
	public static void main(String args[]){
        int target = Integer.parseInt(args[0]);
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums,target));
	}
}