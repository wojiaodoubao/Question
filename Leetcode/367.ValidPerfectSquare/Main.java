import java.util.*;
/**
 * 2016-9-19
 * 题很简单，就是开平方看能不能开出整数
 * 关键就在int的取值范围啊！想一次写对还真挺难。
 * 加法，乘法，都不能越界。
 * 不想用BigInteger，所有就一点点写，结果写得实在丑陋。。。
 * 或者可以这样，sqrt(Integer.MAX_VALUE)介于46340和46341之间，所以就用46340初始化end。
 * 递归爆栈，DP申请不了那么大数组
 */
public class Main{
    public boolean isPerfectSquare(int num) {
    	if(num==0)
    		return false;
    	if(num==1)
    		return true;
    	int start = 1;
    	int end = num;
    	for(end=2;num/end>end;end*=2);
    	while(start<=end){
	    	int mid = (start+end)/2;
	    	long square = mid*mid;
	    	//System.out.println(start+" "+end+" "+mid+" "+square);
	    	if(square==num)
	    		return true;
	    	if(square<0||square>num)
	    		end = mid-1;
	    	else
	    		start = mid+1;
    	}
		return false;
	}
    public boolean isPerfect(int num) {
    	if(num==0)
    		return false;
    	int start = 1;
    	int end = 46340;
    	while(start<=end){
	    	int mid = (start+end)/2;
	    	long square = mid*mid;
	    	if(square==num)
	    		return true;
	    	if(square>num)
	    		end = mid-1;
	    	else
	    		start = mid+1;
    	}
		return false;
	}	
    public boolean isperfectSquare(int num) {
    	if(num<=0)
    		return false;
    	boolean[] perfect = new boolean[num+1];
    	perfect[1] = true;
    	for(int i=1;i*i<=num;i++){
    		perfect[i*i]=true;
    	}
    	return perfect[num];
    }	
	public static void main(String args[]){
		int num = 1;
		System.out.println(new Main().isPerfect(num));
		System.out.println(Integer.MAX_VALUE);
	}
}