package leetCode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] answers = new KClosestPointsToOriginSolution().kClosest(new int[][]{{1, 3}, {-2, 2}}, 1);
        System.out.println(Arrays.stream(answers)
                .map(answer -> answer[0]+","+answer[1])
                .collect(Collectors.toList())
        );
    }
}

class KClosestPointsToOriginSolution {
    public int[][] kClosest(int[][] points, int k) {
        return this.firstSolution(points, k);
    }

    private int[][] firstSolution(int[][] points, int k) {
        //tc - n log k + k = n logk / sc- k +k = k
        Queue<KClosestPointsToOriginPoint> queue = new PriorityQueue(
                (Comparator<KClosestPointsToOriginPoint>) (p1, p2) -> Double.compare(p2.distanceBeforeSqrt, p1.distanceBeforeSqrt));

        for (int i = 0; i < points.length; i++) {
            KClosestPointsToOriginPoint currentPoint = new KClosestPointsToOriginPoint(points[i]);

            if (queue.size() < k) {
                queue.add(currentPoint);
            } else {
                KClosestPointsToOriginPoint farthest = queue.peek();
                if (farthest.distanceBeforeSqrt > currentPoint.distanceBeforeSqrt) {
                    queue.poll();
                    queue.add(currentPoint);
                }
            }

        }

        int[][] result = new int[k][2];

        int idx = 0;
        while (!queue.isEmpty()) {
            KClosestPointsToOriginPoint target = queue.poll();
            result[idx++] = target.coordinate;
        }
        return result;
    }
}

class KClosestPointsToOriginPoint {

    public int[] coordinate;
    public double distanceBeforeSqrt;

    public KClosestPointsToOriginPoint(int[] coordinate) {
        this.coordinate = coordinate; //-2,2
        this.distanceBeforeSqrt = Math.pow((0 - coordinate[0]), 2) + Math.pow((0 - coordinate[1]), 2); //4+4 =8
    }
}