import java.util.*;
/**
 * 2016-4-18
 */
public class Main{
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root==null)
            return result;
        ArrayList<TreeNode> queue1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> queue2 = new ArrayList<TreeNode>();
        boolean isQ1 = true;
        queue1.add(root);
        while(queue1.size()>0||queue2.size()>0){
            if(isQ1){
                TreeNode node = queue1.remove(0);
                list.add(node.val);
                if(node.left!=null)
                    queue2.add(node.left);
                if(node.right!=null)
                    queue2.add(node.right);
                if(queue1.size()==0){
                    isQ1=false;
                    result.add(list);
                    list = new ArrayList<Integer>();
                }
            }
            else{
                TreeNode node = queue2.remove(0);
                list.add(node.val);
                if(node.left!=null)
                    queue1.add(node.left);
                if(node.right!=null)
                    queue1.add(node.right);
                if(queue2.size()==0){
                    isQ1=true;
                    result.add(list);
                    list = new ArrayList<Integer>();                    
                }
            }
        }
        return result;
    }    
    public static void main(String args[]){
        int[] nums = {1,-1,2,-1,3};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        List<List<Integer>> result = new Main().levelOrder(root);
        for(List<Integer> l:result){
            for(Integer i:l){
                System.out.print(i+" ");
            }
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