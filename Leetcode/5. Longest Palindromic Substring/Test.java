/**
 * 2018-12-7
 * p[i][j] = 0 -> not palindromic.
 * p[i][j] > 0 -> all the same palindromic and the character is p[i][j], which is s.charAt(i) too.
 * p[i][j] < 0 -> palindromic but not all the same.
 *
 * 首先证明，如果字符串p是palindromic but not all the same，那么给p的左或右增加任何字符都得不到palindromic字符串：
 * 1. p的长度为1和2时显然成立；
 * 2. p的长度大于等于3时，一定满足能找到i!=j使得p[i]==p[j]且p[i+1] == p[j-1]且p[i]!=p[i+1]；
 * 假设在p的右侧加入p[n]后得到一个回文序列，那么一定有p[0]==p[n]，p[1]==p[n-1]，...，p[i]==p[j+1]，...
 * 则p[j]==p[j+1]==p[i]，与题设矛盾，故假设不成立；同理得证加载左侧也不成立；
 */
public class Test {

  public String longestPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    int[][] palindromic = new int[s.length()][s.length()];  
    for (int i=0;i<s.length();i++) {
      palindromic[i][i] = s.charAt(i);
    }
    int left = 0;
    int right = 0;
    for (int len=1;len<s.length();len++) {
      for (int i=0;i<s.length()-len;i++) {
        int j = i + len;
        if (palindromic[i][j-1] > 0 && palindromic[i][j-1] == s.charAt(j)) { // all the same
          palindromic[i][j] = s.charAt(j);
          if (right - left < len) {
          	left = i;
          	right = j;
          }
        } else if (len > 1 && palindromic[i+1][j-1] != 0 && s.charAt(i) == s.charAt(j)) { // check [i+1,j-1] and i,j
          palindromic[i][j] = -1;
          if (right - left < len) {
          	left = i;
          	right = j;
          }          
        } else { // not palindromic
          palindromic[i][j] = 0;
        }
      }
    }
    return s.substring(left, right+1);
  }

  public static void main(String args[]) {
    System.out.println(new Test().longestPalindrome("babad"));
    System.out.println(new Test().longestPalindrome("cbbd"));
    System.out.println(new Test().longestPalindrome("aaaaaa"));
    System.out.println(new Test().longestPalindrome("caba"));
    System.out.println(new Test().longestPalindrome(""));
  }
}