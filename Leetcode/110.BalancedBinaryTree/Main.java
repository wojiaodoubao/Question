import java.util.*;
/**
 * 2016-4-19
 * one time pass
 */
public class Main{
    public boolean isBalanced(TreeNode root) {
        if(balanced(root)!=-1)
            return true;   
        return false;
    }   
    public int balanced(TreeNode root){
        if(root==null)
            return 0;
        int leftDep = balanced(root.left);
        int rightDep = balanced(root.right);
        if(leftDep==-1||rightDep==-1)
            return -1;
        if(leftDep-rightDep<=1&&leftDep-rightDep>=-1)
            return leftDep>rightDep?leftDep+1:rightDep+1;
        else
            return -1;
    } 
    public static void main(String args[]){

    }
}