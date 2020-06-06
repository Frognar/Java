package pl.polsl.przyszlak.model;

/**
 * Model in MVC structure.
 * Connect graph class with algorithm class.
 * @author Sebastian Przyszlak
 * @version 1.1
 */
public class GraphModel {
    
    /** Graph to store graph. Allow to add new vertices and/or edges */
    private Graph graph;
    
    /** Algorithm class to copy graph and calculate shortest path */
    private FloydWarshallAlgorithm graphAlgorithm;

    /**
     * @return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * @return the graphAlgorithm
     */
    public FloydWarshallAlgorithm getGraphAlgorithm() {
        return graphAlgorithm;
    }

    /**
     * @param graphAlgorithm the graphAlgorithm to set
     */
    public void setGraphAlgorithm(FloydWarshallAlgorithm graphAlgorithm) {
        this.graphAlgorithm = graphAlgorithm;
    }
    
    /**
     * Class constructor with inicjalization function.
     */
    public GraphModel() {
        initialization();
    }
    
    /**
     * Inicjalization.
     * Inicjalize graph and algorithm.
     */
    private void initialization() {
        setGraph(new Graph());
        setGraphAlgorithm(new FloydWarshallAlgorithm());
    }
}
