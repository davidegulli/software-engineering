package io.daves.engineering.datastructures.graphs;

import java.util.*;

public class AdjacencyMapGraph<V, E> implements Graph<V, E> {

    private List<Vertex<V>> vertices = new LinkedList<>();

    private List<Edge<E>> edges = new LinkedList<>();

    private boolean oriented;

    public AdjacencyMapGraph(boolean oriented) {
        this.oriented = oriented;
    }

    @Override
    public int numberOfVertices() {
        return vertices.size();
    }

    @Override
    public List<Vertex<V>> vertices() {
        return vertices;
    }

    @Override
    public int numberOfEdges() {
        return edges.size();
    }

    @Override
    public List<Edge<E>> edges() {
        return edges;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> incomingVertex, Vertex<V> outgoingVertex) {
        InnerVertex<V> v = validate(incomingVertex);
        return v.getIncomingEdges().get(outgoingVertex);
    }

    @Override
    public List<Vertex<V>> terminalVertices(Edge<E> edge) {
        InnerEdge<E> e = validate(edge);
        return e.getVertices();
    }

    @Override
    public Vertex<V> opposite(Edge<E> edge, Vertex<V> vertex) {
        InnerEdge<E> e = validate(edge);

        if(e.getVertices().get(0) == vertex) {
            return e.getVertices().get(1);
        }

        return e.getVertices().get(0);
    }

    @Override
    public int outgoingDegree(Vertex<V> vertex) {
        InnerVertex<V> v = validate(vertex);
        return v.getOutgoingEdges().size();
    }

    @Override
    public int incomingDegree(Vertex<V> vertex) {
        InnerVertex<V> v = validate(vertex);
        return v.getIncomingEdges().size();
    }

    @Override
    public List<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        InnerVertex<V> v = validate(vertex);
        return new ArrayList(v.getOutgoingEdges().values());
    }

    @Override
    public List<Edge<E>> incomingEdges(Vertex<V> vertex) {
        InnerVertex<V> v = validate(vertex);
        return new ArrayList(v.getIncomingEdges().values());
    }

    @Override
    public void insertVertex(V vertexElement) {
        InnerVertex<V> vertex = new InnerVertex<>(vertexElement, oriented);
        vertices.add(vertex);
    }

    @Override
    public Edge<E> insertEdge(E edgeElement, Vertex<V> start, Vertex<V> end) {
        if(getEdge(start, end) != null) {
            throw new IllegalArgumentException();
        }

        InnerEdge<E> edge = new InnerEdge<>(edgeElement, start, end);
        edges.add(edge);
        InnerVertex<V> incoming = validate(start);
        InnerVertex<V> outgoing = validate(end);
        incoming.getIncomingEdges().put(outgoing, edge);
        outgoing.getIncomingEdges().put(incoming, edge);
        return edge;
    }

    private InnerVertex<V> validate(Vertex<V> vertex) throws IllegalArgumentException {
        if (!(vertex instanceof InnerVertex)) {
            throw new IllegalArgumentException();
        }

        return (InnerVertex)vertex;
    }

    private InnerEdge<E> validate(Edge<E> edge) throws IllegalArgumentException {
        if (!(edge instanceof InnerEdge)) {
            throw new IllegalArgumentException();
        }

        return  (InnerEdge)edge;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("----------------------------\n");

        for (Edge<E> edge : edges()) {
            InnerEdge<E> innerEdge = validate(edge);

            Vertex<V> firstVertex = innerEdge.vertices.get(0);
            Vertex<V> secondVertex = innerEdge.vertices.get(1);

            result.append(firstVertex.getElement())
                    .append(" => ")
                    .append(secondVertex.getElement())
                    .append(" - weight: ")
                    .append(edge.getElement())
                    .append("\n");
        }

        result.append("----------------------------");

        return result.toString();
    }

    private class InnerVertex<V> implements Vertex<V>{
        private V element;

        private Map<Vertex<V>, Edge<E>> incomingEdges, outgoingEdges;

        private InnerVertex(V element, boolean orientedGraph) throws IllegalArgumentException {
            incomingEdges = new HashMap<>();
            outgoingEdges = incomingEdges;
            this.element = element;
            if (orientedGraph) {
                outgoingEdges = new HashMap<>();
            }
        }

        @Override
        public V getElement() {
            return element;
        }

        public Map<Vertex<V>, Edge<E>> getIncomingEdges() {
            return incomingEdges;
        }

        public Map<Vertex<V>, Edge<E>> getOutgoingEdges() {
            return outgoingEdges;
        }
    }

    private class InnerEdge<E> implements Edge<E>{

        private E element;

        private List<Vertex<V>> vertices = new ArrayList<>();

        public InnerEdge(E element, Vertex<V> first, Vertex<V> second) {
            this.element = element;
            vertices.add(first);
            vertices.add(second);
        }

        @Override
        public E getElement() {
            return element;
        }

        public List<Vertex<V>> getVertices() {
            return vertices;
        }
    }
}