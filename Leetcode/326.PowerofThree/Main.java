import java.util.*;
import java.lang.Math.*;
/**
 * 2016-4-28
 * 我真是学傻了,初中数学知识都忘光光,还不懂得结合Integer的值域,真是丢人.
 * 我真系学傻咗，初中数学知识都唔记得净系得，仲唔识结合Integer嘅值域，真失礼.
 */
public class Main{
    public boolean isPowerOfThree(int n) {
        return foo1(n);
    }    
    public boolean foo1(int n){//此解法有问题,理论上正确,但因为有精度问题,所以会错.比如243的时候结果就不对!
        double k = Math.log(n)/Math.log(3);
        System.out.println(k+"");
        if((int)k==k)
            return true;
        return false;
    }
    public boolean foo2(int n){//如果MAX_VALUE是243那类由于精度问题会出错的数,那么这个解法也会有问题.
        int k = (int)Math.pow(3,(int)(Math.log(Integer.MAX_VALUE)/Math.log(3)));
        return n<=0?false:k%n==0?true:false;
    }
    public boolean foo2_2(int n){//同理于foo2
        return n<=0?false:n>1162261467?false:1162261467%n==0?true:false;        
    }
    public static void main(String args[]){
        System.out.println(new Main().foo1(243));
        System.out.println(new Main().foo1(26));   
        System.out.println(new Main().foo2(1));
        System.out.println(new Main().foo2(0)); 
        System.out.println(new Main().foo2_2(1162261467));
        System.out.println(new Main().foo2_2(1162261468));   
        System.out.println(new Main().foo2_2(1));
        System.out.println(new Main().foo2_2(0)); 
    }
}