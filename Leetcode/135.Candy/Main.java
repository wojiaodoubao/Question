import java.util.*;
/**
 * 2016-4-12 距离上次刷题隔了好几天
 * 这题想法不难,就从左向右扫描,单调递增则糖单调递增,单调递减则获取整个减区间之后反向计算,ratings值相同的区间,发一颗糖,但是区间两个端点需要特殊考虑
 * 麻烦就在于怎么写能把情况变得很少!递增遇到同,递减遇到同:同的前端点接增,接减,后端点接增,接减,一个个都判断代码就写得傻.
 * 我刚开始就傻了,其实扫描两遍一遍左一遍右,比较容易;我这个从左到右一遍,就得回溯计算递减区间,掺着增减一起写,就麻烦一点,结果我本身还傻逼,麻烦一点就写错!
 * 不开心啊不开心.
 *
 * 这种情况总碰到,就一股"道理我都懂,就是不会写"的感觉.真是捉急.
 * 京东笔试时候一道题,在国际象棋棋盘上放置两个点AB,输出从A走到B的最短路,要求能走斜线就不走直线,比如(0,0)-(3,5)就RU,RU,RU,U,U这样.
 * 这个也是,道理我都懂,就想写短一点,就想不明白,写了半天.我是output="",再根据情况构造output为RD,RU,LD,LU,最后再R,D,L,U.应该还能更短.
 */
public class Main{
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        int index = -1;
        for(int i=0;i<candy.length+1;i++){
            if(i==0)
                candy[i]=1;
            else if(i==candy.length||ratings[i]>ratings[i-1]){
                if(index!=-1){
                    int l=1;
                    candy[i-1]=1;
                    for(int j=i-2;j>=index;j--){
                        if(ratings[j]>ratings[j+1])
                            candy[j]=candy[j+1]+1;
                        else if(ratings[j]<ratings[j-1])
                            candy[j]=1;
                    }
                    if(candy[index-1]<candy[index]+1)
                        candy[index-1]=candy[index]+1;
                    index=-1;
                }
                if(i<candy.length)
                    candy[i]=candy[i-1]+1;
            }
            else if(ratings[i]<ratings[i-1]){
                if(index==-1)
                    index=i;
            }
            else
                candy[i]=1;
        }
        int result = 0;
        for(int i:candy){
            System.out.print(i+" ");
            result+=i;
        }
        System.out.println();
        return result;
    }        
    public static void main(String args[]){
        int[][] ratings = {
            {5,4,3,3,3},
            {9,9,9,8,7,6,5,4},
            {9,8,7},
            {9,9,8,7,6,6},
            {3,3},
            {1,2,2},
            {1,2},
            {1,1,2,2,3,3},
            {1,10,5,10,5},
            {90,90,10,10,30,30,20,20,30,30,10,10,20,20},
            {90,80,80,30},
            {26,54,54,99}
        };
        for(int i=0;i<ratings.length;i++)
            System.out.println(new Main().candy(ratings[i]));
    }
}