import java.util.*;
/**
 * 2016-1-22
 */
public class Main{
    public static int maxProduct(int[] nums) {
    	if(nums==null || nums.length<=0)
    		return 0;
        int max=1;
        int min=1;
        int result=0;
        for(int i=0;i<nums.length;i++){       	
        	if(nums[i]>0){
        		if(max>0)
        			max*=nums[i];
        		else
        			max=nums[i];
        		if(min>0)
        			min=nums[i];
        		else
        			min*=nums[i];
        	}
        	else if(nums[i]<0){
        		int temp_max;
        		int temp_min; 
        		if(max>0)
        			temp_min=max*nums[i];
        		else
        			temp_min=nums[i];
        		if(min>0)
        			temp_max=nums[i];
        		else
        			temp_max=min*nums[i];
        		max=temp_max;
        		min=temp_min;
        	}
        	else{
        		max=0;
        		min=0;
        	}
        	if(i==0)
        		result=max;
        	if(max>result)
        		result=max;
        }
        return result;
    }
	public static void main(String args[]){
		int[] nums={2,3,-2,4};
		System.out.println(maxProduct(nums));
	}
}