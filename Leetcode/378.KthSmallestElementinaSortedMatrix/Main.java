import java.util.*;
/**
 * 2016-9-8
 * 这方案居然也是一下过了，我还以为会更巧一点
 * 满足i*j小于等于k的才有竞争资格，从matrix[0][0]到matrix[i-1][j-1]至少i*j个，由此可以筛掉一批
 * 反之也成立，可以用于优化k大于n*n/2的时候
 */
public class Main{
    public int kthSmallest(int[][] matrix, int k) {
    	List<Integer> list = new ArrayList<Integer>();
    	for(int i=1;i<=matrix.length&&i<=k;i++){
    		for(int j=1;j<=matrix.length&&i*j<=k;j++){
    			list.add(matrix[i-1][j-1]);
    		}
    	}
    	Collections.sort(list);
    	return list.get(k-1);
    }	
    public static void main(String args[]){
    	int[][] matrix = {
		   { 1,  5,  9},
		   {10, 11, 13},
		   {12, 13, 15}
		};
		int k = 5;
    	System.out.println(new Main().kthSmallest(matrix,k));
    }
}