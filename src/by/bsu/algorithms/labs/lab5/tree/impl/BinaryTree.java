package by.bsu.algorithms.labs.lab5.tree.impl;

import by.bsu.algorithms.labs.lab5.node.BinaryTreeNode;
import by.bsu.algorithms.labs.lab5.tree.Tree;
import lombok.Getter;

public class BinaryTree implements Tree {
    @Getter
    private BinaryTreeNode root;

    @Override
    public void add(int value) {
        root = addRecursive(root, value);
    }

    @Override
    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    @Override
    public void delete(int value) {
        deleteRecursive(root, value);
    }

    public void DFS() {
        traverseInOrder(root);
    }

    public int findClosestFromTop(int value) {
        if (root == null) throw new NullPointerException();
        return lookupFromTop(root, value);
    }

    private int lookupFromTop(BinaryTreeNode current, int value) {
        if (current.getValue() == value) return value;
        if (current.getValue() > value) {
            if (current.left != null && lookupFromTop(current.left, value) >= value)
                return lookupFromTop(current.left, value);
            else return current.getValue();
        } else {
            if (current.right != null) return lookupFromTop(current.right, value);
            else return current.getValue();
        }
    }

    private BinaryTreeNode addRecursive(BinaryTreeNode current, int value) {
        if (current == null) return new BinaryTreeNode(value);
        else if (value > current.getValue()) current.right = addRecursive(current.right, value);
        else if (value < current.getValue()) current.left = addRecursive(current.left, value);
        else return current;
        return current;
    }

    private boolean containsNodeRecursive(BinaryTreeNode current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.getValue()) {
            return true;
        }
        return value < current.getValue()
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    private void traverseInOrder(BinaryTreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.getValue());
            traverseInOrder(node.right);
        }
    }

    private BinaryTreeNode deleteRecursive(BinaryTreeNode current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.getValue()) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }
        }
        if (value < current.getValue()) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }
}
