import java.util.*;
/**
 * 2016-4-21
 * 写法太傻逼了不能忍,重新写.
 */
public class Main{
    TreeNode treeNode = null;
    TreeNode p = null;
    TreeNode q = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p=p;
        this.q=q;
        if(ancestor(root)==2)
            return treeNode;
        else
            return null;
    }
    public int ancestor(TreeNode root){
        if(root==null)
            return 0;
        int left = ancestor(root.left);
        int right = ancestor(root.right);
        if(left==2||right==2)
            return 2;
        else if(((left==1&&right==-1)||(left==-1&&right==1))&&treeNode==null){
            treeNode=root;
            return 2;
        }
        else if((left==1||right==1)&&root==q){
            treeNode=root;
            return 2;  
        }
        else if((left==-1||right==-1)&&root==p){
            treeNode=root;
            return 2; 
        }
        else if(left==1||right==1||root==p)
            return 1;
        else if(left==-1||right==-1||root==q)
            return -1;
        else
            return 0;
    } 
    public static void main(String args[]){

    }
}