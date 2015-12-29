import java.util.*;
/**
 * 2016-4-26
 *
 * 基本解法fun2,统计长度,一个先走长度差,之后再一起走,相同则找到了.
 *
 * fun1解法十分巧妙:Discuss88940
 * fun1两种写法,O(m+n)写法是p=p==null?headB:p.next; O(m*n)写法是p=p==null?headA:p.next;
 * 原理是一样的,之所以能够给出最终结果,因为满足下面两点:
 * 1)经过m+n/m*n步之后,p必然==q,因此while会退出.
 * 2)由于p==q则立即退出,那么退出时p,q的前一个节点Front(p)一定!=Front(q).
 * 还有一点巧在p==null时才执行p=headB,如此相当于将null也看作一个节点,即headA,headB一定有相同的尾巴串.
 * 如此就把问题统一了,不需要单独处理headA,headB两者没有intersection的情况!
 */
public class Main{
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return fun1(headA,headB);
    }    
    public ListNode fun1(ListNode headA, ListNode headB){//brilliant解法
        ListNode p = headA;
        ListNode q = headB;
        while(p!=q){
            p=p==null?headB:p.next;//p=p==null?headA:p.next;另一种写法
            q=q==null?headA:q.next;//q=q==null?headB:q.next;
        }
        return p;
    }
    public ListNode fun2(ListNode headA, ListNode headB){//一般解法
        int i=0;
        int j=0;
        ListNode p = headA;
        ListNode q = headB;
        while(p!=null){
            p=p.next;
            i++;
        }
        while(q!=null){
            q=q.next;
            j++;
        }
        p=headA;
        q=headB;
        while(i>j){
            p=p.next;
            i--;
        }
        while(j>i){
            q=q.next;
            j--;
        }
        while(p!=q){
            p=p.next;
            q=q.next;
        }
        return p;
    }
    public static void main(String args[]){
        ListNode headA = new ListNode(8);
        ListNode headB = new ListNode(5);
        ListNode p = headA;
        p.next=new ListNode(7);
        p=p.next;
        p.next=new ListNode(6);
        p=p.next;
        p.next=new ListNode(5);
        p=p.next;
        p.next=new ListNode(4);
        p=p.next;   

        ListNode q=headB;     
        q.next=new ListNode(4);
        q=q.next;
        q.next=new ListNode(3);
        q=q.next;

        q.next=new ListNode(2);
        q=q.next;
        p.next=q;
        q.next=new ListNode(1);
        q=q.next;   

        p=new Main().getIntersectionNode(headA,headB);
        System.out.println(p==null?p:p.val);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}