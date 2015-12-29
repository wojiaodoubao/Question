import java.util.*;
import java.math.*;

public class Main{
    public int guessNumber(int n) {
    	int start=1;
    	int end=n;
    	while(true){
    		int mid = new BigInteger(start+"").add(new BigInteger(end+"")).divide(new BigInteger("2")).intValue();
    		if(guess(mid)>0)
    			start=mid+1;
    		else if(guess(mid)==0)
    			return mid;
    		else
    			end=mid-1;
    	}
    }	
    private int guess(int n){
    	if(this.number>n)
    		return 1;
    	else if(this.number<n)
    		return -1;
    	return 0;
    }
    int number;
    public static void main(String args[]){
    	Main main = new Main();
    	main.number = 1702766719;
    	System.out.println(main.guessNumber(2126753390));
    }
}