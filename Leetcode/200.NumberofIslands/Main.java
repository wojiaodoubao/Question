import java.util.*;
/**
 * 2016-9-3
 * 深搜
 * 深搜广搜不是很熟练，以前遇到这样的给个矩阵去搜的题就马爪，还要继续练
 */
public class Main{
    public int numIslands(char[][] grid) {
    	int result = 0;
    	for(int i=0;i<grid.length;i++){
    		for(int j=0;j<grid[0].length;j++){
    			if(grid[i][j]=='1'){
    				result++;
    				dfs(i,j,grid);
    			}
    		}
    	}
    	return result;
    }	
    private void dfs(int row,int col,char[][] grid){
    	grid[row][col]='2';
    	if(col>0&&grid[row][col-1]=='1')
    		dfs(row,col-1,grid);
    	if(col<grid[0].length-1&&grid[row][col+1]=='1')
    		dfs(row,col+1,grid);
    	if(row>0&&grid[row-1][col]=='1')
    		dfs(row-1,col,grid);
    	if(row<grid.length-1&&grid[row+1][col]=='1')
    		dfs(row+1,col,grid);
    	return;
    }
	public static void main(String args[]){
		Main main = new Main();
		char[][] grid = {
			{'1','1','1','1','0'},
			{'1','1','0','1','0'},
			{'1','1','0','0','0'},
			{'0','0','0','0','0'}
		};
		System.out.println(main.numIslands(grid));
	}
}