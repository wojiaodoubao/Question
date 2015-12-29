import java.util.*;
/**
 * 2016-10-27
 * 越界问题老大难，跪了。好菜好菜。
 */
public class Main{
    public int divide(int dividend, int divisor) {
    	int i = 1;
    	long dividEnd = dividend;
    	long diviSor = divisor;
    	if(dividEnd<0){
    		dividEnd=-1*dividEnd;
    		i=-1*i;
    	}
    	if(diviSor<0){
    		diviSor=-1*diviSor;
    		i=-1*i;
    	}
        long result = 0;
        while(dividEnd>=diviSor){
        	long[] r = div(dividEnd,diviSor);
        	result+=r[0];
        	dividEnd=r[1];
        }
        if(i*result>Integer.MAX_VALUE||i*result<Integer.MIN_VALUE)
        	return Integer.MAX_VALUE;
        return i*(int)result;
    }	
    private long[] div(long a,long b){
    	long num = 1;
    	long pre = 0;
    	long preB = 0;
    	while(a>=b){
    		preB=b;
    		b+=b;
    		pre=num;
    		num+=num;    		
    	}
    	long[] result = new long[2];
    	result[0] = pre;//特殊商
    	result[1] = a-preB;//特殊余数
    	return result;
    }
	public static void main(String args[]){
		System.out.println(new Main().divide(-2147483648,1));
		//System.out.println(new Main().divide(100,9));
	}
}