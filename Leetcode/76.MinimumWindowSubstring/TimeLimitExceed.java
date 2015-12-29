import java.util.*;
/**
 * 2016-4-3
 * 超时!
 * 想法是先把s里的t都按顺序摘出来存到tmp,index_tmp[i]存的是tmp[i]在s中的索引
 * 两指针扫描tmp,setSize表示当前集合的大小,等于t.length()时表示找到了一个解
 * 前指针每次找到解了就+1,后指针每次找不到解就+1
 *
 * 2016-4-4
 * 现在不超时了
 * 之前为了更直观好理解,我先扫描一遍将所有s中属于t的字符按序加入新字符串tmp中,并保存了tmp.charAt(i)在s中的索引.
 * 是因为tmp导致了超时,去掉后就好了.Main.java的解法改成带tmp的也过不了,因此和Hash算法没关系.
 * 理论上仍是O(n)的,但实际就过不了了.测试复杂度应该用足够大的case,显然leeccode没这么做,可能如果case太大即使正确解也要跑很久,它不能接受.
 * 我还以为是Java的HashMap有问题,是我多虑了.HashMap的实现见Java-B,可见其没有效率问题.
 * 
 *
 */
public class TimeLimitExceed{
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> hash = new HashMap<Character,Integer>();
        for(int i=0;i<t.length();i++){
            Integer tmp = hash.get(t.charAt(i));
            if(tmp==null)
                hash.put(t.charAt(i),1);
            else
                hash.put(t.charAt(i),tmp+1);
        }
        if(s==null||t==null||s.length()<t.length())
            return "";
        int setSize = 0;
        int i = 0;
        int j = 0;
        Integer tmp = hash.get(s.charAt(i));
        if(tmp!=null){
            if(tmp-->0)
                setSize++;
            hash.put(s.charAt(i),tmp);
        }
        int left = 0;
        int right = left+s.length()+1;
        while(j<s.length()){
            //System.out.println(i+" "+j+" "+setSize+" "+hash.get(s.charAt(i))+" "+hash.get(s.charAt(j)));
            if(setSize!=t.length()){
                j++;
                while(j<s.length()&&hash.get(s.charAt(j))==null)
                    j++;
                if(j==s.length())
                    break;
                tmp = hash.get(s.charAt(j));
                if(tmp-->0)
                    setSize++;
                hash.put(s.charAt(j),tmp);
            }
            else{
                if(j-i<right-left){
                    right=j;
                    left=i;
                }
                tmp = hash.get(s.charAt(i));
                if(tmp!=null){
                    if(tmp++==0)
                        setSize--;
                    hash.put(s.charAt(i),tmp);
                }                
                i++;
                while(i<=j&&hash.get(s.charAt(i))==null)
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
        System.out.println(new TimeLimitExceed().minWindow(s,t));
    }
}