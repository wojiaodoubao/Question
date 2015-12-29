import java.util.*;
/**
 * 2016-4-19
 * 写法不简洁
 * 使用LinkedList做队列,每层遍历之前记录一下队列元素数目N,循环N此输出node.val并将node.left,node.right加入队列
 * 从左向右的时候left,right加在队尾,此时从头到尾取node;从右向左的时候right,left加在队首,此时从尾到头取node
 */
public class Main{
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null)
            return result;        
        ArrayList<TreeNode> list1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> list2 = new ArrayList<TreeNode>();
        list1.add(root);
        boolean isL1 = true;
        while(list1.size()>0||list2.size()>0){
            if(isL1){
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int i=list1.size()-1;i>=0;i--){
                    TreeNode node = list1.get(i);
                    list.add(node.val);
                    if(node.left!=null)
                        list2.add(node.left);                    
                    if(node.right!=null)
                        list2.add(node.right);
                }
                list1=new ArrayList<TreeNode>();
                result.add(list);
                isL1=false;
            }
            else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int i=list2.size()-1;i>=0;i--){
                    TreeNode node = list2.get(i);
                    list.add(node.val);
                    if(node.right!=null)
                        list1.add(node.right);
                    if(node.left!=null)
                        list1.add(node.left);
                }
                list2=new ArrayList<TreeNode>();
                result.add(list);
                isL1=true;
            }
        }
        return result;
    }    
    public static void main(String args[]){
        int[] nums = {1,2,3,4,5,6,7,8,9,10,11};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);        
        List<List<Integer>> result = new Main().zigzagLevelOrder(root); 
        for(List<Integer> ilist:result){
            for(Integer i:ilist)
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