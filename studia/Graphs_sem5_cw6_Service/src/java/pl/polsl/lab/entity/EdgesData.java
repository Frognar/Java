/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Frogi
 */
@Entity
@Table(name = "EDGES_DATA", catalog = "", schema = "LAB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdgesData.findAll", query = "SELECT e FROM EdgesData e")
    , @NamedQuery(name = "EdgesData.findByEid", query = "SELECT e FROM EdgesData e WHERE e.eid = :eid")
    , @NamedQuery(name = "EdgesData.findByWeight", query = "SELECT e FROM EdgesData e WHERE e.weight = :weight")})
public class EdgesData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "EID")
    private Integer eid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT")
    private double weight;
    @JoinColumn(name = "START_VERTEX", referencedColumnName = "VID")
    @ManyToOne
    private VerticesData startVertex;
    @JoinColumn(name = "END_VERTEX", referencedColumnName = "VID")
    @ManyToOne
    private VerticesData endVertex;

    public EdgesData() {
    }

    public EdgesData(Integer eid) {
        this.eid = eid;
    }

    public EdgesData(Integer eid, double weight) {
        this.eid = eid;
        this.weight = weight;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public VerticesData getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(VerticesData startVertex) {
        this.startVertex = startVertex;
    }

    public VerticesData getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(VerticesData endVertex) {
        this.endVertex = endVertex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eid != null ? eid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdgesData)) {
            return false;
        }
        EdgesData other = (EdgesData) object;
        if ((this.eid == null && other.eid != null) || (this.eid != null && !this.eid.equals(other.eid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.lab.entity.EdgesData[ eid=" + eid + " ]";
    }
    
}
