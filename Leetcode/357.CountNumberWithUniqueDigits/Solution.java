import java.util.*;

class Solution { 
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        } else if (n < 11) {
            int positive = 1;
            for (int i=0;i<n;i++) {
                if (i == 0) {
                    positive *= 9;
                } else {
                    positive *= (10-i);
                }
            }
            return positive + countNumbersWithUniqueDigits(n-1);        
        } else {
            return countNumbersWithUniqueDigits(10);
        }
    }

    public static void main(String args[]) {
        System.out.println(new Solution().countNumbersWithUniqueDigits(Integer.parseInt(args[0])));
    }
}