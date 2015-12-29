import java.util.*;
/**
 * 2016-4-1
 * 可证,这题不能DP
 * 情况还是没有一次考虑全,任重道远啊
 */
public class Main{
    public List<List<Integer>> combinationSum3(int k, int n) {
        return sum(k,n,1,9);
    }
    public List<List<Integer>> sum(int k,int n,int MIN,int MAX){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(n==0){
            result.add(new ArrayList<Integer>());
            return result;
        }
        if(k<=0)
            return result;        
        for(int i=MIN;i<=MAX;i++){
            if(capable(k-1,n-i,i+1,MAX)){
                List<List<Integer>> tmp = sum(k-1,n-i,i+1,MAX);
                for(List<Integer> list:tmp){
                    list.add(0,i);
                    result.add(list);
                }
            }
        }
        return result;
    }
    public boolean capable(int k,int n,int MIN,int MAX){
        if(MAX-MIN+1<k)
            return false;
        if((MIN+MIN+k-1)*k/2>n)
            return false;
        if((MAX-k+1+MAX)*k/2<n)
            return false;
        return true;
    }
    public static void main(String args[]){
        Main main = new Main();
        List<List<Integer>> result = main.combinationSum3(3,15);
        for(List<Integer> list:result){
            for(Integer i:list)
                System.out.print(i+" ");
            System.out.println();
        }        
    }
}