import java.util.*;
/**
 * 2016-3-31
 * last day of March
 */
public class Main{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }     
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder==null||inorder.length<=0)
            return null;
        return bTree(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }    
    public TreeNode bTree(int[] inorder, int[] postorder,int inl,int inr,int posl,int posr){
        TreeNode root = new TreeNode(postorder[posr]);
        if(posr==posl)
            return root;
        for(int i=0;i<=inr-inl;i++){
            if(postorder[posr]==inorder[inr-i]){
                root.right = i==0?null:bTree(inorder,postorder,inr-i+1,inr,posr-i,posr-1);
                root.left = i==(inr-inl)?null:bTree(inorder,postorder,inl,inr-i-1,posl,posr-i-1);
            }
        }
        return root;
    }
    public void posshow(TreeNode root){
        if(root==null)
            return;
        posshow(root.left);
        posshow(root.right);        
        System.out.println(root.val);
    }
    public void inshow(TreeNode root){
        if(root==null)
            return;
        inshow(root.left);
        System.out.println(root.val);        
        inshow(root.right);
    }    
    public static void main(String args[]){
        int[] posorder = {2,3,1};
        int[] inorder = {2,1,3};
        Main main = new Main();
        TreeNode root = main.buildTree(inorder,posorder);
        main.posshow(root);
        main.inshow(root);  
    }
}