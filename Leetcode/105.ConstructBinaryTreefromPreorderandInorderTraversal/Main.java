import java.util.*;
/**
 * 2016-3-31
 * 明天愚人节
 */
public class Main{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length<=0)
            return null;
        return bTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }    
    public TreeNode bTree(int[] preorder,int[] inorder,int preleft,int preright,int inleft,int inright){
        TreeNode root = new TreeNode(preorder[preleft]);
        if(preright==preleft)
            return root;
        for(int i=0;i<=inright-inleft;i++){
            if(preorder[preleft]==inorder[inleft+i]){
                root.left = i==0?null:bTree(preorder,inorder,preleft+1,preleft+i,inleft,inleft+i-1);
                root.right = i==(inright-inleft)?null:bTree(preorder,inorder,preleft+1+i,preright,inleft+i+1,inright);
            }
        }
        return root;
    }
    public void preshow(TreeNode root){
        if(root==null)
            return;
        System.out.println(root.val);
        preshow(root.left);
        preshow(root.right);
    }
    public void inshow(TreeNode root){
        if(root==null)
            return;
        inshow(root.left);
        System.out.println(root.val);        
        inshow(root.right);
    }
    public static void main(String args[]){
        int[] preorder = {1,2,3};
        int[] inorder = {1,2,3};
        Main main = new Main();
        TreeNode root = main.buildTree(preorder,inorder);
        main.preshow(root);
        main.inshow(root);        
    }
}