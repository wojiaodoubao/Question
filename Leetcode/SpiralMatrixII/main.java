import java.util.*;
/**
 * 2015-12-30
 */
public class main{
    public static int[][] generateMatrix(int n) {
        int[][] result = null;
        if(n==0)
            return new int[0][0];
        result = new int[n][n];
        int width = n;
        int height = n;
        String state = "right";
        int x = 0;
        int y = 0;
        int index = 1;
        while(width>0 && height>0){
            if(state.equals("right") && width>0 && height>0){
                for(int i=0;i<width;i++){
                    result[x][y+i]=index++;
                }
                height--;
                x = x+1;
                y = y+width-1;
                state = "down";
            }
            else if(state.equals("down") && width>0 && height>0){
                for(int i=0;i<height;i++){
                    result[x+i][y]=index++;
                }
                width--;
                x = x+height-1;
                y = y-1;
                state = "left";
            }
            else if(state.equals("left") && width>0 && height>0){
                for(int i=0;i<width;i++){
                    result[x][y-i]=index++;
                }
                height--;
                x = x-1;
                y = y-width+1;
                state = "up";
            }
            else if(state.equals("up") && width>0 && height>0){
                for(int i=0;i<height;i++){
                    result[x-i][y]=index++;
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
        int[][] result = generateMatrix(3);
        for(int i=0;i<result.length;i++)
        {
            for(int j=0;j<result[0].length;j++)
                System.out.print(result[i][j]);
            System.out.println("");
        }
    }
}