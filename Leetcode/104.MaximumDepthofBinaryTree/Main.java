import java.util.*;
/**
 * 2016-4-19
 */
public class Main{
    private int max = 0;
    public int maxDepth(TreeNode root) {
        if(root==null)
            return max;
        max(root,1);
        return max;
    }   
    public void max(TreeNode root,int dep){
        if(root.left==null&&root.right==null){
            if(dep>max)
                max=dep;
        }
        else{
            if(root.left!=null)
                max(root.left,dep+1);
            if(root.right!=null)
                max(root.right,dep+1);
        }
    } 
    public static void main(String args[]){

    }
}