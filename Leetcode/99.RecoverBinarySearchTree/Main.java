import java.util.*;
/**
 * 2016-4-15
 * 交换就是把一个后面的b和一个前面的a交换,先中序(左中右)遍历,把交换到前面的b挪回原位,再改中序(右中左)遍历把a挪回原位
 *
 * 一个O(n)空间的解法是:用一个int[n]数组把中序的结果存起来,然后把数组改成从小到大的,再中序遍历一遍树,把树节点的值改成数组对应的值.
 *
 * 这里用栈其实消耗了额外空间,最坏情况空间是O(n)的.递归传地址没这个问题,可以做到常量空间.
 * java递归都是传值的,写不了,用c++传地址倒是可以递归写,递归就得把上一节点做参数.
 * 两次遍历,写着方便容易理解.
 *
 * 刚看到一个java解法,递归,把TreeNode before作为Main的成员变量,对啊我咋没想到...
 */
public class Main{
    class Node{
        TreeNode treeNode;
        Node next;
        public Node(TreeNode treeNode){
            this.treeNode = treeNode;
        }
    }
    public void recoverTree(TreeNode root) {
        preOrder(root);
        postOrder(root);
    }
    public void preOrder(TreeNode root){
        TreeNode before = null;
        Node stack = new Node(null);
        stack.next = new Node(root);
        while(stack.next!=null){
            if(stack.next.treeNode==null){
                stack.next = stack.next.next;//pop
                if(stack.next==null){//stack is empty
                    break;
                }
                TreeNode treeNode = stack.next.treeNode;
                stack.next = stack.next.next;//pop
                Node node = new Node(treeNode.right);
                node.next = stack.next;
                stack.next = node;
                //System.out.print(treeNode.val+" ");
                if(before==null)
                    before=treeNode;
                else{
                    if(before.val>treeNode.val){
                        int swapInt = treeNode.val;
                        treeNode.val=before.val;
                        before.val=swapInt;
                    }
                    before=treeNode;
                }
            }
            else{
                Node node = new Node(stack.next.treeNode.left);
                node.next = stack.next;
                stack.next = node;
            }
        }
    } 
    public void postOrder(TreeNode root){
        TreeNode before = null;
        Node stack = new Node(null);
        stack.next = new Node(root);
        while(stack.next!=null){
            if(stack.next.treeNode==null){
                stack.next = stack.next.next;//pop
                if(stack.next==null){//stack is empty
                    break;
                }
                TreeNode treeNode = stack.next.treeNode;
                stack.next = stack.next.next;//pop
                Node node = new Node(treeNode.left);
                node.next = stack.next;
                stack.next = node;
                //System.out.print(treeNode.val+" ");
                if(before==null)
                    before=treeNode;
                else{
                    if(before.val<treeNode.val){
                        int swapInt = treeNode.val;
                        treeNode.val=before.val;
                        before.val=swapInt;
                    }
                    before=treeNode;
                }
            }
            else{
                Node node = new Node(stack.next.treeNode.right);
                node.next = stack.next;
                stack.next = node;
            }
        }
    }
    public void show(TreeNode root){
        if(root==null)
            return;
        show(root.left);
        System.out.print(root.val+" ");
        show(root.right);
    }
    public static void main(String args[]){
        int[] nums = {4,6,2,1,3,5};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        new Main().recoverTree(root);
        new Main().show(root);
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