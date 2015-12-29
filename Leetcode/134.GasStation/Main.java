import java.util.*;
/**
 * 2016-4-8
 * 久违的一次过!哈哈哈!
 */
public class Main{
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for(int i=0;i<gas.length;){
            int j = 0;
            int left=0;
            for(j=0;j<gas.length;j++){
                if(left>=0)
                    left+=gas[(j+i)%gas.length]-cost[(j+i)%gas.length];
                else
                    break;
            }
            if(left>=0&&j==gas.length)
                return i;
            i+=j;
        }
        return -1;        
    }
    public static void main(String args[]){
        int[] gas = {1,1,1,1,1,1,1,1};
        int[] cost = {0,2,0,0,3,3,0,0};
        System.out.println(new Main().canCompleteCircuit(gas,cost));
    }    
}