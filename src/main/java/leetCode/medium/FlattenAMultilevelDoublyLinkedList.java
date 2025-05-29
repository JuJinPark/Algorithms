package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class FlattenAMultilevelDoublyLinkedList {

    public static void main(String[] args) {
        new FlattenAMultilevelDoublyLinkedListSolution1().flatten(null);
        new FlattenAMultilevelDoublyLinkedListSolution2().flatten(null);
    }
}


/**
 * tc - o(n) + o(n) = o(n)
 * sc - o(n)
 */
class FlattenAMultilevelDoublyLinkedListSolution1 {
    public FlattenNode flatten(FlattenNode head) {
        if (head == null) {
            return head;
        }
        List<FlattenNode> list = new ArrayList();
        this.recur(head, list);

        head.child = null;
        for (int i = 1; i < list.size(); i++) {
            FlattenNode prev = list.get(i - 1);
            FlattenNode cur = list.get(i);
            prev.next = cur;
            cur.prev = prev;
            cur.child = null;
        }

        return head;
    }

    private void recur(FlattenNode FlattenNode, List<FlattenNode> list) {
        if (FlattenNode == null) {
            return;
        }
        list.add(FlattenNode);

        this.recur(FlattenNode.child, list);
        this.recur(FlattenNode.next, list);
    }
}

/**
 * tc - o(n)
 * sc - o(n)
 */
class FlattenAMultilevelDoublyLinkedListSolution2 {
    public FlattenNode flatten(FlattenNode head) {
        if (head == null) {
            return head;
        }
        this.flattenFlattenNode(head);
        return head;
    }

    private FlattenNode[] flattenFlattenNode(FlattenNode FlattenNode) {
        FlattenNode[] headTail = new FlattenNode[2];
        headTail[0] = FlattenNode;
        headTail[1] = FlattenNode;

        FlattenNode cur = FlattenNode;
        while (cur != null) {

            if (cur.child != null) {
                FlattenNode[] childHeadAndTail = this.flattenFlattenNode(cur.child);
                FlattenNode prevNext = cur.next;

                cur.next = childHeadAndTail[0];
                childHeadAndTail[0].prev = cur;
                cur.child = null;

                childHeadAndTail[1].next = prevNext;
                if (prevNext != null) {
                    prevNext.prev = childHeadAndTail[1];
                }

                cur = childHeadAndTail[1];
            }
            headTail[1] = cur;
            cur = cur.next;
        }

        return headTail;
    }
}


class FlattenNode {
    public int val;
    public FlattenNode prev;
    public FlattenNode next;
    public FlattenNode child;
};
