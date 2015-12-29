import java.util.*;
/**
 * 2016-4-25
 * 脑袋想，没用笔
 */
public class Main{
    public void reorderList(ListNode head) {
    	if(head==null)
    		return;
    	ListNode quick = head;
    	ListNode slow = head;
    	int num = 0;
    	while(quick!=null&&quick.next!=null){
    		quick = quick.next.next;
    		slow = slow.next;
    		num++;
    	}   
    	LinkedList<ListNode> stack = new LinkedList<ListNode>();
    	if(quick!=null)
    		slow=slow.next;
    	while(slow!=null){
    		stack.push(slow);
    		slow = slow.next;
    	}
    	slow = head;
    	for(int i=0;i<num;i++){
    		ListNode node = stack.pop();
    		quick = slow.next;
    		slow.next = node;
    		node.next = quick;
    		slow = slow.next.next;//这个地方有意思哦，偶数的话由于最后一个节点形成了到自己的环，所有slow.next.next也没关系，而且最后还可以slow.next=null;
    	}
    	slow.next = null;
    }	
	public static void main(String args[]){
		ListNode list = new ListNode(1);
		ListNode head = list;
		for(int i=2;i<3;i++){
			list.next = new ListNode(i);
			list = list.next;
		}
		new Main().reorderList(head);
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