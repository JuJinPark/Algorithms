package channelTalk;

import java.util.HashMap;
import java.util.Map;

//전구문제 그리디 알고리즘 인듯
public class CTSolution4 {

    private static final char RED = 'R';
    private static final char BLUE = 'B';
    private static final char GREEN = 'G';

    private static final char[] colorIndex = new char[]{RED, GREEN, BLUE};
    private static final Map<Character, Integer> colorIndexMap = new HashMap();

    public CTSolution4() {
        colorIndexMap.put(RED, 0);
        colorIndexMap.put(GREEN, 1);
        colorIndexMap.put(BLUE, 2);
    }

    public int solution(int n, int k, String bulbs) {
        char targetColor = RED;
        char[] bulbsArray = bulbs.toCharArray();
        int count = 0;
        for (int i = 0; i <= n - k; i++) {
            if (bulbsArray[i] != targetColor) {
                int switchCount = getSwitchCount(bulbsArray[i], targetColor);
                change(i, k, bulbsArray, switchCount);
                count += switchCount;
            }
        }

        if (isAllChange(bulbsArray, targetColor)) {
            return count;
        }
        return -1;
    }

    private int getSwitchCount(char currentColor, char targetColor) {
        int currentColorIndex = colorIndexMap.get(currentColor);
        int targetColorIndex = colorIndexMap.get(targetColor);
        if (targetColorIndex < currentColorIndex) {
            targetColorIndex += colorIndex.length + targetColorIndex;
        }
        return targetColorIndex - currentColorIndex;
    }

    private void change(int index, int consecutiveNumbers, char[] bulbsArray, int switchCount) {
        for (int i = index; i < index + consecutiveNumbers; i++) {
            bulbsArray[i] = nextColor(bulbsArray[i], switchCount);
        }

    }

    private char nextColor(char currentColor, int switchCount) {
        int index = colorIndexMap.get(currentColor);
        int nextIndex = (index + switchCount) % colorIndex.length;
        return colorIndex[nextIndex];
    }


    private boolean isAllChange(char[] bulbsArray, char targetColor) {
        for (char c : bulbsArray) {
            if (c != targetColor) {
                return false;
            }
        }
        return true;
    }
}
