import java.util.*;
/**
 * 2016-1-23【难】
 * 此题没想出来，跟84有联系，参考(同一个人写的):http://www.cnblogs.com/higerzhang/p/4109278.html
 * 思路：此题就是逐行处理，每一行其相当于是一个84中的问题，其中每一行高度的求法见函数:getHeight()
 * 求高度可以看做是DP，所以有个DP标签。最精妙的部分还是84的思想，用栈实现。
 */
class Stack{
	public int index;
	public Stack next;
	public Stack(int index,Stack next){
		this.index = index;
		this.next = next;
	}
}
public class Main{
	public static int maximalRectangle(char[][] matrix){
		if(matrix==null || matrix.length<=0 || matrix[0].length<=0)
			return 0;
		int I = matrix.length;
		int J = matrix[0].length;
		int[] heights = new int[J+1];
		int result = 0;
		for(int i=0;i<I;i++){
			Stack stack = new Stack(0,null);
			for(int j=1;j<=J;j++){
				int h = getHeight(i,j,matrix,heights,J);
				while(stack!=null){
					int h_stack = getHeight(i,stack.index,matrix,heights,J);
					if(h_stack<=h)
						break;
					int left = stack.next==null?0:stack.next.index+1;
					result = (j-left)*h_stack>result?(j-left)*h_stack:result;
					heights[stack.index] = h_stack;//heights存储上一层的高度，当stack.index退栈后stack.index成为上一层元素
					stack = stack.next;//pop
				}
				stack = new Stack(j,stack);
			}	
		}
		return result;
	}
	private static int getHeight(int i,int j,char[][] matrix,int[] heights,int J){
		if(j==J)
			return -1;
		if(matrix[i][j]=='0')
			return 0;
		else
			return i==0?1:1+heights[j];
	}
	public static void main(String args[]){
		char[][] matrix={
			{'1','1','1','1','1'},
			{'0','0','1','1','1'},
			{'1','1','1','1','1'}
		};
		System.out.println(maximalRectangle(matrix));
	}
}