import java.util.*;
/**
 * 2016-4-19
 */
public class Main{
    public List<TreeNode> generateTrees(int n) {
        if(n<=0)
            return new ArrayList<TreeNode>();
        return generate(1,n);
    }
    public List<TreeNode> generate(int start,int end){
        List<TreeNode> result = new ArrayList<TreeNode>();
        if(start>end){
            result.add(null);
            return result;
        }
        for(int i=start;i<=end;i++){
            List<TreeNode> leftChildList = generate(start,i-1);
            List<TreeNode> rightChildList = generate(i+1,end);
            for(int j=0;j<leftChildList.size();j++){
                for(int k=0;k<rightChildList.size();k++){
                    TreeNode root = new TreeNode(i);
                    root.left = leftChildList.get(j);
                    root.right = rightChildList.get(k);
                    result.add(root);
                }
            }
        }        
        return result;
    }
    public static void main(String args[]){
        List<TreeNode> list = new Main().generateTrees(0);
        System.out.println(list.size());
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