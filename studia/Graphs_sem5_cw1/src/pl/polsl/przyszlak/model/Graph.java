package pl.polsl.przyszlak.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to store and/or build graph.
 * @author Sebastian Przyszlak
 * @version 1.2
 */
public class Graph {
    
    private List<Vertex> adjacencyList; // Graph represantanion.
    
    /**
     * Class constructior with inicjalization function.
     */
    public Graph(){
        inicjalization();
    }

    /**
     * @return the adjacencyList
     */
    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * @param adjacencyList the adjacencyList to set
     */
    public void setAdjacencyList(List<Vertex> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }
    
    /**
     * Inicjalization.
     */
    private void inicjalization(){
        setAdjacencyList(new LinkedList<>());
    }
    
    /**
     * Checking if the vertex is on the list.
     * @param label label to find in the list
     * @return if vertex with the same label as given was found
     */
    private boolean vertexIsNotInList(String label){
        
        for(int i = 0; i < getAdjacencyList().size(); i++){
            if(getAdjacencyList().get(i).getLabel().equals(label)){
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Finding vertex in list.
     * @param label label of wanted vertex
     * @return index of vertex in the list
     */
    public int findVertexIndex(String label){
        
        for(int i = 0; i < getAdjacencyList().size(); i++){
            if(getAdjacencyList().get(i).getLabel().equals(label)){
                return i;
            }
        }
        
        return -1;  // notFound
    }
    
    /**
     * Adds new vertex to the list and sorting it.
     * @param label label of vertex
     */
    public void addVertex(String label){
        
        if(vertexIsNotInList(label)){
            getAdjacencyList().add(new Vertex(label));
            Collections.sort(getAdjacencyList(), new SortVertex());
        }
    }
    
    /**
     * Adds new edge beetween two vertex.
     * If any of this vertx not exist adds them to the list.
     * @param startVertexLabel  label of the start vertex
     * @param weight            weight of the connection
     * @param endVertexLabel    label of the end vertex
     */
    public void addEdge(String startVertexLabel, double weight, String endVertexLabel){
        addVertex(startVertexLabel);    // add vertices if not in the list.
        addVertex(endVertexLabel);
        
        int startVertexId = findVertexIndex(startVertexLabel);
        
        if(startVertexId == -1){
            System.out.println("ERROR!");
        }
        else{
            getAdjacencyList().get(startVertexId).addEdge(new Edge(weight, endVertexLabel));
        }
    }
    
    /**
     * Save label from all vertex and returns it.
     * @return full list of vertices label 
     */
    public String[] getListOfLabels(){
        String[] listOfLabels = new String[getAdjacencyList().size()];
        
        for(int i = 0; i < getAdjacencyList().size(); i++){
            listOfLabels[i] = getAdjacencyList().get(i).getLabel();
        }
        
        return listOfLabels;
    }
    
    /**
     * Count edges.
     * @return number of all the edges
     */
    public int getNumberOfEdges(){
        int numberOfEdges = 0;
        
        for(int i = 0; i < getAdjacencyList().size(); i++){
            numberOfEdges += getAdjacencyList().get(i).getEdges().size();
        }
        
        return numberOfEdges;
    }
    
    /**
     * Returns label data from vertex list.
     * @return labels of vertices
     */
    public Object[][] getVertexData(){
        Object[][] vertexData = new Object[getAdjacencyList().size()][1];
        
        for(int i = 0; i < getAdjacencyList().size(); i++){
            vertexData[i][0] = getAdjacencyList().get(i).getLabel(); 
        }
        
        return vertexData;
    }
    
    /**
     * Returns information about all edges, start point, end point and weight.
     * @return information about edges
     */
    public Object[][] getEdgesData(){
        int numberOfEdges = getNumberOfEdges();
        int edgesToAdd = numberOfEdges;
        Object[][] edgeData = new Object[numberOfEdges][3];
        
        for(int i = 0; i < getAdjacencyList().size(); i++){
            for(int j = 0; j < getAdjacencyList().get(i).getEdges().size(); j++){
                edgeData[numberOfEdges - edgesToAdd][0] = getAdjacencyList().get(i).getLabel();
                edgeData[numberOfEdges - edgesToAdd][1] = getAdjacencyList().get(i).getEdges().get(j).getEndVertexLabel();
                edgeData[numberOfEdges - edgesToAdd][2] = getAdjacencyList().get(i).getEdges().get(j).getWeight();
                edgesToAdd--;
            }
        }
        
        return edgeData;
    }
    
    /**
     * Reset all data.
     */
    public void reset(){
        
        for(int i = 0; i < getAdjacencyList().size(); i++){
            getAdjacencyList().get(i).getEdges().clear();
        }
        
        this.getAdjacencyList().clear();
    }
}
