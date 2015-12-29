import java.util.*;
/**
 * 2016-4-13
 */
public class Main{
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null)
            return true;
        if((p==null&&q!=null)||(p!=null&&q==null))
            return false;
        if(p.val!=q.val)
            return false;
        if(isSameTree(p.left,q.left)&&isSameTree(p.right,q.right))
            return true;
        return false;    
    }    
    public static void main(String args[]){

    }
}