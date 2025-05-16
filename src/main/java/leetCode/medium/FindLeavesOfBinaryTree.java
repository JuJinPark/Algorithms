package leetCode.medium;

import java.util.*;

/**
 * requirements
 * - collect all leaf, remove leaf  - repeat
 * notes?
 * - order of nodes? -> no
 * - empty ? possible -> no
 */

public class FindLeavesOfBinaryTree {

    public static void main(String[] args) {
        FindLeavesOfBinaryTreeNode root1 = new FindLeavesOfBinaryTreeNode(
                1,
                new FindLeavesOfBinaryTreeNode(
                        2,
                        new FindLeavesOfBinaryTreeNode(4),
                        new FindLeavesOfBinaryTreeNode(5)
                ),
                new FindLeavesOfBinaryTreeNode(3)
        );
        FindLeavesOfBinaryTreeNode root2 = new FindLeavesOfBinaryTreeNode(
                1,
                new FindLeavesOfBinaryTreeNode(
                        2,
                        new FindLeavesOfBinaryTreeNode(4),
                        new FindLeavesOfBinaryTreeNode(5)
                ),
                new FindLeavesOfBinaryTreeNode(3)
        );
        FindLeavesOfBinaryTreeNode root3 = new FindLeavesOfBinaryTreeNode(
                1,
                new FindLeavesOfBinaryTreeNode(
                        2,
                        new FindLeavesOfBinaryTreeNode(4),
                        new FindLeavesOfBinaryTreeNode(5)
                ),
                new FindLeavesOfBinaryTreeNode(3)
        );
        List<List<Integer>> leaves = new FindLeavesOfBinaryTreeSolution1().findLeaves(root1);
        List<List<Integer>> leaves2 = new FindLeavesOfBinaryTreeSolution2().findLeaves(root2);
        List<List<Integer>> leaves3 = new FindLeavesOfBinaryTreeSolution3().findLeaves(root3);

        System.out.println(leaves);
        System.out.println(leaves2);
        System.out.println(leaves3);


    }


}

/**
 * tc - o(n2)
 * sc - o(h) + result o(n)
 */
class FindLeavesOfBinaryTreeSolution1 {

    public List<List<Integer>> findLeaves(FindLeavesOfBinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        this.dfs(root, result);
        return result;
    }

    private void dfs(FindLeavesOfBinaryTreeNode root, List<List<Integer>> result) {
        FindLeavesOfBinaryTreeNode dummyRoot = new FindLeavesOfBinaryTreeNode();
        dummyRoot.left = root;
        while (dummyRoot.left != null) {
            List<Integer> temp = new ArrayList<>();
            this.recur(dummyRoot, temp);
            result.add(temp);
        }
    }

    private boolean recur(FindLeavesOfBinaryTreeNode root, List<Integer> result) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            result.add(root.val);
            return true;
        }
        boolean leftResult = this.recur(root.left, result);
        if (leftResult) {
            root.left = null;
        }
        boolean rightResult = this.recur(root.right, result);
        if (rightResult) {
            root.right = null;
        }

        return false;
    }

}

/**
 * tc - o(n)
 * sc - o(h) + result o(n)
 */
class FindLeavesOfBinaryTreeSolution2 {

    public List<List<Integer>> findLeaves(FindLeavesOfBinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        this.dfs(root, result);
        return result;

    }

    private int dfs(FindLeavesOfBinaryTreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return 0;
        }

        int leftDepth = this.dfs(root.left, result);
        int rightDepth = this.dfs(root.right, result);
        int currentIdx = Math.max(leftDepth, rightDepth);
        while (result.size() <= currentIdx) {
            result.add(new ArrayList<>());
        }
        result.get(currentIdx).add(root.val);
        return currentIdx + 1;
    }
}

/**
 * tc - o(n) + o(n) = o(n)
 * sc -result o(n) + o(n) + o(n) = o(n)
 */
class FindLeavesOfBinaryTreeSolution3 {

    public List<List<Integer>> findLeaves(FindLeavesOfBinaryTreeNode root) {
        Map<Integer, FindLeavesOfBinaryTreeNode> childToParentMap = new HashMap<>();
        Queue<FindLeavesOfBinaryTreeNode> queue = new LinkedList<>();
        this.dfs(root, childToParentMap, queue);
        List<List<Integer>> result = new ArrayList<>();
        this.travel(root, result, childToParentMap, queue);
        return result;

    }

    private void dfs(FindLeavesOfBinaryTreeNode root, Map<Integer, FindLeavesOfBinaryTreeNode> childToParentMap, Queue<FindLeavesOfBinaryTreeNode> queue) {

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            queue.add(root);
        }
        if (root.left != null) {
            childToParentMap.put(root.left.val, root);
        }
        if (root.right != null) {
            childToParentMap.put(root.right.val, root);
        }
        this.dfs(root.left, childToParentMap, queue);
        this.dfs(root.right, childToParentMap, queue);

    }

    private void travel(FindLeavesOfBinaryTreeNode root, List<List<Integer>> result, Map<Integer, FindLeavesOfBinaryTreeNode> childToParentMap, Queue<FindLeavesOfBinaryTreeNode> queue) {
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                FindLeavesOfBinaryTreeNode target = queue.poll();
                if (result.size() <= depth) {
                    result.add(new ArrayList<>());
                }
                result.get(depth).add(target.val);
                FindLeavesOfBinaryTreeNode parent = childToParentMap.get(target.val);
                if (parent != null) {
                    if (parent.left == target) {
                        parent.left = null;
                    }
                    if (parent.right == target) {
                        parent.right = null;
                    }
                    if (parent.left == null && parent.right == null) {
                        queue.add(parent);
                    }
                }

            }

            depth++;
        }
    }


}


class FindLeavesOfBinaryTreeNode {
    int val;
    FindLeavesOfBinaryTreeNode left;
    FindLeavesOfBinaryTreeNode right;

    FindLeavesOfBinaryTreeNode() {
    }

    FindLeavesOfBinaryTreeNode(int val) {
        this.val = val;
    }

    FindLeavesOfBinaryTreeNode(int val, FindLeavesOfBinaryTreeNode left, FindLeavesOfBinaryTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

