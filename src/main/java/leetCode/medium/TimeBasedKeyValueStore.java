package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {
    public static void main(String[] args) {
        TimeBasedKeyValueStore1 timeBasedKeyValueStore1 = new TimeBasedKeyValueStore1();
        timeBasedKeyValueStore1.set("foo","bar",1);
        timeBasedKeyValueStore1.get("foo",1);
        TimeBasedKeyValueStore2 timeBasedKeyValueStore2 = new TimeBasedKeyValueStore2();
        timeBasedKeyValueStore2.set("foo","bar",1);
        timeBasedKeyValueStore2.get("foo",1);
    }
}

/**
 * tc - get: o(n), set(o1)
 * sc - o(m*n)
 */
class TimeBasedKeyValueStore1 {

    private final Map<String, List<TimeMapValue>> map = new HashMap<String, List<TimeMapValue>>();

    public TimeBasedKeyValueStore1() {

    }

    public void set(String key, String value, int timestamp) {
        List<TimeMapValue> target = map.getOrDefault(key, new ArrayList<TimeMapValue>());
        target.add(new TimeMapValue(value, timestamp));
        map.put(key, target);
    }

    public String get(String key, int timestamp) {
        List<TimeMapValue> target = map.getOrDefault(key, new ArrayList<TimeMapValue>());
        for (int i = target.size() - 1; i >= 0; i--) {
            if (target.get(i).getTimestamp() <= timestamp) {
                return target.get(i).getValue();
            }
        }
        return "";
    }
}


/**
 * tc - get: o(log n), set(o1)
 * sc - o(m*n)
 */
class TimeBasedKeyValueStore2 {

    private final Map<String, List<TimeMapValue>> map = new HashMap<String, List<TimeMapValue>>();

    public TimeBasedKeyValueStore2() {

    }

    public void set(String key, String value, int timestamp) {
        List<TimeMapValue> target = map.getOrDefault(key, new ArrayList<TimeMapValue>());
        target.add(new TimeMapValue(value, timestamp));
        map.put(key, target);
    }

    public String get(String key, int timestamp) {
        List<TimeMapValue> target = map.getOrDefault(key, new ArrayList<TimeMapValue>());
        int left = 0;
        int right = target.size() - 1;
        String result = "";
        while (left <= right) {
            int middle = (left + right) / 2;
            int curTimeValue = target.get(middle).getTimestamp();
            if (timestamp < curTimeValue) {
                right = middle - 1;
            } else {
                result = target.get(middle).getValue();
                left = middle + 1;
            }
        }

        return result;
    }
}


class TimeMapValue {
    private final String value;
    private final int timestamp;

    public TimeMapValue(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getValue() {
        return this.value;
    }

    public int getTimestamp() {
        return this.timestamp;
    }
}