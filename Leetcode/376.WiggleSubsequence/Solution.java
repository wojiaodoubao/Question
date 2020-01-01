import java.util.*;

class Solution { 
    
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[][] dp = new int[nums.length][2];
        for (int i=0;i<nums.length;i++) {
            int[] max = new int[2];
            dp[i][0] = 1;
            dp[i][1] = 1;
            for (int j=i-1;j>=0;j--) {
                if (nums[i] > nums[j]) {
                    if (dp[j][0] + 1 > dp[i][1]) {
                        dp[i][1] = dp[j][0] + 1;
                    }
                } else if (nums[i] < nums[j]) {
                    if (dp[j][1] + 1 >  dp[i][0]) {
                        dp[i][0] = dp[j][1] + 1;
                    }
                } else {
                    if (dp[j][0] > dp[i][0]) {
                        dp[i][0] = dp[j][0];
                    }
                    if (dp[j][1] > dp[i][1]) {
                        dp[i][1] = dp[j][1];
                    }
                }
            }
        }
        int max = 1;
        for (int i=0;i<nums.length;i++) {
            if (dp[i][0] > max) {
                max = dp[i][0];
            }
            if (dp[i][1] > max) {
                max = dp[i][1];
            }
        }
        return max;
    }

    public static void main(String args[]) {
        int[] nums = new int[args.length];
        for (int i=0;i<args.length;i++) {
            nums[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Solution().wiggleMaxLength(nums));
    }
}