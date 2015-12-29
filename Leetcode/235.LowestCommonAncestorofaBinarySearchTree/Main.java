import java.util.*;
/**
 * 2016-4-19
 */
public class Main{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==p)
            return root;
        else if(root==q)
            return root;
        else if(p.val>=root.val&&q.val>=root.val)
            return lowestCommonAncestor(root.right,p,q);
        else if(p.val<root.val&&q.val<root.val)
            return lowestCommonAncestor(root.left,p,q);                
        else
            return root;
    }    
    public static void main(String args[]){

    }
}