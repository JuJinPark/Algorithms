package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

public class BuildTreeFromPreOrderAndInorder {

    private static int preOrderIndex = 0;
    private static Map<Integer, Integer> inOrderNumberIndexMap = new HashMap<>();

    public static void main(String[] args) {
        //PNode node = buildTree(new int[]{4, 2, 5, 1, 6, 3}, new int[]{1, 2, 4, 5, 3, 6});
        // PNode node = buildTree(new int[]{2,5,1,3}, new int[]{1,2,5,3});
        PNode node = buildTree(new int[]{8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15}, new int[]{1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15});

        printInorder(node);
    }

    private static void printInorder(PNode node) {
        if (node != null) {
            printInorder(node.left);
            System.out.println(node.value);
            printInorder(node.right);
        }
    }

    private static PNode buildTree(int[] inorder, int[] preorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderNumberIndexMap.put(inorder[i], i);
        }
        return buildRecur(inorder, preorder, 0, inorder.length - 1);
    }

    private static PNode buildRecur(int[] inorder, int[] preorder, int startIndexInOrder, int endIndexRightOrder) {
        if (startIndexInOrder > endIndexRightOrder) {
            return null;
        }
        int rootIndex = searchIndex(inorder, preorder, preOrderIndex);
        preOrderIndex++;
        PNode root = new PNode(inorder[rootIndex]);
        root.left = buildRecur(inorder, preorder, startIndexInOrder, rootIndex - 1);
        root.right = buildRecur(inorder, preorder, rootIndex + 1, endIndexRightOrder);
        return root;

    }

    private static int searchIndex(int[] inorder, int[] preorder, int preOrderIndex) {
        int number = preorder[preOrderIndex];
        return inOrderNumberIndexMap.get(number);
    }


    private static class PNode {
        int value;
        PNode left, right;

        PNode(int value) {
            this.value = value;
        }
    }
}
