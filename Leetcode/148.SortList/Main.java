import java.util.*;
/**
 * 2016-4-27
 * 链表的O(nlogn)排序,常量空间,就只有归并了吧?
 * 堆排的话整堆需要查父亲
 * 快排也有从后往前查找
 * 桶排序不是常量空间
 *
 */
public class Main{
    public ListNode sortList(ListNode head) {
        //不是常量空间了!
        LinkedList<ListNode> listQueue = new LinkedList<ListNode>();
        while(head!=null){
            ListNode p = head;
            head = head.next;
            p.next = null;
            listQueue.add(p);
        }
        while(listQueue.size()>1)
            listQueue.add(mergeSort(listQueue.remove(),listQueue.remove()));
        return listQueue.size()==0?null:listQueue.remove();

        //递归也不是常量空间!只不过隐藏在虚拟机栈里了.

        //常量空间 ListNode p,ListNode q,int n--表示多长的一起合并 n from 1 to length/2
        // 3-2-1-0 -> 2-3-0-1 让它们每次处理之后还是连着的,这样才能下次继续处理.
        //不写了,麻烦,就是这么个意思.
    }
    public ListNode mergeSort(ListNode p,ListNode q){
        ListNode result = new ListNode(-1);
        ListNode R = result;
        while(p!=null&&q!=null){
            if(p.val<q.val){
                result.next = p;
                p=p.next;
            }
            else{
                result.next = q;
                q=q.next;
            }
            result = result.next;
        }
        while(p!=null){
            result.next = p;
            p=p.next;
            result = result.next;
        }
        while(q!=null){
            result.next = q;
            q=q.next;
            result = result.next;
        }
        result.next = null;
        return R.next;
    }    
    public static void main(String args[]){

    }
}