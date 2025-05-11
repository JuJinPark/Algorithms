package leetCode.medium;

/*
Input: nums = [4,3,2,1,2,3,1]
Output: 2
Explanation: We can turn the array into a palindrome in 2 operations as follows:
- Apply the operation on the fourth and fifth element of the array, nums becomes equal to [4,3,2,3,3,1].
- Apply the operation on the fifth and sixth element of the array, nums becomes equal to [4,3,2,3,4].
The array [4,3,2,3,4] is a palindrome.
It can be shown that 2 is the minimum number of operations needed.
 */

import java.util.ArrayList;
import java.util.List;

public class MergeOperationsToTurnArrayIntoAPalindrome {

    public static void main(String[] args) {
        int[] input1 = new int[]{4, 3, 2, 1, 2, 3, 1};
        int[] input2 = new int[]{1, 2, 3, 4};

        int result1 = new MergeOperationsToTurnArrayIntoAPalindromeSolution1().solve(input1);
        int result2 = new MergeOperationsToTurnArrayIntoAPalindromeSolution1().solve(input2);
        System.out.println(result1);
        System.out.println(result2);

        int result3 = new MergeOperationsToTurnArrayIntoAPalindromeSolution2().solve(input1);
        int result4 = new MergeOperationsToTurnArrayIntoAPalindromeSolution2().solve(input2);
        System.out.println(result3);
        System.out.println(result4);
    }
}

/*
 tc - o(n)
 sc - o(1)
*/
class MergeOperationsToTurnArrayIntoAPalindromeSolution1 {

    public int solve(int[] input) {
        int left = 0;
        int right = input.length - 1;
        int operation = 0;
        long leftPrev = 0;
        long rightPrev = 0;

        while (left < right) {
            long currentLeft = leftPrev + input[left];
            long currentRight = rightPrev + input[right];
            if (currentLeft == currentRight) {
                left++;
                right--;
                leftPrev = 0;
                rightPrev = 0;
            } else if (currentLeft < currentRight) {
                left++;
                operation++;
                leftPrev = currentLeft;
            } else {
                right--;
                operation++;
                rightPrev = currentRight;
            }
        }
        return operation;

    }
}

/*
 tc - o(n!)
 sc - o(n)
*/
class MergeOperationsToTurnArrayIntoAPalindromeSolution2 {

    public int solve(int[] input) {
        List<Long> list = new ArrayList<>();
        for (int num : input) {
            list.add((long) num);
        }
        return dfs(list);
    }

    private int dfs(List<Long> nums) {
        if (isPalindrome(nums)) return 0;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            List<Long> next = new ArrayList<>(nums);
            long merged = next.get(i) + next.get(i + 1);
            next.remove(i);       // remove first of the pair
            next.set(i, merged);  // replace second with the merged sum
            min = Math.min(min, 1 + dfs(next));
        }
        return min;
    }

    private boolean isPalindrome(List<Long> nums) {
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            if (!nums.get(left).equals(nums.get(right))) return false;
            left++;
            right--;
        }
        return true;
    }
}
