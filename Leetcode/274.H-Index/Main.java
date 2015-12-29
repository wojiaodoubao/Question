import java.util.*;
/**
 * 2016-10-11
 * 先排序，再二分,其实二分已经没必要了，因为排序已经nlogn了啊！先看了275就一直想着用二分，噫!
 * 最快解法是O(n)的，用Hash，见Solution.java
 *
 * 二分查找的技巧，hIndex的return那行，自己体会一下。
 * 1.如果mid引用数量>=从最左到mid的所有文章，则最后的那篇文章必在[mid,j]，i=mid+1
 * 2.反之，必在[i,mid-1]，j=mid-1
 * 3.如1中条件，不应该i=mid么？但是如果出现[0,2]这种情况就会死循环，使用i=mid+1避免死循环
 * 4.在1中使i=mid+1，就可能漏掉真正解mid，因此在return的时候抢救一下，如果漏掉了真正解，
 * 则mid右侧必然全部是非法解，进而在i=mid+1处退出循环且i是非法解，返回i-1即可。
 */
public class Main{
	private void quickSort(int start,int end,int[] num){
		if(start>=end)
			return;
		int i=start;
		int j=end;
		int tmp = num[j];
		while(i<j){
			while(i<j&&num[i]>tmp)
				i++;
			num[j]=num[i];
			while(i<j&&num[j]<=tmp)
				j--;
			num[i]=num[j];
		}
		num[i] = tmp;
		quickSort(start,i-1,num);
		quickSort(i+1,end,num);
	}
    public int hIndex(int[] citations) {
    	quickSort(0,citations.length-1,citations);
    	int i=0,j=citations.length-1;
    	while(i<j){
    		int mid = (i+j)/2;
    		if(citations[mid]>=mid+1)
    			i=mid+1;
    		else
    			j=mid-1;
    	}
    	return citations.length!=0&&citations[i]>=i+1?i+1:i;
    }	
	public static void main(String args[]){
		int[] nums = {3,0,6,1,5};
		System.out.println(new Main().hIndex(nums));
	}
}