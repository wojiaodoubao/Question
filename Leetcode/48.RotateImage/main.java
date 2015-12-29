import java.util.*;
/**
 * 2016-1-1
 * 新年快乐～好快啊，16年了
 */
public class main{
    public static void rotate(int[][] matrix) {
        //(i,j)->(j,n-1-i) (n-1-j,i)->(i,j)
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
        	return;
        int n = matrix.length;
        //1/4
        for(int i=0;i<matrix.length/2;i++){
        	for(int j=0;j<matrix[0].length/2;j++){
        		int tmp = matrix[i][j];
        		matrix[i][j] = matrix[n-1-j][i];
        		matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
        		matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
        		matrix[j][n-1-i] = tmp;
        	}
        }
        //crossing
        if(n%2>0){
        	int mid = matrix.length/2;
            for(int i=0;i<matrix.length/2;i++){
                int tmp = matrix[i][mid];
                matrix[i][mid] = matrix[n-1-mid][i];
                matrix[n-1-mid][i] = matrix[n-1-i][n-1-mid];
                matrix[n-1-i][n-1-mid] = matrix[mid][n-1-i];
                matrix[mid][n-1-i] = tmp;             
            }
        }
    }	    
	public static void main(String args[]){
		int[][] matrix = {
						  {1,   2,  3,60},
						  {4,   5,  6,61},
						  {7,   8,  9,62},
						  {10,11,12,63}
						};
		rotate(matrix);
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}
	}
}