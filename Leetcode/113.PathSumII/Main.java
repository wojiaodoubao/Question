import java.util.*;
/**
 * 2016-4-19
 */
public class Main{
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null)
            return result;
        path(root,sum);
        return result;
    }
    LinkedList<Integer> list = new LinkedList<Integer>();
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    public void path(TreeNode root, int sum){
        if(root.left==null&&root.right==null){
            if(root.val==sum){
                list.addLast(root.val);
                result.add(deepCopy(list));
                list.removeLast();
            }
        }
        if(root.left!=null){
            list.addLast(root.val);
            path(root.left,sum-root.val);
            list.removeLast();
        }
        if(root.right!=null){
            list.addLast(root.val);
            path(root.right,sum-root.val);
            list.removeLast();
        }
    }   
    public LinkedList<Integer> deepCopy(LinkedList<Integer> list){
        LinkedList<Integer> result = new LinkedList<Integer>();
        for(Integer i:list){
            result.addLast(i);
        }
        return result;
    }
    public static void main(String args[]){
        int[] nums = {-5,4,8,11,-1,13,4,7,2,-1,-1,5,1};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        List<List<Integer>> result = new Main().pathSum(root,12);
        for(List<Integer> l:result){
            for(Integer i:l)
                System.out.print(i+" ");
            System.out.println();
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