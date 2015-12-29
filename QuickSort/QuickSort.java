import java.util.*;
/**
 * 2016-1-21 快排的实现方法
 */
class Stack{
	public int start;
	public int end;
	public Stack next;
	public Stack(int start,int end,Stack next){
		this.start = start;
		this.end = end;
		this.next = next;
	}
}
public class QuickSort{
	/**
	 * 1.分治递归
	 */
	public static void quickSort(int[] nums,int start,int end){
		if(start>=end)
			return;
		int partition = partition_solution1(nums,start,end);
		quickSort(nums,start,partition-1);
		quickSort(nums,partition+1,end);
	}
	/**
	 * 2.循环，每次处理一半
	 */
	public static void quickSort_Solution1(int[] nums,int start,int end){
		if(start>=end)
			return;
		while(start<end){
			int partition = partition_solution1(nums,start,end);
			quickSort(nums,start,partition-1);
			start=partition+1;
		}
	}
	/**
	 * 3.使用栈的循环实现
	 */
	private static Stack stack = null;//没实现同步/锁死
	public static void quickSort_Solution2(int[] nums){
		if(nums==null || nums.length<=0)
			return;
		stack = new Stack(0,nums.length-1,null);
		while(stack!=null){
			if(stack.start>=stack.end){//pop
				stack = stack.next;
				continue;
			}
			int partition = partition_solution1(nums,stack.start,stack.end);
			stack = new Stack(stack.start,partition-1,new Stack(partition+1,stack.end,stack.next));//push			
		}
	}
	/**
	 * 1.partition经典实现 
	 * tmp被选作轴元素，p动的时候tmp位置在q，q动的时候在p，最后p==q的时候就得到tmp位置，即partition位置
	 * 使用其他轴元素的时候（比如随机选择），可以将选到元素与nums[end]交换，其余相同
	 */
	public static int partition(int[] nums,int start,int end){
		int tmp = nums[end];
		while(start<end){
			while(start<end && nums[start]<=tmp)
				start++;
			nums[end]=nums[start];
			while(start<end && nums[end]>=tmp)
				end--;
			nums[start]=nums[end];
		}
		nums[end]=tmp;
		return end;
	}
	/**
	 * 2.另一种partition方法，nums[end]被选作轴元素
	 * [最左侧，lessThan)都小于nums[end]，[lessThan,start)都大于等于nums[end]
	 * 使用其他轴元素的时候（比如随机选择），可以将选到元素与nums[end]交换，其余相同
	 */
	public static int partition_solution1(int[] nums,int start,int end){
		int lessThan = start;
		while(start<end){
			if(nums[start]<nums[end]){
				int tmp = nums[lessThan];
				nums[lessThan] = nums[start];
				nums[start] = tmp;
				lessThan++;
			}
			start++;
		}
		int tmp = nums[lessThan];
		nums[lessThan] = nums[end];
		nums[end] = tmp;
		return lessThan;
	}


	public static void main(String args[]){
		int[] nums = {3,5,7,2,1,8,9,33,23,9,9};
		//quickSort_(nums,0,nums.length-1);
		quickSort_Solution2(nums);
		for(int i:nums)
			System.out.print(i+" ");
		System.out.println();
	}
}