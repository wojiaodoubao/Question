import java.util.*;
/**
 * 2016-1-1
 */
public class main{
    public static int[] productExceptSelf(int[] nums) {
        if(nums==null || nums.length<2)
        	return null;
        int zero_index = -1;//-1 no zero ; -2 more than 1 zero; other index of the only zero
        int sum = 1;
        for(int i=0;i<nums.length;i++){
        	if(nums[i]==0 && zero_index==-1){
        		zero_index=i;
        	}
        	else if(nums[i]==0 && zero_index!=-1){
        		zero_index=-2;
        		break;
        	}
        	else
        		sum *= nums[i];
        }
        int[] result = new int[nums.length];
        for(int i=0;i<result.length;i++){
        	if(zero_index == -2)
        		result[i] = 0;
        	else if(zero_index != -1){
        		if(i==zero_index)
        			result[i] = sum;
        		else
        			result[i] = 0;
        	}
        	else
        		result[i] = sum/nums[i];
        }
        return result;
    }
	public static void main(String args[]){
		int[] nums = {0,2,0,4,0};
		nums = productExceptSelf(nums);
		for(int i:nums)
			System.out.print(i+" ");
		System.out.println();
	}
}