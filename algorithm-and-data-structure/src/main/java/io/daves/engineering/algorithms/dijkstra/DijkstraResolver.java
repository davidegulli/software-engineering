package io.daves.engineering.algorithms.dijkstra;

import io.daves.engineering.datastructures.graphs.AdjacencyMapGraph;
import io.daves.engineering.datastructures.graphs.Edge;
import io.daves.engineering.datastructures.graphs.Graph;
import io.daves.engineering.datastructures.graphs.Vertex;

import java.util.HashMap;
import java.util.Map;

public class DijkstraResolver {

    public void resolve(String source, String dest, Graph<String, Integer> graph) {
        // In this case the key is unique, it's represented by the vertex element
        HashMap<String, VertexDistanceWrapper<String>> wrappedVertices = initVerticesMap(source, graph);

        for (int i = 0; i < wrappedVertices.size(); i++) {

            VertexDistanceWrapper<String> picked = getVertexWrapperWithMinDistance(wrappedVertices);
            picked.setVisited(true);

            System.out.println(picked.getVertex().getElement() + " - Shortest Path:" + picked.overallDistanceFromSource);

            for(Edge<Integer> edge : graph.outgoingEdges(picked.getVertex())) {
                Vertex<String> opposite = graph.opposite(edge, picked.getVertex());

                VertexDistanceWrapper<String> sibling = wrappedVertices.get(opposite.getElement());

                if((picked.overallDistanceFromSource + edge.getElement()) < sibling.overallDistanceFromSource) {
                    sibling.setOverallDistanceFromSource(picked.overallDistanceFromSource + edge.getElement());
                }
            }
        }
    }

    private HashMap<String, VertexDistanceWrapper<String>> initVerticesMap(
            String source, Graph<String, Integer> graph) {

        HashMap<String, VertexDistanceWrapper<String>> result = new HashMap<>();
        for (Vertex<String> vertex : graph.vertices()) {
            if(vertex.getElement().equals(source)) {
                result.put(vertex.getElement(), new VertexDistanceWrapper(0, vertex));
            } else {
                result.put(vertex.getElement(), new VertexDistanceWrapper(vertex));
            }
        }

        return result;
    }

    private VertexDistanceWrapper<String> getVertexWrapperWithMinDistance(
            HashMap<String, VertexDistanceWrapper<String>> map) {

        VertexDistanceWrapper<String> result = null;
        for (VertexDistanceWrapper<String> vertexWrapper : map.values()) {
            if (result == null) {
                result = vertexWrapper;
                continue;
            }

            if(!vertexWrapper.visited
                    && vertexWrapper.overallDistanceFromSource != Integer.MAX_VALUE
                    && vertexWrapper.overallDistanceFromSource < result.overallDistanceFromSource) {

                result = vertexWrapper;
            }
        }

        return result;
    }

    public class VertexDistanceWrapper<V> {
        private int overallDistanceFromSource = Integer.MAX_VALUE;
        private Vertex<V> vertex;

        private boolean visited = false;

        public VertexDistanceWrapper(Vertex<V> vertex) {
            this.vertex = vertex;
        }

        public VertexDistanceWrapper(int overallDistanceFromSource, Vertex<V> vertex) {
            this.overallDistanceFromSource = overallDistanceFromSource;
            this.vertex = vertex;
        }

        public void setOverallDistanceFromSource(int overallDistanceFromSource) {
            this.overallDistanceFromSource = overallDistanceFromSource;
        }

        public int getOverallDistanceFromSource() {
            return overallDistanceFromSource;
        }

        public Vertex<V> getVertex() {
            return vertex;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }

    public static void main(String[] args) {
        Graph<String, Integer> graph = buildGraph();
        System.out.println(graph);

        DijkstraResolver resolver = new DijkstraResolver();
        resolver.resolve("Roma", null, graph);
    }

    public static Graph<String, Integer> buildGraph() {
        Graph<String, Integer> graph = new AdjacencyMapGraph<>(false);

        insertVertices(vertexDataset, graph);
        insertEdges(edgesDataset, graph);

        return graph;
    }

    public static void insertVertices(String[] vertexDataset, Graph<String, Integer> graph) {
        for(String vertexLabel : vertexDataset) {
            graph.insertVertex(vertexLabel);
        }
    }

    public static void insertEdges(String[][] edgesDataset, Graph<String, Integer> graph) {
        Map<String, Vertex<String>> vertexMap = new HashMap<>();
        for (Vertex<String> vertex : graph.vertices()) {
            vertexMap.put(vertex.getElement(), vertex);
        }

        for(String[] edge : edgesDataset) {
            String firstLabel = edge[0];
            String secondLabel = edge[1];
            int weight = Integer.valueOf(edge[2]);

            Vertex<String> firstVertex = vertexMap.get(firstLabel);
            Vertex<String> secondVertex = vertexMap.get(secondLabel);

            graph.insertEdge(weight, firstVertex, secondVertex);
        }
    }

    private static String[] vertexDataset = {
            "Roma", "Firenze", "Milano", "Napoli", "Palermo", "Torino", "Bologna", "Genova", "Bari"
    };

    private static String[][] edgesDataset = {
            { "Roma","Firenze","186" },
            { "Roma","Milano","434" },
            { "Roma","Napoli","246" },
            { "Roma","Palermo","1082" },
            { "Roma","Torino","513" },
            { "Roma","Bologna","245" },
            { "Roma","Genova","553" },
            { "Roma","Bari","607" },
            { "Firenze","Milano","201" },
            { "Firenze","Napoli","456" },
            { "Firenze","Palermo","979" },
            { "Firenze","Torino","191" },
            { "Firenze","Bologna","75" },
            { "Firenze","Genova","214" },
            { "Firenze","Bari","709" },
            { "Milano","Napoli","871" },
            { "Milano","Palermo","1329" },
            { "Milano","Torino","82" },
            { "Milano","Bologna","128" },
            { "Milano","Genova","143" },
            { "Milano","Bari","924" },
            { "Napoli","Palermo","557" },
            { "Napoli","Torino","689" },
            { "Napoli","Bologna","484" },
            { "Napoli","Genova","638" },
            { "Napoli","Bari","220" },
            { "Palermo","Torino","1357" },
            { "Palermo","Bologna","1350" },
            { "Palermo","Genova","1291" },
            { "Palermo","Bari","468" },
            { "Torino","Bologna","196" },
            { "Torino","Genova","153" },
            { "Torino","Bari","903" },
            { "Bologna","Genova","186" },
            { "Bologna","Bari","795" },
            { "Genova","Bari","659" }
    };
}
