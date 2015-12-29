import java.util.*;
/**
 * 2016-1-2
 * 刚开始审错题，写了个从有序0-n数组中找到缺失的那个数，是log(n)的
 * 要单独判断0是不是出现过了，边界条件总是想不全。。。 
 */
public class main{
    public static int missingNumber(int[] nums) {
        int sum = nums.length*(nums.length+1)/2;
        boolean has_zero = false;
        for(int i:nums)
        {
            if(i==0)
                has_zero=true;
            sum-=i;
        }
        if(!has_zero)
            return 0;
        return sum==0?nums.length+1:sum;
    }	
	public static void main(String args[]){
		int[] nums = {1,3,2};
		System.out.println(missingNumber(nums));
	}
}