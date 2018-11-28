/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Mild-TN
 */
@Entity
@Table(name = "ORDERSCUSTOMER")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "OrdersCustomer.findAll", query = "SELECT o FROM OrdersCustomer o")
  , @NamedQuery(name = "OrdersCustomer.findByOrdernumber", query = "SELECT o FROM OrdersCustomer o WHERE o.ordernumber = :ordernumber")
  , @NamedQuery(name = "OrdersCustomer.findByRequireddate", query = "SELECT o FROM OrdersCustomer o WHERE o.requireddate = :requireddate")
  , @NamedQuery(name = "OrdersCustomer.findByShippeddate", query = "SELECT o FROM OrdersCustomer o WHERE o.shippeddate = :shippeddate")
  , @NamedQuery(name = "OrdersCustomer.findByStatus", query = "SELECT o FROM OrdersCustomer o WHERE o.status = :status")
  , @NamedQuery(name = "OrdersCustomer.findByTotalprice", query = "SELECT o FROM OrdersCustomer o WHERE o.totalprice = :totalprice")})
public class OrdersCustomer implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ORDERNUMBER")
  private Integer ordernumber;
  @Basic(optional = false)
  @NotNull
  @Column(name = "REQUIREDDATE")
  @Temporal(TemporalType.DATE)
  private Date requireddate;
  @Column(name = "SHIPPEDDATE")
  @Temporal(TemporalType.DATE)
  private Date shippeddate;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 15)
  @Column(name = "STATUS")
  private String status;
  @Basic(optional = false)
  @NotNull
  @Column(name = "TOTALPRICE")
  private int totalprice;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordernumber")
  private List<Payment> paymentList;
  @JoinColumn(name = "CUSTOMERNUMBER", referencedColumnName = "CUSTOMERNUMBER")
  @ManyToOne(optional = false)
  private Customer customernumber;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordersCustomer")
  private List<OrderDetail> orderDetailList;

  public OrdersCustomer() {
  }

  public OrdersCustomer(Integer ordernumber) {
    this.ordernumber = ordernumber;
  }

  public OrdersCustomer(Integer ordernumber, Date requireddate, String status, int totalprice) {
    this.ordernumber = ordernumber;
    this.requireddate = requireddate;
    this.status = status;
    this.totalprice = totalprice;
  }

  public OrdersCustomer(Date requireddate, Date shippeddate, String status, int totalprice, Customer customernumber) {
    this.requireddate = requireddate;
    this.shippeddate = shippeddate;
    this.status = status;
    this.totalprice = totalprice;
    this.customernumber = customernumber;
  }

    public OrdersCustomer(Integer ordernumber,String status) {
        this.ordernumber = ordernumber;
        this.status = status;
        this.customernumber = customernumber;
    }

  public Integer getOrdernumber() {
    return ordernumber;
  }

  public void setOrdernumber(Integer ordernumber) {
    this.ordernumber = ordernumber;
  }

  public Date getRequireddate() {
    return requireddate;
  }

  public void setRequireddate(Date requireddate) {
    this.requireddate = requireddate;
  }

  public Date getShippeddate() {
    return shippeddate;
  }

  public void setShippeddate(Date shippeddate) {
    this.shippeddate = shippeddate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getTotalprice() {
    return totalprice;
  }

  public void setTotalprice(int totalprice) {
    this.totalprice = totalprice;
  }

  @XmlTransient
  public List<Payment> getPaymentList() {
    return paymentList;
  }

  public void setPaymentList(List<Payment> paymentList) {
    this.paymentList = paymentList;
  }

  public Customer getCustomernumber() {
    return customernumber;
  }

  public void setCustomernumber(Customer customernumber) {
    this.customernumber = customernumber;
  }
  
  public boolean paidStatus(boolean checkPay){
      if(checkPay){
          this.status = "Paid";
          return true;
      }else{
          return false;
      }
  }

  @XmlTransient
  public List<OrderDetail> getOrderDetailList() {
    return orderDetailList;
  }

  public void setOrderDetailList(List<OrderDetail> orderDetailList) {
    this.orderDetailList = orderDetailList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (ordernumber != null ? ordernumber.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof OrdersCustomer)) {
      return false;
    }
    OrdersCustomer other = (OrdersCustomer) object;
    if ((this.ordernumber == null && other.ordernumber != null) || (this.ordernumber != null && !this.ordernumber.equals(other.ordernumber))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "models.OrdersCustomer[ ordernumber=" + ordernumber + " ]";
  }

}
