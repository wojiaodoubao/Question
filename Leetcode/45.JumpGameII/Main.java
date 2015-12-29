import java.util.*;
/**
 * 2016-3-13 跟55很像
 * 虽然是两层循环,但是是O(n)的,因为right从0到nums.length-1一直在向右
 * 看了标签,这没啥吧...
 */
public class Main{
    public int jump(int[] nums) {
        if(nums==null || nums.length<=0)
        	return -1;
        if(nums.length==1)
        	return 0;
        int left = 1;
        int right = nums[0];
        int times = 1;
        while(right<nums.length-1){
        	int newright = -1;
        	for(int i=left;i<=right;i++){
        		if(nums[i]+i>newright)
        			newright = nums[i]+i;
        	}
        	if(newright==-1)
        		return -1;
        	left=right+1;
        	right = newright;
        	times++;
        }
        return times;
    }	
	public static void main(String args[]){
		Main main = new Main();
		int[] nums = {2,3,1,4,4};
		System.out.println(main.jump(nums));
	}
}