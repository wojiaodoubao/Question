import java.util.*;
/**
 * 2016-4-21
 */
public class Main{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode R = result;
        int carry = 0;
        while(l1!=null&&l2!=null){
            int sum = l1.val+l2.val+carry;
            result.next = new ListNode(sum%10);
            result = result.next;
            carry = sum/10;
            l1=l1.next;
            l2=l2.next;
        }    
        while(l1!=null){
            int sum = carry+l1.val;
            result.next = new ListNode(sum%10);
            result = result.next;
            carry = sum/10;
            l1=l1.next;
        }
        while(l2!=null){
            int sum = carry+l2.val;
            result.next = new ListNode(sum%10);
            result = result.next;
            carry = sum/10;
            l2=l2.next;
        }
        if(carry>0)
            result.next = new ListNode(carry);
        return R.next;        
    }    
    public static void main(String args[]){

    }
}