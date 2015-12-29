import java.util.*;
/**
 * 2016-9-3
 * dfs，因为有环，就记录了一下扫描过的点，遇到扫描过的直接返回扫描过的，不创建新节点
 * 乍一看不知道为什么正确，其实也简单，如果A->B->C->A，那遍历到C时候，给C返回了之前的A，C也就完整了，后退B也完整了，A也就完整了。 
 *
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Main{
	private Map<Integer,UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	return node==null?null:clone(node);
    }
    private UndirectedGraphNode clone(UndirectedGraphNode node) {
    	if(map.get(node.label)!=null)
    		return map.get(node.label);
        UndirectedGraphNode result = new UndirectedGraphNode(node.label);
        map.put(node.label,result);
        for(UndirectedGraphNode neighbor:node.neighbors){
        	result.neighbors.add(cloneGraph(neighbor));
        }
        return result;    	
    }
    public static void main(String args[]){

    }
}