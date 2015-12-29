import java.util.*;
/**
 * 2016-9-3 
 * 参考：http://www.cnblogs.com/shawnhue/archive/2013/06/05/leetcode_126.html
 * 这题挺难写，除了有几个点需要想到，还容易写错。
 * 直接看了参考，记住关键点，还是写好久才写对。。。
 *
 * 比较巧妙的地方：
 * 1.bfs天然能找到最短路。
 * 2.bfs过程，按层删点，可以防止回溯，且仍保证结果正确。(因为如果A-B，A-C，那么即使B-C相连，A-B-C...也一定比A-C长)
 * 3.backTrace找路径
 * 4.构造邻接表的方法，选择枚举所有变化而不是到wordList去扫描，因为一个word就length*26种变化，
 *   而wordList可能特别大。当然wordList小而word太长的时候，就还是枚举wordList好，这是两种思想。
 */
public class Main{
    public List<List<String>> findLadders(String beginWord, String endWord
    	, Set<String> wordList) {
    	wordList.add(beginWord);
    	wordList.add(endWord);	
    	List<List<String>> result = null;
    	Map<String,Set<String>> adj = buildAdjacentTable(wordList);
    	Map<String,Set<String>> backTraceRoute = new HashMap<>();//<son,father>
    	LinkedList<String> queue = new LinkedList<>();
    	queue.add(beginWord);
    	wordList.remove(beginWord);
    	int levelNum = 1;
	    int newLevelNum = 0;
    	while(!queue.isEmpty()&&result==null){
    		List<String> removeList = new ArrayList<>();
	    	for(int i=0;i<levelNum;i++){
	    		String father = queue.removeFirst();
	    		Set<String> neighbours = adj.get(father);
	    		if(neighbours==null)
	    			continue;
	    		for(String neighbour:neighbours){
	    			if(!wordList.contains(neighbour))
	    				continue;
	    			Set<String> fatherSet = backTraceRoute.get(neighbour);
	    			fatherSet=fatherSet==null?new HashSet<String>():fatherSet;
	    			fatherSet.add(father);
	    			backTraceRoute.put(neighbour,fatherSet);
	    			newLevelNum++;   
	    			removeList.add(neighbour); 			
	    			if(neighbour.equals(endWord))
						result = new ArrayList<List<String>>();
	    			else
	    				queue.add(neighbour);
	    		}
	    	}
	    	for(String s:removeList)
	    		wordList.remove(s);
	    	levelNum = newLevelNum;
	    	newLevelNum = 0;
	    }
    	return backTrace(backTraceRoute,endWord,beginWord);
    }	
    private List<List<String>> backTrace(Map<String,Set<String>> backTraceRoute,
    	String word,String beginWord){
    	List<List<String>> result = new ArrayList<>();
    	if(word.equals(beginWord)){
    		List<String> list = new ArrayList<String>();
    		list.add(beginWord);
    		result.add(list);
    	}
    	else{
	    	Set<String> fatherSet = backTraceRoute.get(word);
	    	if(fatherSet==null)
	    		return result;
	    	for(String s:fatherSet){
	    		List<List<String>> tmp = backTrace(backTraceRoute,s,beginWord);
	    		for(List<String> list:tmp){
	    			list.add(word);
	    			result.add(list);
	    		}
	    	}
	    }
    	return result;
    }
    private Map<String,Set<String>> buildAdjacentTable(Set<String> wordList){
    	Map<String,Set<String>> adj = new HashMap<>();
    	for(String word:wordList){
    		Set<String> list = new HashSet<String>();
    		for(int i=0;i<word.length();i++){
    			for(char c='a';c<='z';c++){
    				if(c==word.charAt(i))
    					continue;
    				String s = word.substring(0,i)+c+word.substring(i+1,word.length());
    				if(wordList.contains(s))
    					list.add(s);
    			}
    		}
    		adj.put(word,list);
    	}
    	return adj;
    }
	public static void main(String args[]){
		Main main = new Main();
		// String beginWord = "hit";
		// String endWord = "cog";
		// Set<String> set = new HashSet<>();
		// set.add("hot");
		// set.add("dot");
		// set.add("dog");
		// set.add("lot");
		// set.add("log");

		String beginWord = "red";
		String endWord = "tax";
		Set<String> set = new HashSet<>();
		set.add("ted");
		set.add("tex");
		set.add("red");
		set.add("tax");
		set.add("tad");	
		set.add("den");	
		set.add("rex");	
		set.add("pee");	

		// String beginWord = "hot";
		// String endWord = "dog";
		// Set<String> set = new HashSet<>();
		// set.add("hot");
		// set.add("dog");						
		List<List<String>> result = main.findLadders(beginWord,endWord,set);
		for(List<String> list:result){
			for(String s:list){
				System.out.print(s+" ");
			}
			System.out.println();
		}		
	}
}