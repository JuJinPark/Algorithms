package leetCode.medium;

import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] input = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result1 = new TopKFrequentElementsSolution1().topKFrequent(input, k);
        int[] result2 = new TopKFrequentElementsSolution2().topKFrequent(input, k);
        int[] result3 = new TopKFrequentElementsSolution3().topKFrequent(input, k);
    }
}

class TopKFrequentElementsSolution1 {

    /*
    tc - o (n long n) + n + n + k =  o (n log n)
    sc - o (n) + o (n) + sorting(1) + o(k) = o(n)
    */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap();
        List<FreqPair> list = new ArrayList();
        for (int num : nums) {
            int count = freqMap.getOrDefault(num, 0);
            freqMap.put(num, count + 1);
        }
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            list.add(new FreqPair(entry.getKey(), entry.getValue()));
        }
        Collections.sort(list, (l1, l2) -> l2.count - l1.count);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).number;
        }
        return result;
    }
}

class TopKFrequentElementsSolution2 {

    /*
    tc: o(n) + (o (n ) insert * o(log k)-sort) + o (k) = o (n log k)
    sc: o(n) + o(k) + o(k) = o(n) + o(k)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap();
        PriorityQueue<FreqPair> queue = new PriorityQueue<>((l1, l2) -> l1.count - l2.count);
        for (int num : nums) {
            int count = freqMap.getOrDefault(num, 0);
            freqMap.put(num, count + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            FreqPair pair = new FreqPair(entry.getKey(), entry.getValue());
            if (queue.size() < k) {
                queue.offer(pair);
            } else {
                FreqPair leastFreq = queue.peek();
                if (pair.count > leastFreq.count) {
                    queue.poll();
                    queue.offer(pair);
                }
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().number;
        }
        return result;
    }
}

/**
 * tc: o(n) + o(n) + o(n) + o(k) = o(n)
 * sc: o(n) + o(n) + o(k) = o(n)
 */
class TopKFrequentElementsSolution3 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap();
        for (int num : nums) {
            int count = freqMap.getOrDefault(num, 0);
            freqMap.put(num, count + 1);
        }
        List<Integer>[] freqList = new List[nums.length + 1];

        for (int i = 0; i < freqList.length; i++) {
            freqList[i] = new ArrayList();
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            freqList[entry.getValue()].add(entry.getKey());
        }
        int[] result = new int[k];
        int resultIdx = 0;
        for (int i = freqList.length - 1; i >= 0; i--) {
            if (resultIdx >= k) {
                break;
            }
            for (int j = 0; j < freqList[i].size(); j++) {
                result[resultIdx] = freqList[i].get(j);
                resultIdx++;
            }
        }

        return result;
    }
}


class FreqPair {
    int number;
    int count;

    public FreqPair(int number, int count) {
        this.number = number;
        this.count = count;
    }
}
