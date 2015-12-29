import java.util.*;
/**
 * 2016-4-13
 */
public class Main{
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        traversal(root,list);
        return list;        
    }    
    public void traversal(TreeNode root,List<Integer> list){
        if(root==null)
            return;
        list.add(root.val);        
        traversal(root.left,list);
        traversal(root.right,list);
    } 
    public static void main(String args[]){
        int[] treeNum = {1,-1,2,3};
        TreeNode root = TreeNode.createTree(TreeNode.createList(treeNum));
        TreeNode.showTree(root);
        List<Integer> list = new Main().inorderTraversal(root);
        for(Integer i:list)
            System.out.print(i+" ");
        System.out.println();
    }
}