import java.util.*;
/**
 * 2016-4-20
 * 呼应一下116,确实稍做修改即可,见方法1,但是写得太啰嗦...
 * 方法2,使用p指向第一层节点,q指向第二层节点,代码短了不少.
 */
public class Main{
    public void connect(TreeLinkNode root) {//方法1
        while(root!=null){
            TreeLinkNode p = root;
            while(p!=null){
                if(p.left!=null){
                    if(p.right!=null){
                        p.left.next=p.right;
                    }
                    else{
                        TreeLinkNode q = p.next;
                        while(q!=null&&q.left==null&&q.right==null)
                            q=q.next;
                        if(q!=null){
                            if(q.left!=null)
                                p.left.next=q.left;
                            else if(q.right!=null)
                                p.left.next=q.right;
                        }                        
                    }
                }
                if(p.right!=null){
                    TreeLinkNode q = p.next;
                    while(q!=null&&q.left==null&&q.right==null)
                        q=q.next;
                    if(q!=null){
                        if(q.left!=null)
                            p.right.next=q.left;
                        else if(q.right!=null)
                            p.right.next=q.right;
                    }
                }
                p=p.next;
            }
            while(root!=null&&root.left==null&&root.right==null)
                root=root.next;
            if(root!=null){
                if(root.left!=null)
                    root=root.left;
                else
                    root=root.right;
            }
        }       
    }    
    public void coNNECT(TreeLinkNode root){//方法2 更短
        while(root!=null){
            while(root!=null&&root.left==null&&root.right==null)
                root=root.next;
            if(root==null)
                break;
            TreeLinkNode p=root;
            TreeLinkNode q=p.left!=null?p.left:p.right;
            root=q;
            while(p!=null&&q!=null){
                if(p.left!=null&&p.left!=q){
                    q.next=p.left;
                    q=q.next;
                }
                else if(p.right!=null&&p.right!=q){
                    q.next=p.right;
                    q=q.next;
                    p=p.next;
                }
                else
                    p=p.next;
            }
        }
    }
    public static void main(String args[]){

    }
}