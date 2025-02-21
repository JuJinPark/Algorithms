package leetCode.medium;

public class MaximalSquare {
    public static void main(String[] args) {
        int result = new MaximalSquareSolution().maximalSquare(new char[][]{
                new char[]{'0', '1', '1'}
        });
    }
}

class MaximalSquareSolution {
    public int maximalSquare(char[][] matrix) {
        return this.firstSolution(matrix);
    }

    private int firstSolution(char[][] matrix) {
        /*
        tc - o(m*m) + o(3)+ .. = o(m*n)
        sc = o(1)
            [
                [1,1,1]
                [1,2,1]
                [1,1,1]
            ]
            idx = max size of square it can make as right bottom coner
            left up, up, left = size to check
         */
        int maxLength = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int rightBottomConer = matrix[i][j];
                if (rightBottomConer != '0') {
                    int currentMaxLength = this.getMaxLength(i, j, matrix) + 1;
                    matrix[i][j] = (char) (currentMaxLength + '0');
                    maxLength = Math.max(currentMaxLength, maxLength);
                }
            }
        }
        return maxLength * maxLength;
    }

    private int getMaxLength(int i, int j, char[][] matrix) {

        if (i - 1 < 0) {
            return 0;
        }
        int up = matrix[i - 1][j] - '0';
        if (j - 1 < 0) {
            return 0;
        }
        int left = matrix[i][j - 1] - '0';
        int leftUpCorner = matrix[i - 1][j - 1] - '0';
        return Math.min(up, Math.min(left, leftUpCorner));
    }

}
