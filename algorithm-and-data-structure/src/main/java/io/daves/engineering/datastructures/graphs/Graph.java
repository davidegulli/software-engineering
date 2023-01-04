package io.daves.engineering.datastructures.graphs;

import java.util.List;

public interface Graph<V,E> {

    int numberOfVertices();

    List<Vertex<V>> vertices();

    int numberOfEdges();

    List<Edge<E>> edges();

    Edge<E> getEdge(Vertex<V> incomingVertex, Vertex<V> outgoingVertex);

    List<Vertex<V>> terminalVertices(Edge<E> edge);

    Vertex<V> opposite(Edge<E> edge, Vertex<V> vertex);

    int outgoingDegree(Vertex<V> vertex);

    int incomingDegree(Vertex<V> vertex);

    List<Edge<E>> outgoingEdges(Vertex<V> vertex);

    List<Edge<E>> incomingEdges(Vertex<V> vertex);

    void insertVertex(V vertexElement);

    Edge<E> insertEdge(E edgeElement, Vertex<V> start, Vertex<V> end);

}
