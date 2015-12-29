import java.util.*;
/**
 * 2016-3-14
 */
public class Main{
    public void gameOfLife(int[][] board) {
    	if(board==null || board.length<=0 || board[0].length<=0)
    		return;
        final int Height = board.length;
        final int Width = board[0].length;
        //0-dead 1-alive 3-dead2alive 4-alive2dead
        for(int x=0;x<Height;x++){
        	for(int y=0;y<Width;y++){
        		int count = countNeighbour(board,x,y);
        		if(count<2&&board[x][y]==1)
        			board[x][y]=4;
        		else if(count>=2&&count<=3&&board[x][y]==1)
        			continue;
        		else if(count>3&&board[x][y]==1)
        			board[x][y]=4;
        		else if(count==3&&board[x][y]==0)
        			board[x][y]=3;
        	}
        }
        //clear
        for(int x=0;x<Height;x++){
        	for(int y=0;y<Width;y++){
        		if(board[x][y]==4)
        			board[x][y]=0;
        		if(board[x][y]==3)
        			board[x][y]=1;
        	}
        }
    }
    public int countNeighbour(int[][] board,int x,int y){
        final int Height = board.length;
        final int Width = board[0].length;    	
    	int count = 0;
    	if(x-1>=0&&y-1>=0&&(board[x-1][y-1]==1||board[x-1][y-1]==4))
    		count++;
    	if(x-1>=0&&(board[x-1][y]==1||board[x-1][y]==4))
    		count++;
    	if(x-1>=0&&y+1<Width&&(board[x-1][y+1]==1||board[x-1][y+1]==4))
    		count++;
    	if(y-1>=0&&(board[x][y-1]==1||board[x][y-1]==4))
    		count++;
    	if(y+1<Width&&(board[x][y+1]==1||board[x][y+1]==4))
    		count++;
    	if(x+1<Height&&y-1>=0&&(board[x+1][y-1]==1||board[x+1][y-1]==4))
    		count++;
    	if(x+1<Height&&(board[x+1][y]==1||board[x+1][y]==4))
    		count++;
    	if(x+1<Height&&y+1<Width&&(board[x+1][y+1]==1||board[x+1][y+1]==4))
    		count++;
    	return count;
    }
	public static void main(String args[]){
		Main main = new Main();
		int[][] board = {
			{0,0,1,1},
			{1,1,0,0},
			{1,1,1,1}	
		};
		main.gameOfLife(board);
		for(int x=0;x<board.length;x++){
			for(int y=0;y<board[0].length;y++)
				System.out.print(board[x][y]+" ");
			System.out.println();
		}
	}
}