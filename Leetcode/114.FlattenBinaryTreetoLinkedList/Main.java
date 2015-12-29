import java.util.*;
/**
 * 2016-4-19
 * 这题不难但是挺有意思,要把树按照中序遍历的顺序扁平化.
 * 开始忘了得把root.left置为null,囧
 */
public class Main{
    public void flatten(TreeNode root) {
        if(root==null)
            return;
        flat(root);
    }
    public TreeNode[] flat(TreeNode root){
        TreeNode[] result = new TreeNode[2];
        result[0]=root;
        result[1]=root;
        if(root.left==null&&root.right==null){
            return result;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left!=null){
            TreeNode[] flatten = flat(left);
            result[1].right = flatten[0];
            result[1] = flatten[1];
        }
        if(right!=null){
            TreeNode[] flatten = flat(right);
            result[1].right = flatten[0];
            result[1] = flatten[1];            
        }
        root.left=null;
        return result;
    }    
    public static void main(String args[]){
        int[] nums = {1,2,-1,3,-1,4,-1,5};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        new Main().flatten(root);
        while(root!=null){
            System.out.print(root.val+" ");
            if(root.left!=null){
                System.out.println("left is not null ! "+root.left.val);
            }
            root=root.right;
        }
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