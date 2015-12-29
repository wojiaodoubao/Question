import java.util.*;
/**
 * 2015-12-31
 */
public class main{
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
        	return false;
        int width = matrix[0].length;
        int height = matrix.length;
        int left = 0;
        int right = height-1;
        while(left<=right){
        	int mid = (left+right)/2;
        	if(matrix[mid][0]>target)
        		right = mid-1;
        	else if(matrix[mid][width-1]<target)
        		left = mid+1;
        	else{//matrix[mid][0]<=target && matrix[mid][height-1]>=target
        		int l = 0;
        		int r = width-1;
        		while(l<=r){
        			int middle = (l+r)/2;
        			if(matrix[mid][middle]<target)
        				l = middle+1;
        			else if(matrix[mid][middle]>target)
        				r = middle-1;
        			else
        				return true;
        		}
        		return false;
        	}
        }
        return false;
    }	
	public static void main(String args[]){
		int[][] matrix = {
						  {1,   3,  5,  7},
						  {10, 11, 16, 20},
						  {23, 30, 34, 50}
						};
		int target = Integer.parseInt(args[0]);
		System.out.println(searchMatrix(matrix,target));
	}
}