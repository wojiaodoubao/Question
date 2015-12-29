import java.util.*;
/**
 * 2015-12-29
 */
public class main
{
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        if(nums.length<=0)return result;        
        int left = nums[0];
        int right = left;
        for(int i=1;i<nums.length;i++){
        	if(nums[i]==right+1)
        		right++;
        	else{
        		if(right == left)
        			result.add(left+"");
        		else
        			result.add(left+"->"+right);
        		left = nums[i];
        		right = left;
        	}
        }
		if(right == left)
			result.add(left+"");
		else
			result.add(left+"->"+right);        
        return result;
    }
	public static void main(String args[]){
		int[] nums = {0};
		System.out.println(summaryRanges(nums));
	}
}