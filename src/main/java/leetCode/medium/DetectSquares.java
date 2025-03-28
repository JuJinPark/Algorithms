package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetectSquares {

    public static void main(String[] args) {

    }
}

/*
 *  tc - o(n2)
 *  sc - o(n)
 */
class DetectSquaresSolution1 {

    private HashMap<Integer, List<Integer[]>> xMap = new HashMap();
    private HashMap<Integer, List<Integer[]>> yMap = new HashMap();

    public DetectSquaresSolution1() {

    }

    public void add(int[] point) {
        List<Integer[]> xList = xMap.getOrDefault(point[0], new ArrayList());
        xList.add(new Integer[]{Integer.valueOf(point[0]), Integer.valueOf(point[1])});
        xMap.put(point[0], xList);
        List<Integer[]> yList = yMap.getOrDefault(point[1], new ArrayList());
        yList.add(new Integer[]{Integer.valueOf(point[0]), Integer.valueOf(point[1])});
        yMap.put(point[1], yList);

    }

    public int count(int[] point) {
        List<Integer[]> firstPointsCandidates = xMap.getOrDefault(point[0], new ArrayList());
        List<Integer[]> secondPointsCandidates = new ArrayList();

        for (Integer[] firstCan : firstPointsCandidates) {
            int length = Math.abs(point[1] - firstCan[1]);
            if (length == 0) {
                continue;
            }
            List<Integer[]> candidates = yMap.getOrDefault(firstCan[1], new ArrayList());
            for (Integer[] secondCan : candidates) {
                if (Math.abs(secondCan[0] - point[0]) == length) {
                    secondPointsCandidates.add(secondCan);
                }
            }
        }
        List<Integer[]> thirdPointsCandiates = new ArrayList();
        for (Integer[] secondCan : secondPointsCandidates) {
            List<Integer[]> list = xMap.getOrDefault(secondCan[0], new ArrayList());
            for (Integer[] thirdCan : list) {
                if (thirdCan[1] == point[1]) {
                    thirdPointsCandiates.add(thirdCan);
                }
            }
        }
        return thirdPointsCandiates.size();
    }
}

/*
 *  tc - o(n)
 *  sc - o(n)
 */
class DetectSquaresSolution2 {

    private List<int[]> points = new ArrayList();
    private HashMap<String, Integer> countMap = new HashMap();

    public DetectSquaresSolution2() {

    }

    public void add(int[] point) {
        points.add(point);
        String key = point[0] + "," + point[1];
        int count = countMap.getOrDefault(key, 0);
        countMap.put(key, count + 1);
    }

    public int count(int[] point) {
        int result = 0;
        int pointx1 = point[0];
        int pointy1 = point[1];
        for (int[] cur : points) {
            int pointx3 = cur[0];
            int pointy3 = cur[1];

            if (Math.abs(pointx1 - pointx3) == 0 ||
                    (Math.abs(pointx1 - pointx3) != Math.abs(pointy1 - pointy3))) {
                continue;
            }
            int pointx2 = pointx1;
            int pointy2 = pointy3;
            int pointx4 = pointx3;
            int pointy4 = pointy1;
            String point2Key = pointx2 + "," + pointy2;
            String point4Key = pointx4 + "," + pointy4;
            int numberOfPoint2 = countMap.getOrDefault(point2Key, 0);
            int numberOfPoint4 = countMap.getOrDefault(point4Key, 0);
            result += numberOfPoint2 * numberOfPoint4;
        }
        return result;
    }
}