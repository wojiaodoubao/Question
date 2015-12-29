import java.util.*;
/**
 * 2016-10-11
 * 又踩坑里了，越界问题又考虑不周。
 */
public class Main{
	private int bad = 1702766719;
	public boolean isBadVersion(int n){
		if(n>=bad)
			return true;
		return false;
	}
    public int firstBadVersion(int n) {
   		int i=1,j=n;
   		while(i!=j){
   			int mid = half(i,j);
	     	if(!isBadVersion(mid))
	    		i=mid+1;
	    	else
	    		j=mid;
	   	}
	   	return i;
    }	
    private int half(int i,int j){
    	if(i%2>0&&j%2>0)
    		return i/2+j/2+1;
    	return i/2+j/2;
    }
	public static void main(String args[]){
		System.out.println(new Main().firstBadVersion(2126753390));
	}
}