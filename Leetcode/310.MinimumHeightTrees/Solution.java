import java.util.*;
/**
 * 参考：https://discuss.leetcode.com/topic/30572/share-some-thoughts/2
 * 【妙】
 * 1.使树高最小的顶点，一定是图中最长路的中点(如果路长偶数则两个点，奇数则一个点，不会超过两个点)
 * 使用反证法很容易证明，如果不是，则一定从此中点出发有一条更长的，则存在更长路。
 * 2.但是此解法并没去找最长路，而是每次删掉所有叶子节点，直到只剩下1/2个节点，就返回。
 */
public class Solution{
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	if(n==1){
    		List<Integer> result = new ArrayList<Integer>();
    		result.add(0);
    		return result;
    	}
    	List<Set<Integer>> brothers = new ArrayList<>();
    	for(int i=0;i<n;i++)
    		brothers.add(new HashSet<Integer>());
    	for(int[] edge:edges){
    		brothers.get(edge[0]).add(edge[1]);
    		brothers.get(edge[1]).add(edge[0]);    		
    	}   
    	List<Integer> leaves = new ArrayList<Integer>();
    	for(int i=0;i<n;i++){
    		if(brothers.get(i).size()==1)
    			leaves.add(i);
    	}
    	while(n>2){
    		n-=leaves.size();
    		List<Integer> newLeaves = new ArrayList<Integer>();
    		for(int leaf:leaves){
    			int bro = brothers.get(leaf).iterator().next();
    			brothers.get(bro).remove(leaf);
    			if(brothers.get(bro).size()==1)
    				newLeaves.add(bro);
    		}
    		leaves = newLeaves;
    	}
    	return leaves;
    }	
}