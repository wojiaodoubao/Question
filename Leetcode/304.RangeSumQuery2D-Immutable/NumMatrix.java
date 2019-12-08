class NumMatrix {
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = row == 0 ? 0 : matrix[0].length;
        sum = new int[col][row];
        for (int i=0;i<col;i++) {
            int total = 0;
            for (int j=0;j<row;j++) {
                total += matrix[j][i];
                sum[i][j] = total;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int total = 0;
        for (int i=col1;i<=col2;i++) {
            total += row1 == 0 ? sum[i][row2] : (sum[i][row2] - sum[i][row1-1]);
        }
        return total;
    }

    public static void main(String args[]) {
        int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        NumMatrix nm = new NumMatrix(matrix);
        System.out.println(nm.sumRegion(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3])));
    }
}