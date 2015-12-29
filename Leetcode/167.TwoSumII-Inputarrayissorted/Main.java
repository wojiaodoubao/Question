import java.util.*;
/**
 * 2016-10-26
 * 
 * 题目：从递增int[] nums中找到i,j使得nums[i]+nums[j]=target。
 * 思路：i从0开始，j从nums.length-1开始，如果nums[i]+nums[j]小于target,i++；大于target,j--;相等即为所求。
 * 正确性证明：假设p,q是nums上的解，按照思路实现的算法，必定能找到此解。
 * 1.初始时i小于等于p，j大于等于q。
 * 2.不论i,j如何移动，一定有一刻i==p或j==q，假设是i==p先发生(同时发生就已经得证了)，此时j>q，
 *   由于nums递增，因此nums[i]+nums[j]>target，按照算法j要开始--，直到j==q，算法结束。j==q先发生同理。
 * 
 * 本题的二分思想：
 * 整体思路就是前面所述，不过将i++和j--改成了二分查找。
 * 1.和小于target时，固定j，二分地找第一个nums[i]+nums[j]大于等于target；
 * 2.和大于target时，固定i，二分地找第一个nums[i]+nums[j]小于等于target。
 *
 * 思路的正确性确实很容易验证，假设解为p,q后讨论即可。但是是怎么想出来这样做的呢？
 */
public class Main{
    public int[] twoSum(int[] numbers, int target) {
		int i = 0;
		int j = numbers.length-1;
		while(i<j){
			if(numbers[i]+numbers[j]>target){
				int s = i;
				int e = j;
				while(s<e){
					int mid = (s+e)/2+1;
					if(numbers[i]+numbers[mid]<=target)
						s=mid;
					else
						e=mid-1;
				}
				j=s;
			}
			else if(numbers[i]+numbers[j]<target){
				int s = i;
				int e = j;
				while(s<e){
					int mid = (s+e)/2;
					if(numbers[j]+numbers[mid]>=target)
						e=mid;
					else
						s=mid+1;
				}
				i=s;
			}
			else{
				int[] result = new int[2];				
				result[0] = i+1;
				result[1] = j+1;
				return result;
			}
		}        
		return null;
    }	
    public static void main(String args[]){
    	int numbers[] = {2,7,11,15};
    	int target = 100;
    	numbers = new Main().twoSum(numbers,target);
    	for(int i:numbers)
    		System.out.print(i+" ");
    	System.out.println();
    }
}