import java.util.*;
/**
 * 和264相同的解法，通过int[] dp = new int[coins.length];来按照从小到大的顺序生成所有合法钱值；
 * values表示所有可以组合到的价值(也可以叫ugly number)，minCoins表示达到这个值需要最少多少coin；
 *
 * 通过dp的生成原理是：
 * 1. 0是value
 * 2. 所有的value+任意coin得到的仍是value
 * 
 * 从0开始，对每一个value添加每一个coin就能得到所有value；所以反过来把coin加在每一个value上也能得到所有value；
 * 从coin的角度看，dp[i]表示coin[i]目前已经加到哪个value了；初始每个dp[i]都是0；
 * 当要计算next value的时候，只要取min{values.get(dp[i])+coin[i]}就可以了。
 */
class Solution {    
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[coins.length];
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Integer> minCoins = new ArrayList<>();
        values.add(0);
        minCoins.add(0);

        while (values.get(values.size()-1) < amount) {
            int next = Integer.MAX_VALUE;
            for (int i=0;i<coins.length;i++) {
                int v = coins[i] + values.get(dp[i]);
                if (v < next) {
                    next = v;
                }
            }
            values.add(next);
            minCoins.add(Integer.MAX_VALUE);
            for (int i=0;i<coins.length;i++) {
                int v = coins[i] + values.get(dp[i]);
                if (v == next) {
                    if (minCoins.get(dp[i])+1 < minCoins.get(minCoins.size()-1)) {
                        minCoins.set(minCoins.size()-1, minCoins.get(dp[i])+1);
                    }
                    dp[i] = dp[i]+1;
                }
            }
        }
        if (values.get(values.size()-1) > amount) {
            return -1;
        } else {
            return minCoins.get(minCoins.size()-1);
        }
    }

    public static void main(String args[]) {
        int[] coin = new int[args.length-1];
        for (int i=0;i<coin.length;i++) {
            coin[i] = Integer.parseInt(args[i]);
        }
        int amount = Integer.parseInt(args[args.length-1]);
        System.out.println(new Solution().coinChange(coin, amount));
    }
}