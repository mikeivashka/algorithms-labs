package by.bsu.algorithms.labs.lab67;

import by.bsu.algorithms.labs.utils.AbstractGraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

public class SimpleGraphUtils extends AbstractGraphUtils<Integer> {
    private final SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    public SimpleGraphUtils(SimpleGraph graph) {
        copy(graph);
    }

    public SimpleGraphUtils(List<Edge> edges){
        Set<Vertex> vertices = new HashSet<>();
        edges.stream().forEach(e -> {
            vertices.add(e.getFromVertex());
            vertices.add(e.getToVertex());
        });
        copy(new SimpleGraph(new ArrayList<>(vertices), edges));
    }

    @Override
    protected Graph<Integer, DefaultWeightedEdge> getGraph() {
        return graph;
    }

    private void copy(SimpleGraph simpleGraph) {
        simpleGraph.getVertexList().forEach(v -> graph.addVertex(v.getValue()));
        simpleGraph.getEdgeList().forEach(e -> {
            DefaultWeightedEdge weightedEdge = graph.addEdge(e.getFromVertex().getValue(), e.getToVertex().getValue());
            graph.setEdgeWeight(weightedEdge, e.getCost());
        });
    }

}
