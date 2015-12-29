import java.util.*;
/**
 * 2016-1-1
 * 看到一个不错的解法,写一下
 */
public class product{
	public static int[] productExceptSelf(int[] nums) {
		if(nums==null || nums.length==0)
			return null;
		int[] result = new int[nums.length];
		int sum = 1;
		for(int i=0;i<nums.length;i++){
			result[i] = sum;
			sum *= nums[i];
		}
		sum = 1;
		for(int i=nums.length-1;i>-1;i--){
			result[i] *= sum;
			sum *= nums[i];
		}
		return result;
	}
	public static void main(String args[]){
		int[] nums = {1,2,3,0};
		nums = productExceptSelf(nums);
		for(int i:nums)
			System.out.print(i+" ");
		System.out.println();
	}
}