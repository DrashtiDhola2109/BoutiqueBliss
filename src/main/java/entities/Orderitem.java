/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "orderitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderitem.findAll", query = "SELECT o FROM Orderitem o"),
    @NamedQuery(name = "Orderitem.findByOrderItemId", query = "SELECT o FROM Orderitem o WHERE o.orderItemId = :orderItemId"),
    @NamedQuery(name = "Orderitem.findByProductQuantity", query = "SELECT o FROM Orderitem o WHERE o.productQuantity = :productQuantity"),
    @NamedQuery(name = "Orderitem.findByTotalAmount", query = "SELECT o FROM Orderitem o WHERE o.totalAmount = :totalAmount")})
public class Orderitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderItemId")
    private Integer orderItemId;
    @Column(name = "productQuantity")
    private Integer productQuantity;
    @Column(name = "totalAmount")
    private Integer totalAmount;
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    @ManyToOne
    private Ordermaster orderId;
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    @ManyToOne
    private Product productId;

    public Orderitem() {
    }

    public Orderitem(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Ordermaster getOrderId() {
        return orderId;
    }

    public void setOrderId(Ordermaster orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderItemId != null ? orderItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderitem)) {
            return false;
        }
        Orderitem other = (Orderitem) object;
        if ((this.orderItemId == null && other.orderItemId != null) || (this.orderItemId != null && !this.orderItemId.equals(other.orderItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orderitem[ orderItemId=" + orderItemId + " ]";
    }
    
}
