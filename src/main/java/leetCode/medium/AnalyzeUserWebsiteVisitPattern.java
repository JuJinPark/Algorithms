package leetCode.medium;

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {

    public static void main(String[] args) {
        AnalyzeUserWebsiteVisitPatternSolution1 sol = new AnalyzeUserWebsiteVisitPatternSolution1();
        String[] username = {"joe", "joe", "joe", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "about", "career"};
        List<String> pattern = sol.mostVisitedPattern(username, timestamp, website);
        System.out.println(pattern);

    }
}


/**
 * Let n be the number of total entries
 * Let u be the number of unique users
 * Let v be the max number of visits per user (usually ≤ n)
 * p = number of distinct 3-sequence
 * tc - n + n log n + n + (u * v^3) + p = o(n^3)
 * sc - n + n + p  = n+p
 */
class AnalyzeUserWebsiteVisitPatternSolution1 {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        List<UserVisit> userVisits = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            userVisits.add(new UserVisit(timestamp[i], username[i], website[i]));
        }
        Collections.sort(userVisits, (a, b) -> a.time - b.time);

        Map<String, List<UserVisit>> visitPerUser = new HashMap<>();
        for (UserVisit userVisit : userVisits) {
            List<UserVisit> value = visitPerUser.getOrDefault(userVisit.name, new ArrayList<>());
            value.add(userVisit);
            visitPerUser.put(userVisit.name, value);
        }

        Map<String, Integer> frequency = new HashMap<>();
        for (String user : visitPerUser.keySet()) {
            List<UserVisit> visits = visitPerUser.get(user);
            Set<String> seen = new HashSet<>();

            if (visits.size() < 3) continue;
            for (int i = 0; i < visits.size() - 2; i++) {
                for (int j = i + 1; j < visits.size() - 1; j++) {
                    for (int k = j + 1; k < visits.size(); k++) {
                        String pattern = visits.get(i).site + "," + visits.get(j).site + "," + visits.get(k).site;
                        if (!seen.contains(pattern)) {
                            int freq = frequency.getOrDefault(pattern, 0);
                            frequency.put(pattern, freq + 1);
                            seen.add(pattern);
                        }

                    }
                }
            }
        }


        String result = "";
        for (String pattern : frequency.keySet()) {
            if (result.equals("")) {
                result = pattern;
                continue;
            }
            if (frequency.get(result) < frequency.get(pattern)) {
                result = pattern;
            } else if (frequency.get(result).equals(frequency.get(pattern)) && result.compareTo(pattern) > 0) {
                result = pattern;
            }
        }
        return Arrays.asList(result.split(","));
    }

}

class UserVisit {
    public int time;
    public String name;
    public String site;

    public UserVisit(int time, String name, String site) {
        this.time = time;
        this.name = name;
        this.site = site;
    }
}