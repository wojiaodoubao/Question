import java.util.*;
/**
 * 2016-3-12
 * 被忽悠了,还真以为是并查集(Union Find)呢...
 * 使用HashSet辅助(O(1)时间查找删除),先全入Set,再逐个找其相邻元素,所有都是找到后就删除.
 * 其实写错的桶那版也是想O(1)时间查找(bucket[i])删除(最后一遍走过了就相当于删除了),所以写完桶就知道改成哈希就好了
 * 原来HashSet可以直接用,还以为要自己手写哈希...
 */
public class Main{
	public int longestConsecutive(int[] nums) {
		if(nums==null||nums.length<=0)
			return -1;
    	Set<Integer> set = new HashSet<Integer>();
    	for(int i:nums)
    		set.add(i);
    	int consecutive=1;
    	for(int k:nums){
    		int count = count(set,k);
    		if(count>consecutive)
    			consecutive=count;
    	}
    	return consecutive;
    }
    //不能递归查找,查找次数过多会爆栈(还记得方法调用与栈帧吗)
    public int count(Set<Integer> set,int value){
    	int count = 0;
    	if(!set.contains(value))
    		return count;
    	int tmp = value+1;
    	while(set.contains(tmp)){
    		count++;
    		set.remove(tmp);
    		tmp++;
    	}
    	tmp = value-1;
     	while(set.contains(tmp)){
    		count++;
    		set.remove(tmp);
    		tmp--;
    	}   	
    	count++;
    	set.remove(value);
    	return count;
    }
	public static void main(String args[]){
		Main m = new Main();
		int[] nums = {100,4,200,1,3,2,199,198,201,202};
		System.out.println(m.longestConsecutive(nums));
	}
}