package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-k-closest-elements/description/
 */
public class FindKClosestElements {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;
        new FindKClosestElementsSolution1().findClosestElements(arr, k, x);
        new FindKClosestElementsSolution2().findClosestElements(arr, k, x);
    }
}

/**
 * tc - n log k  + k + k log k = o(n log k)+ o (k log k)
 * sc - o(k)
 */
class FindKClosestElementsSolution1 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });

        for (int i = 0; i < arr.length; i++) {
            int[] current = new int[]{Math.abs(arr[i] - x), arr[i]};
            if (queue.size() < k) {
                queue.add(current);
            } else {
                int[] biggest = queue.peek();
                if (biggest[0] > current[0] || (biggest[0] == current[0] && biggest[1] > current[1])) {
                    queue.poll();
                    queue.add(current);
                }
            }
        }

        List<Integer> result = new ArrayList();
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            result.add(current[1]);
        }

        Collections.sort(result);
        return result;

    }
}

/**
 * tc - o(n-k) + o(k) = o(n)
 * sc - o(k)
 */
class FindKClosestElementsSolution2 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = 0;
        int right = arr.length - 1;

        while (right - left + 1 > k) {
            if (Math.abs(x - arr[left]) > Math.abs(x - arr[right])) {
                left++;
            } else {
                right--;
            }
        }

        List<Integer> result = new ArrayList();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;

    }
}

// Solution3 binary Search???
