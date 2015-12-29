import java.util.*;
/**
 * 2016-3-19
 * Wrong! Must not modify the array!
 */
public class Modify{
    public int findDuplicate(int[] nums) {
        if(nums==null||nums.length<=0)
            return -1;
        int i = 0;
        while(i<nums.length){
            if(nums[i]!=i+1)
            {
                if(nums[i]==nums[nums[i]-1])
                    return nums[i];
                else
                    swap(nums,i,nums[i]-1);
            }
            else
                i++;
        }
        return -1;
    }
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String args[]){
        Modify Modify = new Modify();
        int[] nums = {2,1,1,3};
        System.out.println(Modify.findDuplicate(nums));
    }
}