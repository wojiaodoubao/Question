import java.util.*;
/**
 * 2016-1-2
 * 有点意思
 * 从后往前找到第一个比后一个小的nums[j],再从后往前找到第一个比nums[j]大的nums[i],nums把i,j值交换，nums把j+1到尾巴的所有值两两互换
 */
public class main{
    public static void nextPermutation(int[] nums) {
    	if(nums.length==0)
    		return;
    	int j = nums.length-2;
        for(;j>-1;j--){
        	if(nums[j]<nums[j+1])
        		break;
        }
        if(j==-1){//it's the biggest, reverse it
        	for(int i=0;i<nums.length/2;i++){
        		int tmp = nums[i];
        		nums[i] = nums[nums.length-1-i];
        		nums[nums.length-1-i] = tmp;
        	}
        	return;
        }
        for(int i=nums.length-1;i>j;i--){
        	if(nums[i]>nums[j]){//the first one bigger than nums[j]
        		//exchange
        		int tmp = nums[i];
        		nums[i] = nums[j];
        		nums[j] = tmp;
        		//reverse
        		for(int k=0;k<(nums.length-j)/2;k++){
        			tmp = nums[nums.length-1-k];
        			nums[nums.length-1-k] = nums[j+1+k];
        			nums[j+1+k] = tmp;
        		}
        		return;
        	}
        }
    }
	public static void main(String args[]){
		int[] nums = {1,2,3,4,5};
		for(int j=0;j<5*4*3*2*1;j++){
			nextPermutation(nums);
			for(int i:nums)
				System.out.print(i+" ");
			System.out.println();
		}
	}
}