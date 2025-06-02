package leetCode.medium;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        List<String> result1 = new LetterCombinationsOfAPhoneNumberSolution1().letterCombinations("29");
        List<String> result2 = new LetterCombinationsOfAPhoneNumberSolution2().letterCombinations("29");
        System.out.println(result1);
        System.out.println(result2);
    }
}

/**
 * tc - o(1) + 4^n*n  = 4^n * n
 * sc - o(1) + n + n + 4^n *n  = 4^n * n + n
 */
class LetterCombinationsOfAPhoneNumberSolution1 {
    public List<String> letterCombinations(String digits) {
        Map<Character, List<String>> map = this.createDigitMapping();
        List<String> result = new ArrayList();
        if (digits.length() != 0) {
            this.recur(digits, 0, map, new String[digits.length()], result);
        }
        return result;
    }

    private Map<Character, List<String>> createDigitMapping() {
        Map<Character, List<String>> map = new HashMap();
        map.put('2', Arrays.asList(new String[]{"a", "b", "c"}));
        map.put('3', Arrays.asList(new String[]{"d", "e", "f"}));
        map.put('4', Arrays.asList(new String[]{"g", "h", "i"}));
        map.put('5', Arrays.asList(new String[]{"j", "k", "l"}));
        map.put('6', Arrays.asList(new String[]{"m", "n", "o"}));
        map.put('7', Arrays.asList(new String[]{"p", "q", "r", "s"}));
        map.put('8', Arrays.asList(new String[]{"t", "u", "v"}));
        map.put('9', Arrays.asList(new String[]{"w", "x", "y", "z"}));
        return map;
    }

    private void recur(String digits, int depth, Map<Character, List<String>> map, String[] temp, List<String> result) {
        if (depth == digits.length()) {
            result.add(String.join("", temp));
            return;
        }

        char digit = digits.charAt(depth);
        List<String> letters = map.get(digit);
        for (String letter : letters) {
            temp[depth] = letter;
            recur(digits, depth + 1, map, temp, result);
        }
    }
}


/**
 * tc - 4^n * n
 * sc - 4^n *n
 */
class LetterCombinationsOfAPhoneNumberSolution2 {
    public List<String> letterCombinations(String digits) {
        Map<Character, List<String>> map = this.createDigitMapping();
        List<String> result = new ArrayList();
        if (digits.length() == 0) {
            return result;
        }

        result.add("");

        for (char digit : digits.toCharArray()) {
            List<String> temp = new ArrayList();
            for (String letter : map.get(digit)) {
                for (String prev : result) {
                    temp.add(prev + letter);
                }
            }
            result = temp;
        }


        return result;
    }

    private Map<Character, List<String>> createDigitMapping() {
        Map<Character, List<String>> map = new HashMap();
        map.put('2', Arrays.asList(new String[]{"a", "b", "c"}));
        map.put('3', Arrays.asList(new String[]{"d", "e", "f"}));
        map.put('4', Arrays.asList(new String[]{"g", "h", "i"}));
        map.put('5', Arrays.asList(new String[]{"j", "k", "l"}));
        map.put('6', Arrays.asList(new String[]{"m", "n", "o"}));
        map.put('7', Arrays.asList(new String[]{"p", "q", "r", "s"}));
        map.put('8', Arrays.asList(new String[]{"t", "u", "v"}));
        map.put('9', Arrays.asList(new String[]{"w", "x", "y", "z"}));
        return map;
    }
}

