/**
 * 2016-10-11
 * 见274
 */
public class Main{
    public int hIndex(int[] citations) {
    	int i=0,j=citations.length-1;
    	while(i<j){
    		int mid = (i+j)/2;
    		if(citations[mid]>=citations.length-mid)
    			j=mid-1;
    		else
    			i=mid+1;
    	}
    	return citations.length==0?0:citations[i]>=citations.length-i?citations.length-i:citations.length-i-1;
    }	
	public static void main(String args[]){
		int[] num = {0,1,3,5,6};
		System.out.println(new Main().hIndex(num));
	}
}