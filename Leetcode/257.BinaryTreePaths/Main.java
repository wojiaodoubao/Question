import java.util.*;
/**
 * 2016-4-21
 */
public class Main{
    public List<String> binaryTreePaths(TreeNode root) {  
        List<String> result = new ArrayList<String>();      
        if(root==null)
            return result;
        if(root.left==null&&root.right==null){
            List<String> list = new ArrayList<String>();
            list.add(root.val+"");
            return list;
        }
        if(root.left!=null){
            List<String> list = binaryTreePaths(root.left);
            for(int i=0;i<list.size();i++)
                list.set(i,root.val+"->"+list.get(i));
            result.addAll(list);
        }
        if(root.right!=null){
            List<String> list = binaryTreePaths(root.right);
            for(int i=0;i<list.size();i++)
                list.set(i,root.val+"->"+list.get(i));
            result.addAll(list);
        }
        return result;
    }    
    public static void main(String args[]){

    }
}