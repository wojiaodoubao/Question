import java.util.*;
/**
 * 2016-9-3
 * 拓扑排序，Kahn算法
 */
public class Main{
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
    	List<Set<Integer>> edgeIn = new ArrayList<>();
    	List<Set<Integer>> edgeOut = new ArrayList<>();
    	for(int i=0;i<numCourses;i++){
    		edgeOut.add(new HashSet<Integer>());
    		edgeIn.add(new HashSet<Integer>());
    	}
    	for(int[] pre:prerequisites){
    		edgeIn.get(pre[0]).add(pre[1]);
    		edgeOut.get(pre[1]).add(pre[0]);
    	}  
    	LinkedList<Integer> zeroIn = new LinkedList<Integer>();
    	for(int i=0;i<numCourses;i++){
    		if(edgeIn.get(i).size()==0)
    			zeroIn.add(i);
    	}
        int n = numCourses;
    	while(zeroIn.size()>0){
    		int node = zeroIn.pollFirst();
            result[numCourses-n--] = node;
            for(int i:edgeOut.get(node)){
                edgeIn.get(i).remove(node);
                if(edgeIn.get(i).size()==0)
                    zeroIn.add(i);
            }
    	}
    	return n>0?new int[0]:result;
    }	
	public static void main(String args[]){
        Main main = new Main();
        int[][] prerequisites = {{0,1},{1,2},{2,3},{3,0}};
        int n = 4;
        int[] result = main.findOrder(n,prerequisites);
        for(int i:result)
            System.out.println(i);
	}
}