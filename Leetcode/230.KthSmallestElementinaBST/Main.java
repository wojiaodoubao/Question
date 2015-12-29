import java.util.*;
/**
 * 2016-4-21
 * 如何能O(height of BST)查找到kth?
 * 法1.不论插入还是删除,总是让第n/2大的做根节点,左子树右子树同样
 * 法2.不论插入还是删除,总是维护每个节点的左子树节点数量与右子树节点数量
 */
public class Main{
    private int num = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root==null)
            return -1;
        int l = kthSmallest(root.left,k);
        if(l!=-1)
            return l;
        num++;
        if(num==k)
            return root.val;
        l = kthSmallest(root.right,k);
        return l;
    }    
    public static void main(String args[]){

    }
}