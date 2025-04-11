package leetCode.medium;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] input = new int[]{1000, 4, 200, 1, 3, 2};
        new LongestConsecutiveSequenceSolution1().longestConsecutive(input);
        new LongestConsecutiveSequenceSolution2().longestConsecutive(input);
        new LongestConsecutiveSequenceSolution3().longestConsecutive(input);
        new LongestConsecutiveSequenceSolution4().longestConsecutive(input);
    }
}

class LongestConsecutiveSequenceSolution1 {


    // TC -  o(n^2) , SC  = o(n)
    public int longestConsecutive(int[] nums) {
        HashSet sets = new HashSet();
        for (int num : nums) {
            sets.add(num);
        }
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentLength = 1;
            int nextNumber = nums[i] + 1;
            while (true) {
                if (sets.contains(nextNumber)) {
                    currentLength++;
                    nextNumber++;
                } else {
                    break;
                }
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }
}

class LongestConsecutiveSequenceSolution2 {

    // TC -  o(n log n) +n = o (n log n) , SC  = o(1)
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 == nums[i - 1]) {
                currentLength++;
            } else if (nums[i] == nums[i - 1]) {
                // nothing
            } else {
                currentLength = 1;
            }
            maxLength = Math.max(maxLength, currentLength);

        }
        return maxLength;
    }
}

class LongestConsecutiveSequenceSolution3 {
    // TC - o(n) + o(n) + o(n) = o(n) SC = o(n)
    public int longestConsecutive(int[] nums) {
        HashSet sets = new HashSet();
        for (int num : nums) {
            sets.add(num);
        }
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int prvLinkCount = this.graphSearch(current - 1, sets, -1);
            int nextLinkCount = this.graphSearch(current + 1, sets, +1);
            maxLength = Math.max(maxLength, nextLinkCount + prvLinkCount + 1);
        }
        return maxLength;
    }

    private int graphSearch(int startNumber, HashSet sets, int diffNumber) {
        int count = 0;
        while (true) {
            if (sets.contains(startNumber)) {
                count++;
                sets.remove(startNumber);
                startNumber += diffNumber;
            } else {
                break;
            }
        }
        return count;
    }
}

class LongestConsecutiveSequenceSolution4 {

    // TC -  o(n) + o(n) = o(n) , SC  = o(n)
    public int longestConsecutive(int[] nums) {
        HashSet sets = new HashSet();
        for (int num : nums) {
            sets.add(num);
        }
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!sets.contains(nums[i] - 1)) {
                int currentLength = 1;
                while (true) {
                    if (sets.contains(nums[i] + currentLength)) {
                        currentLength++;
                    } else {
                        break;
                    }
                }
                maxLength = Math.max(maxLength, currentLength);
            }

        }
        return maxLength;
    }
}


