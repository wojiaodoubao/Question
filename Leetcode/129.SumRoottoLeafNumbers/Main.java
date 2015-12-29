import java.util.*;
/**
 * 2016-4-13
 * 再简洁一点?
 */
public class Main{
    public int sumNumbers(TreeNode root) {
        if(root!=null)
            return sum(root,0);       
        return 0;
    }
    public int sum(TreeNode root,int value){
        if(root==null)
            return -1;
        int result = value*10+root.val;
        int leftSum = sum(root.left,result);
        int rightSum = sum(root.right,result);
        if(leftSum==-1&&rightSum==-1)
            return result;
        else
            result=0;
        if(leftSum>0)
            result+=leftSum;
        if(rightSum>0)
            result+=rightSum;
        return result;
    }    
    public static void main(String args[]){
        int[] treeNum = {};
        TreeNode root = TreeNode.createTree(TreeNode.createList(treeNum));
        TreeNode.showTree(root);
        System.out.println(new Main().sumNumbers(root));
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