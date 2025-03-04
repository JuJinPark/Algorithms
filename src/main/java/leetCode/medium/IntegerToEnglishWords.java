package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class IntegerToEnglishWords {

    public static void main(String[] args) {
        String result = new IntegerToEnglishWordsSolution().numberToWords(123123);
    }
}

class IntegerToEnglishWordsSolution {
    private final String[] ones = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] tens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] twenties = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        return this.firstSolution(num);

    }

    private String firstSolution(int num) {
        /*
        d - number of digits
        tc - o(d/3) - o(d)
        sc - o(d/3) - o(d)
         */
        if (num == 0) {
            return "Zero";
        }
        List<String> chunks = this.toChunks(num);
        String[] thousands = new String[]{"", "Thousand", "Million", "Billion"};

        StringBuilder result = new StringBuilder();
        for (int i = chunks.size() - 1; i >= 0; i--) {
            String englishRep = this.convert(chunks.get(i));
            String thousand = thousands[i];
            if (i == 0) {
                result.append(" ").append(englishRep);
            } else if (!englishRep.isEmpty()) {
                result.append(" ").append(englishRep).append(" ").append(thousand);
            }
        }

        return result.toString().trim();
    }


    private List<String> toChunks(int num) {
        List<String> chunks = new ArrayList<>();
        String stringRep = num + "";
        for (int i = stringRep.length(); i > 0; i -= 3) {
            int start = Math.max(i - 3, 0);
            chunks.add(stringRep.substring(start, i));
        }
        return chunks;
    }

    private String convert(String stringNum) {
        String result = "";

        if (stringNum.length() == 3) {
            int numRep = stringNum.charAt(0) - '0';
            String nexString = stringNum.substring(1);
            if (numRep == 0) {
                result = this.convert(nexString);
            } else {
                result = ones[numRep] + " " + "Hundred" + " " + this.convert(nexString);
            }
        }

        if (stringNum.length() == 2) {
            int numRepFor2 = Integer.parseInt(stringNum);
            if (numRepFor2 < 10) {
                result = this.convert(numRepFor2 + "");
            } else if (numRepFor2 >= 10 && numRepFor2 < 20) {
                int idx = numRepFor2 - 10;
                result = tens[idx];
            } else {
                result = twenties[(numRepFor2 / 10)] + " " + this.convert(stringNum.substring(1));
            }
        }

        if (stringNum.length() == 1) {
            int numRepFor1 = Integer.parseInt(stringNum);
            if (numRepFor1 > 0) {
                result = ones[numRepFor1];
            }
        }
        return result.trim();
    }

}
