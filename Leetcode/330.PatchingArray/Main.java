import java.util.*;
/**
 * 2016-4-7
 * 我实在是太SB了!不会做就得了,抄还抄不对!
 * [http://storypku.com/2016/03/leetcode-question-330-patching-array/]
 * 想法就是:当前集合中的数能组合出的连续区间是[1,interval],但没必要保存当前集合
 * 1.[1,interval]表示目前集合的组合能组合出的连续区间. //missInt就表示[1,missInt)都可以组合出来
 * 2.如果nums[i]<=interval+1,那么加入nums[i]之后[1,interval+nums[i]]都可以组合出来.
 * 3.如果nums[i]>interval+1,那么就缺少了interval+1,补充它,之后区间变成[1,interval*2+1]
 * 
 * 这道题还要考虑越界问题的!所以用了double,c++中可以用unsigned. 
 *
 * 正确性不太好证:你看有没有这种可能:当前集合组合出[1,2,...,k]和M,M>k+1,之后补充k+1变成[1,2k+1],
 * 而M=2k+2,本来该是[1,2k+2],少算了一个2k+2,结果导致补充数数量出现偏差?
 * [1,2,...,k](k+1)[k+2,...,2k+1]M
 */
public class Main{   
    public int minPatches(int[] nums, int n) {
        int result=0;
        double missInt=1;
        int i=0;
        while(missInt<=n){
            if(i<nums.length&&nums[i]<=missInt){
                missInt+=nums[i++];
            }
            else{
                missInt+=missInt;
                result++;                
            }
        }
        return result;
    }
    public int patches(int[] nums, int n){
        int result = 0;
        double interval = 0;
        int i=0;
        while(interval<n){
            if(i>=nums.length||nums[i]>interval+1){
                interval=interval*2+1;
                result++;
            }
            else{
                interval+=nums[i++];
            }
        }
        return result;
    }
    public static void main(String args[]){
        int[] nums = {1,2,31,33};
        int n = 2147483647;
        System.out.println(new Main().minPatches(nums,n));
        System.out.println(new Main().patches(nums,n));
    }
}