import java.util.*;
/**
 * 2016-4-21
 * 1.超时解法的想法是中序遍历,发现最后一层的最后一个节点之后退出,从而知道最后一层节点数量.最坏复杂度O(n)
 * 2.分治解法:每次分别计算左子树右子树树高,一样高则最后节点在右子树,否则在左子树.复杂度T(n)=T(n/2)+log(n)*2
 * Master Therom:由于log(n)不是多项式的大于1,所以不能用.
 * 这么考虑,每次计算log(n),每次砍掉一半所以至多log(n)次也砍完了,因此一定O(logn*logn).更精确的上届就不求了.
 */
public class Main{
    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        if(root.left==null)
            return 1;
        if(root.right==null)
            return 2;
        int lLength = 0;
        TreeNode p = root.left;
        while(p!=null){
            lLength++;
            p=p.left;
        }
        int rLength = 0;
        p=root.right;
        while(p!=null){
            p=p.left;
            rLength++;
        }
        //System.out.println(llLength+" "+lrLength+" "+rlLength+" "+rrLength);
        if(lLength==rLength)
            return calculate(lLength)+countNodes(root.right)+1;
        else{
            return countNodes(root.left)+calculate(rLength)+1;
        }
    }    
    private int calculate(int length){
        int sum = 1;
        while(length-->0)
            sum*=2;
        return sum-1;
    }

    //超时解法
    private int lastLevelNum = 0;
    private int levels = 0;
    public int timeLimitExceeded(TreeNode root){
        if(root==null)
            return 0;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        LinkedList<Integer> levelStack = new LinkedList<Integer>();
        while(root!=null){
            stack.push(root);
            root=root.left;
            levelStack.push(++levels);
        }
        while(stack.peekFirst()!=null){
            TreeNode node = stack.pop();
            int level = levelStack.pop();
            if(level==levels)
                lastLevelNum++;
            else if(node.left==null)
                break;
            if(node.right!=null){
                node=node.right;
                while(node!=null){
                    stack.push(node);
                    levelStack.push(++level);
                    node=node.left;
                }
            }
        }
        int sum = 1;
        while(--levels>0)
            sum*=2;
        sum=sum-1+lastLevelNum;
        return sum;        
    }
    public static void main(String args[]){
        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,361,362,363,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,433,434,435,436,437,438,439,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500};
        ArrayList<Integer> list = TreeNode.createList(nums);
        TreeNode root = TreeNode.createTree(list);
        System.out.println(new Main().countNodes(root));
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