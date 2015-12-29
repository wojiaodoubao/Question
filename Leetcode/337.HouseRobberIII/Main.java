import java.util.*;
/**
 * 2016-4-21
 * 这题乍一看挺唬人的
 * 二叉树的问题,用递归来考虑很多都会迎刃而解.
 */
public class Main{
    public int rob(TreeNode root) {
        return robber(root,true);
    }   
    public int robber(TreeNode root, boolean available){
        if(root==null)
            return 0;
        if(available){
            int leftAvailable = robber(root.left,true);
            int leftUnavailable = robber(root.left,false);
            int rightAvailable = robber(root.right,true);
            int rightUnavailable = robber(root.right,false);            
            if(leftAvailable+rightAvailable>leftUnavailable+rightUnavailable+root.val)
                return leftAvailable+rightAvailable;
            else
                return leftUnavailable+rightUnavailable+root.val;
        }
        else{
            int leftAvailable = robber(root.left,true);   
            int rightAvailable = robber(root.right,true);
            return leftAvailable+rightAvailable;
        }
    } 
    public static void main(String args[]){
        int[] nums = {3,2,3,-1,3,-1,1};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        System.out.println(new Main().rob(root));
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    public static TreeNode createTree(ArrayList<Integer> nums){
        TreeNode root = null;
        if(nums==null||nums.size()<=0)
            return root;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        root=new TreeNode(nums.get(0));
        queue.add(root);
        int i=1;
        while(queue.size()>0){
            TreeNode node = queue.remove();
            if(i<nums.size()&&nums.get(i)!=null){
                node.left = new TreeNode(nums.get(i));
                queue.add(node.left);
            }
            i++;            
            if(i<nums.size()&&nums.get(i)!=null){
                node.right=new TreeNode(nums.get(i));
                queue.add(node.right);
            }
            i++;            
        }
        return root;
    }
    public static ArrayList<Integer> createList(int[] nums){//-1 for null
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(nums==null||nums.length<=0)
            return list;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==-1)
                list.add(null);
            else
                list.add(nums[i]);
        }
        return list;
    }
    public static void showTree(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null)
            queue.add(root);
        while(queue.size()>0){
            TreeNode node = queue.remove();
            if(node!=null){
                System.out.print(node.val+" ");
                queue.add(node.left);
                queue.add(node.right);
            }
            else
                System.out.print("null ");
        }
        System.out.println();
    }
}