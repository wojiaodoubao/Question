import java.util.*;
/**
 * 2016-3-13
 */
public class Main{
    public boolean canJump(int[] nums) {
        if(nums==null || nums.length<=0)
        	return false;
        int left = 0;
        int right = left+nums[left];
        while(right<nums.length-1){//注意边界条件,right>=nums.length-1则能走到
        	int newright = 0;
        	for(int i=left;i<=right;i++){
        		if(nums[i]+i>newright)
        			newright = nums[i]+i;
        	}
        	if(newright==0)
        		return false;
        	left = right+1;
        	right = newright;
        }
        return true;
    }	
	public static void main(String args[]){
		Main main = new Main();
		//int[] nums = {2,3,1,1,4};
		//int[] nums = {3,2,1,0,4};
		int[] nums={0};
		System.out.println(main.canJump(nums));
	}
}