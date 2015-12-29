import java.util.*;
/**
 * 2016-4-19
 * 这个解法有点蠢
 * dicuss-94041提供了一个非常巧妙的解法!
 * dicuss-83856使用快慢指针来求中点.
 * 
 * 效率上94041是2n,O(n)
 * 这个解法是T(n)+n,其中T(n)=2T(n/2)+1,利用Master定理知是O(n)
 * 83856是T(n)=2T(n/2)+n/2,利用Master定理知:O(nlogn)
 */
public class Main{
    public TreeNode sortedListToBST(ListNode head) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(head!=null){
            list.add(head.val);
            head=head.next;
        }
        return arrayToBST(list,0,list.size()-1);
    }    
    public TreeNode arrayToBST(ArrayList<Integer> nums,int start,int end){
        if(start>end){
            return null;
        }
        else if(start==end){
            return new TreeNode(nums.get(start));
        }
        else{
            int mid = (start+end)/2;
            TreeNode node = new TreeNode(nums.get(mid));
            node.left = arrayToBST(nums,start,mid-1);
            node.right = arrayToBST(nums,mid+1,end);
            return node;
        }
    }
    public static void main(String args[]){

    }
}
