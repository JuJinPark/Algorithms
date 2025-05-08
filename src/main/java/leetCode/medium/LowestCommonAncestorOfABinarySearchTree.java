package leetCode.medium;

public class LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {

    }
}


/**
 * tc - o(n)
 * sc - o(h)
 */

class LowestCommonAncestorOfABinarySearchTreeSolution1 {
    private LCABSTreeNode found = null;

    public LCABSTreeNode lowestCommonAncestor(LCABSTreeNode root, LCABSTreeNode p, LCABSTreeNode q) {
        this.recur(root, p, q);
        return found;
    }

    private boolean recur(LCABSTreeNode root, LCABSTreeNode p, LCABSTreeNode q) {
        if (found != null) {
            return false;
        }
        if (root == null) {
            return false;
        }

        boolean leftResult = this.recur(root.left, p, q);
        boolean rightResult = this.recur(root.right, p, q);

        if (leftResult && rightResult) {
            found = root;
            return true;
        }

        if (leftResult && (root.val == p.val || root.val == q.val)) {
            found = root;
            return true;
        }

        if (rightResult && (root.val == p.val || root.val == q.val)) {
            found = root;
            return true;
        }

        if (leftResult) {
            return true;
        }
        if (rightResult) {
            return true;
        }

        return root.val == p.val || root.val == q.val;

    }
}

/**
 * tc - o(n)
 * sc - o(h)
 */

class LowestCommonAncestorOfABinarySearchTreeSolution2 {
    private LCABSTreeNode found = null;

    public LCABSTreeNode lowestCommonAncestor(LCABSTreeNode root, LCABSTreeNode p, LCABSTreeNode q) {
        this.recur(root, p, q);
        return found;
    }

    private boolean recur(LCABSTreeNode root, LCABSTreeNode p, LCABSTreeNode q) {
        if (found != null) {
            return false;
        }
        if (root == null) {
            return false;
        }

        boolean leftResult = false;
        boolean rightResult = false;

        if (p.val < root.val || q.val < root.val) {
            leftResult = this.recur(root.left, p, q);
        }

        if (p.val > root.val || q.val > root.val) {
            rightResult = this.recur(root.right, p, q);
        }

        if (leftResult && rightResult) {
            found = root;
            return true;
        }

        if (leftResult && (root.val == p.val || root.val == q.val)) {
            found = root;
            return true;
        }

        if (rightResult && (root.val == p.val || root.val == q.val)) {
            found = root;
            return true;
        }

        if (leftResult) {
            return true;
        }
        if (rightResult) {
            return true;
        }

        return root.val == p.val || root.val == q.val;

    }
}

/**
 * tc - o(h)
 * sc - o(h)
 */
class LowestCommonAncestorOfABinarySearchTreeSolution3 {
    public LCABSTreeNode lowestCommonAncestor(LCABSTreeNode root, LCABSTreeNode p, LCABSTreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root; // This is the split point
            }
        }
        return null;
    }

}

class LCABSTreeNode {
    int val;
    LCABSTreeNode left;
    LCABSTreeNode right;

    LCABSTreeNode(int x) {
        val = x;
    }
}
