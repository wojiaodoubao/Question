import java.util.*;
/**
 * 2016-4-3
 */
public class Main{   
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null)
            return null;
        int length = 1;
        ListNode p = head;
        for(length=1;p.next!=null;length++)
            p=p.next;
        ListNode tail = p;
        k=k%length;
        p=head;
        for(int i=0;i<length-k-1;i++)
            p=p.next;
        tail.next = head;
        head = p.next;
        p.next=null;
        return head;
    }
    public static void main(String args[]){
        ListNode head = new ListNode(1);
        ListNode p = head;
        for(int i=2;i<4;i++){
            p.next=new ListNode(i);
            p=p.next;
        }
        p=head;
        while(p!=null){
            System.out.print(p.val+" ");
            p=p.next;
        } 
        System.out.println();       
        int k = 2;
        p=new Main().rotateRight(head,k);
        while(p!=null){
            System.out.print(p.val+" ");
            p=p.next;
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
} 