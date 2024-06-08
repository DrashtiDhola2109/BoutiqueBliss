/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "ordermaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordermaster.findAll", query = "SELECT o FROM Ordermaster o"),
    @NamedQuery(name = "Ordermaster.findByOrderId", query = "SELECT o FROM Ordermaster o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Ordermaster.findByPaymentId", query = "SELECT o FROM Ordermaster o WHERE o.paymentId = :paymentId"),
    @NamedQuery(name = "Ordermaster.findByOrderDate", query = "SELECT o FROM Ordermaster o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Ordermaster.findByOrderTime", query = "SELECT o FROM Ordermaster o WHERE o.orderTime = :orderTime"),
    @NamedQuery(name = "Ordermaster.findByModeOfPayment", query = "SELECT o FROM Ordermaster o WHERE o.modeOfPayment = :modeOfPayment"),
    @NamedQuery(name = "Ordermaster.findByPaymentStatus", query = "SELECT o FROM Ordermaster o WHERE o.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "Ordermaster.findByCgst", query = "SELECT o FROM Ordermaster o WHERE o.cgst = :cgst"),
    @NamedQuery(name = "Ordermaster.findBySgst", query = "SELECT o FROM Ordermaster o WHERE o.sgst = :sgst"),
    @NamedQuery(name = "Ordermaster.findByGrandTotal", query = "SELECT o FROM Ordermaster o WHERE o.grandTotal = :grandTotal")})
public class Ordermaster implements Serializable {

    @Size(max = 50)
    @Column(name = "modeOfPayment")
    private String modeOfPayment;
    @Size(max = 50)
    @Column(name = "paymentStatus")
    private String paymentStatus;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "orderId")
    private String orderId;
    @Column(name = "paymentId")
    private Integer paymentId;
    @Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Column(name = "orderTime")
    @Temporal(TemporalType.TIME)
    private Date orderTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cgst")
    private Float cgst;
    @Column(name = "sgst")
    private Float sgst;
    @Column(name = "grandTotal")
    private Integer grandTotal;
    @OneToMany(mappedBy = "orderId")
    private Collection<Orderitem> orderitemCollection;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Usermaster username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<Appointment> appointmentCollection;

    public Ordermaster() {
    }

    public Ordermaster(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


    public Float getCgst() {
        return cgst;
    }

    public void setCgst(Float cgst) {
        this.cgst = cgst;
    }

    public Float getSgst() {
        return sgst;
    }

    public void setSgst(Float sgst) {
        this.sgst = sgst;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    @XmlTransient
    public Collection<Orderitem> getOrderitemCollection() {
        return orderitemCollection;
    }

    public void setOrderitemCollection(Collection<Orderitem> orderitemCollection) {
        this.orderitemCollection = orderitemCollection;
    }

    public Usermaster getUsername() {
        return username;
    }

    public void setUsername(Usermaster username) {
        this.username = username;
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordermaster)) {
            return false;
        }
        Ordermaster other = (Ordermaster) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordermaster[ orderId=" + orderId + " ]";
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
}
