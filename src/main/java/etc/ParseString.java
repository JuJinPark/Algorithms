package etc;


import java.util.*;

public class ParseString {
    public static void main(String[] args) {
        String result1_1= ParseString.recoverPassword1("a<B>geld~colde<<~>>");
        String result1_2 = ParseString.recoverPassword2("a<B>geld~colde<<~>>");
        System.out.println(result1_1.equals("Bagelcode"));
        System.out.println(result1_2.equals("Bagelcode"));


        String result2_1 = ParseString.recoverPassword1("babo~~~~~~~babaisyou");
        String result2_2 = ParseString.recoverPassword2("babo~~~~~~~babaisyou");
        System.out.println(result2_1.equals("babaisyou"));
        System.out.println(result2_2.equals("babaisyou"));


        String result3_1 = ParseString.recoverPassword1("Ve<<<<<Los~~as>>>>>>>gas");
        String result3_2 = ParseString.recoverPassword2("Ve<<<<<Los~~as>>>>>>>gas");
        System.out.println(result3_1.equals("LasVegas"));
        System.out.println(result3_2.equals("LasVegas"));

        String result4_1 = ParseString.recoverPassword1("abc<<d><x");
        String result4_2 = ParseString.recoverPassword1("abc<<d><x");
        System.out.println(result4_1.equals("adxbc"));
        System.out.println(result4_2.equals("adxbc"));


    }

    public static String recoverPassword2(String input) {
        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                left.addLast(c);
            } else if (c == '~') {
                if (!left.isEmpty()) left.removeLast();
            } else if (c == '<') {
                if (!left.isEmpty()) right.addLast(left.removeLast());
            } else if (c == '>') {
                if (!right.isEmpty()) left.addLast(right.removeLast());
            }
        }

        // 조합: left + reversed right
        StringBuilder sb = new StringBuilder();
        for (char c : left) sb.append(c);
        List<Character> rightList = new ArrayList<>(right);
        Collections.reverse(rightList);
        for (char c : rightList) sb.append(c);

        return sb.toString();
    }



    private static String recoverPassword1(String input) {
        Node dummy = new Node(null);
        Node node = dummy;
        for(int i =0; i<input.length(); i++) {
            char cur = input.charAt(i);
            if(Character.isAlphabetic(cur)) {
                Node prevNext = node.next;
                Node newNode = new Node(cur);
                node.next = newNode;
                newNode.prev = node;
                newNode.next = prevNext;
                if(prevNext!=null) {
                    prevNext.prev = newNode;
                }

                node = newNode;
            }else if(cur == '<') {
                if(node != dummy) {
                    node = node.prev;
                }
            }else if(cur == '>') {
                if(node.next != null) {
                    node = node.next;
                }
            } else if(cur =='~') {
                if(node != dummy) {
                    Node prevNode = node.prev;
                    prevNode.next = node.next;
                    node = prevNode;
                }

            }
        }
        Node loopNode = dummy.next;
        StringBuilder builder = new StringBuilder();
        while(loopNode!=null) {
            builder.append(loopNode.character+"");
            loopNode = loopNode.next;
        }
        return builder.toString();
    }
}

class Node {
    public Node prev;
    public Character character;
    public Node next;

    public Node(Character character) {
        this.character = character;
    }
}

// Bagelcode
// 갓갓해커 A 은 서버팀 어피치가 사용하는 키보드에 해킹툴인 키로거를 심었다.
// 이 키로거는 어피치가 타이핑하는 모든 키를 기록한다.

// A 은 이를 이용하여 어피치가 누른 비밀번호를 알아내려고 한다.
// 어피치가 비밀번호를 치기 위해 누른 키가 input 으로 주어지면,
// 이를 이용하여 비밀번호를 찾아내는 프로그램을 시간 복잡도를 고려하여 작성하시오.

// 문제의 단순화를 위해 다음과 같은 입력만 주어진다고 가정한다.

// - [A-Za-z] 비밀번호로 들어오는 문자
// - "~" 는 백스페이스 키를나타 낸다.
// - "<" 는 왼쪽 방향키를 나타낸다.
// - ">" 는 오른쪽 방향키를 나타낸다.

// Example)

// Input 1
// a<B>geld~colde<<~>>
// Output 1
// Bagelcode

// Input 2
// babo~~~~~~~babaisyou

// Output 2
// babaisyou

// Input 3
// Ve<<<<<Los~~as>>>>>>>gas

// Output 3
// LasVegas