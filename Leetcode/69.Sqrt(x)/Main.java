/**
 * 2016-10-27
 * 1.注意越界
 * 2.如果平方根不是整数，返回整数部分
 */
public class Main{
    public int mySqrt(int x) {
    	int i = 0;
    	int j = x;
    	while(i<j){
    		int mid = (i+j)/2+1;
    		long square = (long)mid*(long)mid;
    		if(square<=x)
    			i=mid;
    		else
    			j=mid-1;
    	}    
    	return j;
    }	
	public static void main(String args[]){
		System.out.println(new Main().mySqrt(3));
	}
}