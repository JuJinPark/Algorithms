package leetCode.medium;

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        new WordSearchSolution1().exist(board, "ABCCED");
    }
}

/**
 * tc - o(m*n*4^k)
 * sc - o(k)
 */
class WordSearchSolution1 {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (recur(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean recur(char[][] board, String word, int idx, int i, int j) {
        if (idx == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return false;
        }

        if (board[i][j] != word.charAt(idx)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#';
        boolean found = recur(board, word, idx + 1, i, j + 1) ||
                recur(board, word, idx + 1, i, j - 1) ||
                recur(board, word, idx + 1, i + 1, j) ||
                recur(board, word, idx + 1, i - 1, j);

        board[i][j] = temp;  // restore original value
        return found;
    }
}
