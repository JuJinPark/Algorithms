package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartitionLabels {
    public static void main(String[] args) {
        List<Integer> result = new PartitionLabelsSolution().partitionLabels("adadaf");
    }
}

class PartitionLabelsSolution {
    public List<Integer> partitionLabels(String s) {
        return this.secondSolution(s);
    }

    /*
    k = number of unique characters
    tc- o(n) + o(n) + o(26)= o(n), sc - o(k) + o(n) + o(k) = o(n)
    */
    private List<Integer> firstSolution(String s) {
        HashMap<Character, int[]> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int[] position = map.getOrDefault(cur, new int[]{s.length(), -1});
            position[0] = Math.min(position[0], i);
            position[1] = Math.max(position[1], i);
            map.put(cur, position);
        }

        List<int[]> results = new ArrayList<>();
        results.add(map.get(s.charAt(0)));
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            int[] position = map.get(cur);
            this.mergeAndChange(results, position);
        }

        List<Integer> finalResult = new ArrayList();
        for (int[] pos : results) {
            finalResult.add(pos[1] - pos[0] + 1);
        }
        return finalResult;
    }

    private void mergeAndChange(List<int[]> result, int[] curPos) {
        int[] lastPos = result.get(result.size() - 1);
        if (this.interleaving(lastPos, curPos)) {
            lastPos[0] = Math.min(curPos[0], lastPos[0]);
            lastPos[1] = Math.max(curPos[1], lastPos[1]);
        } else {
            result.add(curPos);
        }
    }

    private boolean interleaving(int[] frontPos, int[] backPos) {
        int minEndPos = Math.min(frontPos[1], backPos[1]);
        int maxStarPos = Math.max(frontPos[0], backPos[0]);
        if (maxStarPos <= minEndPos) {
            return true;
        }
        return false;
    }

    /*
    tc- o(n) + o(n) = o(n), sc - o(k) + o(k) = o(k)
    */

    private List<Integer> secondSolution(String s) {
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int position = map.getOrDefault(cur, -1);
            position = Math.max(position, i);
            map.put(cur, position);
        }

        List<Integer> results = new ArrayList();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int lastPos = map.get(cur);
            end = Math.max(end, lastPos);
            if (i == end) {
                results.add(end - start + 1);
                start = i + 1;
            }
        }

        return results;
    }
}
