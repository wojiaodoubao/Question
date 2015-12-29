import java.util.*;
/**
 * 2016-9-21
 * easy
 */
public class Main{
	private void quickSort(int[] num,int start,int end){
		if(start>end)
			return;
		int tmp = num[end];
		int i=start;int j=end;
		while(i<j){
			while(i<j&&num[i]<tmp)
				i++;
			num[j]=num[i];
			while(i<j&&num[j]>=tmp)
				j--;
			num[i]=num[j];
		}
		num[i]=tmp;
		quickSort(num,start,i-1);
		quickSort(num,i+1,end);
	}
    public int[] intersect(int[] nums1, int[] nums2) {
    	quickSort(nums1,0,nums1.length-1);
    	quickSort(nums2,0,nums2.length-1);
    	int i=0;int j=0;
    	List<Integer> result = new ArrayList<>();
    	while(i<nums1.length&&j<nums2.length){
    		if(nums1[i]==nums2[j]){
    			result.add(nums1[i]);
    			i++;j++;
    		}
    		else if(nums1[i]<nums2[j])
    			i++;
    		else
    			j++;
    	}
    	int[] r = new int[result.size()];
    	for(i=0;i<result.size();i++)
    		r[i]=result.get(i);
    	return r;
    }	
	public static void main(String args[]){
		int[] nums1 = {1,2,2,1,3};
		int[] nums2 = {2,2,3,3};
		int[] r = new Main().intersect(nums1,nums2);
		for(int i:r)
			System.out.print(i+" ");
		System.out.println();
	}
}