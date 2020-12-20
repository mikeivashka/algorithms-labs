package by.bsu.algorithms.labs.lab5;

import by.bsu.algorithms.labs.lab5.node.BinaryTreeNode;
import by.bsu.algorithms.labs.lab5.tree.impl.BinaryTree;
import by.bsu.algorithms.labs.utils.AbstractGraphUtils;
import lombok.Getter;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Random;

public class BinaryTreeUtils extends AbstractGraphUtils<Integer> {
    @Getter
    private final Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    public BinaryTreeUtils(BinaryTree tree) {
        copy(tree.getRoot());
    }

    public static BinaryTree generateBinaryTree(int verticesCount, int minValueBand, int maxValueBand) {
        BinaryTree tree = new BinaryTree();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < verticesCount; i++) {
            tree.add(random.nextInt(maxValueBand) + minValueBand + 1);
        }
        return tree;
    }

    private void copy(BinaryTreeNode node) {
        if (node == null) return;
        graph.addVertex(node.getValue());
        if (node.left != null) {
            graph.addVertex(node.left.getValue());
            graph.addEdge(node.getValue(), node.left.getValue());
        }
        if (node.right != null) {
            graph.addVertex(node.right.getValue());
            graph.addEdge(node.getValue(), node.right.getValue());
        }
        copy(node.left);
        copy(node.right);
    }
}
