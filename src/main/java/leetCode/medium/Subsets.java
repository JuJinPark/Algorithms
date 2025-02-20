package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        List<List<Integer>> subsets = new SubsetsSolution().subsets(new int[]{2, 0, 1});
    }
}

class SubsetsSolution {
    public List<List<Integer>> subsets(int[] nums) {
        return this.firstSolution(nums);
    }

    /**
     * 1 , 2, 3
     * []  - [] - []
     * - [3]
     * - [2]
     * ...
     * [1] -
     * .   ...
     */
    private List<List<Integer>> firstSolution(int[] nums) {
        /*
         tc - o(2^n)*o(n) sc - o(2^n * n) + o(n)
         */
        List<List<Integer>> result = new ArrayList<>();
        this.recur(nums, result, 0, new ArrayList<>());
        return result;
    }


    private void recur(int[] nums, List<List<Integer>> result, int idx, List<Integer> temp) {
        if (idx > nums.length - 1) {
            result.add(new ArrayList<>(temp));
            return;
        }

        // case take idx
        temp.add(nums[idx]);
        this.recur(nums, result, idx + 1, temp); // [1],1  - [1,2], 1
        temp.remove(temp.size() - 1);
        // case not take idx
        this.recur(nums, result, idx + 1, temp);// [] 1, - [1]1
    }
}
