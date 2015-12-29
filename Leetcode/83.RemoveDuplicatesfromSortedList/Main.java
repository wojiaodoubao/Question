import java.util.*;
/**
 * 2016-4-23
 */
public class Main{
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while(p!=null&&p.next!=null){
            if(p.next.val==p.val)
                p.next = p.next.next;
            else
                p=p.next;
        } 
        return head;  
    }    
    public static void main(String args[]){

    }
}