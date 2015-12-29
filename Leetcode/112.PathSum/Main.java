import java.util.*;
/**
 * 2016-4-15
 */
public class Main{
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return false;       
        if(root.val==sum&&root.left==null&&root.right==null)
            return true;
        if(hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val))
            return true;
        return false;
    }
    public static void main(String args[]){

    }
}