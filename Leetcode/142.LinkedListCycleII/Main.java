import java.util.*;
/**
 * 2016-4-4
 * 说明:
 * 1.hash没什么好说的
 * 2.参考讨论中:O(1)额外空间,快慢指针. --我也从这思考了但是没想出来,还是挺巧的!
 * 见图:
 *    (CIRCLE-START-POINT)
 *            |
 *-------A----@----------------
 *            |               |
 *            |               |
 *            C               B   [环是顺时针,环长loop]
 *            |               |
 *            |-------@-------|
 *                    |
 *               (MEET-POINT)
 * [约定]设从head到开始点距离为A(A个移动),从开始点到交汇点距离为B,交汇点到开始点距离为C.
 * [注意一个事实]slow指针在环中最多走loop个移动.因为当slow进入环时,quick距slow至多loop,所以最多loop步就追上了.
 * 相距loop是个特殊情况,A==0的时候quick与slow相距loop;A>0时quick与slow相距小于等于loop-1.不过不影响分析,知道slow顶多走loop步就行了.
 * [分析](A+B+n*loop)/2==(A+B)/1,可得A+B==n*loop.所以如果p1从head出发,p2从交汇点出发,都以速度1移动,那么它们相遇时相交在Start-point.
 * 因为同时出发A个移动后p1到start-point,p2走A个移动其实是走了n*loop-B个移动,就是meet-point向前推B个移动,也是在start-point.
 * p1在A个移动后第一次进入环,所以在之前不可能与p2相遇,而A个移动后就与p2在start-point相遇了,说明是第一次相遇.
 * 即,p1和p2开始移动后第一次相遇的点就是start-point.(之后就一直一起走了...)
 *
 * 3.没找到不需要额外空间的解法...
 */
public class Main{
    public ListNode detectCycle(ListNode head) {
        //return cycle_hash(head);
        return cycle(head);
    }
    public ListNode cycle_hash(ListNode head){
        HashMap<ListNode,Object> map = new HashMap<ListNode,Object>();
        while(head!=null){
            if(map.get(head)==null)
                map.put(head,new Object());
            else
                return head;
            head=head.next;
        }        
        return head;        
    }
    public ListNode cycle(ListNode head){//照抄Discuss中O(1)空间解法
        if(head==null||head.next==null)
            return null;
        ListNode slow = head.next;
        ListNode quick = head.next.next;
        while(slow!=quick){
            if(quick==null||quick.next==null)
                return null;
            slow=slow.next;
            quick=quick.next.next;
        }
        slow = head;
        while(slow!=quick){
            slow=slow.next;
            quick=quick.next;
        }
        return slow;
    } 
    public static void main(String args[]){

    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}