import java.util.*;
/**
 * 2016-1-4
 * 经典问题，为什么都是两层循环，最后我要超过5ms？怎么实现能2.5ms？
 */
public class main{
    public static int minPathSum(int[][] grid) {
    	if(grid==null || grid.length==0 || grid[0].length==0)
    		return 0;
        for(int i=0;i<grid.length;i++){
        	if(i!=0)
        		grid[i][0]+=grid[i-1][0];
        	for(int j=1;j<grid[0].length;j++){
        		if(i==0)
        			grid[i][j]+=grid[i][j-1];
        		else
        			grid[i][j]+=grid[i-1][j]<grid[i][j-1]?grid[i-1][j]:grid[i][j-1];
        	}
        }
        return grid[grid.length-1][grid[0].length-1];
    }
	public static void main(String args[]){
		int[][] grid = {
			{0,2,3,4},
			{2,0,7,5},
			{3,4,7,6}
		};
		System.out.println(minPathSum(grid));
	}
}