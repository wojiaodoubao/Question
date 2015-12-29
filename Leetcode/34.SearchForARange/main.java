import java.util.*;
/**
 * 2015-12-31
 */
public class main{
    public static int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length==0){
        	int[] result = {-1,-1};
        	return result;
        }
        return search(nums,0,nums.length-1,target);
    }
    private static int[] search(int[] nums,int start,int end,int target){
    	int[] result = {-1,-1};
    	if(start==end){
    		if(nums[start] == target){
    			result[0] = start;
    			result[1] = start;
    			return result;
    		}
    		else
    			return result;
    	}
    	if(nums[(start+end)/2]>target)
    		return search(nums,start,(start+end)/2,target);
    	else if(nums[(start+end)/2]<target)
    		return search(nums,(start+end)/2+1,end,target);
    	else{//nums[(start+end)/2]==target
    		int[] left = search(nums,start,(start+end)/2,target);
    		int[] right = search(nums,(start+end)/2+1,end,target);
    		if(left[0]!=-1)
    			result[0]=left[0];
    		else
    			result[0]=(start+end)/2;
    		if(right[1]!=-1)
    			result[1]=right[1];
    		else
    			result[1]=(start+end)/2;
    		return result;
    	}
    }
	public static void main(String args[]){
		int[] nums = {5,7,7,8,8,10};
		int target = Integer.parseInt(args[0]);
		for(int i:searchRange(nums,target))
			System.out.print(i);
	}
}