import java.util.*;
/**
 * 2016-4-23
 */
public class Main{
    public ListNode deleteDuplicates(ListNode head) {
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode R = result;
        while(result.next!=null){
            ListNode p = result.next.next;
            while(p!=null&&p.val==result.next.val)
                p=p.next;
            if(p!=result.next.next)
                result.next = p;
            else
                result=result.next;
        }
        return R.next;    
    }    
    public static void main(String args[]){

    }
}