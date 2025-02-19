package leetCode.medium;

public class SortColor {

    public static void main(String[] args) {
        new SortColorSolution().sortColors(new int[]{2,0,1});
    }
}

class SortColorSolution {
    public void sortColors(int[] nums) {

        this.secondSolution(nums);
    }

    private void secondSolution(int[] nums) {
        /*
         tc - o(n) sc - o(1)
         */

        int idx = 0;
        int left = 0;
        int right = nums.length - 1;
        while (idx <= right) {
            int value = nums[idx];
            if (value == 2) {
                this.swap(idx, right, nums);
                right--;
            } else if (value == 0) {
                this.swap(idx, left, nums);
                left++;
                idx++;
            } else {
                idx++;
            }

        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void firsSolution(int[] nums) {
        /*
         count 0, 1, 2 => write count from 0 ->2 in nums
         tc - o(2n) sc - o(1)
         */
        int redCount = 0;
        int whiteCount = 0;
        int blueCount = 0;
        for (int value : nums) {
            if (value == 0) {
                redCount++;
            } else if (value == 1) {
                whiteCount++;
            } else {
                blueCount++;
            }
        }
        int idx = 0;
        for (int i = 0; i < redCount; i++) {
            nums[idx] = 0;
            idx++;
        }
        for (int i = 0; i < whiteCount; i++) {
            nums[idx] = 1;
            idx++;
        }
        for (int i = 0; i < blueCount; i++) {
            nums[idx] = 2;
            idx++;
        }
    }
}
