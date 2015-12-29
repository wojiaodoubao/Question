import java.util.*;
/**
 * 2016-4-5
 * 使用KMP实现
 * 都有点忘了KMP了
 */
public class Main{
    public int strStr(String haystack, String needle) {
        if(haystack==null||needle==null||haystack.length()<needle.length())
            return -1;
        if(needle.length()<=0)
            return 0;
        int[] match=new int[needle.length()];
        match[0]=0;
        int i=-1;
        int j=1;
        //create match
        for(j=1;j<needle.length();j++){
            while(i>-1&&needle.charAt(i+1)!=needle.charAt(j))
                i=match[i]-1;
            if(needle.charAt(i+1)==needle.charAt(j))
                i++;
            match[j]=i+1;
        }
        //scan
        i=0;j=0;
        while(i<haystack.length()&&j<needle.length()){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
            }
            else if(j==0)
                i++;
            else
                j=match[j-1];
        }
        if(j==needle.length())
            return i-j;
        return -1;
    }    
    public static void main(String args[]){
        System.out.println(new Main().strStr("","e"));
    }
}