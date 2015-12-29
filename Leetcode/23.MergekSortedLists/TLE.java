import java.util.*;
/**
 * 2016-4-22
 * 最小堆存储k个lists的头,每次出一个,N*logK,K是lists个数,N是所以list的节点和
 * 不知道为什么会超时
 */
public class TLE{
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);
        ListNode resultP = result;   
        int N = lists.length;
        Heap heap = new Heap(N);
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null)
                heap.add(lists[i]);
        }
        heap.arrangeHeap();
        ListNode node = heap.remove();
        while(node!=null){
            resultP.next = node;
            resultP = resultP.next;
            if(node.next!=null){
                heap.add(node.next);
                heap.arrangeHeap();
            }
            node = heap.remove();
        }
        resultP.next=null;
        return result.next;
    }    
    public static void main(String args[]){
        int N = 10000;
        ListNode[] lists = new ListNode[N];
        for(int i=0;i<N;i++){
            lists[i] = new ListNode(N-i);//10*i
            ListNode tmp = lists[i];
            for(int j=1;j<1;j++){
                tmp.next = new ListNode(j+10*i);
                tmp = tmp.next;
            }
        }
        ListNode result = new TLE().mergeKLists(lists);
        while(result!=null){
            System.out.print(result.val+" ");
            result = result.next;
        }
        System.out.println();
    }
    class Heap{//small heap
        ListNode[] heap;
        int index = 1;
        private void swap(int i,int j){
            ListNode t = heap[i];
            heap[i] = heap[j];
            heap[j] = t;
        }
        public Heap(int N){
            heap = new ListNode[N+1];
        }
        public void showHeap(){
            for(int i=1;i<index;i++)
                System.out.print(heap[i].val+" ");
            System.out.println();   
        }    
        public boolean add(ListNode node){
            if(index>heap.length)
                return false;
            heap[index++]=node;
            return true;
        }
        public void arrangeHeap(){
            for(int i=(index-1)/2;i>=1;i--){
                int j=i;
                while(j<=(index-1)/2){
                    if(heap[j].val<=heap[j*2].val&&(j*2+1>=index||heap[j].val<=heap[j*2+1].val))
                        break;
                    else if(heap[j].val>heap[j*2].val){
                        if(j*2+1<index&&heap[j*2+1].val<heap[j].val&&heap[j*2+1].val<=heap[j*2].val){
                            swap(j,j*2+1);
                            j=j*2+1;
                        }
                        else{
                            swap(j,j*2);
                            j=j*2;                            
                        }
                    }
                    else if(j*2+1<index){
                        swap(j,j*2+1);
                        j=j*2+1;
                    }
                }
            }
        }
        public ListNode remove(){
            if(index==1)
                return null;
            ListNode result = heap[1];
            heap[1]=heap[--index];
            arrangeHeap();
            return result;
        }
    }    
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}