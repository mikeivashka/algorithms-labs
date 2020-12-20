package by.bsu.algorithms.labs.lab12;

import by.bsu.algorithms.labs.lab67.Edge;
import by.bsu.algorithms.labs.lab67.SimpleGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalCities {
    public static List<Edge> findMinimumSpanningTree(SimpleGraph graph) {
        var res = new ArrayList<>(graph.getEdgeList());
        var span = new ArrayList<Edge>(res.size());
        res.sort(Comparator.comparingInt(Edge::getCost));
        DisjointSetUnion dsu = new DisjointSetUnion(res.size());
        res.forEach(re -> {
            if (dsu.findSet(re.getFromVertex().getValue()) != dsu.findSet(re.getToVertex().getValue())) {
                span.add(re);
                dsu.mergeSets(re.getFromVertex().getValue(), re.getToVertex().getValue());
            }
        });
        return span;
    }



}
