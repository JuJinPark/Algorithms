package leetCode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 0}
        };
        new ShortestPathInBinaryMatrixSolution1().shortestPathBinaryMatrix(grid);
        new ShortestPathInBinaryMatrixSolution2().shortestPathBinaryMatrix(grid);
    }
}

/**
 * tc - o( 8^n*n)
 * sc - o(n*n) + o(n*n) = o(n*n)
 */
class ShortestPathInBinaryMatrixSolution1 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid.length];
        visited[0][0] = true;
        int result = this.findShorestPathDFS(grid, 0, 0, visited);
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }

    private int findShorestPathDFS(int[][] grid, int i, int j, boolean[][] visited) {
        int current = grid[i][j];
        if (current == 1) {
            return Integer.MAX_VALUE;
        }

        if (i == grid.length - 1 && j == grid.length - 1) {
            return 1;
        }
        int shortestPath = Integer.MAX_VALUE;

        int[] directions = new int[]{1, 1, 1, 0, 0, 1, 1, -1, -1, 1, -1, 0, -1, -1, 0, -1};

        for (int k = 0; k < directions.length; k = k + 2) {
            int nextI = i + directions[k];
            int nextJ = j + directions[k + 1];
            if ((nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid.length) && !visited[nextI][nextJ]) {
                visited[nextI][nextJ] = true;
                int path = this.findShorestPathDFS(grid, nextI, nextJ, visited);
                shortestPath = Math.min(shortestPath, path);
                visited[nextI][nextJ] = false;
            }
        }

        if (shortestPath == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return shortestPath + 1;
    }
}

/**
 * tc - o(n*n)
 * sc - o(n*n) = o(n*n)
 */
class ShortestPathInBinaryMatrixSolution2 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid.length];
        int[] directions = new int[]{1, 1, 1, 0, 0, 1, 1, -1, -1, 1, -1, 0, -1, -1, 0, -1};
        return this.findShorestPath(grid, visited, directions);
    }

    private int findShorestPath(int[][] grid, boolean[][] visited, int[] directions) {
        Queue<int[]> queue = new LinkedList();
        if (grid[0][0] == 0) {
            queue.add(new int[]{0, 0});
            visited[0][0] = true;
        }
        int path = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                if (current[0] == grid.length - 1 && current[1] == grid.length - 1) {
                    return path;
                }

                for (int k = 0; k < directions.length; k = k + 2) {
                    int nextI = current[0] + directions[k];
                    int nextJ = current[1] + directions[k + 1];
                    if ((nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid.length)
                            && !visited[nextI][nextJ] && grid[nextI][nextJ] == 0) {
                        visited[nextI][nextJ] = true;
                        queue.add(new int[]{nextI, nextJ});
                    }
                }

            }
            path++;
        }

        return -1;

    }
}
