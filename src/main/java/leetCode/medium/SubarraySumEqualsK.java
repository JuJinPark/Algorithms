package leetCode.medium;

import java.util.HashMap;

public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int result = new SubarraySumEqualsKSolution().subarraySum(new int[]{1, 1, 1}, 2);
    }
}

class SubarraySumEqualsKSolution {
    public int subarraySum(int[] nums, int k) {
        return this.firstSolution(nums, k);
    }

    private int firstSolution(int[] nums, int k) {
        /*
         tc- o(n)
         sc - o(n)
         */
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, 1);
        int count = 0;
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int targetPrefixToRemove = prefixSum - k;
            count += map.getOrDefault(targetPrefixToRemove, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}