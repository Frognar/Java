package pl.polsl.przyszlak.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Single Vertex in the graph with all his edges.
 * @author Sebastian Przyszlak
 * @version 1.1
 */
public class Vertex {
    
    /** Label of the vertex */
    private String label;
    
    /** List of edges between this vertx and another */
    private List<Edge> edges;

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the edges
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * @param edges the edges to set
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
    
    /**
     * Class constructor with adding label and inicjalization function.
     * @param label label of the vertex
     */
    public Vertex(String label) {
        inicjalization();
        this.label = label;
    }
    
    /**
     * Class constructor with inicjalization function.
     */
    public Vertex() {
        inicjalization();
    }
    
    /**
     * Inicjalization.
     */
    private void inicjalization() {
        setEdges(new LinkedList<>());
    }
    
    /**
     * Adds new edge to the edge list in the vertex.
     * @param edge new edge to add
     */
    public void addEdge(Edge edge) {
        this.getEdges().add(edge);
    }
}
