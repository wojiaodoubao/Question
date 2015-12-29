import java.util.*;
/**
 * 2016-4-21
 */
public class Main{
    public String addBinary(String a, String b) {
        int carry = 0;
        int i=0;
        String result = "";
        while(i<a.length()&&i<b.length()){
            int sum = carry+a.charAt(a.length()-1-i)-48+b.charAt(b.length()-1-i)-48;
            result=sum%2+result;
            carry=sum/2;
            i++;
        }
        while(i<a.length()){
            int sum = carry+a.charAt(a.length()-1-i)-48;
            result=sum%2+result;
            carry=sum/2;            
            i++;
        }
        while(i<b.length()){
            int sum = carry+b.charAt(b.length()-1-i)-48;
            result=sum%2+result;
            carry=sum/2;            
            i++;
        }
        if(carry>0)
            result=carry+result;
        return result;
    }    
    public static void main(String args[]){
        System.out.println(new Main().addBinary("101","100"));
    }
}