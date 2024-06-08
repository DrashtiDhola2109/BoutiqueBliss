/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Administrator
 */
@Entity
@Table(name = "sizemaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sizemaster.findAll", query = "SELECT s FROM Sizemaster s"),
    @NamedQuery(name = "Sizemaster.findBySizeId", query = "SELECT s FROM Sizemaster s WHERE s.sizeId = :sizeId"),
    @NamedQuery(name = "Sizemaster.findBySize", query = "SELECT s FROM Sizemaster s WHERE s.size = :size"),
    @NamedQuery(name = "Sizemaster.findByIsHide", query = "SELECT s FROM Sizemaster s WHERE s.isHide = :isHide")})
public class Sizemaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sizeId")
    private Integer sizeId;
    @Size(max = 50)
    @Column(name = "size")
    private String size;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isHide")
    private boolean isHide;
    @OneToMany(mappedBy = "sizeId")
    private Collection<Product> productCollection;

    public Sizemaster() {
    }

    public Sizemaster(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Sizemaster(Integer sizeId, boolean isHide) {
        this.sizeId = sizeId;
        this.isHide = isHide;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean getIsHide() {
        return isHide;
    }

    public void setIsHide(boolean isHide) {
        this.isHide = isHide;
    }

    @JsonbTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sizeId != null ? sizeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sizemaster)) {
            return false;
        }
        Sizemaster other = (Sizemaster) object;
        if ((this.sizeId == null && other.sizeId != null) || (this.sizeId != null && !this.sizeId.equals(other.sizeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sizemaster[ sizeId=" + sizeId + " ]";
    }
    
}
