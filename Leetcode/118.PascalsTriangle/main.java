import java.util.*;
/**
 * 2016-1-2
 */
public class main{
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<numRows;i++){
        	List<Integer> list = new ArrayList<Integer>();
        	if(i==0)
        		list.add(1);
        	else{
	        	for(int j=0;j<=i;j++){
	        		if(j==0 || j==i)
	        			list.add(1);//result.get(i-1).get(0);
	        		else
	        			list.add(result.get(i-1).get(j-1)+result.get(i-1).get(j));
	        	}
	        }
	        result.add(list);
        }
        return result;
    }	
	public static void main(String args[]){
		List<List<Integer>> result = generate(1);
		for(List<Integer> list:result){
			for(int i:list)
				System.out.print(i);
			System.out.println();
		}
	}
}