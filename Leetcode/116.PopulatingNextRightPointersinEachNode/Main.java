import java.util.*;
/**
 * 2016-4-18
 * 这题挺有意思的.不是完全二叉树的话,代码稍微改改是不是也行?就判断一下,p.left==null p.right==null.
 * 见117
 */
public class Main{
    public void connect(TreeLinkNode root) { 
        if(root==null)
            return;
        while(root.left!=null){
            TreeLinkNode p = root;
            while(p!=null){
                p.left.next=p.right;
                p.right.next=p.next==null?null:p.next.left;
                p=p.next;
            }
            root=root.left;
        }
    }    
    public static void main(String args[]){

    }
}