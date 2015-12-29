import java.util.*;
/**
 * 2016-4-28
 * 这道题非常有意思!
 * 
 * 我先是模拟的做:灯泡i被改变的次数是它的因子的种类数,比如4是3次{1,2,4},6是4次{1,2,3,6}.
 * 这样写出来就是func2,O(n^2),果然超时了...
 * 于是我就把前26个数的结果都用这个模拟解法算了一遍,规律居然这么显然:3*1;5*2;7*3;9*4;...;(2*i+1)*i;...
 * 所以就有了解法func1,我就只想到这么解方程了,最多也就O(n)嘛.
 *
 * 规律的解释:
 * 1)已经有了K个灯泡,再后面无论加多少个灯泡,这K个的亮法都是不变的.
 * 2)如何确定下一个灯泡亮还是不亮呢?列一下亮的灯泡的编号:1,4,9,16,...,i^2,...
 * 于是我们发现了,亮的灯泡都是i^2.这是为什么呢?
 * 因为对于任意一个正整数k,列出k的所有因子{1,a2,a3,...,b3,b2,k},其中ai*bi==k.
 * 可以发现因子都是成对出现的,只有存在ai*ai==k的时候,其因子数才是奇数,即可以亮,否则都是偶数,不会亮.
 * 因此n个灯泡亮的个数就等于n中有多少i^2,于是有了解法3,见func3.
 * 
 */
public class Main{
    public int bulbSwitch(int n) {
        return func3(n);
    }  
    public int func1(int n){
        int i=0;
        for(i=0;i<n;i++){
            if((i+2)*i>=n)
                break;
        }
        return i;
    }  
    public int func2(int n){
        int[] bulbs = new int[n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j*i<=n;j++){
                bulbs[j*i]++;
            }
        }   
        int result = 0;
        for(int i=1;i<=n;i++){
            if(bulbs[i]%2>0)
                result++;
        }
        return result;        
    } 
    public int func3(int n){
        int i=0;
        for(i=0;i*i<=n;i++);
        return i-1;
    }   
    public static void main(String args[]){
        for(int i=1;i<26;i++)
            System.out.println(new Main().bulbSwitch(i));
        System.out.println(new Main().bulbSwitch(9999999));        
    }
}