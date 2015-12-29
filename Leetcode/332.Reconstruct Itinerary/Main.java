import java.util.*;
/**
 * 2016-9-4
 * 欧拉路径问题，经典问题
 * 写得笨，见文件-欧拉路
 */
public class Main{
    public List<String> findItinerary(String[][] tickets) {
    	Map<String,List<String>> adjacent = new HashMap<>();
    	int edgeNum = 0;
    	for(String[] ss:tickets){
    		List<String> list = adjacent.get(ss[0]);
    		if(list==null)
    			list = new ArrayList<String>();
    		list.add(ss[1]);
    		adjacent.put(ss[0],list);
    		edgeNum++;
    		Integer i = doneTicket.get(ss[0]+" "+ss[1]);
    		if(i==null)
    			i=0;
    		doneTicket.put(ss[0]+" "+ss[1],i+1);
    	}  
    	for(Map.Entry<String,List<String>> entry:adjacent.entrySet()){
    		Collections.sort(entry.getValue());
    	}
    	return dfs(adjacent,"JFK",edgeNum);
    }	
    private Map<String,Integer> doneTicket = new HashMap<String,Integer>();
    private List<String> dfs(Map<String,List<String>> adjacent,String node,int edgeNum){
    	List<String> result = new ArrayList<String>();
    	result.add(node);
    	if(edgeNum==0)
    		return result;
    	List<String> neighbours = adjacent.get(node);
    	if(neighbours==null)
    		return result;
    	for(String nei:neighbours){
    		String key = node+" "+nei;
    		int i = doneTicket.get(key);
    		if(i==0)
    			continue;
    		doneTicket.put(key,i-1);
    		List<String> list = dfs(adjacent,nei,edgeNum-1);
    		if(list.size()==edgeNum){
    			result.addAll(list);
    			return result;
    		}
    		doneTicket.put(key,i);
    	}
    	return result;
    }
	public static void main(String args[]){
		// String[][] tickets = {
		// 	{"JFK","A"},
		// 	{"JFK","B"},
		// 	{"B","C"},
		// 	{"C","JFK"},
		// 	{"JFK","B"},
		// 	{"B","C"},
		// };
		String[][] tickets = {{"MEL","PER"},{"SYD","CBR"},{"AUA","DRW"},{"JFK","EZE"},{"PER","AXA"},{"DRW","AUA"},{"EZE","SYD"},{"AUA","MEL"},{"DRW","AUA"},{"PER","ANU"},{"CBR","EZE"},{"EZE","PER"},{"MEL","EZE"},{"EZE","MEL"},{"EZE","TBI"},{"ADL","DRW"},{"ANU","EZE"},{"AXA","ADL"}};
		// String[][] tickets = {
		// 	{"JFK","A"},
		// 	{"A","B"},
		// 	{"B","C"},
		// 	{"C","JFK"},
		// 	{"JFK","E"}			
		// };		
		// String[][] tickets = {};
		List<String> list = new Main().findItinerary(tickets);
		for(String s:list){
			System.out.print(s+" ");
		}
		System.out.println();
	}
}