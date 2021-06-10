import channelTalk.CTSolution2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class channelTalkSol2Test {

    @Test
    void test1() {
        CTSolution2 sol = new CTSolution2();
        assertEquals(3, sol.solution(1,1,1,1));
    }

    @Test
    void test2() {
        CTSolution2 sol = new CTSolution2();
        assertEquals(8, sol.solution(5,5,3,3));
    }
}
