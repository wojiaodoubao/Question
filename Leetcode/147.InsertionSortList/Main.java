import java.util.*;
/**
 * 2016-4-25
 * 脑袋想，没用笔
 */
public class Main{
    public ListNode insertionSortList(ListNode head) {
    	if(head==null)
    		return head;
    	ListNode p = new ListNode(-1);
    	p.next = head;
    	head = p;
    	p = head.next;
    	int num=1;    	
    	while(p.next!=null){
    		ListNode tmp = head;
    		int i=0;
	    	for(i=0;i<num;i++){
	    		if(tmp.next.val>p.next.val)
	    			break;
	    		tmp = tmp.next;
	    	}
	    	if(i!=num){
		    	ListNode q = p.next;
		    	p.next = p.next.next;
		    	q.next = tmp.next;
		    	tmp.next = q;
		    }
		    else
		    	p=p.next;
	    	num++;
    	}
    	return head.next;
    }	
	public static void main(String args[]){
		int N = 3;
		ListNode list = new ListNode(0);
		ListNode head = list;
		list.next = new ListNode(3);
		list = list.next;
		list.next = new ListNode(2);
		list = list.next;
		list.next = new ListNode(4);
		list = list.next;				
		head = new Main().insertionSortList(head);
		while(head!=null){
			System.out.print(head.val+" ");
			head = head.next;
		}		
	}
}
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}