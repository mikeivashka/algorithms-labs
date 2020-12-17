package by.bsu.algorithms.labs.lab5.node;

public class BinaryTreeNode {

    private final int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public int getValue() {
        return value;
    }
}
