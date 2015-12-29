import java.util.*;
/**
 * 2016-4-25
 * 审错题写的交换.
 * 这个交换是有坑的!!!这个交换是有坑的!!!这个交换是有坑的!!!
 * 很容易写错!!!比m到n的reverse还容易错!!!
 * 交换的时候要考虑到m==n和m+1==n的情况!单独处理显得有些蠢(好吧m==n我也是单独处理的),下面的写法能处理所有m!=n的情况.
 */
public class Swap{
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n)
            return head;
        ListNode p = new ListNode(-1);
        ListNode MF = null;
        ListNode NF = null;
        p.next = head;
        head = p;
        int i=0;
        while(p!=null){
            if(i==m-1){
                MF = p;
            }
            if(i==n-1){
                NF = p;
                break;
            }
            i++;
            p=p.next;
        }
        p=MF.next;
        MF.next = NF.next;
        NF.next = NF.next.next;        
        MF.next.next = p;
        //
        p=NF.next;
        NF.next = MF.next.next;
        MF.next.next = MF.next.next.next;
        NF.next.next = p;
        return head.next;
    }    
    public static void main(String args[]){
        ListNode node = new ListNode(1);
        ListNode root = node;
        for(int i=2;i<5;i++){
            node.next = new ListNode(i);
            node = node.next;
        }
        root = new Swap().reverseBetween(root,1,2);
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