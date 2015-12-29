import java.util.*;
/**
 * 2016-4-3
 */
public class Main{
    public void quickSort(int[] nums,int left,int right){
        if(left>=right)
            return;
        int L = left;
        int R = right;
        int tmp = nums[right];
        while(left<right){
            while(left<right&&nums[left]<=tmp)
                left++;
            nums[right]=nums[left];
            while(left<right&&nums[right]>=tmp)
                right--;
            nums[left]=nums[right];
        }
        nums[left]=tmp;
        quickSort(nums,L,left-1);
        quickSort(nums,left+1,R);
    }    
    public int threeSumClosest(int[] nums, int target) {
        quickSort(nums,0,nums.length-1);
        Integer result = nums[0]+nums[1]+nums[2];
        for(int i=0;i<nums.length-2;i++){
            //prune
            if(nums[i]+nums[i+1]+nums[i+2]>target){
                result=distance((nums[i]+nums[i+1]+nums[i+2]),target)<distance(result,target)
                    ?(nums[i]+nums[i+1]+nums[i+2]):result;
                continue;
            }
            if(nums[i]+nums[nums.length-1]+nums[nums.length-2]<target){
                result=distance((nums[i]+nums[nums.length-1]+nums[nums.length-2]),target)<distance(result,target)
                    ?(nums[i]+nums[nums.length-1]+nums[nums.length-2]):result;
                continue;                
            }
            //2-pointers
            int l = i+1;
            int r = nums.length-1;
            while(l<r){
                int tmp = nums[i]+nums[l]+nums[r];
                result = distance(tmp,target)<distance(result,target)?tmp:result;
                if(tmp>target)
                    r--;
                else if(tmp<target)
                    l++;
                else{
                    System.out.print(i+" "+l+" "+r);
                    return tmp;
                }
            }
            while(i<nums.length-2&&nums[i]==nums[i+1])
                i++;
        }
        return result;
    }
    public int distance(int a,int b){
        if(a>b)
            return a-b;
        return b-a;
    }    
    public static void main(String args[]){
        int[] nums = {-1,2,1,-4};
        int target = 1;
        System.out.println(new Main().threeSumClosest(nums,target));
    }
}