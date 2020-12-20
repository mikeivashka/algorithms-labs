package by.bsu.algorithms.labs.lab5.node;

import lombok.Getter;

public class BinaryTreeNode {

    @Getter
    private final int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}
