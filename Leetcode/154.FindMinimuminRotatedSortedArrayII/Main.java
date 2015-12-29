import java.util.*;
/**
 * 2016-3-29
 * 应该看一下其他解法,还没有看
 *
 * 计算一下时间复杂度
 * 这个解法有点粗暴,就是多判断了一种情况nums[mid]==nums[left]==nums[right]
 * 解决方法也粗暴,就取一半判断一下是不是全相同值,如果不是那就在这半里找最小值,否则到另一半找
 * 最坏情况下,全都一样,O(n)
 * 最好情况,全都不一样,O(logn)
 */
public class Main{
    public int findMin(int[] nums) {
        return find(nums,0,nums.length-1);
    }
    public int find(int[] nums,int left,int right){
        if(left==right)
            return nums[left];
        if(nums[left]<nums[right])
            return nums[left];
        int mid = (left+right)/2;
        if(nums[mid]<nums[(mid+nums.length-1)%nums.length])
            return nums[mid];
        if(nums[mid]>nums[left])
            return find(nums,mid+1,right);
        else if(nums[mid]<nums[left])
            return find(nums,left,mid-1);
        else if(nums[mid]>nums[right])
            return find(nums,mid+1,right);
        else if(nums[mid]<nums[right])
            return find(nums,left,mid-1);
        if(isFlat(nums,left,mid))
            return find(nums,mid+1,right);
        else
            return find(nums,left,mid-1);                        
    } 
    boolean isFlat(int[] nums,int left,int right){
        for(int i=left;i<=right;i++){
            if(nums[i]!=nums[left])
                return false;
        }
        return true;
    } 
    public static void main(String args[]){
        int[] nums = {1};
        System.out.println(new Main().findMin(nums));
    }
}