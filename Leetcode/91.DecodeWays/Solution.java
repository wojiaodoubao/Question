class Solution {
    public int numDecodings(String s) {
        int[] array = new int[s.length()];
        for (int i=0;i<array.length;i++) {
            array[i] = Integer.parseInt(s.charAt(i)+"");
        }

        int[][] conditions = new int[array.length][2];
        for (int i=0;i<conditions.length;i++) {
            conditions[i] = new int[2];
        }
        if (array[0] == 0) {
          return 0;
        } else {
          conditions[0] = new int[]{1,0};
        }

        for (int i=1;i<array.length;i++) {
            boolean combine =couldCombine(array[i-1], array[i]);
            boolean mustCombine = array[i] == 0;
            if (mustCombine && !combine) {
              return 0;
            }
            if (mustCombine) {
              conditions[i][0] = 0;
              if (i == 1) {
                conditions[i][1] = 1;
              } else {
                conditions[i][1] = conditions[i-2][0] + conditions[i-2][1];
              }
            } else {
              if (combine) {
                  if (i == 1) {
                    conditions[i][1] = 1;
                  } else {
                    conditions[i][1] = conditions[i-2][0] + conditions[i-2][1];
                  }
              }
              conditions[i][0] = conditions[i-1][0] + conditions[i-1][1];
            }
        }

        return conditions[conditions.length-1][0] + conditions[conditions.length-1][1];
    }

    private boolean couldCombine(int i, int j) {
        if (i == 0) {
            return false;
        }
        int sum = i * 10 + j;
        if (sum <= 26) {
            return true;
        } else {
            return false;
        }
    }
}
