import java.util.*;
/**
 * 2016-1-22 不会
 */
public class Main{
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }
    //-1 means nums1[1] while 1 means nums2[1]
    public static double findK(int[] nums1,int[] nums2){
        int K = (nums1.length+nums2.length)/2;
    	int start1 = 0;
    	int end1 = nums1.length-1;
    	int start2 = 0;
    	int end2 = nums2.length-1;
    	while(K>0 && (end1-start1>1 || end2-start2>1)){
    		int mid1 = (start1+end1)/2;
    		int mid2 = (start2+end2)/2;
    		System.out.println(K+" "+start1+" "+end1+" "+start2+" "+end2+" "+mid1+" "+mid2);
    		if(nums1[mid1]<nums2[mid2]){
    			K=K-(mid1-start1);    			
    			start1=mid1;
    			end2=mid2;
    		}
    		else if(nums1[mid1]>nums2[mid2]){
    			K=K-(mid2-start2);    			
    			start2=mid2;
    			end1=mid1;
    		}
    		else{
    			end1=mid1;
    			end2=mid2;
    		}
    	}
    	int[] result = new int[4];
    	int i=0;int j=0;int z=0;
    	while(i<2&&j<2){
    		result[z++] = nums1[start1+i]<nums2[start2+j]?nums1[start1+i++]:nums2[start2+j++];
    	}
    	while(i<2)
    		result[z++] = nums1[start1+i++];
    	while(j<2)
    		result[z++] = nums2[start2+j++];
   		System.out.println(K+" "+start1+" "+end1+" "+start2+" "+end2);
        if((nums1.length+nums2.length)%2==0){
            return ((double)(result[K-1]+result[K]))/2;
        }
        else
            return (double)result[K];
    }
    public static void main(String args[]){
    	int[] nums1 = {1,2,3};
    	int[] nums2 = {4,5,6,8,10,12,14};
    	double r = findK(nums1,nums2);
        System.out.println(r);
    }	
}