import java.util.*;
/**
 * 2016-4-3
 */
public class Main{
    public ListNode partition(ListNode head, int x) {
        ListNode less = null;
        ListNode equal_more = null;
        ListNode p = null;
        ListNode q = null;
        while(head!=null){         
            if(head.val<x){
                if(less==null){
                    less=head;
                    p=less;
                }
                else{
                    p.next=head;
                    p=p.next;
                }
            }
            else{                
                if(equal_more==null){
                    equal_more=head;
                    q=equal_more;
                }
                else{
                    q.next=head;
                    q=q.next;
                }
            }
            head=head.next;
        }
        if(less==null)
            return equal_more;
        head=less;
        p.next=equal_more;
        if(q!=null)
            q.next=null;
        return head;
    }
    public void show(ListNode head){
        if(head==null){
            System.out.println("is null");
            return;
        }
        ListNode p = head;
        while(p!=null){
            System.out.print(p.val+" ");
            p=p.next;
        }
        System.out.println();
    }
    public static void main(String args[]){
        ListNode head = new ListNode(3);
        ListNode p = head;
        p.next = new ListNode(1);
        p=p.next;
        p.next = new ListNode(2);
        p=new Main().partition(head,5);   
        new Main().show(p);     
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}