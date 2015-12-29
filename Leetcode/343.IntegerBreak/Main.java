import java.util.*;
/**
 * 2016-4-27
 * 这道题很神奇!
 * 规律是:3越多越大! Discuss98978,Discuss99178都是利用这一点做的.
 * breakInt是我瞎猜的,可能碰巧也能实现有3就分3.
 * dp是O(n^2)的dp解法,这个容易证明正确性,不过更加耗时.
 * dp99178是O(n)的动态规划.
 * more3是直接求最多几个3,然后公式计算的.
 *
 * 证明为什么分出尽量多的3,可以使结果最大.--帅
 * 证明:
 * 1)若X>4,则a*(X-a)>X(a2). --结论显然
 * 2)任意X存在一个拆分X=1*a1+2*a2+...+X*aX.我们要求的是MUL=1^a1*2^a2*...*X^aX.
 * 由于(1)可知,任何X>4拆成a*(X-a)后都更大,因此上面的拆分中,若i>4且i*ai>0,则拆开i可以使得MUL更大.
 * 3)由于(2),我们只需要考虑1,2,3,4的拆分.其中:
 *  -1的拆分我们不考虑,见(2)中的两个公式,1对于MUL没有任何贡献,且'消耗'了X.
 *   任何一个a1!=0的拆分都可以通过去一个1加入到某一个i使其变成i+1,从而增大MUL.//MUL->MUL*((i+1)/i)
 *  -4的拆分我们不考虑,因为拆分出4就等于拆分出2*2,即4^a4=2^(a4*2).
 * 4)从而最终的形式为:X=2*n+3*m;MUL=2^n*3^m;求m为何值的时候MUL最大?
 *   n=(X-3*m)/2,带入MUL得到MUL(m)=2^(X-3*m)/2*3^m.对m求导,发现m在实数域上单调递增,从而m取值尽量大即可,去m=MUL/3.
 * 证毕.
 */
public class Main{
    public int integerBreak(int n) {
        //return dp(n);
        //return breakInt(n,true);  
        return more3(n);
    }
    public int breakInt(int n,boolean mustBreak){
        if(mustBreak&&n<=3)
            return n-1;           
        else if(!mustBreak&&n<=4)
            return n;
        int p = breakInt(n/2,false)*breakInt(n-n/2,false);
        int q = breakInt(n/2-1,false)*breakInt(n-n/2+1,false);
        return p>q?p:q;
    }  
    public int dp(int n){
        int[] num = new int[n+1];
        if(n<=3)
            return n-1;
        if(n==4)
            return 4;
        num[1]=1;
        num[2]=2;
        num[3]=3;
        num[4]=4;        
        for(int i=5;i<n+1;i++){
            int max = 0;
            for(int j=1;j<=i-j;j++){
                max=max<num[j]*num[i-j]?num[j]*num[i-j]:max;
            }
            num[i]=max;
        }
        return num[n];
    } 
    public int dp_Discuss99178(int n){
        int[] num = new int[n+1];
        if(n<=3)
            return n-1;
        if(n==4)
            return 4;
        num[1]=1;
        num[2]=2;
        num[3]=3;
        num[4]=4;        
        for(int i=5;i<n+1;i++){
            num[i] = 3*num[i-3];
        }
        return num[n];        
    }
    public int more3(int X){
        if(X<=3)
            return X-1;
        if(X==4)
            return 4;        
        int m = 0;
        int n = 0;
        if(X%3==2){
            n=1;
            m=X/3;
        }
        else if(X%3==1){
            n=2;
            m=X/3-1;
        }
        else
            m=X/3;
        int result = 1;
        for(int i=0;i<m;i++){
            result*=3;
        }
        for(int i=0;i<n;i++){
            result*=2;
        }
        return result;
    } 
    public static void main(String args[]){
        System.out.println(new Main().integerBreak(Integer.parseInt(args[0])));
    }
}