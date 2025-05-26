package leetCode.hard;

import java.util.*;

public class MaximumFrequencyStack {

    public static void main(String[] args) {
        MaximumFrequencyStackSol1 MaximumFrequencyStackSol1 = new MaximumFrequencyStackSol1();
    }
}


/**
 * n - time of operation
 * s - unique value
 * sc - s + n = o(n)
 */
class MaximumFrequencyStackSol1 {

    int maxFreq = 0;
    Map<Integer, List<Integer>> allFreqMap = new HashMap();
    Map<Integer, Integer> freqPerValueMap = new HashMap();

    public MaximumFrequencyStackSol1() {

    }

    /**
     * tc - o(1)
     *
     * @param val
     */
    public void push(int val) {
        int freq = freqPerValueMap.getOrDefault(val, 0);
        int newFreq = freq + 1;
        if (newFreq > maxFreq) {
            this.maxFreq = newFreq;
        }
        freqPerValueMap.put(val, newFreq);
        List<Integer> list = allFreqMap.getOrDefault(newFreq, new ArrayList());
        list.add(val);
        allFreqMap.put(newFreq, list);
    }

    /**
     * tc - o(1)
     *
     * @return
     */
    public int pop() {
        List<Integer> list = allFreqMap.get(this.maxFreq);
        int target = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        int freq = freqPerValueMap.get(target);
        freqPerValueMap.put(target, freq - 1);

        if (list.isEmpty()) {
            this.maxFreq--;
        }
        return target;
    }
}

