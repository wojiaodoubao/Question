import java.util.*;
/**
 * 2016-4-19 
 */
public class Main{
    private Integer depth;
    public int minDepth(TreeNode root) {
        if(root==null)
            return 0;
        minDep(root,1);
        return depth;   
    }   
    private void minDep(TreeNode root,int dep){
        if(root.left==null&&root.right==null){
            if(depth==null)
                depth=dep;
            else if(depth>dep)
                depth=dep;
        }
        else{
            if(root.left!=null)
                minDep(root.left,dep+1);
            if(root.right!=null)
                minDep(root.right,dep+1);
        }
    }
    public static void main(String args[]){

    }
}