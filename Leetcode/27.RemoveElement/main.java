import java.util.*;
/**
 * 2016-1-1
 */
public class main{
    public static int removeElement(int[] nums, int val) {
        if(nums==null || nums.length==0)
        	return 0;
        int num = 0;
        int i=0;
        while(i<nums.length-num){
        	if(nums[i]==val){
        		nums[i] = nums[nums.length-1-num];
        		num++;
        	}
        	else
        		i++;
        }
        return nums.length-num;
    }
	public static void main(String args[]){
		int[] nums = {3,3};
		int val = Integer.parseInt(args[0]);
		System.out.println(removeElement(nums,val));
		for(int i=0;i<nums.length;i++)
			System.out.print(nums[i]);
		System.out.println();
	}
}