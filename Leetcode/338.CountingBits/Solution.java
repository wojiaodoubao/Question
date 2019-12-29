import java.util.*;

class Solution {    
    public int[] countBits(int num) {
        if (num < 0) {
            return null;
        }
        if (num == 0) {
            return new int[]{0};
        }
        int[] res = new int[num+1];
        res[1] = 1;
        for (int i=2;i<res.length;) {
            for (int j=0;j<i && i+j<res.length;j++) {
                res[i+j] = 1 + res[j];
            }
            i = i * 2;
        }
        return res;
    }

    public static void main(String args[]) {
        int[] res = new Solution().countBits(Integer.parseInt(args[0]));
        for (int i : res) {
            System.out.print(i + ",");
        }
    }
}