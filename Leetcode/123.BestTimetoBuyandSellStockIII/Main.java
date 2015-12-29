import java.util.*;
/**
 * 2016-3-30
 * DP,扫描了两遍,第一遍求S[i]表示[0,i]天最大收益,第二遍求T[j,N-1]天最大收益和整体最大收益
 */
public class Main{
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=0)
            return 0;
        int[] S = new int[prices.length];
        int min = -1;
        int profit = 0;
        for(int i=0;i<prices.length;i++){
            if(min==-1)
                min=prices[i];
            else if(prices[i]-min>profit)
                profit=prices[i]-min;
            else if(min>prices[i])
                min=prices[i];
            S[i] = profit;
        }
        int max = -1;
        profit = 0;
        int result = S[S.length-1];
        for(int i=prices.length-1;i>0;i--){
            if(max==-1)
                max=prices[i];
            else if(max-prices[i]>profit)
                profit=max-prices[i];
            else if(prices[i]>max)
                max=prices[i];
            if(S[i-1]+profit>result)
                result = S[i-1]+profit;
        }
        //不需要处理i==0的情况,因为i==0的情况就同S[S.length-1]
        return result;
    }    
    public static void main(String args[]){
        int[] prices = {1};
        System.out.println(new Main().maxProfit(prices));
    }
}