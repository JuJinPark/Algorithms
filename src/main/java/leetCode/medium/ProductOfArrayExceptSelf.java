package leetCode.medium;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 5, 6};
        new ProductOfArrayExceptSelfSolution1().productExceptSelf(input);
        new ProductOfArrayExceptSelfSolution2().productExceptSelf(input);
        new ProductOfArrayExceptSelfSolution3().productExceptSelf(input);
        new ProductOfArrayExceptSelfSolution4().productExceptSelf(input);

    }
}

class ProductOfArrayExceptSelfSolution1 {
    /**
     * tc - o(n2)
     * sc - o(n) result
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int tempResult = 1;//
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    tempResult *= nums[j];
                }
            }

            result[i] = tempResult;
        }

        return result;
    }
}

/**
 * prdouctArrayFromStart [1, 2, 6, 24]
 * prdocutArrayFromEnd = [24,24,12,4]
 * nums = [1,....2 ....,3,4]
 * i
 * prodcdut start to  i-1 * prdouct of from end to i+1
 */

class ProductOfArrayExceptSelfSolution2 {
    /**
     * tc - o(n) + o(n) + o(n) = o(n)
     * sc - o(n) + o(n) + o(n) = o(n)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] productArrayFromStart = new int[nums.length];
        productArrayFromStart[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            productArrayFromStart[i] = productArrayFromStart[i - 1] * nums[i];
        }
        int[] productArrayFromEnd = new int[nums.length];
        productArrayFromEnd[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            productArrayFromEnd[i] = productArrayFromEnd[i + 1] * nums[i];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {

            int productFromStart = 1;
            if (i > 0) {
                productFromStart = productArrayFromStart[i - 1];
            }
            int productFromEnd = 1;
            if (i < nums.length - 1) {
                productFromEnd = productArrayFromEnd[i + 1];
            }
            result[i] = productFromStart * productFromEnd;
        }
        return result;

    }
}

class ProductOfArrayExceptSelfSolution3 {

    /**
     * tc - o(n) + o(n) = o(n)
     * sc - o(n) result + o(1) = o(n)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int prevProduct = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int curProduct = prevProduct * nums[i + 1];
            result[i] = result[i] * curProduct;
            prevProduct = curProduct;
        }
        return result;
    }
}

class ProductOfArrayExceptSelfSolution4 {

    /**
     * tc - o(n) + o(n) = o(n)
     * sc - o(n) + o(1) = o(n)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int zeroCount = 0;
        int prod = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                prod *= nums[i];
            } else {
                zeroCount++;
            }
        }

        if (zeroCount > 1) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            if (zeroCount > 0) {
                if (nums[i] == 0) {
                    result[i] = prod;
                } else {
                    result[i] = 0;
                }
            } else {
                result[i] = prod / nums[i];
            }
        }

        return result;
    }
}
