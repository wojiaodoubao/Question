import java.util.*;
/**
 * 2016-4-25
 * 一开始审错题,以为是交换m,n呢.
 * 保存m的前一个MF,翻转头q.next,翻转尾qTail,n的下一个p.
 */
public class Main{
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode p = new ListNode(-1);
        p.next = head;
        head = p;
        ListNode MF=null;
        ListNode q = new ListNode(-2);
        ListNode qTail = null;
        int i=0;
        while(p!=null){
            if(i==m-1){
                MF=p;
                qTail=p.next;
                p=p.next;
            }
            else if(i>=m&&i<=n){
                ListNode tmp = q.next;
                q.next = p;
                p=p.next;
                q.next.next = tmp;
            }
            else if(i==n+1){
                break;
            }
            else{
                p=p.next;
            }
            i++;
        }
        MF.next = q.next;
        qTail.next = p;       
        return head.next;
    }    
    public static void main(String args[]){
        ListNode node = new ListNode(1);
        ListNode root = node;
        for(int i=2;i<5;i++){
            node.next = new ListNode(i);
            node = node.next;
        }
        root = new Main().reverseBetween(root,1,4);
        while(root!=null){
            System.out.print(root.val+" ");
            root = root.next;
        }
        System.out.println();
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}