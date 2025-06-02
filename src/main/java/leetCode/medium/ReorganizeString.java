package leetCode.medium;

import java.util.*;

public class ReorganizeString {

    public static void main(String[] args) {
        new ReorganizeStringSolution1().reorganizeString("aab");
        new ReorganizeStringSolution2().reorganizeString("aab");
        new ReorganizeStringSolution3().reorganizeString("aab");

    }
}

/**
 * not working (wrong approach)
 */
class ReorganizeStringSolution1 {
    public String reorganizeString(String s) {
        HashMap<String, ReorganizeStringNode> map = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            String stringWord = s.charAt(i) + "";
            ReorganizeStringNode value = map.getOrDefault(stringWord, new ReorganizeStringNode());
            value.word = stringWord;
            value.frequency = value.frequency + 1;
            map.put(stringWord, value);
        }

        List<ReorganizeStringNode> orderedList = new ArrayList<ReorganizeStringNode>(map.values());
        Collections.sort(orderedList, (a, b) -> b.frequency - a.frequency);

        StringBuilder builder = new StringBuilder();

        int left = 0;
        int right = 1;
        while (true) {
            //System.out.println(builder.toString());

            ReorganizeStringNode leftNode = orderedList.get(left);
            // System.out.println(leftNode.frequency);

            if (leftNode.frequency <= 0 && right >= orderedList.size()) {
                break;
            }
            ReorganizeStringNode rightNode = null;
            if (right < orderedList.size()) {
                rightNode = orderedList.get(right);
            }

            if (leftNode.frequency > 0) {
                builder.append(leftNode.word);
                leftNode.frequency = leftNode.frequency - 1;
            }

            if (rightNode != null && rightNode.frequency > 0) {
                builder.append(rightNode.word);
                rightNode.frequency = rightNode.frequency - 1;
                if (rightNode.frequency == 0) {
                    right++;
                }
            }

            if (builder.length() >= 2 && builder.charAt(builder.length() - 1) == builder.charAt(builder.length() - 2)) {
                return "";
            }

        }

        return builder.toString();

    }
}

/**
 * tc - o(n!)
 * sc - n + n + n  - o(n)
 */

class ReorganizeStringSolution2 {
    public String reorganizeString(String s) {

        return this.recur(s, new String[s.length()], new HashSet(), 0);
    }

    private String recur(String s, String[] temp, Set<Integer> visited, int depth) {

        if (depth == s.length()) {
            return String.join("", temp);
        }
        for (int i = 0; i < s.length(); i++) {
            String current = s.charAt(i) + "";
            if (depth > 0) {
                String prev = temp[depth - 1];
                if (current.equals(prev)) {
                    continue;
                }
            }
            if (!visited.contains(i)) {
                temp[depth] = s.charAt(i) + "";
                visited.add(i);
                String result = recur(s, temp, visited, depth + 1);
                if (!result.equals("")) {
                    return result;
                }
                visited.remove(i);
            }
        }
        return "";
    }

}

/**
 * tc - n + k log k + n * log k = n log k
 * sc - n + k + n = n +k
 */
class ReorganizeStringSolution3 {
    public String reorganizeString(String s) {
        HashMap<String, ReorganizeStringNode> map = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            String stringWord = s.charAt(i) + "";
            ReorganizeStringNode value = map.getOrDefault(stringWord, new ReorganizeStringNode());
            value.word = stringWord;
            value.frequency = value.frequency + 1;
            map.put(stringWord, value);
        }

        PriorityQueue<ReorganizeStringNode> queue = new PriorityQueue<>(
                (a, b) -> b.frequency - a.frequency);

        queue.addAll(map.values());

        StringBuilder result = new StringBuilder();

        while (queue.size() >= 2) {
            ReorganizeStringNode node1 = queue.poll();
            ReorganizeStringNode node2 = queue.poll();

            result.append(node1.word);
            result.append(node2.word);
            node1.frequency = node1.frequency - 1;
            node2.frequency = node2.frequency - 1;

            if (node1.frequency > 0) {
                queue.add(node1);
            }

            if (node2.frequency > 0) {
                queue.add(node2);
            }

        }

        while (!queue.isEmpty()) {
            ReorganizeStringNode node = queue.poll();
            int maxSize = node.frequency;
            if (maxSize > 1) {
                return "";
            }
            result.append(node.word);

        }

        return result.toString();

    }
}

class ReorganizeStringNode {
    public String word;
    public int frequency;
}