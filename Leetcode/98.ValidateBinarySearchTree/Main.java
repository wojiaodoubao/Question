import java.util.*;
/**
 * 2016-4-12
 * 利用二叉排序树中根遍历是有序的来检测
 * 增加了TreeNode的生成方法,方便测试.可以到这里来拷贝.
 */
public class Main{
    static class M{
        int t;
        boolean flag = false;
        public boolean check(int t){
            if(flag==false){
                this.t=t;
                flag=true;
                return true;
            }
            if(t>this.t){
                this.t=t;
                return true;
            }
            return false;
        }
    }
    public boolean isValidBST(TreeNode root) {
        M m = new M();
        return validBST(root,m);               
    }
    public boolean validBST(TreeNode root,M m){
        if(root==null)
            return true;
        if(!validBST(root.left,m))
            return false;
        if(!m.check(root.val))
            return false;
        return validBST(root.right,m);
    }
    public static void main(String args[]){
        int[] nums = {1,1};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        TreeNode.showTree(root);
        System.out.println(new Main().isValidBST(root));
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
