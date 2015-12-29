import java.util.*;
/**
 * 2016-4-22
 */
public class Main{
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode R = result;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                result.next = l1;
                result = result.next;
                l1=l1.next;
            }
            else{
                result.next = l2;
                result = result.next;
                l2=l2.next;
            }
        }   
        if(l1!=null)
            result.next = l1;
        if(l2!=null)
            result.next = l2;
        return R.next;
    }    
    public static void main(String args[]){

    }
}