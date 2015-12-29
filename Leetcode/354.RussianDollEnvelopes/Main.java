import java.util.*;
/**
 * 2016-9-19
 * 靠，这就过了？O(n^2)哪，没想到
 * 排个序，n^2算一遍，用上DP了，也没用上二分查找。。。
 */
public class Main{
	class En{
		int x;
		int y;
		public En(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	private void quickSort(En[] list,int start,int end){
		if(start>end)
			return;
		En tmp = new En(list[end].x,list[end].y);
		int i = start;
		int j = end;
		while(i<j){
			while(i<j&&list[i].x*list[i].y<tmp.x*tmp.y)
				i++;
			list[j].x = list[i].x;
			list[j].y = list[i].y;
			while(i<j&&list[j].x*list[j].y>=tmp.x*tmp.y)
				j--;
			list[i].x = list[j].x;
			list[i].y = list[j].y;
		}
		list[i].x = tmp.x;
		list[i].y = tmp.y;
		quickSort(list,start,i-1);
		quickSort(list,i+1,end);
	}
    public int maxEnvelopes(int[][] envelopes) {
    	if(envelopes==null||envelopes.length==0||envelopes[0].length==0)
    		return 0;
    	En[] enlist = new En[envelopes.length];
    	for(int i=0;i<envelopes.length;i++){
    		enlist[i] = new En(envelopes[i][0],envelopes[i][1]);
    	}	
    	quickSort(enlist,0,enlist.length-1);
    	int[] dp = new int[enlist.length];
    	int result = 0;
    	for(int i=0;i<enlist.length;i++){
    		for(int j=i+1;j<enlist.length;j++){
    			if(enlist[j].x>enlist[i].x&&enlist[j].y>enlist[i].y){
    				if(dp[j]<dp[i]+1){
    					dp[j]=dp[i]+1;
    					result = result<dp[j]?dp[j]:result;
    				}
    			}
    		}    		
    	}
    	return result+1;
    }
	public static void main(String args[]){
		Main main = new Main();
		int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
		System.out.println(main.maxEnvelopes(envelopes));
	}
}