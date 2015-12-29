import java.util.;
/**
 * 2016-4-4
 * 下面四种方法:
 * 1.对每个节点构造到自己的环,head向下,如果head.next==head说明有环(可能本来就有一个这样的环,也可能是沿着环走回到自己掰出来的环了)
 * 2.[好]和1一样,不过递归实现,就不用显式定义指针了
 * 3.使用快慢指针,保持了list原样
 * 使用额外空间的方法还有很多,比如还可以在val上做文章,就不一一写了.
 *
 * 4.[妙]照Discuss里抄的,不使用额外空间!
 * 如果head没构成单环(head=head.next),head和head.next没构成双环(head=head.next.next),就往下走并且'剔除一个节点':
 *      1.剔除不是删掉,是把head.next指到head.next.next
 *      2.剔除谁无所谓,这里是[以判断是否构成单环双环时的head为基准]剔除head.next.next;也可以剔除head.next;
 * [剔除不会破坏环!剔除不会破坏环!剔除不会破坏环!]且使得链表越来越小,所以如果有环,环中节点又越来越少,那它最后一定变成双环或单环.
 * 
 * 自己画一个环感受一下:
 *             a-a-a-a 
 *             |     |
 * a-a-a-a-a-a-a-a-a-a
 * 因为有head=head.next,说明head在往后走,如果有环,不论环含有几个节点,head都会走到环中的节点,因为不论几个节点环都是构成一个无穷的节点list
 * 一旦head指向到环中,那这个环就会开始越来越小直到变成两节点环或单节点环.
 */
public class Main{
    public boolean hasCycle(ListNode head) {
        //return cycle1(head);
        /*
        if(head==null)
            return false;
        else
            return cycle2(head,head.next);
        */
        //return cycle3(head);
        return cycle4(head);
    } 
    public boolean cycle1(ListNode head){//使用了额外空间,定义了一个变量;
        while(head!=null){
            if(head==head.next)
                return true;
            ListNode p = head;
            head = head.next;
            p.next = p;
        }
        return false;        
    } 
    public boolean cycle2(ListNode head,ListNode son){//使用了额外空间,但没有显式定义变量
        if(head==null||son==null)
            return false;
        if(head==son)
            return true;
        head.next=son.next;
        son.next=head;
        return cycle2(head,head.next);
    }
    public boolean cycle3(ListNode head){//快慢针,不会修改list
        if(head==null||head.next==null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow!=fast){
            if(slow==null||fast==null||fast.next==null)
                return false;
            slow=slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    public boolean cycle4(ListNode head){//照Discuss里抄的,不使用额外空间 剔除head.next.next
        if(head==null)
            return false;
        while(head!=null&&head.next!=null){
            if(head==head.next.next)
                return true;
            head = head.next;
            head.next = head.next!=null?head.next.next:null;
        }
        return false;
    }
    public boolean cycle5(ListNode head){//照Discuss里抄的,不使用额外空间 剔除head.next
        if(head==null)
            return false;
        while(head!=null&&head.next!=null){
            if(head==head.next.next)
                return true;
            head.next = head.next.next;
            head = head.next;
        }
        return false;
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