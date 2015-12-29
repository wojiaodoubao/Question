import java.util.*;
/**
 * 2016-4-29
 * 因为循环有序,所以找到第一个之后就找到了第n个
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
        if(nums[mid]>=nums[left])
            return find(nums,mid+1,right);
        return find(nums,left,mid);
    }
    public static void main(String args[]){
        int[] nums = {3,4,5,6,1,2};
        System.out.println(new Main().findMin(nums));
    }
}