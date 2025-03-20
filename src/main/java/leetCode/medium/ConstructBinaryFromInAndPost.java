package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryFromInAndPost {
    public static void main(String[] args) {
        ConstructBinaryFromInAndPostTreeNode constructBinaryFromInAndPostTreeNode =
                new ConstructBinaryFromInAndPostSolution().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }
}


class ConstructBinaryFromInAndPostSolution {
    private int postorderCur;

    public ConstructBinaryFromInAndPostTreeNode buildTree(int[] inorder, int[] postorder) {
        return this.firstSolution(inorder, postorder);
    }

    private ConstructBinaryFromInAndPostTreeNode firstSolution(int[] inorder, int[] postorder) {
        /*
         tc - o(n) + o(n)= o(n)
         sc - o(n) + h o(n) = o(n)
         */
        Map<Integer, Integer> inorderMap = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        this.postorderCur = postorder.length - 1;
        return this.buildTree(inorderMap, inorder, postorder, 0, inorder.length - 1);


    }

    private ConstructBinaryFromInAndPostTreeNode buildTree(Map<Integer, Integer> inorderMap, int[] inorder, int[] postorder, int leftIdx, int rightIdx) {
        if (postorderCur < 0) {
            return null;
        }
        if (leftIdx > rightIdx) {
            return null;
        }
        int rootIdx = inorderMap.get(postorder[postorderCur]);
        postorderCur--;
        ConstructBinaryFromInAndPostTreeNode root = new ConstructBinaryFromInAndPostTreeNode(inorder[rootIdx]);
        root.right = buildTree(inorderMap, inorder, postorder, rootIdx + 1, rightIdx);
        root.left = buildTree(inorderMap, inorder, postorder, leftIdx, rootIdx - 1);
        return root;
    }
}

class ConstructBinaryFromInAndPostTreeNode {
    int val;
    ConstructBinaryFromInAndPostTreeNode left;
    ConstructBinaryFromInAndPostTreeNode right;

    ConstructBinaryFromInAndPostTreeNode() {
    }

    ConstructBinaryFromInAndPostTreeNode(int val) {
        this.val = val;
    }

    ConstructBinaryFromInAndPostTreeNode(int val, ConstructBinaryFromInAndPostTreeNode left, ConstructBinaryFromInAndPostTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

