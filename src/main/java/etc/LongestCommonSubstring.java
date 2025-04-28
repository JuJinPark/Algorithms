package etc;

public class LongestCommonSubstring {

    public static void main(String[] args) {
        new LongestCommonSubstringSolution1().result("GeeksforGeeks", "GeeksQuiz");
        new LongestCommonSubstringSolution2().result("GeeksforGeeks", "GeeksQuiz");


    }
}


/**
 * tc - 3^max(m,n)
 * sc - depth(max(m,n)
 */
class LongestCommonSubstringSolution1 {

    public int result(String text1, String text2) {
        return this.recur(text1,text2,0,0,0);
    }

    public int recur(String text1, String text2, int i, int j, int count) {
        if(i >= text1.length() || j >= text2.length()) {
            return count;
        }
        int newCount = count;
        if(text1.charAt(i)==text2.charAt(j)) {
            newCount = this.recur(text1,text2, i+1, j+1, count+1);
        }
        int maxCountFromOtherCase1 = this.recur(text1,text2,i,j+1,0);
        int maxCountFromOtherCase2 = this.recur(text1,text2,i+1,j,0);
        return Math.max(newCount, Math.max(maxCountFromOtherCase1,maxCountFromOtherCase2));
    }

}

/**
 * tc - o(m*n)
 * sc - o(m*n)
 */

class LongestCommonSubstringSolution2 {
    public int result(String text1, String text2) {
        // dp[r][c]  = the length of the longest common substring ending at s1[r-1] and s2[c-1].
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        int max = 0;
        for (int i = 1; i <= text1.length(); i++) {
            char char1 = text1.charAt(i-1);
            for(int j =1; j <= text2.length(); j++) {
                char char2 = text2.charAt(j-1);
                if(char1 == char2) {
                    dp[i][j] =  dp[i-1][j-1] +1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}