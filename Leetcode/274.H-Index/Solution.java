/**
 * 2016-10-11
 * 代码很直观了
 */
public class Solution{
	public int hIndex(int[] citations) {
		int[] nums = new int[citations.length+1];
		for(int num:citations){
			if(num>citations.length)
				nums[citations.length]++;
			else
				nums[num]++;
		}
		int k = 0;
		for(int i=citations.length;i>=0;i--){//k-文章数，i-引用数
			k+=nums[i];
			if(k>=i)
				return i;
		}
		return 0;//永远不会在这里返回的！
	}	
}