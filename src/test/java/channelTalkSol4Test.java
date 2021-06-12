import channelTalk.CTSolution4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class channelTalkSol4Test {

    @Test
    void test1() {
        CTSolution4 sol = new CTSolution4();
        assertEquals(3, sol.solution(6, 3, "RBGRGB"));
    }

    @Test
    void test2() {
        CTSolution4 sol = new CTSolution4();
        assertEquals(-1, sol.solution(3, 2, "BGG"));
    }

    @Test
    void test3() {
        CTSolution4 sol = new CTSolution4();
        assertEquals(6, sol.solution(4, 2, "GBBG"));
    }
}
