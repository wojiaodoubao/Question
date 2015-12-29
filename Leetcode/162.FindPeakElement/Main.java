import java.util.*;
/**
 * 2016-3-29
 * 第一下没读明白,以为求所有peak,就没反应过来log(n)
 */
public class Main{
    public int findPeakElement(int[] nums) {
        return findPeak(nums,0,nums.length-1);
    }
    public int findPeak(int[] nums,int left,int right){
        int mid = (left+right)/2;
        if(isPeak(nums,mid)<0)
            return findPeak(nums,left,mid);
        else if(isPeak(nums,mid)>0)
            return findPeak(nums,mid+1,right);
        return mid;
    }
    //-1-down 0-peak 1-up 
    int isPeak(int[] nums,int index){
        if(nums.length==1)
            return 0;
        if(index==0){
            if(nums[1]<nums[0])
                return 0;
            else 
                return 1;
        }
        if(index==nums.length-1){
            if(nums[nums.length-1]>nums[nums.length-2])
                return 0;
            else
                return -1;
        }
        if(nums[index]>nums[index-1]&&nums[index]>nums[index+1])
            return 0;
        else if(nums[index-1]<nums[index])
            return 1;
        else
            return -1;
    }    
    public static void main(String args[]){
        int[] nums = {2,1};
        System.out.println(new Main().findPeakElement(nums));
    }
}