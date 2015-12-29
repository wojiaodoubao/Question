import java.util.*;
/**
 * 2016-4-1
 * 排序-去重-分治 这题最低复杂度是多少呢?要是能动态规划就能好不少,但是没想出来,也证明不了不能DP
 * 复杂度挺高的,应该有更好的解法?
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
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        quickSort(candidates,0,candidates.length-1);
        int[] nums = new int[candidates.length];
        int i=0;int j=0;
        for(i=0,j=0;i<nums.length;){
            if(i==0){
                nums[j]=candidates[i];
                i++;j++;
            }
            else if(nums[j-1]==candidates[i])
                i++;
            else{
                nums[j]=candidates[i];
                i++;j++;
            }
        }
        return sum(nums,0,j-1,target);
    }
    public List<List<Integer>> sum(int[] candidates,int left,int right,int target){
        //System.out.println(left+" "+right+" "+target);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(target==0){
            result.add(new ArrayList<Integer>());
            return result;
        }
        for(int i=right-left;i>=0;i--){
            if(candidates[left+i]>target)
                continue;
            List<List<Integer>> tmp = sum(candidates,left,left+i,target-candidates[left+i]);
            for(List<Integer> list:tmp){
                list.add(candidates[left+i]);
                result.add(list);
            }
        }
        return result;
    }
    public static void main(String args[]){
        Main main = new Main();
        int[] candidates = {1,2};
        int target = 1;
        List<List<Integer>> result = main.combinationSum(candidates,target);
        for(List<Integer> list:result){
            for(Integer i:list)
                System.out.print(i+" ");
            System.out.println();
        }
    }
}