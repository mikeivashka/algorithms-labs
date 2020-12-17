package by.bsu.algorithms.labs.lab5;

import by.bsu.algorithms.labs.lab5.tree.AbstractGraphUtils;
import by.bsu.algorithms.labs.lab5.tree.BPlusTree;
import lombok.Getter;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class BPlusGraphUtils extends AbstractGraphUtils<String> {
    @Getter
    private final Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    public BPlusGraphUtils(BPlusTree tree) {
        copy(tree.getFirstLeaf());
        if (tree.getRoot() != null) copy(tree.getRoot());
    }

    public static BPlusTree generateBinaryTree(int m, int verticesCount, int minValueBand, int maxValueBand) {
        var tree = new BPlusTree(m);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < verticesCount; i++) {
            tree.insert(random.nextInt(maxValueBand) + minValueBand + 1);
        }
        return tree;
    }

    private void copy(BPlusTree.Node tree) {
        if (tree == null) return;
        graph.addVertex(Arrays.toString(tree.getValues()));
        if (tree instanceof BPlusTree.InternalNode) {
            Arrays.stream(((BPlusTree.InternalNode) tree)
                    .getChildPointers())
                    .filter(Objects::nonNull)
                    .forEach(p -> {
                        copy(p);
                        if (!Arrays.equals(p.getValues(), tree.getValues()))
                            graph.addEdge(Arrays.toString(tree.getValues()), Arrays.toString(p.getValues()));
                    });
        }
    }


}

