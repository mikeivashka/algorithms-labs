package by.bsu.algorithms.labs.lab12.runner;

import by.bsu.algorithms.labs.lab12.KruskalCities;
import by.bsu.algorithms.labs.lab67.SimpleGraph;
import by.bsu.algorithms.labs.lab67.SimpleGraphUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> adjList = Arrays.asList(
                Arrays.asList(0, 8, 2, 2, 12),
                Arrays.asList(10, 0, 4, 22, 22),
                Arrays.asList(12, 3, 0, 14, 4),
                Arrays.asList(12, 1, 1, 0, 8),
                Arrays.asList(20, 1, 4, 2, 0)
        );
        SimpleGraph simpleGraph = new SimpleGraph(adjList);
        SimpleGraphUtils simpleGraphUtils =
                new SimpleGraphUtils(KruskalCities
                        .findMinimumSpanningTree(simpleGraph));
        System.out.println(simpleGraph.getEdgeList());
        System.out.println("\nSolution: \n" +
                KruskalCities.findMinimumSpanningTree(simpleGraph));
        try {
            simpleGraphUtils.print("graphs/kruscalImpl.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
