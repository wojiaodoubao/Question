import java.util.*;
/**
 * 2016-10-10
 * DP,Binary Search
 * 解法实在丑陋，但还是说一下：
 * 1.从右向左扫描，i from n-1 to 0，i表示点索引(key)，value表示该点构成的最长序列的长度。
 * 2.此题就是需要从i右侧的点中选出比i大且value值最大的点，则i的value为该点的value+1。
 * 3.将i右侧的点按值构成一个二叉排序树root，相同节点只包括最近者。
 * 4.相同节点只包括最近者是贪心的思想，容易验证，i与较近者构成的序列一定不差于i与较远者构成的序列。
 * 5.算法递归运行:如果i对应的值比当前根节点大，那么root.left可以舍弃掉；
 *              如果i对应的值比当前根节点小，那么root.left和right都不能舍弃，都需要计算；
 *              如果i对应的值与当前节点一样大，root.left可以舍弃，最后需要把此root更新为更近者。
 * 6.递归时候保存一路上遇到的最大value，当根节点大或等时value不变，根节点小时，value=max(root,value)。
 *
 *
 * Brilliant
 * https://discuss.leetcode.com/topic/28738/java-python-binary-search-o-nlogn-time-with-explanation
 * 想法巧妙，代码简短，这么短却很巧妙的契合各种边界情况，感觉很厉害。
 * 它的想法是:
 * 1.维护一个tail数组，tail[i]存的是长度为i+1的序列的最小结尾，比如两个2序列4,5和5,6，那tail[1]=5。
 * 2.size表示tail数组的大小，也表示现在最长的序列已经有size这么长了。
 * 3.for(x:nums)，tail表示的是x左侧所有值构造出的tail结果，现在只需要用x对tail进行更新，就能得到包括x的tail结果。
 *   在tail中找到>=x的最小值tail[i]:
 *   (1) if x is larger than all tails, append it(tail[++size]=x), increase the size by 1
 *   (2) if tails[i-1] < x <= tails[i], update tails[i]=x
 * 4.容易验证if j>i，then tail[j]>tail[i]，即tail是递增序列，用x更新tail时可以在tail上二分查找。
 * public int lengthOfLIS(int[] nums) {
 *    int[] tails = new int[nums.length];
 *    int size = 0;
 *    for (int x : nums) {//即使nums的最后结果是size=nums.length也没事，因为那时也不会再进循环了
 *        int i = 0, j = size;
 *        while (i != j) {//找到>=x的最小tail，巧
 *            int m = (i + j) / 2;
 *            if (tails[m] < x)
 *                i = m + 1;
 *            else
 *                j = m;
 *        }
 *        tails[i] = x;//不论是不是大于所有tail都update，巧
 *        if (i == size) ++size;//大于所有tail，size++
 *    }
 *    return size;
 * }
 * // Runtime: 2 ms 
 */
public class Main{
	private class Node{
		int key;
		int value;
		Node left;
		Node right;
		public Node(int key,int value){
			this.key = key;
			this.value = value;
		}
	}
	private int[] nums;
    public int lengthOfLIS(int[] nums) {
    	if(nums==null||nums.length==0)
    		return 0;
    	this.nums = nums;
    	Node root = new Node(nums.length-1,1);
    	int max = 1;
    	for(int i=nums.length-2;i>=0;i--){
    		sameNode=null;
    		int tmp = length(root,i,0);
    		max = max>tmp?max:tmp;
    	}
    	//show(root);
    	return max;
    }	
    private void show(Node root){
    	if(root==null)
    		return;
    	show(root.left);
    	System.out.println(root.key+" "+nums[root.key]+" "+root.value);
    	show(root.right);
    }
    private Node sameNode;
    public int length(Node root,int key,int value){
    	if(nums[key]>=nums[root.key]){
    		if(nums[key]==nums[root.key])
    			sameNode = root;
    		if(root.right==null){
    			if(sameNode!=null){
    				sameNode.key = key;
    				sameNode.value = value+1;
    			}
    			else
    				root.right = new Node(key,value+1);
    			return value+1;
    		}
    		else
    			return length(root.right,key,value);
    	}
    	else{
    		value = value>root.value?value:root.value;
    		int ttt = -1;
    		if(root.right!=null)
    			ttt = length(root.right,key,value);
    		if(root.left==null){
    			if(sameNode!=null){
    				sameNode.key = key;    				
    				sameNode.value = value+1;    			
    			}
    			else
    				root.left = new Node(key,value+1);
    			ttt = ttt>value+1?ttt:value+1;
    		}
    		else{
    			int l = length(root.left,key,value);
    			ttt = ttt>l?ttt:l;
    		}
    		return ttt;
    	}
    }
	public static void main(String args[]){
		//int[] num = {1,3,6,7,9,4,10,5,6};
		int[] num = {2,15,3,7,8,6,18};
		System.out.println(new Main().lengthOfLIS(num));
	}
}