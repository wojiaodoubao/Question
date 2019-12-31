import java.util.*;

class Solution { 
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int[][] dp = new int[nums.length][2];
        dp[nums.length-1][0] = -1;
        dp[nums.length-1][1] = 1;
        int max = 1;
        int index = nums.length-1;
        for (int i=nums.length-2;i>=0;i--) {
            dp[i][0] = -1;
            dp[i][1] = 1;
            for (int j=nums.length-1;j>i;j--) {
                if (nums[j]%nums[i]==0) {
                    if (dp[j][1]+1>dp[i][1]) {
                        dp[i][1] = dp[j][1] + 1;
                        dp[i][0] = j;
                        if (dp[i][1]>max) {
                            max = dp[i][1];
                            index = i;
                        }
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = dp[index][0];
        }
        return res;
    }

    public static void main(String args[]) {
        int[] input = new int[args.length];
        for (int i=0;i<input.length;i++) {
            input[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Solution().largestDivisibleSubset(input));
    }
}