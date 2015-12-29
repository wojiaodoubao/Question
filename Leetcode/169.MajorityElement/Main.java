import java.util.*;
/**
 * 2016-3-30
 * log(n)求解
 * 如下标记所言,使用'<='的时候有case过不了,[1,1,....,1,2,2,...,2]
 * 而且这case用random partition也没用
 * 好烦
 */
public class Main{
    public int majorityElement(int[] nums) {
        if(nums==null||nums.length<=0)
            return -1;
        return element(nums,0,nums.length-1,nums.length/2);
    }
    public int element(int[] nums,int Left,int Right,int index){
        int left = Left;
        int right = Right;
        int pattern = nums[right];
        while(left<right){
            while(left<right&&nums[left]<pattern)//<=时候有case过不了
                left++;
            nums[right]=nums[left];
            while(left<right&&nums[right]>=pattern)
                right--;
            nums[left]=nums[right];
        }
        nums[left]=pattern;
        if(index+Left==left)
            return nums[left];
        else if(index+Left>left)
            return element(nums,left+1,Right,index-(left-Left+1));
        else
            return element(nums,Left,left-1,index);
    }
    public static void main(String args[]){
        int[] nums = {2,2,2,2,5,3,4};
        System.out.println(new Main().majorityElement(nums));
    }
}