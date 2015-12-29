import java.util.*;
/**
 * 2016-4-21
 */
public class Main{
    public TreeNode invertTree(TreeNode root) {
        if(root==null)
            return root;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }    
    public static void main(String args[]){

    }
}