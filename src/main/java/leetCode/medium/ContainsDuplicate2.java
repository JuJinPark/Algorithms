package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        int k = 2;
        new ContainsDuplicate2Solution1().containsNearbyDuplicate(nums, k);
        new ContainsDuplicate2Solution2().containsNearbyDuplicate(nums, k);
        new ContainsDuplicate2Solution3().containsNearbyDuplicate(nums, k);
    }
}

/**
 * tc - o(n*k)
 * sc - o(1)
 */
class ContainsDuplicate2Solution1 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            int max = Math.min(i + k, nums.length - 1);
            for (int j = i + 1; j <= max; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * tc - o(n)
 * sc - o(2k) = o(k)
 */
class ContainsDuplicate2Solution2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0) {
            return false;
        }
        HashMap<Integer, Integer> slidingMap = new HashMap();

        for (int i = 1; i <= k; i++) {
            if (i >= nums.length) {
                break;
            }
            this.add(slidingMap, nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (slidingMap.containsKey(nums[i])) {
                return true;
            }
            int removeKey = i - k;
            if (removeKey >= 0) {
                this.remove(slidingMap, nums[removeKey]);
            }
            int nextKey = i + 1;
            if (nextKey < nums.length) {
                this.remove(slidingMap, nums[nextKey]);
            }

            int toAddKey = i + k + 1;
            if (toAddKey <= nums.length - 1) {
                this.add(slidingMap, nums[toAddKey]);
            }
            this.add(slidingMap, nums[i]);

        }
        return false;
    }

    private void remove(HashMap<Integer, Integer> slidingMap, int removeKey) {
        int count = slidingMap.get(removeKey);
        if (count == 1) {
            slidingMap.remove(removeKey);
        } else {
            slidingMap.put(removeKey, count - 1);
        }
    }

    private void add(HashMap<Integer, Integer> slidingMap, int addKey) {
        int count = slidingMap.getOrDefault(addKey, 0);
        slidingMap.put(addKey, count + 1);
    }
}

/**
 * tc - o(n)
 * sc - o(n-unique number)
 */
class ContainsDuplicate2Solution3 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (seen.containsKey(val) && i - seen.get(val) <= k) {
                return true;
            }
            seen.put(val, i);
        }

        return false;
    }

}
