import java.util.*;
/**
 * 2016-4-4 此题不错!做对甚好!唯憾代码结构不好!
 *
 * 这道题的想法就是,双指针i,j游走,[i,j]表示当前的窗口,当窗口中包括了所有t中字符时,就是一个合法解,最后返回最短的窗口.
 * 使用hash存储t中所有字符,注意t中字符是可以重复的,因此需要计数.hash[c]表示窗口中目前缺少hash[c]个c.
 * setSize==t.length()时表示窗口包括了所有t中字符,此时i向前;setSize!=t.length()时j向前.
 * 移动i将字符踢出窗口,移动j将字符加入窗口,踢出与加入都只考虑t中字符.
 * 如果踢出字符c导致窗口缺少c,setSize--;如果加入c导致不再缺少c,则setSize++;如果未导致缺少或不再缺少,则setSize不变.
 * 
 * 为了更直观好理解,我曾经先扫描一遍将所有s中属于t的字符按序加入新字符串tmp中,并保存了tmp.charAt(i)在s中的索引.
 * 理论上仍是O(n)的,但实际就过不了了.测试复杂度应该用足够大的case,显然leeccode没这么做,可能如果case太大即使正确解也要跑很久,它不能接受.
 */
public class Main{
    public String minWindow(String s, String t) {
        int[] hash = new int[200];//ascii字符,'a'存在hash['a']
        for(int i=65;i<hash.length;i++)
            hash[i]=0-s.length()-1;//区分有效桶和无效桶
        for(int i=0;i<t.length();i++){
            if(hash[t.charAt(i)]<0)
                hash[t.charAt(i)]=1;
            else
                hash[t.charAt(i)]++;
        }
        if(s==null||t==null||s.length()<t.length())
            return "";
        int setSize = 0;
        int i = 0;
        int j = 0;
        if(hash[s.charAt(i)]-->0)
            setSize++;
        int left = 0;
        int right = left+s.length()+1;
        while(j<s.length()){
            //System.out.println(i+" "+j+" "+" "+setSize+" "+hash[s.charAt(i)]+" "+hash[s.charAt(j)]);
            if(setSize!=t.length()){
                j++;
                while(j<s.length()&&hash[s.charAt(j)]==0-s.length()-1)
                    j++;
                if(j==s.length())
                    break;
                if(hash[s.charAt(j)]-->0)
                    setSize++;
            }
            else{
                if(j-i<right-left){
                    right=j;
                    left=i;
                }
                if(hash[s.charAt(i)]++==0)
                    setSize--;
                i++;
                while(i<=j&&hash[s.charAt(i)]==0-s.length()-1)
                    i++;
            }
        }
        if(right-left>=s.length()+1)
            return "";
        return s.substring(left,right+1);
    }
    public static void main(String args[]){
        String s = "bba";
        String t = "ab";
        System.out.println(new Main().minWindow(s,t));
    }
}