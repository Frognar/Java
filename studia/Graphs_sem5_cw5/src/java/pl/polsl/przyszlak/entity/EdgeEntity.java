package pl.polsl.przyszlak.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Edge entity for database
 *
 * @author Sebastian Przyszlak
 * @version 1.0
 */
@Entity
@Table(name = "EDGES_DATA")
public class EdgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EID")
    /**
     * id
     */
    private int id;

    @ManyToOne
    @JoinColumn(name = "START_VERTEX")
    /**
     * Start vertex
     */
    private VertexEntity startVertex;

    @ManyToOne
    @JoinColumn(name = "END_VERTEX")
    /**
     * End vertex
     */
    private VertexEntity endVertex;

    @Column(name = "WEIGHT")
    /**
     * Weight of the edge
     */
    private double weight;

    /**
     * Empty class constructor
     */
    public EdgeEntity() {
       
    }

    /**
     * EdgeEntity class constructor
     * @param startVertex - start vertex
     * @param endVertex - end vertex
     * @param weight - weight of the edge
     */
    public EdgeEntity(VertexEntity startVertex, VertexEntity endVertex, double weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }
    
    /**
     * get id
     * @return id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * get start vertex
     * @return startVertex
     */
    public VertexEntity getStartVertex() {
        return this.startVertex;
    }
    
    /**
     * get end vertex
     * @return endVertex
     */
    public VertexEntity getEndVertex() {
        return this.endVertex;
    }
    
    /**
     * get weight
     * @return weight
     */
    public double getWeight() {
        return this.weight;
    }
}
