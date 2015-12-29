import java.util.*;
/**
 * 2016-4-23
 * 这题的点找得挺好~ 任意长度翻转
 */
public class Main{
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode R = root;
        while(isReversable(root,k)){
            ListNode tail = root.next;
            for(int i=0;i<k-1;i++){
                ListNode p = root.next;
                root.next = tail.next;
                tail.next = root.next.next;
                root.next.next = p;
            }
            root = tail;
        }
        return R.next;    
    } 
    void showList(ListNode list){
        while(list!=null){
            System.out.print(list.val+" ");
            list = list.next;
        }
        System.out.println();        
    }  
    boolean isReversable(ListNode head, int k){      
        for(int i=0;i<k;i++){
            if(head.next==null)
                return false;
            head = head.next;
        }
        return true;
    } 
    public static void main(String args[]){
        ListNode list = new ListNode(0);
        ListNode head = list;
        for(int i=1;i<=5;i++){
            list.next = new ListNode(i);
            list = list.next;
        }
        list = new Main().reverseKGroup(null,6);
        new Main().showList(list);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}