import java.util.*;
/**
 * 2016-4-13
 */
public class Main{
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;    
        return symmetric(root.left,root.right);
    }    
    public boolean symmetric(TreeNode root1,TreeNode root2){
        if(root1==null&&root2!=null||root1!=null&&root2==null)
            return false;
        if(root1==null&&root2==null)
            return true;
        if(root1.val!=root2.val)
            return false;
        if(symmetric(root1.left,root2.right)&&symmetric(root1.right,root2.left))
            return true;
        return false;
    }
    public static void main(String args[]){
        int[] treeNum = {1,2,2,-1,3,-1,3};
        TreeNode root = TreeNode.createTree(TreeNode.createList(treeNum));
        TreeNode.showTree(root);
        System.out.println(new Main().isSymmetric(root));
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
