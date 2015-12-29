import java.util.*;
/**
 * 2016-9-2
 * 拓扑排序，Kahn算法
 */
public class Main{
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    	List<Set<Integer>> edgeIn = new ArrayList<>();
    	List<Set<Integer>> edgeOut = new ArrayList<>();
    	for(int i=0;i<numCourses;i++){
    		edgeOut.add(new HashSet<Integer>());
    		edgeIn.add(new HashSet<Integer>());
    	}
    	for(int[] pre:prerequisites){
    		edgeIn.get(pre[1]).add(pre[0]);
    		edgeOut.get(pre[0]).add(pre[1]);
    	}  
    	LinkedList<Integer> zeroIn = new LinkedList<Integer>();
    	for(int i=0;i<numCourses;i++){
    		if(edgeIn.get(i).size()==0)
    			zeroIn.add(i);
    	}
    	while(zeroIn.size()>0){
            numCourses--;
    		int node = zeroIn.pollFirst();
            for(int i:edgeOut.get(node)){
                edgeIn.get(i).remove(node);
                if(edgeIn.get(i).size()==0)
                    zeroIn.add(i);
            }
    	}
    	return numCourses>0?false:true;
    }	
	public static void main(String args[]){
        Main main = new Main();
        int[][] prerequisites = {{0,1},{1,2},{2,3}};
        int n = 4;
        System.out.println(main.canFinish(n,prerequisites));
	}
}