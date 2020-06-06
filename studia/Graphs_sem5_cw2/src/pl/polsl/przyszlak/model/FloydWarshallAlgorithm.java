package pl.polsl.przyszlak.model;

import java.util.List;

/**
 * Class for shortest path Floyd Warshall Algorithm.
 * Contain distanceMatrix to save distance between all vertices
 * ane pathMatrix to store information how to get from A to B.
 * @author Sebastian Przyszlak
 * @version 1.2
 */
public class FloydWarshallAlgorithm {
    
    /** 2d Array with distance between all the vertices */
    private double[][] distanceMatrix;
    
    /** 2d Array with next step in path between all vertices */
    private String[][] pathMatrix;
    
    /** Array with labels of all vertices */
    private String[] listOfLabels;
    
    /**
     * @return the distanceMatrix
     */
    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    /**
     * @param distanceMatrix the distanceMatrix to set
     */
    public void setDistanceMatrix(double[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    /**
     * @return the pathMatrix
     */
    public String[][] getPathMatrix() {
        return pathMatrix;
    }

    /**
     * @param pathMatrix the pathMatrix to set
     */
    public void setPathMatrix(String[][] pathMatrix) {
        this.pathMatrix = pathMatrix;
    }

    /**
     * @return the listOfLabels
     */
    public String[] getListOfLabels() {
        return listOfLabels;
    }

    /**
     * @param listOfLabels the listOfLabels to set
     */
    public void setListOfLabels(String[] listOfLabels) {
        this.listOfLabels = listOfLabels;
    }
    
    /**
     * Class constructor.
     */
    public FloydWarshallAlgorithm() {
    
    }
    
    /**
     * Inicjalization.
     * distance[A][A] = 0 and path[A][A] = "A".
     * distance[A][B] = inf. and path [A][B] = "".
     * then call firstStepInFWAlgorithm method
     * and calculateShortestPath method
     * @param adjacencyList list of vertices and it's edges
     * @param listOfLabels  full list of vertices label
     */
    public void doAlgorithm(List<Vertex> adjacencyList, String... listOfLabels) {
        
        this.setListOfLabels(listOfLabels);
        setDistanceMatrix(new double[listOfLabels.length][listOfLabels.length]);
        setPathMatrix(new String[listOfLabels.length][listOfLabels.length]);
        
        for(int i = 0; i < getListOfLabels().length; i++) {
            for(int j = 0; j < getListOfLabels().length; j++) {
                getDistanceMatrix()[i][j] = Double.POSITIVE_INFINITY;
                getPathMatrix()[i][j] = "";
            }
            
            getDistanceMatrix()[i][i] = 0;
            getPathMatrix()[i][i] = getListOfLabels()[i];
        }
        
        firstStepInFWAlgorithm(adjacencyList);
        calculateShortestPath();
    }
    
    /**
     * First step of the FW algorithm.
     * Update distance and path matrix if there is edge between A and B
     * distance[A][B] = weight of edge.
     * path[A][B] = "A".
     * @param adjacencyList list of vertices and it's edges
     */
    private void firstStepInFWAlgorithm(List<Vertex> adjacencyList) {
        
        for(int i = 0; i < adjacencyList.size(); i++) {
            for(Edge ed : adjacencyList.get(i).getEdges()) {
                int indexOfEndVertex = findIndexOfVertex(ed.getEndVertexLabel());
                
                if(indexOfEndVertex >= 0) {
                    if(getDistanceMatrix()[i][indexOfEndVertex] >= ed.getWeight()) {
                        getDistanceMatrix()[i][indexOfEndVertex] = ed.getWeight();
                        getPathMatrix()[i][indexOfEndVertex] = ed.getEndVertexLabel();
                    }
                }
            }
        }
    }
    
    /**
     * Floyd-Warshall Algorithm.
     * Calcuate shortest distance and saving path.
     */
    private void calculateShortestPath() {
        for(int k = 0; k < getDistanceMatrix().length; k++) {
            for(int i = 0; i < getDistanceMatrix().length; i++) {
                for(int j = 0; j < getDistanceMatrix().length; j++) {
                    if(getDistanceMatrix()[i][j] >= getDistanceMatrix()[i][k] + getDistanceMatrix()[k][j]) {
                        getDistanceMatrix()[i][j] = getDistanceMatrix()[i][k] + getDistanceMatrix()[k][j];
                        if(getDistanceMatrix()[i][j] < Double.POSITIVE_INFINITY && j!=k && i != j && i != k)
                            getPathMatrix()[i][j] = getPathMatrix()[i][k];
                    }
                }
            }
        }
    }
    
    /**
     * Find ngative cycle.
     * @return if ther is a negative cycle
     */
    public boolean negativeCycle() {
        for(int i = 0; i < this.distanceMatrix.length; i++){
            if(this.distanceMatrix[i][i] < 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns index of the wanted vertex.
     * @param label label of wanted vertex
     * @return  index of vertex
     */
    private int findIndexOfVertex(String label) {
        
        for(int i = 0; i < getListOfLabels().length; i++) {
            
            if(getListOfLabels()[i].equals(label)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Returns data from distance matrix.
     * First column is a list of veritces.
     * @return data about distance between all vertices
     */
    public Object[][] getDistanceData() {
        
        Object[][] distanceData = new Object[this.getDistanceMatrix().length][this.getDistanceMatrix().length + 1];
        
        for(int i = 0; i < this.getDistanceMatrix().length; i++) {
            for(int j = 1; j < this.getDistanceMatrix().length + 1; j++) {
                distanceData[i][j] = getDistanceMatrix()[i][j - 1];
            }
            
            distanceData[i][0] = getListOfLabels()[i] + " |";
        }
        
        return distanceData;
    }
    
    /**
     * Returns data from path matrix.
     * First column is a list of veritces.
     * @return data about path.
     */
    public Object[][] getPathData() {
        
        Object[][] pathData = new Object[this.getDistanceMatrix().length][this.getDistanceMatrix().length + 1];
        
        for(int i = 0; i < this.getDistanceMatrix().length; i++) {
            for(int j = 1; j < this.getDistanceMatrix().length + 1; j++) {
                pathData[i][j] = getPathMatrix()[i][j - 1];
            }
            
            pathData[i][0] = getListOfLabels()[i] + " |";
        }
        
        return pathData;
    }
}
