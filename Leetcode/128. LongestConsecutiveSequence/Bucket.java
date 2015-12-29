import java.util.*;
/**
 * 2016-3-12 错误解法 我也知道肯定不行,但还是想试试
 * 先值入桶,再扫描一遍桶查找最长连续序列,取值空间跨度大时失败
 */
public class Bucket{
	class MaxMin{
		public int max;
		public int min;
		public MaxMin(int max,int min){
			this.max = max;
			this.min = min;
		}
		public String toString(){
			return max+" "+min;
		}
	}
	//find Max&Min in O(3n/2)
	public MaxMin findMaxMin(int[] nums,int left,int right){
		if(left>right)
			return null;
		if(left==right)
			return new MaxMin(nums[left],nums[left]);
		else if(left+1==right)
			return new MaxMin(nums[left]>nums[right]?nums[left]:nums[right]
				,nums[left]>nums[right]?nums[right]:nums[left]);
		else{
			MaxMin l = findMaxMin(nums,left,(left+right)/2);
			MaxMin r = findMaxMin(nums,(left+right)/2+1,right);
			return new MaxMin(l.max>r.max?l.max:r.max,l.min<r.min?l.min:r.min);
		}
	}
	//桶排序思想,先入桶,再扫描一遍
    public int longestConsecutive(int[] nums) {
    	if(nums==null || nums.length<=0)
    		return -1;
        MaxMin mm = findMaxMin(nums,0,nums.length-1);
        int[] bucket = new int[mm.max-mm.min+1];//java自动初始化为0
        for(int k:nums)
        	bucket[k-mm.min]=1;
        int consecutive = 1;
        int temp = 0;
        for(int k:bucket){
        	if(k==1){
        		temp++;
        		continue;
        	}
        	if(consecutive<temp){
        		consecutive = temp;
        	}
        	temp=0;
        }
    	if(consecutive<temp){
    		consecutive = temp;
    	}        
        return consecutive;
    }	
	public static void main(String args[]){
		Bucket m = new Bucket();
		int[] nums = {100,4,200,1,3,2,198,197,199,201,202};
		System.out.println(m.longestConsecutive(nums));
	}
}