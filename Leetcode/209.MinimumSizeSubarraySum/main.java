import java.util.*;
/**
 * 2016-1-3
 * 又没审好题，还以为要返回最短的序列。。。
 */
public class main{
    public static int minSubArrayLen(int s, int[] nums) {
    	if(nums==null || nums.length==0)
    		return 0;
    	int mini = nums.length;
    	int sum = 0;
    	int index = 0;
    	for(int i=0;i<nums.length;i++){
            if(i!=0)
    		  sum-=nums[i-1];
    		while(sum<s && index<nums.length)
    			sum+=nums[index++];
    		if(index==nums.length && sum<s)
                return i==0?0:mini;
    		mini = (index-i)>mini?mini:(index-i);
    	}
    	return mini;
    }
	public static void main(String args[]){
		int[] nums = {1,2,3,4,5};
		System.out.println(minSubArrayLen(11,nums));
	}
}