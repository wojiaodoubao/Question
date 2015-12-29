import java.util.*;
/**
 * 2016-4-14
 * 没思路,dicuss81478看了一个很巧的方法,然后自己写,人家33行3ms,我61行6ms,真是.........任重道远
 * 这种搜索的题做得少,题里关键的点抓不到.
 * 比如:
 * 1.之前都匹配,下一个字符导致右括号多了,删除包括此右括号和其之前的任何一个右括号,都可以.
 * 2.防止出现重复结果.这个方法巧的地方就在于不需要HashSet来去重.是通过回溯有界(deleteStartHere)来实现的.
 * 3.倒过来再来一次,巧得不行.
 * 81478的代码写得也真心好,任重道远...
 * 
 * 腾讯笔试跪了,今天去交简历让我等,不知道还有没有得面.想面一次还真难.
 * 微软黑科技还真挺多的,感觉自己时间真是都浪费了,尤其本科.
 */
public class Main{
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        List<String> list = new ArrayList<String>();  
        invalidParentheses(s,-1,0,list);//正序删除右括号
        //反序处理左括号:字符串顺序和'(',')'都要翻转
        for(String string:list){
            string = turnOver(string);//翻转
            List<String> tmpList = new ArrayList<String>();
            invalidParentheses(string,-1,0,tmpList);
            result.addAll(tmpList);
        }
        //收集
        list = new ArrayList<String>();
        for(String string:result){
            string = turnOver(string);
            list.add(string);
        }
        return list;  
    }
    public void invalidParentheses(String s,int deleteStartHere,
        int searchStartHere,List<String> result){
        int flag=0;
        for(int i=searchStartHere;i<s.length();i++){
            if(s.charAt(i)=='(')
                flag++;
            else if(s.charAt(i)==')')
                flag--;
            else
                ;
            if(flag<0){
                int j=i;
                while(j>deleteStartHere){
                    while(j>deleteStartHere&&s.charAt(j)!=')')
                        j--;
                    while(j>0&&j>deleteStartHere&&s.charAt(j-1)==')')
                        j--;
                    if(j>deleteStartHere){
                        invalidParentheses(s.substring(0,j)+s.substring(j+1,s.length()),j-1,i,result);
                        j--;
                    }
                }
                return;
            }
        }
        result.add(s);
        return;
    }
    public String turnOver(String s){
        String result = "";
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)=='(')
                result+=')';
            else if(s.charAt(i)==')')
                result+='(';
            else
                result+=s.charAt(i);
        }
        return result;
    }
    public static void main(String args[]){
        String s = "))(()";
        //s = "())";
        s=")(ab)())()";
        List<String> result = new Main().removeInvalidParentheses(s);
        for(String string:result)
            System.out.println(string);
    }
}