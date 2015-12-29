import java.util.*;
/**
 * 2016-4-3
 */
public class Main{
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        for(int i=0;i<n;i++){
            if(p==null)
                return head;
            p=p.next;
        }
        if(p==null)
            head=head.next;
        else{     
            ListNode q = head;
            while(p.next!=null){
                p=p.next;
                q=q.next;
            }
            q.next = q.next.next;
        }
        return head;
    }
    public static void main(String args[]){

    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
} 