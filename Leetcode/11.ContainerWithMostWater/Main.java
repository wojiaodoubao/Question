import java.util.*;
/**
 * 2016-4-5
 * 这题很不错! 可惜没想出来,看的题解
 * 核心思想是:i,j停留处,表示i左j右的板都既低于height[i]也低于height[j],因此此时(j-i)是h这个高度能达到的最长距离
 * 初始时i=0,j=height.length,显然正确.之后每次移动较低的那个,直到其变高停止.(如果中途i==j则退出)
 */
public class Main{
    public int maxArea(int[] height) {
        if(height==null||height.length<2)
            return 0;
        int i=0;
        int j=height.length-1;
        int result=0;
        while(i<j){
            int h = height[i]<height[j]?height[i]:height[j];
            result = result>h*(j-i)?result:h*(j-i);
            if(height[i]<height[j]){
                int p=i+1;
                while(p<j&&height[p]<=height[i])
                    p++;
                i=p;
            }
            else{
                int p=j-1;
                while(i<p&&height[p]<=height[j])
                    p--;
                j=p;
            }
        }
        return result;
    }    
    public static void main(String args[]){
        int[] height = {1,2,3,100,100,3,1,6};
        System.out.println(new Main().maxArea(height));
    }
}