package pl.polsl.przyszlak.model;

/**
 * Model in MVC structure.
 * Connect graph class with algorithm class.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class GraphModel {
    private Graph graph;
    private FloydWarshallAlgorithm graphAlgorithm;
    
    /**
     * Class constructor with inicjalization function.
     */
    public GraphModel(){
        initialization();
    }

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
     * Inicjalization.
     * Inicjalize graph and algorithm.
     */
    private void initialization(){
        setGraph(new Graph());
        setGraphAlgorithm(new FloydWarshallAlgorithm());
    }
    
    /**
     * send request do graph class to add new vertx with specived label.
     * @param label label of vertx to add
     */
    public void addVertex(String label){
        getGraph().addVertex(label);
        algorithmUpdate();
    }
    
    /**
     * Send request to graph class to add new edge between startVertex and endVertex with weigh.
     * @param startVertexLabel  label of start vertex
     * @param weight            weight of the edge
     * @param endVertexLabel    label of end vertex
     */
    public void addEdge(String startVertexLabel, double weight, String endVertexLabel){
        getGraph().addEdge(startVertexLabel, weight, endVertexLabel);
        algorithmUpdate();
    }
    
    /**
     * Update distane and path matrixs, when add new vertex or edge, etc.
     * Calculate all data, with 'new' graph.
     * @return if detect negative cycle.
     */
    public boolean algorithmUpdate(){
        getGraphAlgorithm().inicjalization(getGraph().getAdjacencyList(), getGraph().getListOfLabels());
        getGraphAlgorithm().firstStepInFWAlgorithm(getGraph().getAdjacencyList());
        return getGraphAlgorithm().calculateShortestPath();
    }
    
    /**
     * Send request to graph to reset data, and updating (reset) algorithm data.
     */
    public void reset(){
        this.getGraph().reset();
        this.algorithmUpdate();
    }
}
