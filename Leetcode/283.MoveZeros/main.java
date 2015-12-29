import java.util.*;
/**
 * 2016-1-2
 */
public class main{
    public static void moveZeroes(int[] nums) {
    	if(nums==null || nums.length==0)
    		return;
        int zero = 0;
        int next_zero;
		while(zero<nums.length && nums[zero]!=0)
			zero++;        
		for(int i=zero+1;i<nums.length;i++){
			if(nums[i]!=0){
				nums[zero] = nums[i];
				nums[i] = 0;
				while(zero<nums.length && nums[zero]!=0)
					zero++;
			}
		}
    }
	public static void main(String args[]){
		int[] nums = {0,1,0,3,12};
		moveZeroes(nums);
		for(int i:nums)
			System.out.print(i+",");
		System.out.println();
	}
}