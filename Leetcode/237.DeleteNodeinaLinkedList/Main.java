import java.util.*;
/**
 * 2016-4-27
 */
public class Main{
    public void deleteNode(ListNode node) {
        while(node.next.next!=null){
            node.val = node.next.val;
            node=node.next;
        }   
        node.val = node.next.val;
        node.next = null;
    }    
    public static void main(String args[]){

    }
}