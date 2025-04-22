package leetCode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] input = {
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        new ValidSudokuSolution1().isValidSudoku(input);
        new ValidSudokuSolution2().isValidSudoku(input);
        new ValidSudokuSolution3().isValidSudoku(input);
    }
}

class ValidSudokuSolution1 {
    // tc - o(n*n*3n) = o(n^3)
    // sc - o(1)
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char cur = board[i][j];
                int boxNum = (i / 3) * 3 + (j / 3);
                if (cur == '.') continue;
                if (!checkRow(board, i, cur, i, j) ||
                        !checkColumn(board, j, cur, i, j) ||
                        !checkBox(board, boxNum, cur, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRow(char[][] board, int row, char target, int targetRow, int targetCol) {
        for (int i = 0; i < board.length; i++) {
            if (targetRow == row && targetCol == i) continue;
            if (board[row][i] == (target)) return false;
        }
        return true;
    }

    private boolean checkColumn(char[][] board, int column, char target, int targetRow, int targetCol) {
        for (int i = 0; i < board.length; i++) {
            if (targetRow == i && targetCol == column) continue;
            if (board[i][column] == (target)) return false;
        }
        return true;
    }

    private boolean checkBox(char[][] board, int boxNum, char target, int targetRow, int targetCol) {
        int row = (boxNum / 3) * 3;
        int column = (boxNum % 3) * 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (targetRow == i && targetCol == j) continue;
                if (board[i][j] == (target)) return false;
            }
        }
        return true;
    }
}


class ValidSudokuSolution2 {
    // tc - o(n*n)
    // sc - o(n)*o(n)*3 = o(n2)
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rowMap = new HashMap<>();
        Map<Integer, Set<Character>> columnMap = new HashMap<>();
        Map<Integer, Set<Character>> boxMap = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char cur = board[i][j];
                int boxNum = (i / 3) * 3 + (j / 3);
                if (cur == ('.')) continue;

                rowMap.putIfAbsent(i, new HashSet<>());
                columnMap.putIfAbsent(j, new HashSet<>());
                boxMap.putIfAbsent(boxNum, new HashSet<>());

                if (rowMap.get(i).contains(cur) ||
                        columnMap.get(j).contains(cur) ||
                        boxMap.get(boxNum).contains(cur)) {
                    return false;
                }

                rowMap.get(i).add(cur);
                columnMap.get(j).add(cur);
                boxMap.get(boxNum).add(cur);
            }
        }
        return true;
    }
}

class ValidSudokuSolution3 {

    // tc - o(n*n)
    // sc - o(n) *3 = o(n)
    public boolean isValidSudoku(char[][] board) {
        int[] raw = new int[9];
        int[] column = new int[9];
        int[] box = new int[9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ('.')) continue;
                int val = board[i][j] - '1';
                int boxNum = (i / 3) * 3 + (j / 3);

                // 특정 자리수 가 1인지 확인하는 법 1100 & 100 같은 자리에 1이면 1이 어딘가 나옴으로 0은 아님
                if ((raw[i] & (1 << val)) != 0) {
                    return false;
                }
                if ((column[j] & (1 << val)) != 0) {
                    return false;
                }
                if ((box[boxNum] & (1 << val)) != 0) {
                    return false;
                }

                raw[i] = raw[i] | (1 << val);
                column[j] = column[j] | (1 << val);
                box[boxNum] = box[boxNum] | (1 << val);
            }
        }
        return true;
    }
}
