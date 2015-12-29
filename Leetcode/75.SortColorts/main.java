import java.util.*;
/**
 * 2015-12-31
 * 一次遍历算法 帅～
 * left左边都是0，right右边都是1，left++的时候p也要++
 */
public class main{
	public static void sortColors(int[] nums) {
		if(nums.length==0 || nums.length==1)
			return;
		int left = 0;
		while(left<nums.length && nums[left]==0)//left左边的都是0
			left++;
		int right = nums.length-1;
		while(right>-1 && nums[right]==2)//right右边的都是2
			right--;
		for(int p=left;p<=right && left<right;){//left>=right时，显然已经有序了，因为left左边全是0,right右边全是2
			if(nums[p]==0)
			{
				int tmp = nums[p];
				nums[p] = nums[left];
				nums[left] = tmp;
				left++;
				p++;
			}
			else if(nums[p]==2)
			{
				int tmp = nums[p];
				nums[p] = nums[right];
				nums[right] = tmp;
				right--;
			}
			else if(nums[p]==1)
				p++;
			else
				;
		}
    }
	public static void main(String args[]){
		int[] nums = {2,0,1,0,2,1,2,1,0,2,1,0};
		sortColors(nums);
		for(int i:nums)
			System.out.print(i);
	}
}