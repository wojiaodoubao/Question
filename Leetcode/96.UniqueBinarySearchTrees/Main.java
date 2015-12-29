import java.util.*;
/**
 * 2016-4-18
 * 求二叉搜索树structurally unique和求二叉树structurally unique应该是一样的.
 * n节点结构定了二叉搜索树的值分配就定了.
 * 任何n节点结构都可以拿来分配值使之成为BST.
 */
public class Main{
    public int numTrees(int n) {
        if(n<=0)
            return 0;
        int[] nums = new int[n+1];
        nums[0]=1;
        nums[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<=i-1;j++){
                nums[i]+=nums[i-j-1]*nums[j];
            }
        }
        return nums[n];
    }    
    public static void main(String args[]){
        System.out.println(new Main().numTrees(3));
    }
}