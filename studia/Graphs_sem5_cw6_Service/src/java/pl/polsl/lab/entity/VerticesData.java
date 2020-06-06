/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Frogi
 */
@Entity
@Table(name = "VERTICES_DATA", catalog = "", schema = "LAB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VerticesData.findAll", query = "SELECT v FROM VerticesData v")
    , @NamedQuery(name = "VerticesData.findByVid", query = "SELECT v FROM VerticesData v WHERE v.vid = :vid")
    , @NamedQuery(name = "VerticesData.findByLabel", query = "SELECT v FROM VerticesData v WHERE v.label = :label")})
public class VerticesData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "VID")
    private Integer vid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LABEL")
    private String label;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="START_VERTEX")
    private List<EdgesData> edgesDataList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="END_VERTEX")private List<EdgesData> edgesDataList1;
    public VerticesData() {
    }

    public VerticesData(Integer vid) {
        this.vid = vid;
    }

    public VerticesData(Integer vid, String label) {
        this.vid = vid;
        this.label = label;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlTransient
    public List<EdgesData> getEdgesDataList() {
        return edgesDataList;
    }

    public void setEdgesDataList(List<EdgesData> edgesDataList) {
        this.edgesDataList = edgesDataList;
    }

    @XmlTransient
    public List<EdgesData> getEdgesDataList1() {
        return edgesDataList1;
    }

    public void setEdgesDataList1(List<EdgesData> edgesDataList1) {
        this.edgesDataList1 = edgesDataList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vid != null ? vid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VerticesData)) {
            return false;
        }
        VerticesData other = (VerticesData) object;
        if ((this.vid == null && other.vid != null) || (this.vid != null && !this.vid.equals(other.vid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.lab.entity.VerticesData[ vid=" + vid + " ]";
    }
    
}
