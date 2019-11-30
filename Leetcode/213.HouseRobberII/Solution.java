class Solution {
    public int rob(int[] nums) {
    	if (nums.length == 0) {
    		return 0;
    	}
        int max = nums[0];
        int lastRob = 0;
        int lastSkip = 0;
        // choose the first house.
        lastRob = nums[0];
        lastSkip = -1;
        for (int i=1; i<nums.length-1; i++) {
        	int skip = lastRob >= 0 ? Math.max(lastRob,lastSkip) : 0;
        	int rob = lastSkip >= 0 ? lastSkip + nums[i] : 0;
        	lastSkip = skip;
        	lastRob = rob;
   			max = Math.max(lastSkip, max);
   			max = Math.max(lastRob, max);
        }
        // skip the first house.
        lastRob = 0;
        lastSkip = 0;
        for (int i=1; i<nums.length; i++) {
        	int skip = lastRob >= 0 ? Math.max(lastRob,lastSkip) : 0;
        	int rob = lastSkip >= 0 ? lastSkip + nums[i] : 0;
        	lastSkip = skip;
        	lastRob = rob;
   			max = Math.max(lastSkip, max);
   			max = Math.max(lastRob, max);
        }
        return max;      
    }

    public static void main(String args[]) {
    	int[] nums = new int[args.length];
    	for (int i=0;i<nums.length;i++) {
    		nums[i] = Integer.parseInt(args[i]);
    	}
    	System.out.println(new Solution().rob(nums));
    }
}