import java.util.*;
/**
 * 2016-4-23
 */
public class Main{
    public ListNode swapPairs(ListNode head) {
        if(head==null)
            return null;
        ListNode root = new ListNode(0);
        ListNode R = root;
        root.next = head;
        while(root.next!=null&&root.next.next!=null){
            ListNode p = root.next;
            root.next = root.next.next;
            p.next = root.next.next;
            root.next.next = p;
            root = root.next.next;
        }
        return R.next;
    }    
    public static void main(String args[]){

    }
}