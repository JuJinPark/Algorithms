package kakao2021;

import java.util.Arrays;

public class AdvertInsert {
    private static final int HOUR_IN_SECOND= 60*60;
    private static final int MINUTE_IN_SECOND= 60;

    public String solution(String play_time, String adv_time, String[] logs) {
        long[] playCount;
        playCount = new long[stringToInt(play_time)+1];
        for (String log : logs) {
            String[] split = log.split("-");
            int startTimeInSecond = stringToInt(split[0]);
            int endTimeInSecond = stringToInt(split[1]);
            remarkStartAndEndTime(playCount,startTimeInSecond,endTimeInSecond);
        }
        accumulatePlayCount(playCount);

        int adTimeInSecond = stringToInt(adv_time);
        long maxAccPlayCount = playCount[adTimeInSecond -1];
        int maxStartTime = 0;
        for (int i = 1; i <= playCount.length - adTimeInSecond; i++) {
            long currentAccPlayCount = playCount[i+adTimeInSecond-1]-playCount[i-1];
            if(maxAccPlayCount < currentAccPlayCount){
                maxAccPlayCount = currentAccPlayCount;
                maxStartTime= i;
            }
        }
        return intToStringTime(maxStartTime);
    }

    private void accumulatePlayCount(long[] playCount) {
        for (int i = 1; i < playCount.length; i++) {
            playCount[i]+=playCount[i-1];
        }
        for (int i = 1; i < playCount.length; i++) {
            playCount[i]+=playCount[i-1];
        }

    }

    private void remarkStartAndEndTime(long[] playCount, int startTimeInSecond, int endTimeInSecond) {
        playCount[startTimeInSecond]++;
        playCount[endTimeInSecond]--;
    }

    private String intToStringTime(int maxStartTime) {
        return String.format("%02d:%02d:%02d", maxStartTime / (HOUR_IN_SECOND), (maxStartTime / MINUTE_IN_SECOND) % 60, maxStartTime % 60);
    }

    private int stringToInt(String play_time) {
        int[] timeArray = Arrays.stream(play_time.split(":"))
                .mapToInt(Integer::parseInt).toArray();
        return (timeArray[0]*(HOUR_IN_SECOND))+(timeArray[1]*(MINUTE_IN_SECOND))+timeArray[2];
    }

}
