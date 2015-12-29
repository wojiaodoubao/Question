import java.util.*;
/**
 * 2016-9-2
 * 超时解法
 * 想法：
 * 1.这个图本身就是一棵树。
 * 2.以node为根的树高取决于node到其他点距离中最长的距离。
 * 3.for each node，深搜，遍历所有节点，计算从node出发到其他节点中的最长距离，存入distance。
 * 时间复杂度：
 * 预先把节点兄弟关系存入brothers，O(edges.length)。因为n-1条边，所以n-1个兄弟关系，存了两份所以共2*(n-1)个单元。
 * 复杂度不会算。枚举O(n)，查兄弟总共O(2n-2)，枚举每个之后dfs都得O(n)
 */
public class Main{
	private int[] distance; 
	private int N;
	private int[][] Edges;
	private List<List<Integer>> brothers = new ArrayList<List<Integer>>();
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	distance = new int[n];
    	N = n;
    	Edges = edges;
    	//构造brothers
		for(int i=0;i<n;i++){
			brothers.add(new ArrayList<Integer>());
		}
		for(int i=0;i<edges.length;i++){
			brothers.get(edges[i][0]).add(edges[i][1]);
			brothers.get(edges[i][1]).add(edges[i][0]);
		}    	
		//calculate
    	for(int i=0;i<n;i++){
    		if(brothers.get(i).size()>1)
    			dfs(0,i,i,-1);
    		else
    			distance[i]=n;
    	}	
    	//O(n)找到所有最小
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<N;i++){
			if(result.size()==0)
				result.add(i);
			else if(distance[result.get(0)]>distance[i]){
				result.clear();
				result.add(i);
			}
			else if(distance[result.get(0)]==distance[i])
				result.add(i);
			else
				continue;
		}
		return result;
    }	
    private void dfs(int length,int current,int source,int father){
    	if(distance[source]<length)
    		distance[source] = length;
    	List<Integer> bro = brothers.get(current);
    	for(int i:bro){
			if(i!=father&&i!=current&&i!=source)
				dfs(length+1,i,source,current);  		
    	}
    }
	public static void main(String args[]){
		Main main = new Main();
		int n = 6;
		//int[][] edges = {{1,0},{1,2},{1,3}};	
		int[][] edges = {{0,3},{1,3},{2,3},{4,3},{5,4}};
		List<Integer> list = main.findMinHeightTrees(n,edges);
		for(Integer i:list)
			System.out.print(i+" ");
	}
}