package leetCode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DesignBrowserHistory {
    public static void main(String[] args) {
        BrowserHistorySolution1 browserHistorySolution1 = new BrowserHistorySolution1("abc.com");
        BrowserHistorySolution2 browserHistorySolution2 = new BrowserHistorySolution2("abc.com");
        BrowserHistorySolution3 browserHistorySolution3 = new BrowserHistorySolution3("abc.com");
    }
}


/**
 * sc - o(n)
 */
class BrowserHistorySolution1 {
    List<String> histories = new ArrayList();
    int index;

    public BrowserHistorySolution1(String homepage) {
        this.histories.add(homepage);
        this.index = 0;
    }

    /**
     * o(n)
     */
    public void visit(String url) {
        this.removeAllUntil(index);
        this.histories.add(url);
        this.index++;
    }

    /**
     * o(1)
     */
    public String back(int steps) {
        int backIndex = Math.max(0, this.index - steps);
        this.index = backIndex;
        return this.histories.get(this.index);
    }

    /**
     * o(1)
     */
    public String forward(int steps) {
        int forwardIndex = Math.min(this.histories.size() - 1, this.index + steps);
        this.index = forwardIndex;
        return this.histories.get(this.index);
    }

    private void removeAllUntil(int index) {
        while (index < this.histories.size() - 1) {
            this.histories.remove(this.histories.size() - 1);
        }

    }
}

/**
 * sc - o(n)
 */
class BrowserHistorySolution2 {
    Stack<String> backward = new Stack();
    Stack<String> forward = new Stack();

    public BrowserHistorySolution2(String homepage) {
        this.backward.push(homepage);
    }

    /**
     * o(1)
     */
    public void visit(String url) {
        this.backward.push(url);
        this.forward = new Stack();
    }

    /**
     * o(n)
     */
    public String back(int steps) {
        while (backward.size() > 1 && steps > 0) {
            this.forward.push(this.backward.pop());
            steps--;
        }

        return this.backward.peek();
    }

    /**
     * o(n)
     */
    public String forward(int steps) {
        while (forward.size() > 0 && steps > 0) {
            this.backward.push(this.forward.pop());
            steps--;
        }
        return this.backward.peek();
    }

}

/**
 * sc - o(n)
 */
class BrowserHistorySolution3 {
    private BrowserHistoryNode current;

    public BrowserHistorySolution3(String homepage) {
        this.current = new BrowserHistoryNode(homepage);
    }

    /**
     * o(1)
     */
    public void visit(String url) {
        BrowserHistoryNode newNode = new BrowserHistoryNode(url);
        newNode.prev = this.current;
        this.current.next = newNode;
        this.current = newNode;
    }

    /**
     * o(n)
     */
    public String back(int steps) {
        while (true) {
            BrowserHistoryNode prev = this.current.prev;
            if (prev == null || steps < 1) {
                break;
            }
            this.current = prev;
            steps--;

        }

        return this.current.url;
    }

    /**
     * o(n)
     */
    public String forward(int steps) {
        while (true) {
            BrowserHistoryNode next = this.current.next;
            if (next == null || steps < 1) {
                break;
            }
            this.current = next;
            steps--;
        }

        return this.current.url;
    }

}

class BrowserHistoryNode {
    public BrowserHistoryNode prev;
    public BrowserHistoryNode next;
    public String url;

    BrowserHistoryNode(String url) {
        this.url = url;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */

