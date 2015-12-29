import java.util.*;
/**
 * 2016-1-1
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class main{
    public static void rotate(int[] nums, int k) {
    	if(nums==null || nums.length==0)
    		return;
    	//method1(nums,k);
    	method2(nums,k);
    	//method3(nums,k);
    	//method4(nums,k);
    }
    private static void method1(int[] nums,int k){
		//想循环着放 	
    }
    private static void method2(int[] nums,int k){
    	int start = nums.length-k%nums.length;
    	int[] tmp = new int[start];
    	for(int i=0;i<start;i++){
    		tmp[i] = nums[i];
    	}
    	for(int i=0;i<nums.length-start;i++){
    		nums[i] = nums[start+i];
    	}
    	for(int i=0;i<start;i++){
    		nums[i+nums.length-start] = tmp[i];
    	}
    }
    //递归，O(1) extra space
    private static void method3(int[] nums,int k){
    	if(k%nums.length==0)
    		return;
    	method3(nums,k-1);
    	int tmp = nums[0];
    	for(int i=1;i<nums.length;i++){
    		nums[i-1] = nums[i];
    	}
    	nums[nums.length-1] = tmp;
    }
    private static void method4(int[] nums,int k){
    	int[] tmp = new int[nums.length];
    	int left_k = nums.length-k%nums.length;
    	for(int i=nums.length-1;i>-1;i--){
    		tmp[(i-left_k+nums.length)%nums.length] = nums[i];
    	}
    	for(int i=0;i<nums.length;i++){
    		nums[i] = tmp[i];
    	}
    }
	public static void main(String args[]){
		int[] nums = {1,2};
		int k = Integer.parseInt(args[0]);
		rotate(nums,k);
		for(int i : nums){
			System.out.print(i);
		}
		System.out.println("");
	}
}