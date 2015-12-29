import java.util.*;
/**
 * 2016-4-19
 */
public class Main{
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null||nums.length<=0)
            return null;
        return arrayToBST(nums,0,nums.length-1);
    }
    public TreeNode arrayToBST(int[] nums,int start,int end){
        if(start>end){
            return null;
        }
        else if(start==end){
            return new TreeNode(nums[start]);
        }
        else{
            int mid = (start+end)/2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = arrayToBST(nums,start,mid-1);
            node.right = arrayToBST(nums,mid+1,end);
            return node;
        }
    } 
    public static void main(String args[]){

    }
}