import java.util.*;
/**
 * 2016-9-5
 * nice，带memorization的dfs/dfs+dp
 * 0.构造matrix.length*matrix[0].length个点，(i,j)->i*matrix[0].length+j
 * 1.构造邻接表
 * 2.将点按照在矩阵中的值大小排序
 * 3.从大到小枚举点并逐个深搜
 * 由于预先知道深搜的走向(拓扑序，值大的肯定最后走)，所以可以"从后往前"带存储地计算/DP
 */
public class Main{
    public int longestIncreasingPath(int[][] matrix) {
    	int result = 0;
    	if(matrix==null||matrix.length==0||matrix[0].length==0)
			return result;    	
    	List<List<Integer>> topo = new ArrayList<>();
    	int[] sequence = new int[matrix.length*matrix[0].length];
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				sequence[i*matrix[0].length+j]=i*matrix[0].length+j;
				List<Integer> list = new ArrayList<>();
				if(i>0&&matrix[i-1][j]>matrix[i][j])
					list.add((i-1)*matrix[0].length+j);
				if(i<matrix.length-1&&matrix[i+1][j]>matrix[i][j])
					list.add((i+1)*matrix[0].length+j);
				if(j>0&&matrix[i][j-1]>matrix[i][j])
					list.add((i)*matrix[0].length+j-1);
				if(j<matrix[0].length-1&&matrix[i][j+1]>matrix[i][j])
					list.add((i)*matrix[0].length+j+1);
				topo.add(list);//i*matrix[0].length+j
			}
		}  
		quickSort(matrix,sequence,0,sequence.length-1);
		int[] memorization = new int[matrix.length*matrix[0].length];
		for(int i=sequence.length-1;i>=0;i--){
			int l = dfs(sequence[i],topo,memorization);
			result = l>result?l:result;
		}
		return result;
    }
    private void quickSort(int[][] matrix,int[] sequence,int start,int end){
    	if(start>=end)
    		return;
    	int i=start;
    	int j=end;
    	int midV = sequence[end];
    	while(i<j){
    		while(i<j&&matrix[sequence[i]/matrix[0].length][sequence[i]%matrix[0].length]
    			<matrix[midV/matrix[0].length][midV%matrix[0].length])
    			i++;
    		sequence[j]=sequence[i];
    		while(i<j&&matrix[sequence[j]/matrix[0].length][sequence[j]%matrix[0].length]
    			>=matrix[midV/matrix[0].length][midV%matrix[0].length])
    			j--;
    		sequence[i]=sequence[j];
    	}
    	sequence[i]=midV;
    	quickSort(matrix,sequence,start,i-1);
    	quickSort(matrix,sequence,i+1,end);
    }
    private int dfs(int node,List<List<Integer>> topo,int[] memorization){
    	if(memorization[node]>0)
    		return memorization[node];
    	List<Integer> list = topo.get(node);
    	List<Integer> next = new ArrayList<Integer>();
    	int result = 0;
    	for(int i:list){
    		int l = dfs(i,topo,memorization);
    		result = l>result?l:result;
    	}
    	memorization[node]=result+1;
    	return result+1;
    }
    public static void main(String args[]){
		int[][] nums = {
		  {9,9,4},
		  {6,6,8},
		  {2,1,1}
		};
		// int[][] nums = {
		//   {3,4,5},
		//   {3,2,6},
		//   {2,2,1}
		// };		
    	System.out.println(new Main().longestIncreasingPath(nums));
    }	
}