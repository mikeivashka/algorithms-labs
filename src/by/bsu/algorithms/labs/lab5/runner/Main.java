package by.bsu.algorithms.labs.lab5.runner;

import by.bsu.algorithms.labs.lab5.BPlusGraphUtils;
import by.bsu.algorithms.labs.lab5.BinaryTreeUtils;
import by.bsu.algorithms.labs.lab5.tree.BPlusTree;
import by.bsu.algorithms.labs.lab5.tree.impl.BinaryTree;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        BinaryTree tree = BinaryTreeUtils.generateBinaryTree(12, 0, 80);
        var graphUtils = new BinaryTreeUtils(tree);
        graphUtils.print("graphs/graph.png");
        tree.DFS();
        System.out.println("\n" + tree.findClosestFromTop(40));

        BPlusTree bPlusTree = BPlusGraphUtils.generateBinaryTree(5, 40, 0, 80);
        var bPlusGraphUtils = new BPlusGraphUtils(bPlusTree);
        bPlusGraphUtils.print("graphs/bplusgraph.png");
        System.out.println(bPlusTree.findClosestFromTop(12));
        bPlusTree.DFSearch();
    }
}
