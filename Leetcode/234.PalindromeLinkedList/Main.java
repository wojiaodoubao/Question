import java.util.*;
/**
 * 2016-4-3
 * O(n)时间,O(1)空间解法,不错
 */
public class Main{
    public boolean isPalindrome(ListNode head) {
        //count length
        int length = 0;
        ListNode p = head;
        while(p!=null){
            p=p.next;
            length++;
        }
        if(length<=1)
            return true;
        //find mid
        p=head;
        for(int i=0;i<length/2;i++)
            p=p.next;
        if(length%2>0)
            p=p.next;
        ListNode q = p.next;
        while(q!=null){
            ListNode tmp = q.next;
            q.next=p;
            p=q;
            q=tmp;
        }
        //check head and p lenght/2 times
        for(int i=0;i<length/2;i++){
            if(head.val!=p.val)
                return false;
            head=head.next;
            p=p.next;
        }
        return true;
    }
    public static void main(String args[]){
        ListNode p = new ListNode(1);
        ListNode head = p;
        p.next=new ListNode(2);
        p=p.next;        
        p.next=new ListNode(1);
        p=p.next;        
        p.next=new ListNode(9);
        p=p.next;
        p.next=new ListNode(1);
        p=p.next;                
        System.out.println(new Main().isPalindrome(head));
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}