package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EncodeAndDecodeStrings {

    public static void main(String[] args) {
        String encode = new EncodeAndDecodeStringsHeaderFirstStringSolution().encode(Arrays.asList(""));
        String encode2 = new EncodeAndDecodeStringsHeaderFirstStringSolution().encode(Arrays.asList());
    }
}

/*
 Not working when delimiter exist in string
 m = m is the sum of lengths of all the strings
 n = number of strings
 * tc - o(n + m)
 * sc - o(m+n)
 */
class EncodeAndDecodeStringsDelimiterSolution {
    private String delimiter = "/";

    public String encode(List<String> strs) {
        if (strs.size() == 0) {
            return null;
        }
        return strs
                .stream()
                .collect(Collectors.joining(this.delimiter));
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        if (str == null) {
            return result;
        }
        String[] split = str.split(this.delimiter);
        for (String each : split) {
            result.add(each);
        }
        return result;
    }
}

/*
 m = m is the sum of lengths of all the strings
 n = number of strings
 * tc - o(n+m)
 * sc - o(m+n)
 */
class EncodeAndDecodeStringsHeaderPerStringSolution {

    private String headSeparator = "#";

    public String encode(List<String> strs) {
        return strs
                .stream()
                .map(i -> i.length() + this.headSeparator + i)
                .collect(Collectors.joining());
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList();
        String length = "";
        int idx = 0;
        while (idx < str.length()) {
            String cur = str.charAt(idx) + "";
            if (cur.equals(this.headSeparator)) {
                int lengthInNumber = Integer.parseInt(length);
                result.add(str.substring(idx + 1, idx + 1 + lengthInNumber));
                idx = idx + 1 + lengthInNumber;
                length = "";
            } else {
                length += cur;
                idx++;
            }

        }
        return result;
    }
}

/*
 m = m is the sum of lengths of all the strings
 n = number of strings
 * tc - o(n+m)
 * sc - o(m+n)
 */
class EncodeAndDecodeStringsHeaderFirstStringSolution {

    private String headSeparator = "#";
    private String sizeSeparator = ",";

    public String encode(List<String> strs) {
        String header =
                strs.stream()
                        .map(i -> i.length() + "")
                        .collect(Collectors.joining(this.sizeSeparator));

        if (header.isEmpty()) {
            return "";
        }
        return header + this.headSeparator + strs
                .stream()
                .collect(Collectors.joining());
    }

    public List<String> decode(String str) {
        if (str.isEmpty()) {
            return new ArrayList();
        }
        String header = "";
        int idx = 0;
        while (idx < str.length()) {
            String cur = str.charAt(idx) + "";
            if (cur.equals(this.headSeparator)) {
                idx++;
                break;
            } else {
                header += cur;
                idx++;

            }
        }
        String[] sizes = header.split(this.sizeSeparator);
        List<String> result = new ArrayList<>();
        int newIdx = idx;
        for (String size : sizes) {
            int sizeInNumber = Integer.parseInt(size);
            result.add(str.substring(newIdx, newIdx + sizeInNumber));
            newIdx += sizeInNumber;
        }
        return result;

    }
}



