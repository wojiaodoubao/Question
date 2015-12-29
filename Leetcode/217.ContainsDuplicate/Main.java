import java.util.*;
/**
 * 2016-3-31
 * Hash
 */
public class Main{
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Object> map = new HashMap<Integer,Object>();
        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])==null)
                map.put(nums[i],new Object());
            else
                return true;
        }
        return false;
    }    
    public static void main(String args[]){
        int[] nums = {1,2,3,4,5};
        System.out.println(new Main().containsDuplicate(nums));
    }
}