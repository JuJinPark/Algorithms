package leetCode.medium;

import java.util.HashMap;

/**
 * tc - o(1) per method so o(n) total
 * sc - o(n) - number of unique log messages at worst all of messages could be unique
 */
public class LoggerRateLimiter {

    public static void main(String[] args) {
        int[] timestamps = {1, 2, 3, 8, 10, 11};
        String[] messages = {"foo", "bar", "foo", "bar", "foo", "foo"};
        LoggerRateLimiterSol1 sol1 = new LoggerRateLimiterSol1();
        for (int i = 0; i < timestamps.length; i++) {
//            System.out.println(timestamps[i]+","+messages[i]);
            boolean shouldPrintMessage = sol1.shouldPrintMessage(timestamps[i], messages[i]);
            System.out.println(shouldPrintMessage);
        }
    }
}

class LoggerRateLimiterSol1 {

    private final HashMap<String, Integer> logHistory = new HashMap<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        int nextAvailableTime = logHistory.getOrDefault(message, -100) + 10;
        if (nextAvailableTime > timestamp) {
            return false;
        } else {
            logHistory.put(message, timestamp);
            return true;
        }
    }
}
