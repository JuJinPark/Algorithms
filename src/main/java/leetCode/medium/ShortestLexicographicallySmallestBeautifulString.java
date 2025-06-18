package leetCode.medium;

public class ShortestLexicographicallySmallestBeautifulString {

    public static void main(String[] args) {
        new ShortestLexicographicallySmallestBeautifulStringSol1().shortestBeautifulSubstring("100011001", 3);
    }
}

/**
 * tc - o(n)
 * sc - o(n)
 */
class ShortestLexicographicallySmallestBeautifulStringSol1 {
    public String shortestBeautifulSubstring(String s, int k) {
        String result = "";
        int numberOfOnes = 0;
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            if (s.charAt(right) == '1') {
                numberOfOnes++;
            }
            if (numberOfOnes == k) {

                while (numberOfOnes == k && left < s.length()) {

                    String current = s.substring(left, right + 1);
                    if (result.equals("")) {
                        result = current;
                    } else {
                        if (result.length() > current.length() ||
                                (result.length() == current.length() && current.compareTo(result) == -1)) {
                            result = current;
                        }
                    }

                    if (s.charAt(left) == '1') {
                        numberOfOnes--;
                    }
                    left++;
                }
            }
            right++;

        }

        return result;
    }
}


/**
 * tc - o(n)
 * sc - o(n)
 * 똑같은 방법이나 sliding widnow 구현 조금 차이..
 */

class ShortestLexicographicallySmallestBeautifulStringSol2 {

    public String shortestBeautifulSubstring(String s, int k) {
        String result = "";
        int numberOfOnes = 0;
        int left = 0;
        int right = 0;

        if (s.charAt(right) == '1') {
            numberOfOnes++;
        }

        while (right < s.length()) {

            if (numberOfOnes == k) {
                String current = s.substring(left, right + 1);
                if (k == 1) {
                    return "1";
                }
                // System.out.println(current);
                if (result.equals("")) {
                    result = current;
                } else {
                    if (result.length() > current.length() ||
                            (result.length() == current.length() && current.compareTo(result) == -1)
                    ) {
                        result = current;
                    }
                }

                if (s.charAt(left) == '1') {
                    numberOfOnes--;
                }
                left++;

                while (s.charAt(left) != '1') {
                    left++;
                }

            } else {
                right++;
                if (right < s.length() && s.charAt(right) == '1') {
                    numberOfOnes++;
                }
            }
        }


        return result;
    }
}
