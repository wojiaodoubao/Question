import java.util.*;
/**
 * 2015-12-31
 * 使用常量额外空间
 * 抄
 */
public class main{
	public static void setZeroes(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
        	return;
        boolean row = false;
        boolean column = false;
        for(int i=0;i<matrix.length;i++){
        	for(int j=0;j<matrix[0].length;j++){
        		if(matrix[i][j]==0){
        			if(i==0)
        				row = true;
        			if(j==0)
        				column = true;
        			if(i!=0 && j!=0){
        				matrix[0][j]=0;
        				matrix[i][0]=0;
        			}
        		}
        	}
        }
        for(int i=matrix.length-1;i>=0;i--){
        	for(int j=matrix[0].length-1;j>=0;j--){
        		if((i==0 && row) || (j==0 && column) || matrix[i][0]==0 || matrix[0][j]==0){
        			matrix[i][j]=0;
        		}
        	}
        }
    }
	public static void main(String args[]){
		int[][] matrix = {
			{0,2,0},
			{1,2,3},
			{2,3,4}
		};
		setZeroes(matrix);
        for(int i=0;i<matrix.length;i++){
        	for(int j=0;j<matrix[0].length;j++){
				System.out.print(""+matrix[i][j]);
			}
			System.out.println("");
		}
	}
}