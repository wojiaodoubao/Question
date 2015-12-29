import java.util.*;
/**
 * 2016-1-22 
 * 一维空间，动态规划，S[i]表示以i结尾的和最大的subarray的值，从最大结尾i可以简单反推出subArray，向前查所有S[i]>0的
 */
public class Main{
    public static int maxSubArray(int[] nums) {
    	if(nums==null || nums.length<=0)
    		return 0;
    	int sum = 0;
    	int max = 0;
    	for(int i=0;i<nums.length;i++){
    		if(sum>=0){
    			sum+=nums[i];
    		}
    		else
    			sum=nums[i];
    		if(i==0)
    			max=sum;
    		else if(max<sum)
    			max=sum;
    	}
    	return max;
    }
	public static void main(String args[]){
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray(nums));
	}
}