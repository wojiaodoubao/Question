import java.util.*;
/**
 * 2016-4-1
 * 超时!
 * 2016-4-2
 * 增加isPossible剪枝之后过了.复杂度没降低,过了应该是因为case正好没测到.
 */
public class TimeLimitExceed{
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
    public List<List<Integer>> fourSum(int[] nums, int target) {
        quickSort(nums,0,nums.length-1);
        return sum(nums,0,nums.length-1,target,4);       
    }    
    public List<List<Integer>> sum(int[] nums,int left,int right,int target,int n){
        //System.out.println(left+" "+right+" "+target+" "+n);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(!isPossible(nums,left,right,target,n))
            return result;
        if(n==0){//target==0
            ArrayList<Integer> list = new ArrayList<Integer>();
            result.add(list);
            return result;
        }
        else{
            for(int i=0;i<=right-left-n+1;i++){
                if(i>0&&nums[left+i]==nums[left+i-1])
                    continue;
                List<List<Integer>> tmp = sum(nums,left+i+1,right,target-nums[left+i],n-1);
                for(List<Integer> list:tmp){
                    list.add(0,nums[left+i]);
                    result.add(list);
                }
            }
        }
        return result;
    }
    boolean isPossible(int[] nums,int left,int right,int target,int n){
        if(n==0){
            if(target!=0)
                return false;
            else
                return true;
        }
        else if(right-left+1<n)
            return false;
        else{
            int min = 0;
            int max = 0;
            for(int i=0;i<n;i++){
                min+=nums[left+i];
                max+=nums[right-i];
            }
            if(min>target||max<target)
                return false;
            return true;
        }
    }
    public static void main(String args[]){
        TimeLimitExceed main = new TimeLimitExceed();
        int[] candidates = {-471,-434,-418,-395,-360,-357,-351,-342,-317,-315,-313,-273,-272,-249,-240,-216,-215,-214,-209,-198,-179,-164,-161,-141,-139,-131,-103,-97,-81,-64,-55,-29,11,40,40,45,64,87,95,101,115,121,149,185,230,230,232,251,266,274,277,287,300,325,334,335,340,383,389,426,426,427,457,471,494};
        int target = 2705;
        long time = System.nanoTime();
        List<List<Integer>> result = main.fourSum(candidates,target);
        System.out.println("nano time:"+(System.nanoTime()-time));
        for(List<Integer> list:result){
            for(Integer i:list)
                System.out.print(i+" ");
            System.out.println();
        }
    }
}