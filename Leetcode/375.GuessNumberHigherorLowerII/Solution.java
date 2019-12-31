import java.util.*;

class Solution { 
    
    public int getMoneyAmount(int n) {
        if (n<=0) {
            return 0;
        }
        int[][] dp = new int[n][n];
        for (int i=1;i<n;i++) {
            for(int j=0;j+i<n;j++) {
                if (i == 1) {
                    dp[i][j] = j+1;
                } else if (i == 2) {
                    dp[i][j] = j+2;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int z=j+1;z<i+j;z++) {
                        int max = z+1+Math.max(dp[z-1-j][j],dp[i+j-z-1][z+1]);
                        if (max < min) {
                            min = max;
                        }
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[n-1][0];
    }

    public static void main(String args[]) {
        System.out.println(new Solution().getMoneyAmount(Integer.parseInt(args[0])));
    }
}