import java.util.*;
/**
 * 2016-1-2
 */
public class main{
    public static List<Integer> getRow(int rowIndex) {
    	rowIndex = rowIndex+1;
        List<Integer> result = new ArrayList<Integer>();
        if(rowIndex<=0)
        	return result;
        result.add(1);
        for(int i=1;i<rowIndex;i++){
        	result.add(1);
        	for(int j=i-1;j>0;j--){
        		result.set(j,result.get(j)+result.get(j-1));
        	}
        }
        return result;
    }	
	public static void main(String args[]){
		List<Integer> list = getRow(4);
		for(int i:list)
			System.out.print(i);
		System.out.println();
	}
}