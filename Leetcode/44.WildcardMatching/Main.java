import java.util.*;
/**
 * 2016-4-6
 * 开心!开心!开心! 做对啦!做对啦!做对啦!
 * 这道题做了4个小时!!! 有一种大一C语言实验三的感觉. 代码写得有点蠢,这么.......长,不过思路很棒!
 *
 * [字符串匹配的回溯,就是找pattern的前缀与s的后缀的最长匹配!!!!!!!!!!]
 * [字符串匹配的回溯,就是找pattern的前缀与s的后缀的最长匹配!!!!!!!!!!]
 * [字符串匹配的回溯,就是找pattern的前缀与s的后缀的最长匹配!!!!!!!!!!]
 * KMP中直接用pattern计算回溯数组,因为匹配成功的s部分和pattern长得一样,前缀后缀就从KMP计算就好
 * 此处因为引入了'?',使得s与pattern匹配的部分不再能从pattern中看出来,所以要用pattern前缀与s[0,i]后缀去比
 *
 * S:xxxxxxxxxx
 * P:Head*Mid[1]*Mid[2]*.....*Tail
 * 预处理:先将p中连续的*都改成一个*,所以不再考虑连续几个*的情况.
 * 1.p被"*"split为很多段,就是要保证这些段,按顺序,不重叠,都能在s中找到.
 * 2.找的过程就是贪心,子串都尽量往前匹配.
 *   首子串Head匹配s的前面,尾子串Tail匹配s的后面,首尾子串都是硬匹,即一个字符也不差的匹.
 *   中间子串Mid[i]就尽量向前匹配,中间子串的匹配是在s中找pattern的字符串匹配问题.
 *   
 *  下面代码结构没这么清晰,代码结构是:先分为p中有*和没*两种情况,没*就和s硬匹,有*就分为*之前和*之后,*之后硬匹,*之前用match()来匹
 *  在match中,从p开始到第一个*之前硬匹(此时Left==-1),之后采用KMP匹配.
 *
 * 3.改KMP. 构造KMP数组kmp[],存储p中各个点的回溯值.i一直向后,j可以回溯,每个Mid独立处理,Left标记每个Mid的最左端字符下标.(回溯只限于Mid内,不会回溯到前一个Mid,因此Left是递增的)
 * [核心:回溯值的计算]
 * 用s[i]与p[Left+k]比较,因为回溯实际是找p前缀与s后缀的最长匹配!
 * 比较成功说明s[i-k,i]与p[Left,Left+k]匹配且是最长前后缀匹配,一旦p[j+1]!=s[i+1]需要回溯,前一个回溯点就是p[Left+k+1],j=Left+k+1//程序中一旦相等,k++,kmp[j]=Left+k;
 *               //s[i] p[j]匹配成功
 *               else if(Left>=0){//match并且前面有*,此时Left有效,构造kmp[];前面无*时无需考虑kmp
 *                   if(j==Left){
 *                       k=0;
 *                       kmp[j]=Left+k;
 *                   }
 *                   else{
 *                       while(k>0&&!is_match(s.charAt(i),p.charAt(Left+k)))//k>0&&!is_match(p.charAt(j),p.charAt(Left+k))
 *                           k=kmp[Left+k-1]-Left;
 *                       if(is_match(s.charAt(i),p.charAt(Left+k)))
 *                           k++;
 *                       kmp[j]=Left+k;
 *                   }
 *                   i++;j++;
 *               }
 *               else{//match并且前面无*,此时Left无效,不构造kmp
 *                   i++;j++;
 *               }
 * 举例来说,s=abcdc,p=?b??ba,回溯值是[0][0,1][0,1,1][]||s=ababacabada,p=*a???ada*
 *  a b c d c
 *  ? b ? ? b a
 * [0]
 * [0,1]
 * [0,1,1]
 * [0,1,1,1]
 * [0,1,1,1,0]
 *
 *  a b a b a c a b a d a
 *  a ? ? ? a d a
 * [0](0,0,0)--i,j,k
 * [0,0](1,1,0)
 * [0,0,1](2,2,1)
 * [0,0,1,2](3,3,2)
 * [0,0,1,2,3](4,4,3)
 * [0,0,1,2,3](5,5,3)--不等回溯
 * [0,0,1](5,3,1)--回溯到此
 * [0,0,1,2](6,4,2)
 * [0,0,1,2,3](7,5,2)--不等回溯
 * [0,0,1](7,3,1)--回溯到此
 * [0,0,1,2](8,4,2)
 * [0,0,1,2,3](9,5,3)
 * [0,0,1,2,3,4](10,6,4)
 * [0,0,1,2,3,4,5](11,7,5)--最后11,7超过s,p的长度而退出
 *
 */
public class Main{
    public boolean isMatch(String s, String p) {
        String q = "";
        boolean has_star = false;
        for(int i=0;i<p.length();i++){
            if(!has_star&&p.charAt(i)=='*')
                has_star=true;
            q+=p.charAt(i);
            while(i<p.length()-1&&p.charAt(i)=='*'&&p.charAt(i+1)=='*')
                i++;
        }
        p=q;
        if(has_star){
            int j=p.length()-1;
            int i=s.length()-1;
            for(j=p.length()-1,i=s.length()-1;j>=0&&i>=0;j--,i--){
                if(p.charAt(j)=='*'){
                    //System.out.println("return from match");                    
                    return match(s.substring(0,i+1),p.substring(0,j+1));
                }
                else if(!is_match(p.charAt(j),s.charAt(i)))
                    return false;
            }
            //只可能因为i<0退出到这里,因为p中有*,所以j必然>=0
            if(j==0&&p.charAt(j)=='*')
                return true;
            else
                return false;
        }
        else{//no star
            if(s.length()!=p.length())
                return false;
            int i=0;
            for(i=0;i<s.length();i++){
                if(!is_match(s.charAt(i),p.charAt(i)))
                    break;
            }
            if(i==s.length())
                return true;
            else
                return false;
        }
    }
    public boolean match(String s,String p){//p ends with *
        if(s==null||p==null)
            return false;
        //改KMP
        int[] kmp = new int[p.length()];
        int i=0;
        int j=0;
        int Left=-1;//还没有第一个*
        int k=-1;
        while(i<s.length()&&j<p.length()){
            //System.out.println(i+" "+j);
            //for(int lll:kmp)
            //   System.out.print(lll+" ");
            //System.out.println();
            if(p.charAt(j)=='*'){
                Left=++j;
                //k=-1;
            }
            else{
                if(!is_match(s.charAt(i),p.charAt(j))){//not match
                    if(Left < 0)//前面没有*,硬匹,没match的也无法被*match
                        return false;
                    if(j==Left)
                        i++;
                    else{
                        j=kmp[j-1];
                        k=j>Left?kmp[j-1]-Left:0;//k也是要回溯的,很关键!
                    }                   
                }
                else if(Left>=0){//match并且前面有*,此时Left有效,构造kmp[];前面无*时无需考虑kmp
                    if(j==Left){
                        k=0;
                        kmp[j]=Left+k;
                    }
                    else{
                        while(k>0&&!is_match(s.charAt(i),p.charAt(Left+k)))//k>0&&!is_match(p.charAt(j),p.charAt(Left+k))
                            k=kmp[Left+k-1]-Left;
                        if(is_match(s.charAt(i),p.charAt(Left+k)))
                            k++;
                        kmp[j]=Left+k;
                    }
                    i++;j++;
                }
                else{//match并且前面无*,此时Left无效,不构造kmp
                    i++;j++;
                }
            }
        }
        //System.out.println(i+" "+j+" ");
        //System.out.println(s);
        //System.out.println(p);
        if(i<s.length())
            return true;
        else if(j==p.length()-1)
            return true;
        else
            return false;
    }
    public boolean is_match(char a,char b){
        if(a=='?'||b=='?')
            return true;
        else if(a==b)
            return true;
        else 
            return false;
    }
    public boolean recursion(String s,String p){//Time Limit Exceeded
        if(s.length()==0&&p.length()==0)
            return true;
        if(p.length()==0)
            return false;
        if(s.length()==0&&(p.length()>1||p.charAt(0)!='*'))
            return false;
        if(p.charAt(0)=='?')
             return recursion(s.substring(1,s.length()),p.substring(1,p.length()));           
        else if(p.charAt(0)=='*'){
            for(int i=0;i<s.length()+1;i++){
                if(recursion(s.substring(i,s.length()),p.substring(1,p.length())))
                    return true;
            }
            return false;
        }
        else if(s.charAt(0)!=p.charAt(0))
            return false;
        else
            return recursion(s.substring(1,s.length()),p.substring(1,p.length()));
    } 
    public static void main(String args[]){
        String s = "ababca";
        String p = "?b*??*ca";
        System.out.println(new Main().isMatch(s,p));         
        s = "aabcdedefghijkl";
        p = "aabc*";   
        System.out.println(new Main().isMatch(s,p));         
        s = "mississippi";
        p = "m*si*";
        System.out.println(new Main().isMatch(s,p));         
        s = "aabb";
        p = "*?aa*";
        System.out.println(new Main().isMatch(s,p));         
        s = "abaaaa";
        p = "?b??b?*";
        System.out.println(new Main().isMatch(s,p));         
        s = "ho";
        p = "**ho";
        System.out.println(new Main().isMatch(s,p));         
        s = "abcdef";
        p = "a?de*";
        System.out.println(new Main().isMatch(s,p));         
        s = "ababaca";
        p = "a???aca*";
        System.out.println(new Main().isMatch(s,p));         
        s = "aa";
        p = "a*";
        System.out.println(new Main().isMatch(s,p));         
        s = "baab";
        p = "*?ab*";
        System.out.println(new Main().isMatch(s,p));         
        s = "ababacabada";
        p = "*a???ada*";
        System.out.println(new Main().isMatch(s,p));      
    }
}