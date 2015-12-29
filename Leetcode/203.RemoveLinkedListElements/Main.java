import java.util.*;
/**
 * 2016-4-27
 */
public class Main{
    public ListNode removeElements(ListNode head, int val) {
        if(head==null)
            return head;
        ListNode p = new ListNode(-1);
        p.next = head;
        head = p;
        while(p.next!=null){
            if(p.next.val==val){
                p.next = p.next.next;
            }
            else
                p=p.next;
        }
        return head.next;
    }    
    public static void main(Strign args[]){

    }
}