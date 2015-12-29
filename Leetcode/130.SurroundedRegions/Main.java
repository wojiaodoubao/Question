import java.util.*;
/**
 * 2016-9-3
 * 题不难，但是测试时间设置的好尴尬
 * 就是先对外围'O'搜索并标记'F'，再全遍历一遍把'F'改'O'其余都改'X'
 * 搜索先dfs用递归实现的，stackoverflow
 * 于是改bfs，用LinkedList做队列，死活就是超时
 * 最后用LinkedList做栈实现dfs，就过了
 * dfs和bfs应该一样的啊，是我的问题？
 */
public class Main{
    public void solve(char[][] board) {
    	if(board==null||board.length<=1)
    		return;
    	for(int i=0;i<board.length;i++){
    		for(int j=0;j<board[0].length;j++){
    			if(i>0&&i<board.length-1&&j>0&&j<board[0].length-1)
    				continue;
    			if(board[i][j]=='O')
    				dfs(i,j,board);
    		}
    	}
    	for(int i=0;i<board.length;i++){
    		for(int j=0;j<board[0].length;j++){
    			if(board[i][j]=='F')
    				board[i][j]='O';
    			else
    				board[i][j]='X';
    		}
    	}
    }	
    private class Coordination{
    	int row;
    	int col;
    	public Coordination(int row,int col){
    		this.row = row;
    		this.col = col;
    	}
    }
    private void dfs(int row,int col,char[][] board){
    	LinkedList<Coordination> stack = new LinkedList<Coordination>();
    	stack.add(new Coordination(row,col));
    	while(!stack.isEmpty()){
    		Coordination cor = stack.removeLast();
    		row = cor.row;col = cor.col;
    		board[row][col]='F';
	     	if(row>0&&board[row-1][col]=='O')
	    		stack.add(new Coordination(row-1,col));
	    	if(row<board.length-1&&board[row+1][col]=='O')
	    		stack.add(new Coordination(row+1,col));
	    	if(col>0&&board[row][col-1]=='O')
	    		stack.add(new Coordination(row,col-1));
	    	if(col<board[0].length-1&&board[row][col+1]=='O')
	    		stack.add(new Coordination(row,col+1));
    	}
    }   
	public static void main(String args[]){
		char[][] board = {
			{'X','X','X','X'},
			{'X','O','O','X'},
			{'X','X','O','X'},
			{'X','O','X','X'}
		};
		// char[][] board = {{'X'}};
		new Main().solve(board);
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++)
				System.out.print(board[i][j]+" ");
			System.out.println();
		}
	}
}