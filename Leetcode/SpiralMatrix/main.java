import java.util.*;
/**
 * 2015-12-30
 * 写得又慢又不好。。。
 */
public class main{
	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
        if(matrix.length<=0 || matrix[0].length<=0)
            return result;
		int x = 0;
		int y = 0;
		int width = matrix[0].length;
		int height = matrix.length;
		String state = "right";
        while(width>0 && height>0){
            if(state.equals("right") && width>0 && height>0){
                for(int i=0;i<width;i++){
                	result.add(matrix[x][y+i]);
                }
                height--;
                x = x+1;
                y = y+width-1;
                state = "down";
            }
            else if(state.equals("down") && width>0 && height>0){
                for(int i=0;i<height;i++){
                	result.add(matrix[x+i][y]);
                }
                width--;
                x = x+height-1;
                y = y-1;
                state = "left";
            }
            else if(state.equals("left") && width>0 && height>0){
                for(int i=0;i<width;i++){
                	result.add(matrix[x][y-i]);
                }
                height--;
                x = x-1;
                y = y-width+1;
                state = "up";
            }
            else if(state.equals("up") && width>0 && height>0){
                for(int i=0;i<height;i++){
                	result.add(matrix[x-i][y]);
                }
                width--;
                x = x-height+1;
                y = y+1;
                state = "right";
            }
            else{}
        }    	
    	return result;
    }

	public static void main(String args[]){
		int[][] matrix = {
 { 1, 2, 3 },
 { 4, 5, 6 },
 { 7, 8, 9 }
};
		System.out.println(spiralOrder(matrix));
	}
}