import java.util.*;
/**
 * 2016-4-20
 * 后续遍历的迭代(循环)实现,使用双栈.这个写法挺巧妙的,忽地一下想到的,不错.
 * 把stack1改成队列,把stack2的进栈顺序改为先rightChild后leftChild,则可以实现先序遍历的迭代实现.
 */
public class Main{
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();    
        if(root==null)
            return result;
        LinkedList<TreeNode> stack1 = new LinkedList<TreeNode>();
        LinkedList<TreeNode> stack2 = new LinkedList<TreeNode>();
        stack2.push(root);
        while(stack2.peekFirst()!=null){
            TreeNode node = stack2.pop();
            stack1.push(node);
            if(node.left!=null)
                stack2.push(node.left);            
            if(node.right!=null)
                stack2.push(node.right);
        }
        while(stack1.peekFirst()!=null)
            result.add(stack1.pop().val);
        return result;
    }
    public static void main(String args[]){
        int[] nums = {4,6,2,1,3,5};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        List<Integer> result = new Main().postorderTraversal(root);
        for(Integer i:result)
            System.out.print(i+" ");
        System.out.println();
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