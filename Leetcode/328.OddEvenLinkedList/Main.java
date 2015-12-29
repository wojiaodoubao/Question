import java.util.*;
/**
 * 2016-4-21
 * 链表操作不太熟
 */
public class Main{    
    public ListNode oddEvenList(ListNode head) {
        if(head==null)
            return head;
        ListNode even = new ListNode(0);
        ListNode p = head;
        ListNode q = even;
        while(p.next!=null){
            q.next=p.next;
            q=q.next;
            if(p.next.next!=null)
                p.next=p.next.next;
            else
                break;
            p=p.next;
        }    
        q.next=null;
        p.next=even.next;
        return head;
    }    
    public static void main(String args[]){
        ListNode head = new ListNode(1);
        ListNode p = head;
        for(int i=2;i<10;i++){
            p.next=new ListNode(i);
            p=p.next;
        }
        head = new Main().oddEvenList(head);
        while(head!=null){
            System.out.print(head.val);
            head=head.next;
        }
        System.out.println();
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}