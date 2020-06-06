package pl.polsl.przyszlak.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Vertex entity for database
 * @author Sebastian Przyszlak
 * @version 1.0
 */
@Entity
@Table(name = "VERTICES_DATA")
public class VertexEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VID")
    /**
     * id
     */
    private int id;

    @Column(name = "LABEL")
    /**
     * label
     */
    private String label;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//(mappedBy = "startVertex")
    @JoinColumn(name="START_VERTEX")
    /**
     * list of edges where this vertex is a start vertex
     */
    private List<EdgeEntity> edgeStart = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//(mappedBy = "endVertex")
    @JoinColumn(name="END_VERTEX")
    /**
     * list of edges where this vertex is a end vertex
     */
    private List<EdgeEntity> edgeEnd = new ArrayList<>();
    
    /**
     * VertexEntity class empty constructor
     */
    public VertexEntity() {
    }

    /**
     * VertexEntity class constructor
     * @param label - label
     */
    public VertexEntity(String label) {
        this.label = label;
    }
    
    /**
     * get id
     * @return id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * get label
     * @return label
     */
    public String getLabel() {
        return this.label;
    }
    
    /**
     * get list of edges where this vertex is a start vertex
     * @return EdgeStart
     */
    public List<EdgeEntity> getEdgeStart(){
        return this.edgeStart;
    }
    
    /**
     * get list of edges where this vertex is a end vertex
     * @return EdgeEnd
     */
    public List<EdgeEntity> getEdgeEnd(){
        return this.edgeEnd;
    }
}
