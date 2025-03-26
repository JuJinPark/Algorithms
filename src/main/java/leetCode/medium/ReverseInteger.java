package leetCode.medium;

public class ReverseInteger {

    public static void main(String[] args) {
        int reverse = new ReverseIntegerSolution().reverse(123);
    }
}

class ReverseIntegerSolution {

    public int reverse(int x) {
        return this.secondSolution(x);
    }

    // using long not allowed
    private int firstSolution(int x) {
        /*
         tc - d (digit count) sc - o(1)
         */
        Long result = 0L; // 21
        int target = x; // 0
        if (x < 0) {
            target = target * -1;
        }
        while (target > 0) {
            result = result * 10;
            int current = target % 10; // 0
            result += current; //
            target = target / 10;
        }
        if (x < 0) {
            result = result * (-1l);
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return result.intValue();
    }

    private int secondSolution(int x) {
        /*
            tc - d (digit count) sc - o(1)
         */
        int result = 0;
        int target = x;
        if (x < 0) {
            target = -target;
        }
        while (target > 0) {
            int current = target % 10;
            if (result > (Integer.MAX_VALUE / 10)) {
                return 0;
            }
            if (result == (Integer.MAX_VALUE / 10) && current > (Integer.MAX_VALUE % 10)) {
                return 0;
            }
            result = result * 10;
            result += current;
            target = target / 10;
        }
        if (x < 0) {
            result = result * -1;
        }
        return result;
    }
}
