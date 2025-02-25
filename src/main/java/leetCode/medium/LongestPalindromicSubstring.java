package leetCode.medium;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String result = new LongestPalindromicSubstringSolution().longestPalindrome("babad");
    }
}

class LongestPalindromicSubstringSolution {
    public String longestPalindrome(String s) {
        return this.firstSolution(s);
    }

    private String firstSolution(String s) {
        /*
         tc- o(n2) = 2n of centers *n  sc - o(n) same as length of s
         */
        String result = s.substring(0, 1);
        for (int i = 0; i < s.length() - 1; i++) {
            //even - i,i+1 would be the center
            String evenPalindrome = this.findLongestPalindrome(i, i + 1, s);
            //odd - i would be the center
            String oddPalindrome = this.findLongestPalindrome(i, i, s);

            if (evenPalindrome.length() > result.length()) {
                result = evenPalindrome;
            }
            if (oddPalindrome.length() > result.length()) {
                result = oddPalindrome;
            }
        }

        return result;
    }

    private String findLongestPalindrome(int c1Idx, int c2Idx, String s) {
        int left = c1Idx;
        int right = c2Idx;
        while (true) {
            if (left < 0) {
                break;
            }
            if (right > s.length() - 1) {
                break;
            }
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return s.substring(left + 1, right);

    }

    private String secondSolution(String s) {
        // DP way TODO
        return s;
    }
}

