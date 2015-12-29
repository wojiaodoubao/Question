import java.util.*;
/**
 * 2016-4-1
 * 今天愚人节真的是有点鱼啊!
 * 上午计算理论就不顺,下午做点题还各种SB错误,可能是今天起太早了...
 */
public class Main{
    public boolean isPalindrome(String s) {
        if(s==null||s.length()<=0)
            return true;
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            if(i<s.length()&&!isalpha(s.charAt(i))&&!isnumeric(s.charAt(i)))
                i++;
            else if(j>-1&&!isalpha(s.charAt(j))&&!isnumeric(s.charAt(j)))
                j--;
            else if((isnumeric(s.charAt(i))&&!isnumeric(s.charAt(j)))
                ||(!isnumeric(s.charAt(i))&&isnumeric(s.charAt(j))))
                return false;
            else if(s.charAt(i)!=s.charAt(j)&&s.charAt(i)+32!=s.charAt(j)&&s.charAt(i)-32!=s.charAt(j))
                return false;
            else{
                i++;
                j--;
            }
        }
        return true;
    }  
    boolean isalpha(char c){
        if(c>=65&&c<=90)
            return true;
        if(c>=97&&c<=122)
            return true;
        return false;
    }  
    boolean isnumeric(char c){
        if(c>=48&&c<=57)
            return true;
        return false;
    }
    public static void main(String args[]){
        System.out.println(new Main().isPalindrome("0P"));
    }
}