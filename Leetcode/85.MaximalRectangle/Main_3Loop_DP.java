import java.util.*;
/**
 * 2016-1-22
 * 失败，空间超了
 * O(m*n*n)，暴力是O(m*m*n*n)
 * 思想:
 * S[i][j][k]表示:以点i,j为左上角向右宽k的矩形的最大面积，S[i][j][k]=S[i+1][j][k]==0?k:S[i+1][j][k]+k
 * 代码实现的时候S[i][j][k]表示:以点i,j为左上角向右宽k的矩形向下的最长长度。S[i][j][k]=S[i+1][j][k]+1
 * 
 * 网上还看到一种DP,见第二个解法:http://www.cnblogs.com/higerzhang/p/4109278.html
 */
public class Main_3Loop_DP{
    public static int maximalRectangle(char[][] matrix) {
    	if(matrix==null || matrix.length<=0 || matrix[0].length<=0)
    		return 0;
        int max = 0;
    	int I = matrix.length;
    	int J = matrix[0].length;
        int[][][] S = new int[I][J][J];
    	//init
    	S[I-1][J-1][0]=matrix[I-1][J-1]=='1'?1:0;
    	for(int j=J-2;j>=0;j--){
    		if(matrix[I-1][j]!='1'){
				S[I-1][j][0]=0;
				continue;
			}
			S[I-1][j][0]=1;
    		for(int k=1;S[I-1][j+1][k-1]==1 && k<J;k++){//其实此处k一定<J
    			S[I-1][j][k]=1;
    		}
    	}
    	//dp
    	for(int j=J-1;j>=0;j--){
    		for(int i=I-2;i>=0;i--){
                int k=0;
    			for(k=0;k<J-j&&matrix[i][j+k]=='1';k++){
                    S[i][j][k]=S[i+1][j][k]+1;
                    max=S[i][j][k]*(k+1)>max?S[i][j][k]*(k+1):max;
    			}
    		}
    	}
    	return max;
    }
	public static void main(String args[]){
		char[][] matrix={
			{'1','1','0','1','1'},
			{'0','0','1','1','1'},
			{'1','1','0','1','0'}
		};
		System.out.println(maximalRectangle(matrix));
	}
}