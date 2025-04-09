package leetCode.medium;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        String[] input = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result1 = new GroupAnagramsSolution1().groupAnagrams(input);
        List<List<String>> result2 = new GroupAnagramsSolution2().groupAnagrams(input);
        List<List<String>> result3 = new GroupAnagramsSolution3().groupAnagrams(input);
    }
}


/*
    Requirements: group the anagrams together
    Notes:
        - result can be any order
        - empty string input possible
        - input only lowercase english
        - empty length in put possible -> no
 */
class GroupAnagramsSolution1 {
    /**
     * m - number of strings
     * n - longest length of a string
     * TC - nm log nm
     * SC - m * n
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            result.putIfAbsent(key, new ArrayList<>());
            result.get(key).add(str);
        }
        return new ArrayList(result.values());
    }
}


class GroupAnagramsSolution2 {
    /**
     * m - number of strings
     * n - longest length of a string
     * TC - n * m + m  = nm
     * SC - m * n
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();
        for (String str : strs) {
            String key = this.createKey(str);
            result.putIfAbsent(key, new ArrayList<>());
            result.get(key).add(str);
        }
        return new ArrayList(result.values());
    }

    private String createKey(String str) {
        int[] charCount = new int[26];
        for (char ch : str.toCharArray()) {
            charCount[ch - 'a']++;
        }
        return Arrays.toString(charCount);
    }

}

class GroupAnagramsSolution3 {
    /**
     * m - number of strings
     * n - longest length of a string
     * TC - m * (m * n)
     * SC - m * n
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Set<Integer> removedIdx = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            if (removedIdx.contains(i)) {
                continue;
            }
            List<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            removedIdx.add(i);
            String Ikey = this.createKey(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (removedIdx.contains(j)) {
                    continue;
                }
                String Jkey = this.createKey(strs[j]);
                if (Ikey.equals(Jkey)) {
                    temp.add(strs[j]);
                    removedIdx.add(j);
                }
            }
            result.add(temp);
        }
        return result;
    }

    private String createKey(String str) {
        int[] charCount = new int[26];
        for (char ch : str.toCharArray()) {
            charCount[ch - 'a']++;
        }
        return Arrays.toString(charCount);
    }

}


