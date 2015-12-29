import java.util.*;
/**
 * 2016-10-27
 * 又被越界坑了！总是栽。
 */
public class Main{
    public double mypow(double x, int n) {
    	if(n<0)
    		return 1/myPow(x,-n);
    	if(n==0)
    		return 1.0;
    	double tmp = myPow(x,n/2);
    	if(n%2==0)
    		return tmp*tmp;
    	else
    		return tmp*tmp*x;
    }	
    public double myPow(double x, int n) {
    	long N = n<0?-1*(long)n:(long)n;
    	LinkedList<Long> stack = new LinkedList<Long>();
    	while(N>0){
    		stack.add(N%2);
    		N = N%2>0?N-1:N/2;
    	}
    	double result = 1;
    	while(!stack.isEmpty()){
    		if(stack.removeLast()==0)
    			result = result*result;
    		else
    			result = result*x;
    	}
    	return n>0?result:1/result;
	}
	public static void main(String args[]){
		System.out.println(new Main().myPow(2,-2147483648));
	}
}