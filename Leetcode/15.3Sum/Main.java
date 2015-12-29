import java.util.*;
/**
 * 2016-4-2
 * 2-pointers降维过了,这个解法是对的,18里面剪枝的方法是碰巧过的.
 * hash那个没过,有一个case一直是10^7ns级的,而2-pointers是10^6ns级的
 * 我猜可能是direct_hash:1.没剪枝,2.有一个3数排序,3.去重
 * 实际测试:
 * 1.剪枝不解决根本问题,2-pointers去掉剪枝也能过,hash增加剪枝也过不了
 * 2.改成像2-pointers一样先排序,从而去掉了三数排序和去重,同时增加剪枝,还是过不了.(sort_hash)
 * 这个时候sort_hash与2-pointers的区别已经只有处理i+1~length-1这一部分了,2-p是两指针处理,hash是hashmap处理
 * hash是两边扫描,不知道是不是因为这个过不了,复杂度我觉得没算错.
 *
 * 有剪枝的sort_hash最快,然后是无剪枝的sort_hash,最后是direct_hash,但仍然都是10^7ns级
 */
public class Main{
    public void quickSort(int[] nums,int left,int right){
        if(left>=right)
            return;
        int L = left;
        int R = right;
        int tmp = nums[right];
        while(left<right){
            while(left<right&&nums[left]<=tmp)
                left++;
            nums[right]=nums[left];
            while(left<right&&nums[right]>=tmp)
                right--;
            nums[left]=nums[right];
        }
        nums[left]=tmp;
        quickSort(nums,L,left-1);
        quickSort(nums,left+1,R);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        quickSort(nums,0,nums.length-1);
        //return two_pointers(nums);
        return sort_hash(nums);
    }   
    public List<List<Integer>> two_pointers(int[] nums){
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<nums.length-2;){
            //prune
            if((nums[i]+nums[i+1]+nums[i+2])>0)
                break;
            while(i<nums.length-2&&(nums[nums.length-1]+nums[nums.length-2]+nums[i])<0)
                i++;
            //2 pointers
            int l=i+1;
            int r=nums.length-1;
            while(l<r){
                if(nums[i]+nums[l]+nums[r]>0)
                    r--;
                else if(nums[i]+nums[l]+nums[r]<0)
                    l++;
                else{
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(nums[i]);
                    tmp.add(nums[l]);
                    tmp.add(nums[r]);
                    result.add(tmp);
                    l++;
                    while(l<r&&nums[l]==nums[l-1])
                        l++;
                }
            }
            i++;
            while(i<nums.length-2&&nums[i]==nums[i-1])
                i++;            
        }
        return result;
    }
    public List<List<Integer>> sort_hash(int[] nums){//对排序过的array利用hash降维
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<nums.length-2;){      
            //prune
            if((nums[i]+nums[i+1]+nums[i+2])>0)
                break;
            while(i<nums.length-2&&(nums[nums.length-1]+nums[nums.length-2]+nums[i])<0)
                i++;            
            //hash
            HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
            for(int j=0;j<nums.length;j++){
                if(map.get(nums[j])==null)
                    map.put(nums[j],1);
                else
                    map.put(nums[j],map.get(nums[j])+1);                
            }            
            for(int j=i+1;j<nums.length-1;j++){
                Integer n = map.get(0-(nums[i]+nums[j]));
                if(n==null)
                    continue;
                if((nums[i]!=nums[j]&&nums[j]==(0-nums[i]-nums[j])&&n<2)
                    ||(nums[i]==nums[j]&&nums[j]==(0-nums[i]-nums[j])&&n<3))
                    continue;
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                tmp.add(nums[i]);
                tmp.add(nums[j]);
                tmp.add(0-nums[i]-nums[j]);             
                result.add(tmp);
                map.put(nums[i],null);
                map.put(nums[j],null);
            }
            i++;
            while(i<nums.length-2&&nums[i]==nums[i-1])
                i++; 
         }
         return result;       
    }    
    public List<List<Integer>> direct_hash(int[] nums){//对array(不需要排序)利用hash降维
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();
        for(int j=0;j<nums.length;j++){
            if(map.get(nums[j])==null)
                map.put(nums[j],1);
            else
                map.put(nums[j],map.get(nums[j])+1);                
        }
        Integer[] array = map.keySet().toArray(new Integer[0]);       
        for(int i=0;i<array.length-2;i++){
            //hash
            for(int j=i+1;j<array.length-1;j++){
                Integer n = map.get(0-(array[i]+array[j]));
                if(n==null)
                    continue;
                if((array[i]!=array[j]&&array[j]==(0-array[i]-array[j])&&n<2)
                    ||(array[i]==array[j]&&array[j]==(0-array[i]-array[j])&&n<3))
                    continue;
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                int[] p = sort_3(array[i],array[j],0-(array[i]+array[j]));
                tmp.add(p[0]);
                tmp.add(p[1]);
                tmp.add(p[2]);             
                if(!set.contains(tmp)){
                    result.add(tmp);
                    set.add(tmp);
                }
            }           
         }
         return result;       
    }  
    private int[] sort_3(int a,int b,int c){
        int[] result = new int[3];
        if(a<=b){
            if(b<=c){result[0]=a;result[1]=b;result[2]=c;}
            else{
                if(a<=c){result[0]=a;result[1]=c;result[2]=b;}
                else{result[0]=c;result[1]=a;result[2]=b;}
            } 
        }
        else{
            if(b>=c){result[0]=c;result[1]=b;result[2]=a;}
            else{
                if(a>=c){result[0]=b;result[1]=c;result[2]=a;}
                else{result[0]=b;result[1]=a;result[2]=c;}
            }
        }
        return result;
    }
    public static void main(String args[]){
        Main main = new Main();
        int[] candidates = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
        long time = System.nanoTime();
        List<List<Integer>> result = main.threeSum(candidates);
        System.out.println("nano time:"+(System.nanoTime()-time));
        /*
        for(List<Integer> list:result){
            for(Integer i:list)
                System.out.print(i+" ");
            System.out.println();
        }
        */
    }
}