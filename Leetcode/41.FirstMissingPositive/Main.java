import java.util.*;
/**
 * 2016-3-15
 * 这道题非常难
 * 数组nums长度为N,则考虑两种情况:1.存的是1,2,3,...,N 此时应该返回N+1; 2.不是情况1,则第一个丢失正整数一定在1~N中.
 * 从左向右处理每个数nums[i],如果其在[1,N]中则将其放到对应位置,如果不再则不管它,一遍结束后得到[1,N]中数在对应位置的数组
 *
 * 桶解释
 * 很容易想到用桶实现的O(n)方法:都扔到桶里扫描一遍.Hash本质和桶是一个原理,只不过处理大范围稀疏数据更强.
 * 此题解法其实是把自己当做桶来用...桶i存值i+1,然后桶从左向右数就可以了
 * 桶只需要1~N共N个桶就够了,把对应这N个桶的数都扔到桶里,如果满了就返回N+1,否则返回从左向右第一个空桶对应的数
 * 下面的实现中,存了[1,N]之外的数的桶就被认为是空桶.
 *
 * 后记
 * O(n)线性表解法总是很容易想到桶,但是桶往往不能直接求解,就要稍微变化一下:
 * 比如哈希,比如此题分析出只需要N个桶,于是以自己为桶
 */
public class Main{
    public int firstMissingPositive(int[] nums) {
        if(nums==null||nums.length<=0)
            return 1;
        int i = 0;
        while(i<nums.length){
            if(nums[i]==i+1||nums[i]<=0||nums[i]>nums.length||nums[i]==nums[nums[i]-1])//桶装对或桶空或对应桶已满
                i++;            
            else{//桶装错且nums[i]对应桶为空
                exchange(nums,i,nums[i]-1);
            }
        }
        for(i=0;i<nums.length;i++){//扫描一遍桶
            if(nums[i]!=i+1)
                return i+1;
        }   
        return nums.length+1;
    }
    private void exchange(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String args[]){
        Main main = new Main();
        int[] nums = {10,4,16,54,17,-7,21,15,25,31,61,1,6,12,21,46,16,56,54,12,23,20,38,63,2,27,35,11,13,47,13,11,61,39,0,14,42,8,16,54,50,12,-10,43,11,-1,24,38,-10,13,60,0,44,11,50,33,48,20,31,-4,2,54,-6,51,6};
        System.out.println(main.firstMissingPositive(nums));
    }
}