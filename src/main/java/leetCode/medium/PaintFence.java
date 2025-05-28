package leetCode.medium;

public class PaintFence {

    public static void main(String[] args) {
        new PaintFenceSolution1().sol(3,1);
    }
}

/**
 * n-post,k color return ways to paint them
 * no three or more consecutive posts with the same color
 * every post must be painted
 *
 * n - 1 k- 1
 * no possible wya n-3 k1 -> 0
 *
 * easy way try every cases paint every fences with every colors
 *  n =3 k =2
 *
 *  1 - 2
 *  2 - 2
 *  3 -
 */

/**
 * tc - o(n)
 * sc - o(n)
 */
class PaintFenceSolution1 {

    public int sol(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (k == 1 && n > 2) return 0;

        int[][] dp = new int[n][2];
        dp[0][1] = k;

        for (int i = 1; i < n ; i++) {
            dp[i][0] = dp[i-1][1]; // same: post i is the same color as i - 1

            dp[i][1] = (dp[i-1][0] + dp[i-1][1]) * (k-1); // diff: post i is different from i - 1
        }

        return dp[n-1][0] + dp[n-1][1];
    }
}
