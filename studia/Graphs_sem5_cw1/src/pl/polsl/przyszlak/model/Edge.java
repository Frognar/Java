package pl.polsl.przyszlak.model;

/**
 * Edge from one vertex, which store information about weight and destination.
 * @author Sebastian Przyszlak
 * @version 1.1
 */
public class Edge {
    private double weight;
    private String endVertexLabel;
    /**
     * Class constructor.
     * Creating empty object.
     */
    public Edge(){
        
    }
    
    /**
     * Class constructor specyfying weight of the connection and the label of the target vertex.
     * @param weight            'cost' of the connection
     * @param endVertexLabel    label of the target vertex
     */
    public Edge(double weight, String endVertexLabel){
        this.weight = weight;
        this.endVertexLabel = endVertexLabel;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return the endVertexLabel
     */
    public String getEndVertexLabel() {
        return endVertexLabel;
    }

    /**
     * @param endVertexLabel the endVertexLabel to set
     */
    public void setEndVertexLabel(String endVertexLabel) {
        this.endVertexLabel = endVertexLabel;
    }
}
