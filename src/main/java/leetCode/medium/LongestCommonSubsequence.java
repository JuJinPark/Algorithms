package leetCode.medium;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        int result = new LongestCommonSubsequenceSolution().longestCommonSubsequence("adb", "ssdd");
    }
}

class LongestCommonSubsequenceSolution {
    public int longestCommonSubsequence(String text1, String text2) {
        return this.firstSolution(text1, text2);
    }

    private int firstSolution(String text1, String text2) {
        // tc - m*n sc-n
        int longest = 0;
        int[] dp = new int[text1.length()];

        for (char c : text2.toCharArray()) {
            int current = 0;
            for (int i = 0; i < dp.length; i++) {
                if (current < dp[i]) {
                    current = dp[i];
                } else if (text1.charAt(i) == c) {
                    dp[i] = current + 1;
                    longest = Math.max(longest, dp[i]);
                }

            }
        }
        return longest;
    }
}