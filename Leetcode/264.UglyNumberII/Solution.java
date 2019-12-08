class Solution {
    public int nthUglyNumber(int n) {
        int n2 = 0;// 上一个没乘2的ugly数
        int n3 = 0;// 上一个没乘3的ugly数
        int n5 = 0;// 上一个没乘5的ugly数
        int[] ugly = new int[n];// 全部ugly数
        ugly[0] = 1;
        for (int i=1;i<n;i++) {
            ugly[i] = Math.min(Math.min(ugly[n2]*2,ugly[n3]*3), ugly[n5]*5);
            if (ugly[n2]*2 == ugly[i]) n2++;
            if (ugly[n3]*3 == ugly[i]) n3++;
            if (ugly[n5]*5 == ugly[i]) n5++;
        }
        return ugly[n-1];
    }
    public static void main(String args[]) {
    	System.out.println(new Solution().nthUglyNumber(Integer.parseInt(args[0])));
    }
}