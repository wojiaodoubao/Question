import java.util.*;
/**
 * 2016-3-29
 * 解决了不错.
 * 每扫描一次划分一个区间[border[0],border[1]],区间外的数就不考虑了
 * 对每次取的数nums[i],如果比他小的多于nums[i]-border[0],那么重复数在[border[0],nums[i]-1]中,反之在[nums[i]+1,border[1]]
 */
public class Solution {
    public int findDuplicate(int[] nums) {
        if(nums==null || nums.length<=0)
            return -1;
        int[] border = new int[2];
        border[0] = 1;
        border[1] = nums.length;
        int left = 0;
        int right = 0;
        int current = 0;
        for(int i=0;i<nums.length;i=(i+1)%nums.length){
            if(nums[i]>=border[0]&&nums[i]<=border[1]){
                left=0;
                right=0;
                current=0;
                for(int j=0;j<nums.length;j++){
                    if(nums[j]<border[0] || nums[j]>border[1])
                        continue;
                    if(nums[j]<nums[i])
                        left++;
                    else if(nums[j]>nums[i])
                        right++;
                    else if(++current>1)
                        return nums[i];
                }
                if(left>nums[i]-border[0])
                    border[1]=nums[i]-1;
                else
                    border[0]=nums[i]+1;
            }
        }
        return -1;
    }    
    public static void main(String args[]){
        Solution so = new Solution();
        int[] nums = {1,2,3,4,4,5};
        System.out.println(so.findDuplicate(nums));
    }
}