import java.util.;
/**
 * 2016-4-27
 * O(2n),第一遍构造副本,map和mapNode,第二遍迭代map-key(等价与扫描一遍所有节点)完成random指向
 * 这题就是费空间,其他没什么.
 */
public class Main{
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode result = new RandomListNode(-1);
        RandomListNode R = result;
        HashMap<RandomListNode,List<RandomListNode>> map = new HashMap<RandomListNode,List<RandomListNode>>();
        HashMap<RandomListNode,RandomListNode> mapNode = new HashMap<RandomListNode,RandomListNode>();
        while(head!=null){
            //copy to result
            RandomListNode node = new RandomListNode(head.label);
            result.next = node;
            result = result.next;  
            //map head node to result node
            mapNode.put(head,node);
            //deal random pointer          
            List<RandomListNode> tmpList = map.get(head.random);
            if(tmpList==null){
                tmpList = new ArrayList<RandomListNode>();
                map.put(head.random,tmpList);
            }
            tmpList.add(node);
            head = head.next;
        }
        for(RandomListNode node:map.keySet()){
            List<RandomListNode> tmpList = map.get(node);
            RandomListNode k = mapNode.get(node);
            for(RandomListNode n:tmpList){
                n.random = k;
            }            
        }
        return R.next;    
    }    
    public static void main(String args[]){

    }
}
class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}