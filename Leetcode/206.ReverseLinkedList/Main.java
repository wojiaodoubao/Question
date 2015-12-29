import java.util.*;
/**
 * 2016-4-27
 */
public class Main{
    public ListNode reverseList(ListNode head) {
        ListNode result = new ListNode(-1);
        while(head!=null){
            ListNode p = result.next;
            result.next = head;
            head = head.next;
            result.next.next = p;
        }
        return result.next;   
    }    
    public static void main(String args[]){

    }
}