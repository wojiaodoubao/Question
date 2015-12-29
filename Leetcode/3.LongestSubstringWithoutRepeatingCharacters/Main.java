import java.util.*;
/**
 * 2016-4-4
 * 用和76一样的解法,[i,j]表示窗口,setSize表示窗口中字符种类数,setSize==j-i+1表示窗口是无重复的字串
 * 是无重复字串则移动j,否则移动i.移动j加字符,移动i删字符,如增删使字符种类数变化,则对应修改setSize
 */
public class Main{
    public int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()<=0)
            return 0;
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        int result = 1;
        int setSize = 1;//窗口[i,j]中字符种类数
        int i=0;
        int j=0;
        map.put(s.charAt(i),1);
        while(j<s.length()){
            System.out.println(i+" "+j+" "+setSize+" "+(setSize==j-i+1));
            if(j-i+1==setSize){//substring without repeating characters
                if(j-i+1>result)
                    result = j-i+1;
                if(++j>=s.length())
                    break;
                Integer t = map.get(s.charAt(j));
                if(t==null||t==0)
                    setSize++;
                map.put(s.charAt(j),t==null?1:t+1);
            }
            else{//substring with repeating characters
                Integer t = map.get(s.charAt(i));
                if(t==1)
                    setSize--;
                map.put(s.charAt(i),t-1);
                i++;
            }
        }
        return result;
    }    
    public static void main(String args[]){
        System.out.println(new Main().lengthOfLongestSubstring("a"));
    }    
}