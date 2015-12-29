import java.util.*;
/**
 * 2015-12-30
 * 写得好烂，囧
 * 用不好List,Set,Map,Collection
 */
public class main{
    public static HashSet<List<Integer>> subsets(List<Integer> nums) {
        HashSet<List<Integer>> result = null;
        if(nums.size()==0){
            result = new HashSet<List<Integer>>();
            result.add(new ArrayList<Integer>());
            return result;
        }
        List<Integer> subnums = nums.subList(0,nums.size()-1);
        result = subsets(subnums);
        HashSet<List<Integer>> newSubSets = new HashSet<List<Integer>>();
        for(List<Integer> list : result){
            List<Integer> tmp = new ArrayList<Integer>();
            tmp.addAll(list);
            tmp.add(nums.get(nums.size()-1));
            newSubSets.add(tmp);
        }
        result.addAll(newSubSets);
        return result;
    }
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++)
        {
            list.add(nums[i]);
        }
        Collections.sort(list);
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        for(List<Integer> l : subsets(list))
        {
            result.add(l);
        }
        return result;
    }
    public static void main(String args[]){
        int[] nums = {1,2,2};
        System.out.println(subsetsWithDup(nums));
    }
}