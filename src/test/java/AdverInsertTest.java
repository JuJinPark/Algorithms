import channelTalk.CTSolution2;
import kakao2021.AdvertInsert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdverInsertTest {
    @Test
    void test1() {
        AdvertInsert sol = new AdvertInsert();
        assertEquals("01:30:59", sol.solution("02:03:55","00:14:15",
                new String[] {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
    }
    @Test
    void test2() {
        AdvertInsert sol = new AdvertInsert();
        assertEquals("01:00:00", sol.solution("99:59:59","25:00:00",
                new String[] {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
    }

    @Test
    void test3() {
        AdvertInsert sol = new AdvertInsert();
        assertEquals("00:00:00", sol.solution("50:00:00","50:00:00",
                new String[] {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));
    }
}
