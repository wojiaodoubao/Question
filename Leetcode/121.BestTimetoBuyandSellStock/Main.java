import java.util.*;
/**
 * 2016-3-29
 * 说是动态规划也行,但只需要O(1)额外空间
 */
public class Main{
    public int maxProfit(int[] prices) {
        int min = -1;
        int maxProfit = 0;
        for(int i=0;i<prices.length;i++){
            if(min==-1){
                min = prices[i];
                continue;
            }
            if(prices[i]>min){
                if(maxProfit<prices[i]-min)
                    maxProfit = prices[i]-min;
            }
            else{
                min=prices[i];
            }
        }
        return maxProfit;
    }
    public static void main(String args[]){
        int[] prices = {5,4,2,1,3};
        System.out.println(new Main().maxProfit(prices));
    }
}