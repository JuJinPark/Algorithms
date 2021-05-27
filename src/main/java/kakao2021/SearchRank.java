package kakao2021;

import java.util.*;
import java.util.stream.Collectors;

public class SearchRank {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        IndexedInfo indexedInfo = new IndexedInfo(info);
        for (int i = 0; i < query.length; i++) {
            answer[i] = indexedInfo.find(query[i]);
        }
        return answer;
    }
}

class IndexedInfo {

    Map<String, List<Integer>> indexedInfo = new HashMap();

    public IndexedInfo(String[] info) {

        //모든 조합 만들기
        for (String s : info) {
            String[] infoArray = s.split(" ");
            String[][] toMakeCombination = new String[infoArray.length - 1][2];
            for (int j = 0; j < infoArray.length - 1; j++) {
                toMakeCombination[j][0] = infoArray[j];
                toMakeCombination[j][1] = "-";
            }
            int number = Integer.parseInt(infoArray[infoArray.length - 1]);
            makeCombination(4, new String[4], toMakeCombination, 0, number);
        }

        //숫자 작은 순으로 정렬하기
        Set<String> keys = indexedInfo.keySet();
        for (String key : keys) {
            List<Integer> integers = indexedInfo.get(key);
            Collections.sort(integers);
        }
    }

    private void makeCombination(int r, String[] temp, String[][] toMakeCombination, int current, int number) {
        if (r == current) {
            String joinedString = String.join(",", temp);
            if(!indexedInfo.containsKey(joinedString)){
                indexedInfo.put(joinedString, new ArrayList<>());
            }
            indexedInfo.get(joinedString).add(number);

        } else {
            for (int i = 0; i < toMakeCombination[current].length; i++) {
                temp[current] = toMakeCombination[current][i];
                makeCombination(r, temp, toMakeCombination,current + 1,number);
            }
        }
    }

    public int find(String query) {
        //찾을 key생성
        String[] split = query.split(" and ");
        String[] lastSplit = split[split.length-1].split(" ");
        String key = split[0]+","+split[1]+","+split[2]+","+lastSplit[0];
        int number = Integer.parseInt(lastSplit[1]);

        List<Integer> numberList = new ArrayList<>();
        if(indexedInfo.containsKey(key)){
            numberList = indexedInfo.get(key);
        }
        //이분 탐색으로 lower bound 찾기
        int lowerBoundIndex = binarySearchLowerBound(numberList,number);
        return numberList.size() - lowerBoundIndex;


    }

    private int binarySearchLowerBound(List<Integer> numberList, int number) {
        int low = 0;
        int high = numberList.size();
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (number <= numberList.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


}
