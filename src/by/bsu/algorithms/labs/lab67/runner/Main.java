package by.bsu.algorithms.labs.lab67.runner;

import by.bsu.algorithms.labs.lab67.Edge;
import by.bsu.algorithms.labs.lab67.SimpleGraph;
import by.bsu.algorithms.labs.lab67.SimpleGraphUtils;
import by.bsu.algorithms.labs.lab67.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<Vertex> vertices = new ArrayList<>();
        Vertex v1 = new Vertex(1);
        vertices.add(v1);
        Vertex v2 = new Vertex(2);
        vertices.add(v2);
        Vertex v3 = new Vertex(3);
        vertices.add(v3);
        Vertex v4 = new Vertex(4);
        vertices.add(v4);
        Vertex v5 = new Vertex(5);
        vertices.add(v5);
        Vertex v6 = new Vertex(7);
        vertices.add(v6);


        final List<Edge> edges = new ArrayList<>();
        Edge e1 = new Edge(5, v1, v2).addEdgeFromVertex();
        edges.add(e1);
        Edge e2 = new Edge(10, v2, v4).addEdgeFromVertex();
        edges.add(e2);
        Edge e3 = new Edge(7, v1, v4).addEdgeFromVertex();
        edges.add(e3);
        Edge e4 = new Edge(8, v1, v5).addEdgeFromVertex();
        edges.add(e4);
        Edge e5 = new Edge(6, v5, v3).addEdgeFromVertex();
        edges.add(e5);
        Edge e6 = new Edge(2, v3, v4).addEdgeFromVertex();
        edges.add(e6);
        Edge e7 = new Edge(10, v3, v6).addEdgeFromVertex();
        edges.add(e7);

        SimpleGraph graph = new SimpleGraph(vertices, edges);

        graph.printAdjacencyMatrix();

        graph.addEdge(new Edge(1, new Vertex(6), v5));
        graph.printAdjacencyMatrix();
        System.out.println("\n" + graph.getEdgeList());

        graph.addEdge(new Edge(1, new Vertex(6), v1));
        graph.printAdjacencyMatrix();
        System.out.println(graph.getEdgeList() + "\n");

        graph.removeEdge(new Edge(1, new Vertex(6), v1));
        graph.printAdjacencyMatrix();
        System.out.println(graph.getEdgeList() + "\n");

        graph.removeVertex(new Vertex(6));
        graph.printAdjacencyMatrix();
        System.out.println(graph.getEdgeList() + "\n");



        graph.bfSearch();


        graph.paintVertices();
        System.out.println(graph.getVertexList());

        try {
            new SimpleGraphUtils(graph).print("graphs/simplegraph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
