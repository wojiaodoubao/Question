import java.util.*;
/**
 * 2016-3-31
 * Hash
 */
public class Main{
    public boolean containsNearbyDuplicate(int[] nums,int k) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])!=null&&i-map.get(nums[i])<=k)
                return true;
            else
                map.put(nums[i],i);
        }
        return false;
    }    
    public static void main(String args[]){
        int[] nums = {1,2,3,4,3};
        System.out.println(new Main().containsNearbyDuplicate(nums,2));
    }
}