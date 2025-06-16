package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        new MergeIntervalsSolution1().merge(intervals);
        new MergeIntervalsSolution2().merge(intervals);
    }
}


/**
 * tc - o nlogn +n + n *n = n^2
 * sc - o(n) + o(n) = o(n)
 */
class MergeIntervalsSolution1 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        List<int[]> intervalsList = new ArrayList();
        for (int i = 0; i < intervals.length; i++) {
            intervalsList.add(intervals[i]);
        }
        boolean isMerged = true;
        while (isMerged) {
            List<int[]> newList = new ArrayList();
            boolean newIsMerged = false;
            int index = 0;
            if (intervalsList.size() == 1) {
                newList.add(intervalsList.get(0));
            }
            while (index < intervalsList.size() - 1) {
                int[] first = intervalsList.get(index);
                int[] second = intervalsList.get(index + 1);
                if (isMergeable(first, second)) {
                    int[] mergedArray = this.merge(first, second);
                    newList.add(mergedArray);
                    newIsMerged = true;
                    if (index == intervalsList.size() - 3) {
                        newList.add(intervalsList.get(intervalsList.size() - 1));
                    }
                    index = index + 2;
                } else {
                    newList.add(first);
                    if (index == intervalsList.size() - 2) {
                        newList.add(second);
                    }
                    index++;

                }
            }

            // this.print(newList);
            isMerged = newIsMerged;
            intervalsList = newList;
        }

        int[][] result = new int[intervalsList.size()][2];
        for (int i = 0; i < intervalsList.size(); i++) {
            result[i] = intervalsList.get(i);
        }
        return result;
    }

    private boolean isMergeable(int[] first, int[] second) {
        return second[0] <= first[1];
    }

    private int[] merge(int[] first, int[] second) {
        return new int[]{Math.min(first[0], second[0]), Math.max(first[1], second[1])};
    }

    private void print(List<int[]> input) {
        System.out.println("----");
        for (int[] element : input) {
            System.out.println(element[0] + "," + element[1]);
        }
        System.out.println("----");
    }
}

/**
 * tc - n log n + n = n log n
 * sc - n
 */
class MergeIntervalsSolution2 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        List<int[]> intervalsList = new ArrayList();
        intervalsList.add(intervals[0]);

        for (int[] current : intervals) {
            int[] last = intervalsList.get(intervalsList.size() - 1);
            if (this.isMergeable(last, current)) {
                int[] merged = this.merge(last, current);
                last[0] = merged[0];
                last[1] = merged[1];
            } else {
                intervalsList.add(current);
            }
        }

        return intervalsList.toArray(new int[intervalsList.size()][]);
    }

    private boolean isMergeable(int[] first, int[] second) {
        return second[0] <= first[1];
    }

    private int[] merge(int[] first, int[] second) {
        return new int[]{Math.min(first[0], second[0]), Math.max(first[1], second[1])};
    }


}
