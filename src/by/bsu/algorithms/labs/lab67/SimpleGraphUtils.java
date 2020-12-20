package by.bsu.algorithms.labs.lab67;

import by.bsu.algorithms.labs.utils.AbstractGraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class SimpleGraphUtils extends AbstractGraphUtils<Integer> {
    private final SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    public SimpleGraphUtils(SimpleGraph graph) {
        copy(graph);
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
