import java.util.*;
/**
 * 2016-4-18
 * 栈中序遍历:用栈空间是O(h)的,h是树高度.
 */
public class BSTIterator{
    class Node{
        TreeNode node;
        Node next;
        public Node(TreeNode node){
            this.node = node;
        }
    }
    private Node stack;
    public BSTIterator(TreeNode root) {
        stack = new Node(null);
        while(root!=null){
            Node node = new Node(root);
            node.next = stack.next;
            stack.next = node;
            root=root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(stack.next==null)
            return false;
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        Node node = stack.next;
        stack.next = stack.next.next;
        if(node.node.right!=null){
            TreeNode treeNode = node.node.right;
            while(treeNode!=null){
                Node tmpNode = new Node(treeNode);
                tmpNode.next = stack.next;
                stack.next = tmpNode;
                treeNode = treeNode.left;
            }
        }
        return node.node.val;    
    }    
    public static void main(String args[]){
        //int[] nums = {4,6,2,1,3,-1,5};
        int[] nums = {};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        BSTIterator main = new BSTIterator(root);
        while(main.hasNext()){
            System.out.print(main.next()+" ");
        }
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