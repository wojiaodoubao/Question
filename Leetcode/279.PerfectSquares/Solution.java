import java.util.*;
/**
 * 2016-9-2
 * 直接看答案
 * 参考:http://www.cnblogs.com/grandyang/p/4800552.html
 * 参考:https://discuss.leetcode.com/topic/24255/summary-of-4-different-solutions-bfs-dp-static-dp-and-mathematics/2
 * 有数学方法，O(n*开平方)；动态规划O(n^2)；
 */
public class Solution{
    public int numSquares(int n) {
        return dp1(n);
        //return dp2(n);
        //return math(n);
    }
    public int dp1(int n){//好理解
    	int[] dp = new int[n+1];
    	dp[1]=1;
    	for(int i=2;i<=n;i++){
    		for(int j=1;j*j<=i;j++){
    			dp[i] = dp[i]==0?dp[i-j*j]+1:min(dp[i-j*j]+1,dp[i]);
    		}
    	}
    	return dp[n];
    }
    public int dp2(int n){//一般不这么写
    	int[] dp = new int[n+1];
    	for(int i=1;i<=n;i++)
    		dp[i]=Integer.MAX_VALUE;
    	for(int i=0;i<=n;i++){
    		for(int j=1;j*j+i<=n;j++){
    			dp[i+j*j]=min(dp[i]+1,dp[i+j*j]);
    		}
    	}
    	return dp[n];
    }
    public int math(int n){
    	while(n%4==0)
    		n=n/4;
    	if(n%8==7)
    		return 4;
    	for(int i=0;i*i<=n;i++){
    		int b = (int)Math.sqrt(n-i*i);
    		if(i*i+b*b==n)
    			return i==0&&b==0?0:i!=0&&b!=0?2:1;
    	}
    	return 3;
    }
    private int min(int a,int b){
    	return a<b?a:b;
    }
    public static void main(String args[]){
    	Solution solution = new Solution();
    	System.out.println(solution.numSquares(12));
    	System.out.println(solution.numSquares(13));
    	System.out.println(solution.numSquares(16));
    }
}