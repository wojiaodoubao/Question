import java.util.*;
/**
 * 2016-3-29
 */
public class Main{
    public int maxProfit(int[] prices) {
        int sum = 0;
        int current = -1;
        for(int i=0;i<prices.length-1;i++){
            if(prices[i]<prices[i+1]){
                if(current==-1)
                    current = prices[i];
            }
            else if(prices[i]>prices[i+1]){
                if(current!=-1){
                    sum+=prices[i]-current;
                    current = -1;
                }
            }
        }
        if(current!=-1&&current<prices[prices.length-1])
            sum+=prices[prices.length-1]-current;
        return sum;
    }
    public static void main(String args[]){
        int[] prices = {4,5,3,4,1,2};
        System.out.println(new Main().maxProfit(prices));
    }
}