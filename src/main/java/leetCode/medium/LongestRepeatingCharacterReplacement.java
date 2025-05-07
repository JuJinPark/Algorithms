package leetCode.medium;

import java.util.Collections;
import java.util.HashMap;

public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        String input = "ASSSS";
        int k = 1;
        new LongestRepeatingCharacterReplacementSolution1().characterReplacement(input, k);
        new LongestRepeatingCharacterReplacementSolution2().characterReplacement(input, k);
        new LongestRepeatingCharacterReplacementSolution3().characterReplacement(input, k);
        new LongestRepeatingCharacterReplacementSolution4().characterReplacement(input, k);

    }
}

/**
 * tc - o(n2)
 * sc - o(m) = o(1) = 26 alphabet
 */
class LongestRepeatingCharacterReplacementSolution1 {
    public int characterReplacement(String s, int k) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> countMap = new HashMap();
            int maxCharCount = 0;
            for (int j = i; j < s.length(); j++) {
                char current = s.charAt(j);
                int prevCount = countMap.getOrDefault(current, 0);
                countMap.put(current, prevCount + 1);
                maxCharCount = Math.max(maxCharCount, countMap.get(current));
                int substringLength = j - i + 1;
                if (substringLength - maxCharCount <= k) {
                    result = Math.max(substringLength, result);
                }
            }
        }
        return result;
    }
}

/**
 * tc - o(n) * o(m)
 * sc - o(m) = o(1) = 26 alphabet
 */
class LongestRepeatingCharacterReplacementSolution2 {
    public int characterReplacement(String s, int k) {
        int result = 0;
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> countMap = new HashMap();
        while (right < s.length()) {
            char current = s.charAt(right);
            int prevCount = countMap.getOrDefault(current, 0);
            countMap.put(current, prevCount + 1);
            int substringLength = right - left + 1;
            int maxCharCount = Collections.max(countMap.values());
            if (substringLength - maxCharCount <= k) {
                result = Math.max(substringLength, result);
                right++;
            } else {
                char toRemove = s.charAt(left);
                int prevCountToRemove = countMap.get(toRemove);
                countMap.put(toRemove, prevCountToRemove - 1);
                left++;
                right++;
            }

        }
        return result;
    }
}

/**
 * tc - o(n)
 * sc - o(m) = o(1) = 26 alphabet
 */
class LongestRepeatingCharacterReplacementSolution3 {
    public int characterReplacement(String s, int k) {
        int result = 0;
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> countMap = new HashMap();
        int maxCharCount = 0;
        while (right < s.length()) {
            char current = s.charAt(right);
            int prevCount = countMap.getOrDefault(current, 0);
            countMap.put(current, prevCount + 1);
            int substringLength = right - left + 1;
            maxCharCount = Math.max(maxCharCount, countMap.get(current));
            if (substringLength - maxCharCount <= k) {
                result = Math.max(substringLength, result);
                right++;
            } else {
                char toRemove = s.charAt(left);
                int prevCountToRemove = countMap.get(toRemove);
                countMap.put(toRemove, prevCountToRemove - 1);
                left++;
                right++;
            }

        }
        return result;
    }
}

/**
 * tc - o(n)
 * sc - o(m) = o(1) = 26 alphabet
 * 3번과 크게 다르지않은 while 문도 결국엔 한번만 돌는걸로 보임 여러번 줄이는 경우는 없음
 */
class LongestRepeatingCharacterReplacementSolution4 {
    public int characterReplacement(String s, int k) {
        int result = 0;
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> countMap = new HashMap();
        int maxCharCount = 0;
        while (right < s.length()) {
            char current = s.charAt(right);
            int prevCount = countMap.getOrDefault(current, 0);
            countMap.put(current, prevCount + 1);
            int substringLength = right - left + 1;
            maxCharCount = Math.max(maxCharCount, countMap.get(current));
            while (substringLength - maxCharCount > k) {
                char toRemove = s.charAt(left);
                int prevCountToRemove = countMap.getOrDefault(toRemove, 0);
                countMap.put(toRemove, prevCountToRemove - 1);
                left++;
                substringLength = right - left + 1;
            }
            result = Math.max(substringLength, result);
            right++;

        }
        return result;
    }
}
