import java.util.*;
/**
 * 2016-1-4
 * 1.题里其实没说有序是升序还是降序，也没说nums1和nums2是同一种排序规则，我就默认都是升序写了，其实它也都是升序
 * 2.其实有一个不用额外空间的写法，就是直接往nums1里面写，nums1里要被覆盖的内容就转移到nums2中
 * 但是要记住哪些被转移了，并且被转移的nums1中元素还要保证能按序去比较，最少赋值n次，最多m+m+n次；下面是(m+n)*2次
 */
public class main{
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m+n];
        int i=0;
        int j=0;
        int k=0;
        while(i<m && j<n){
			result[k++] = nums1[i]<nums2[j]?nums1[i++]:nums2[j++];
        }
        while(i<m)
        	result[k++] = nums1[i++];
        while(j<n)
        	result[k++] = nums2[j++];
        for(i=0;i<m+n;i++)
        	nums1[i]=result[i];
    }
	public static void main(String args[]){

	}
}