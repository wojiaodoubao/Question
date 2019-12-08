class Solution {
    public int maximalSquare(char[][] matrix) {
    	if (matrix.length == 0) {
    		return 0;
    	}
    	int max = 0;
    	int column = matrix[0].length;
    	int row = matrix.length;
        int[][] nums = new int[row][column];
        for (int i=0;i<column;i++) {
        	nums[0][i] = matrix[0][i] == '1' ? 1 : 0;
        	if (matrix[0][i] == '1') {
        		max = 1;
        	}
        }
        for (int j=1;j<row;j++) {
        	for (int i=0;i<column;i++) {
        		if (i == 0) {
        			nums[j][i] = matrix[j][i] == '1' ? 1 : 0;
        		} else if (matrix[j][i] != '1') {
        			nums[j][i] = 0;
        		} else {
        			if (nums[j][i-1] > 0 && nums[j-1][i-1] > 0 && nums[j-1][i] > 0) {
        				nums[j][i] = Math.min(nums[j-1][i],Math.min(nums[j][i-1], nums[j-1][i-1])) + 1;
        			} else {
        				nums[j][i] = 1;
        			}
        		}
        		if (nums[j][i] > max) {
        			max = nums[j][i];
        		}
        	}
        }
        return max * max;
    }

    public static void main(String args[]) {
    	char[][] matrix = {
    		{'1','0','1','1','0'},
    		{'1','1','1','1','1'},
    		{'1','1','1','1','1'},
    		{'1','1','1','1','0'},
    		{'1','1','1','1','0'}
    	};
    	System.out.println(new Solution().maximalSquare(matrix));
    }
}