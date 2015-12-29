import java.util.*;
/**
 * 2016-4-18
 * 情况想全比较难.
 * 后续遍历:
 * 每一个节点保存以这个节点为[一端]的最长路的值.当遍历到这个节点时,mid表示左子树-当前节点-右子树构成的最长路的值.
 * 一端=max{左儿子端,右儿子端,0}+当前值;mid={左儿子端,右儿子端,左儿子端+右儿子端,0}+当前值;
 *
 * 例如:5,4,8,11,-1,13,3,7,2,-1,-1,-1,1
 * val:7,2,18,22,13,1,4,21,27
 * mid:7,2,20,22,13,1,4,25,48
 */
public class Main{
    private Integer max = null;
    public int maxPathSum(TreeNode root) {
        pathSum(root);
        return max;
    }
    public void pathSum(TreeNode root){
        if(root==null)
            return;
        pathSum(root.left);
        pathSum(root.right);
        int mid = root.val;
        if(root.left!=null&&root.left.val>=0)
            mid+=root.left.val;
        if(root.right!=null&&root.right.val>=0)
            mid+=root.right.val;
        if(root.left==null){
            if(root.right!=null&&root.right.val>=0)
                root.val+=root.right.val;
        }
        else if(root.right==null){
            if(root.left!=null&&root.left.val>=0)
                root.val+=root.left.val;
        }
        else{
            if(root.left.val>root.right.val&&root.left.val>=0)
                root.val+=root.left.val;
            else if(root.right.val>=root.left.val&&root.right.val>=0)
                root.val+=root.right.val;
        }
        if(max==null)
            max=mid;
        else if(max<mid)
            max=mid;
        return;
    }    
    public static void main(String args[]){
        //int[] nums = {5,4,8,11,-1,13,3,7,2,-1,-1,-1,1};
        int[] nums = {3,-2};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        System.out.println(new Main().maxPathSum(root));
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