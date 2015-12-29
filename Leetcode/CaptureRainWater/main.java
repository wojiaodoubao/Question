import java.util.*;
/**
 * 2015-12-29
 */
public class main
{
	public static int trap(int[] height) {
        int length = height.length;
        if(length==0)return 0;
        //init&cal
        int flow = 0;
        int maxH = 0;
        for(int i=0;i<length;i++)
        {
        	if(height[i]>maxH){
                flow += (i-0)*(height[i]-maxH);
        		maxH = height[i];
            }
        }
        maxH = 0;
        for(int i=length-1;i>=0;i--)
        {
        	if(height[i]>maxH){
                flow += (length-1-i)*(height[i]-maxH);
        		maxH = height[i];
            }
        }
        for(int i : height){
            flow += i;
        }
        int sum = maxH * length - flow;
        return sum;
    }
	public static void main(String args[]){
		int[] length = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(trap(length));
	}
}