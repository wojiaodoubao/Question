import java.util.*;
/**
 * 2015-12-30
 * 写得好烂，囧
 * 用不好List,Set,Map,Collection
 */
public class main{
	public static List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> result = null;
    	if(nums.length==0){
    		result = new ArrayList<List<Integer>>();
    		result.add(new ArrayList<Integer>());
    		return result;
    	}
    	int[] subnums = new int[nums.length-1];
    	for(int i=0;i<subnums.length;i++){
    		subnums[i] = nums[i];
    	}
    	result = subsets(subnums);
    	List<List<Integer>> newSubSets = new ArrayList<List<Integer>>();
    	for(List<Integer> list : result){
    		List<Integer> tmp = new ArrayList<Integer>();
    		tmp.addAll(list);
    		tmp.add(nums[nums.length-1]);
    		Collections.sort(tmp);
    		newSubSets.add(tmp);
    	}
    	result.addAll(newSubSets);
    	return result;
    }
	public static void main(String args[]){
		int[] nums = {4,1,0};
		System.out.println(subsets(nums));
	}
}