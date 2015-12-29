import java.util.*;
/**
 * 2016-4-19
 * 这个是用一个队列,之前用两个队列...不好
 */
public class Main{
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root==null)
            return result;        
        LinkedList<TreeNode> treeNodeQueue = new LinkedList<TreeNode>();
        treeNodeQueue.add(root);
        while(treeNodeQueue.peekFirst()!=null)
        {
            int N = treeNodeQueue.size();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<N;i++){
                TreeNode node = treeNodeQueue.remove();
                list.add(node.val);
                if(node.left!=null)
                    treeNodeQueue.add(node.left);
                if(node.right!=null)
                    treeNodeQueue.add(node.right);
            }     
            result.addFirst(list);
        }
        return result;
    }    
    public static void main(String args[]){

    }
}