import java.util.*;
/**
 * 2016-1-22【难】
 * 此题很妙，没想出来，参考:http://www.cnblogs.com/higerzhang/p/4107268.html
 * 说明思路：
 * 核心就是："为每一个元素i计算其向左向右扩展所能形成的矩形的面积，并取其中最大的。"
 * \枚举是显而易见的方法，我其实并没有意识到核心是上面，还一直想DP。
 * 例子1：2 1 5 6 2 3
 * 例子2：7 6 5 4 3 0
 * 1.使用栈存储一个高度递增的索引，比如0,1 2 3,1 4 5。
 * 2.i是索引，i指向的元素比栈顶指向的高就i入栈(因为仍能保证栈递增)，否则就退栈直到栈顶指向元素比i指向的元素矮。
 * 3.每次退栈都计算一次面积，left表示heights[stack.index]所能达到的最左端，它=stack.next.index;i-1表示它能达到的最右端。
 * 
 * 简单分析可知，最终结果矩形的高度必定是heights中的某一个值，比如例子2就是：0,3,4,5,6,7中某一个。
 * 栈的结构保证了：[stack.next.index+1,stack.index)，stack.index，(stack.index,i-1]，中每个元素都>=heights[stack.index]，
 * \即[stack.next.index+1,i-1]中每个元素都>=heights[stack.index]。(除了stack.index之外都是被退栈而出的，所以比stack.index高)
 * 整个算法保证了，对于[0,n-1]每一个索引i,其高度heights[i]向左向右的扩展而形成的矩形面积都被计算了。
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
	private static Stack stack;
    public static int largestRectangleArea(int[] heights) {
        if(heights==null || heights.length<=0)
        	return 0;
        int result = 0;
        stack = new Stack(0,null);//push 0
        for(int i=1;i<=heights.length;i++){
        	int h = i==heights.length?-1:heights[i];//h=-1 while i==heights.length
        	while(stack!=null && heights[stack.index]>h){
        		int left = stack.next==null?0:stack.next.index+1;
        		if(heights[stack.index]*(i-left)>result)//pop index
        			result = heights[stack.index]*(i-left);
        		stack = stack.next;
        	}
        	stack = new Stack(i,stack);//push i
        }
        return result;
    }
	public static void main(String args[]){
		//int[] heights = {7,6,5,4,3,0};
		int[] heights = {1,2,3,4,5,6};
		//int[] heights = {2,1,5,6,2,3};
		System.out.println(largestRectangleArea(heights));
	}
}