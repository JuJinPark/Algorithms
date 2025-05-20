package leetCode.medium;

import java.util.*;

public class MinimumScoreOfAPathBetweenTwoCities {

    public static void main(String[] args) {
        int n = 4;
        int[][] roads = new int[][]{{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}};
        new MinimumScoreOfAPathBetweenTwoCitiesSolution1().minScore(n, roads);
        new MinimumScoreOfAPathBetweenTwoCitiesSolution2().minScore(n, roads);
        new MinimumScoreOfAPathBetweenTwoCitiesSolution3().minScore(n, roads);
    }
}


/**
 * tc - o(m) + o(n + m) = o(n+m)
 * sc - o(m*2) + o(n) = o(n+m)
 */
class MinimumScoreOfAPathBetweenTwoCitiesSolution1 {
    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int i = 0; i < roads.length; i++) {
            int[] current = roads[i];
            int fromNode = current[0];
            int toNode = current[1];
            int distance = current[2];
            List<int[]> fromEdges = graph.getOrDefault(fromNode, new ArrayList());
            fromEdges.add(new int[]{toNode, distance});
            graph.put(fromNode, fromEdges);

            List<int[]> toEdges = graph.getOrDefault(toNode, new ArrayList());
            toEdges.add(new int[]{fromNode, distance});
            graph.put(toNode, toEdges);
        }


        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            List<int[]> nexts = graph.getOrDefault(currentNode, new ArrayList());
            for (int[] next : nexts) {
                int score = next[1];
                int node = next[0];
                minScore = Math.min(score, minScore);
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }

        return minScore;
    }
}


/**
 * tc - o(m) + o(n + m) = o(n+m)
 * sc - o(m*2) + o(n) = o(n+m)
 */
class MinimumScoreOfAPathBetweenTwoCitiesSolution2 {
    int minScore = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int i = 0; i < roads.length; i++) {
            int[] current = roads[i];
            int fromNode = current[0];
            int toNode = current[1];
            int distance = current[2];
            List<int[]> fromEdges = graph.getOrDefault(fromNode, new ArrayList());
            fromEdges.add(new int[]{toNode, distance});
            graph.put(fromNode, fromEdges);

            List<int[]> toEdges = graph.getOrDefault(toNode, new ArrayList());
            toEdges.add(new int[]{fromNode, distance});
            graph.put(toNode, toEdges);
        }


        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        this.dfs(graph, 1, visited);
        return minScore;
    }

    private void dfs(Map<Integer, List<int[]>> graph, int currentNode, boolean[] visited) {
        List<int[]> nexts = graph.getOrDefault(currentNode, new ArrayList());
        for (int[] next : nexts) {
            int score = next[1];
            int node = next[0];
            minScore = Math.min(score, minScore);
            if (!visited[node]) {
                visited[node] = true;
                dfs(graph, node, visited);
            }
        }

    }
}

/**
 * tc -  n + m +m = o(m+n)
 * sc - o(n)
 */
class MinimumScoreOfAPathBetweenTwoCitiesSolution3 {
    int[] parent;

    public int minScore(int n, int[][] roads) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int[] road : roads) {
            union(road[0], road[1]);
        }

        int root1 = find(1);
        int minScore = Integer.MAX_VALUE;

        for (int[] road : roads) {
            int a = road[0], b = road[1], dist = road[2];
            if (find(a) == root1 || find(b) == root1) {
                minScore = Math.min(minScore, dist);
            }
        }

        return minScore;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parent[px] = py;
        }
    }
}