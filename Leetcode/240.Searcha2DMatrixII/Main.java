import java.util.*;
/**
 * 2016-10-26
 * 这道题的思路可以，很实用。
 * 二分查找，先列二分找到left和right，再i from right to left，行二分查找target。
 * 
 * 注意循环实现：找到>=target的第一个索引和<=target的第一个索引时的写法！
 * while(i<j){
 * 	>=时：mid=(i+j)/2；if(num[mid]>=target)j=mid;else i=mid+1;
 * 	<=时：mid=(i+j)/2+1；if(num[mid]<=target)i=mid;else j=mid-1;
 * }
 * num[i]即为所求
 */
public class Main {
    public boolean searchMatrix(int[][] matrix, int target) {
    	if(matrix==null||matrix.length==0||matrix[0].length==0)
    		return false;
		int left=0;
		int i=matrix.length-1;
		while(left<i){
			int mid = (left+i)/2+1;//Nice
			if(matrix[mid][0]<=target)
				left=mid;
			else
				i=mid-1;
		}
		int right = matrix.length-1;
		i = 0;
		while(i<right){
			int mid = (i+right)/2;
			if(matrix[mid][matrix[mid].length-1]<target)
				i=mid+1;
			else
				right=mid;
		}
		for(i=right;i<=left;i++){
			if(binarySearch(matrix[i],target))
				return true;
		}
		return false;
    }
    private boolean binarySearch(int[] num,int target){
    	int i = 0;
    	int j = num.length-1;
    	while(i<j){
    		int mid = (i+j)/2;
    		if(num[mid]<target)
    			i=mid+1;
    		else
    			j=mid;
    	}
    	return num[i]==target;
    }
    public static void main(String args[]){
    	int[][] matrix = {
		  {1,   4,  7, 11, 15},
		  {2,   5,  8, 12, 19},
		  {3,   6,  9, 16, 22},
		  {10, 13, 14, 17, 24},
		  {18, 21, 23, 26, 30}
		};
		System.out.println(new Main().searchMatrix(matrix,5));
    }
}