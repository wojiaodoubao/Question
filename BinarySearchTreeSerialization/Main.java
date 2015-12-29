import java.util.*;
/**
 * 2016-4-25
 * 今天面试微软,第一个面试官问:二叉搜索树怎么序列化和反序列化? 当时没想出来,后来等第二个面试官的时候想到了,不过没什么卵用了.
 * 其实就是二叉搜索树的先序遍历,就可以作为它的序列化.因为遇到比a大的那就一定在它右子树,比a小的一定在左子树.
 *
 * 就到此为止了,不再为面试准备什么了,零碎的东西看起来太耗费时间了.开始集中时间做些有意义的事.
 * 面试的时候也感觉到了,单纯看一些知识点面试题什么的作用也不大,不同人侧重点也不一样,知识点面试题准备再多也是准备不全的
 * 更好的策略是自己有所长,然后就走这个方向,面试官发现你擅长这个方向也就开始问你这个方向的东西,把面试官引到自己方向上而不是跟着乱跑.
 * 
 * 一次面试过了就高兴,砸了就郁闷;一次考试高了就兴高采烈,低了就郁郁寡欢;成功了就得意忘形,失败了就妄自菲薄;
 * 一天根本带不来什么实力上的改变,一次面试,一次考试,前后不过几个小时,不会因为一次成功就变强一次失败就变弱,变的只是心态.
 * 要自信,也要看到未来的路还有好长.
 */
public class Main{
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public TreeNode binarySearchTree(int[] nums){//反序列化 BST先序遍历->BST
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(-1);
        stack.push(root);
        for(int i=0;i<nums.length;i++){
            TreeNode treeNode = stack.peekFirst();
            if(nums[i]<treeNode.val){
                treeNode.left = new TreeNode(nums[i]);
                stack.push(treeNode.left);
            }
            else{
                treeNode = stack.pop();
                while(stack.peekFirst()!=null&&nums[i]>=stack.peekFirst().val)
                    treeNode = stack.pop();
                treeNode.right = new TreeNode(nums[i]);
                stack.push(treeNode.right);
            }
        }
        return root.right;
    }
    public List<Integer> midOrder(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        while(root!=null){
            stack.push(root);
            root = root.left;
        }
        while(stack.peekFirst()!=null){
            TreeNode node = stack.pop();
            result.add(node.val);
            node = node.right;
            while(node!=null){
                stack.push(node);
                node = node.left;
            }
        }
        return result;
    }
    public static void main(String args[]){
        Main main = new Main();
        int[] nums = {10,4,2,1,3,5,7,15};
        List<Integer> list = main.midOrder(main.binarySearchTree(nums));       
        for(Integer i:list)
            System.out.print(i+" ");
        System.out.println();

    }
}