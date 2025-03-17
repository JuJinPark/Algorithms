package leetCode.medium;

public class KokoEatingBananas {

    public static void main(String[] args) {
        int result = new KokoEatingBananasSolution().minEatingSpeed(new int[]{3, 6, 7, 11}, 8);
    }
}

class KokoEatingBananasSolution {
    public int minEatingSpeed(int[] piles, int h) {
        return this.firstSolution(piles, h);
    }

    private int firstSolution(int[] piles, int h) {
        // m - max piles, n - number of piles tc - o(n long m) + n sc - o(1)
        int high = this.findMax(piles);
        int low = 1;
        if (piles.length == h) {
            return high;
        }
        while (low <= high) {
            int middle = (high + low) / 2;
            if (this.isPossible(middle, piles, h)) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
            ;
        }
        return low;
    }

    private boolean isPossible(int k, int[] piles, int h) {
        long totalHourTaken = 0;
        for (int i = 0; i < piles.length; i++) {
            totalHourTaken += piles[i] / k;
            if (piles[i] % k != 0) {
                totalHourTaken++;
            }
        }
        if (totalHourTaken <= h) {
            return true;
        }
        return false;
    }

    private int findMax(int[] piles) {
        int max = 0;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        return max;
    }
}
