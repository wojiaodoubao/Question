import java.util.*;
/**
 * 2016-4-21
 * 此题不错.第一种解法超时,第二种过了.
 * 解法一:把num1,num2都分割成一个个个位数,共n*m次个位乘法,n*m次个位加法
 * 解法二:模仿列式计算,共计m次乘法m次加法,每次乘法n*1次个位乘法,可见少了加法次数
 */
public class Main{
    public String multiply(String num1, String num2) {
        String result = "0";
        String p = num1.length()<num2.length()?num1:num2;
        String q = num1.length()>=num2.length()?num1:num2;
        for(int i=0;i<p.length();i++){
            String tempR = MULTIPLY(q,p.charAt(p.length()-i-1),i);
            result = add(tempR,result);
        }             
        return result;
    }    
    public String MULTIPLY(String s,char c,int index){
        String result = "";
        if(c-48==0)
            return "0";
        for(int i=0;i<index;i++)
            result+="0";
        int carry = 0;
        for(int i=0;i<s.length();i++){
            int sum = (c-48)*(s.charAt(s.length()-i-1)-48)+carry;
            result = sum%10+result;
            carry = sum/10;
        }
        if(carry>0)
            result=carry+result;
        return result;
    }
    public String add(String num1,String num2){
        String result = "";
        int carry = 0;
        int i=0;
        for(i=0;i<num1.length()&&i<num2.length();i++){
            int t = num1.charAt(num1.length()-i-1)-48+num2.charAt(num2.length()-i-1)-48+carry;
            result = t%10+result;
            carry = t/10;
        }
        while(i<num1.length()){
            int t = carry+num1.charAt(num1.length()-i-1)-48;
            result = t%10+result;
            carry = t/10;
            i++;
        }
        while(i<num2.length()){
            int t = carry+num2.charAt(num2.length()-i-1)-48;
            result = t%10+result;
            carry = t/10;
            i++;
        }
        if(carry>0)
            result=1+result;
        return result;
    }     
    /*下面这个解法超时了*/
    public String multiplyTLE(String num1, String num2) {
        String result = "0";
        for(int i=0;i<num1.length();i++){
            for(int j=0;j<num2.length();j++){
                String t = multi(num1.charAt(num1.length()-i-1),num2.charAt(num2.length()-j-1),i,j);
                //System.out.println(num1.charAt(num1.length()-i-1)+" "+num2.charAt(num2.length()-j-1)+" "+i+" "+j+" "+t+" "+result);
                result = add(result,t);
            }
        } 
        return result;                  
    }   
    public String multi(char c1,char c2,int index1,int index2){//10,10->'1','1',1,1
        String result = "0";
        int t = (c1-48)*(c2-48);
        if(t==0)
            return result;
        for(int i=1;i<index1+index2;i++){
            result+="0";
        }
        return t+result;
    }
    public static void main(String args[]){
        String num1 = "379793381910974090173405088322972943";
        String num2 = "4515503214242784384118516920342794464987611881430043826418762953520018971372435632";
        num1 = "999";
        num2 = "9";
        System.out.println(new Main().multiply(num1,num2));
    }
}