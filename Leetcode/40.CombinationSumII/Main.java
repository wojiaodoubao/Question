import java.util.*;
/**
 * 2016-4-1
 * 排序-分治
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
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        quickSort(candidates,0,candidates.length-1);
        return sum(candidates,0,candidates.length-1,target);
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
            if(i<right-left&&candidates[left+i]==candidates[left+i+1])
                continue;
            List<List<Integer>> tmp = sum(candidates,left,left+i-1,target-candidates[left+i]);
            for(List<Integer> list:tmp){
                list.add(candidates[left+i]);
                result.add(list);
            }
        }
        return result;
    }
    public static void main(String args[]){
        Main main = new Main();
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> result = main.combinationSum2(candidates,target);
        for(List<Integer> list:result){
            for(Integer i:list)
                System.out.print(i+" ");
            System.out.println();
        }
    }
}