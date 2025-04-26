package leetCode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        new LongestSubstringWithoutRepeatingCharactersSolution1().lengthOfLongestSubstring("asdf");
        new LongestSubstringWithoutRepeatingCharactersSolution2().lengthOfLongestSubstring("asdf");
        new LongestSubstringWithoutRepeatingCharactersSolution3().lengthOfLongestSubstring("asdf");

    }
}


/**
 * t - o(n*m)
 * s - o(m)
 */
class LongestSubstringWithoutRepeatingCharactersSolution1 {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            char cur = s.charAt(i);
            set.add(cur);
            for (int j = i + 1; j < s.length(); j++) {
                char next = s.charAt(j);
                if (set.contains(next)) {
                    break;
                }
                set.add(next);
            }
            result = Math.max(result, set.size());
        }
        return result;
    }
}

/**
 * tc - o(n)
 * sc - o(m)
 */
class LongestSubstringWithoutRepeatingCharactersSolution2 {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (set.contains(cur)) {
                while (set.contains(cur)) {
                    set.remove(s.charAt(left));
                    left++;
                }
            } else {
                set.add(cur);
                right++;
            }
            result = Math.max(right - left, result);
        }

        return result;
    }
}

/**
 * tc - o(n)
 * sc - o(m)
 */
class LongestSubstringWithoutRepeatingCharactersSolution3 {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char cur = s.charAt(right);
            left = Math.max(left, map.getOrDefault(cur, -1) + 1);
            map.put(cur, right);
            right++;
            result = Math.max(right - left, result);
        }

        return result;
    }
}
