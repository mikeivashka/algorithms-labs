package by.bsu.algorithms.labs.lab67;

import java.util.*;

public class SimpleGraph {
    private final List<Vertex> vertexList = new ArrayList<>();
    private final List<Edge> edgeList = new ArrayList<>();
    private final Map<Vertex, Integer> vertexIndexes = new HashMap<>();

    public SimpleGraph(SimpleGraph g) {
        for (Vertex v : g.getVertexList()) {
            this.vertexList.addAll(g.vertexList);
        }
        this.edgeList.addAll(g.getEdgeList());
        generateVertexIndex();
    }

    public SimpleGraph(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList.addAll(vertexList);
        this.edgeList.addAll(edgeList);
        generateVertexIndex();
    }

    public SimpleGraph(List<List<Integer>> adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            vertexList.add(new Vertex(i));
            for (int j = 0; j < i; j++) {
                edgeList.add(new Edge(adjacencyMatrix.get(i).get(j), vertexList.get(i), vertexList.get(j)));
            }
        }
        generateVertexIndex();
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void addVertex(Vertex v) {
        this.vertexList.add(v);
        generateVertexIndex();
    }

    public void removeVertex(Vertex v) {
        for (Edge e : this.edgeList) {
            Vertex from = e.getFromVertex();
            Vertex to = e.getToVertex();

            if (from.equals(v) || to.equals(v)) {
                this.edgeList.remove(e);
                break;
            }
        }

        this.vertexList.remove(v);
        generateVertexIndex();
    }

    public void addEdge(Edge e) {
        this.edgeList.add(e);
        if (!this.vertexList.contains(e.getFromVertex())) {
            this.vertexList.add(e.getFromVertex());
        } else if (!this.vertexList.contains(e.getToVertex())) {
            this.vertexList.add(e.getToVertex());
        }
        generateVertexIndex();
    }

    public void removeEdge(Edge e) {
        this.edgeList.remove(e);
        generateVertexIndex();
    }

    public List<List<Integer>> toAdjacencyList() {
        var adjList = new ArrayList<List<Integer>>();

        for (int i = 0; i < this.vertexList.size(); i++) {
            adjList.add(new ArrayList<>());
        }

        for (Edge e : this.edgeList) {
            int from = getVertexIndex(e.getFromVertex());
            int to = getVertexIndex(e.getToVertex());

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        return adjList;
    }

    public byte[][] toAdjacencyMatrix() {
        byte[][] adjMatrix = new byte[this.vertexList.size() + 1][this.vertexList.size() + 1];

        for (byte[] row : adjMatrix) {
            Arrays.fill(row, (byte) 0);
        }

        for (Edge e : this.edgeList) {
            int from = getVertexIndex(e.getFromVertex());
            int to = getVertexIndex(e.getToVertex());

            adjMatrix[from][to] = 1;
            adjMatrix[to][from] = 1;
        }

        return adjMatrix;
    }

    public void bfSearch() {
        bfs(vertexList.get(0));
    }

    private void bfs(Vertex start) {
        var toVisit = new LinkedList<Vertex>();
        System.out.print(start.getValue() + " -> ");
        toVisit.add(start);
        start.setVisited(true);
        while (!toVisit.isEmpty()) {
            var currentVertex = toVisit.remove();
            currentVertex.getEdges()
                    .forEach(edge -> {
                        if (!edge.getToVertex().isVisited()) {
                            toVisit.add(edge.getToVertex());
                            System.out.print(edge.getToVertex().getValue() + " -> ");
                            edge.getToVertex().setVisited(true);
                        }
                    });
        }
        System.out.println("\n");
    }

    public void paintVertices() {
        List<Vertex> verticesCopy = new ArrayList<>(vertexList);
        int currentColor = 1;
        int paintedVertices = 0;
        verticesCopy.sort(Comparator.comparingInt(v -> v.getEdges().size()));
        verticesCopy.get(0).setColor(currentColor);
        for (currentColor = 1; currentColor <= vertexList.size() && paintedVertices < vertexList.size(); currentColor++) {
            int finalCurrentColor = currentColor;
            for (int j = currentColor - 1; j < verticesCopy.size(); j++) {
                if (verticesCopy.get(j).getColor() == 0 && verticesCopy.get(j).getEdges().stream()
                        .noneMatch(e -> e.getToVertex().getColor() == finalCurrentColor || e.getFromVertex().getColor() == finalCurrentColor)
                ) {
                    verticesCopy.get(j).setColor(currentColor);
                    paintedVertices++;
                }
            }

        }
    }

    public void printAdjacencyMatrix() {
        byte[][] am = this.toAdjacencyMatrix();
        for (int i = 0; i < this.getVertexList().size(); i++) {
            for (int j = 0; j < this.getVertexList().size(); j++) {
                System.out.print(am[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    private void generateVertexIndex() {
        for (int i = 0; i < this.vertexList.size(); i++) {
            this.vertexIndexes.put(this.vertexList.get(i), i);
        }
    }

    public int getVertexIndex(Vertex v) {
        return this.vertexIndexes.get(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleGraph simpleGraph = (SimpleGraph) o;

        if (!vertexList.equals(simpleGraph.vertexList)) return false;
        if (!edgeList.equals(simpleGraph.edgeList)) return false;
        return vertexIndexes.equals(simpleGraph.vertexIndexes);
    }

    @Override
    public int hashCode() {
        int result = vertexList.hashCode();
        result = 31 * result + edgeList.hashCode();
        result = 31 * result + vertexIndexes.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Vertex v : vertexList)
            builder.append(v.toString());
        return builder.toString();
    }

}
