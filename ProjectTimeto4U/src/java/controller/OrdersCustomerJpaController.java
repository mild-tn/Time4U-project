/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Customer;
import model.Payment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;
import model.Account;
import model.OrderDetail;
import model.OrdersCustomer;

/**
 *
 * @author Mild-TN
 */
public class OrdersCustomerJpaController implements Serializable {

  public OrdersCustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(OrdersCustomer ordersCustomer) throws RollbackFailureException, Exception {
    if (ordersCustomer.getPaymentList() == null) {
      ordersCustomer.setPaymentList(new ArrayList<Payment>());
    }
    if (ordersCustomer.getOrderDetailList() == null) {
      ordersCustomer.setOrderDetailList(new ArrayList<OrderDetail>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Customer customernumber = ordersCustomer.getCustomernumber();
      if (customernumber != null) {
        customernumber = em.getReference(customernumber.getClass(), customernumber.getCustomernumber());
        ordersCustomer.setCustomernumber(customernumber);
      }
      List<Payment> attachedPaymentList = new ArrayList<Payment>();
      for (Payment paymentListPaymentToAttach : ordersCustomer.getPaymentList()) {
        paymentListPaymentToAttach = em.getReference(paymentListPaymentToAttach.getClass(), paymentListPaymentToAttach.getCardnumber());
        attachedPaymentList.add(paymentListPaymentToAttach);
      }
      ordersCustomer.setPaymentList(attachedPaymentList);
      List<OrderDetail> attachedOrderDetailList = new ArrayList<OrderDetail>();
      for (OrderDetail orderDetailListOrderDetailToAttach : ordersCustomer.getOrderDetailList()) {
        orderDetailListOrderDetailToAttach = em.getReference(orderDetailListOrderDetailToAttach.getClass(), orderDetailListOrderDetailToAttach.getOrderDetailPK());
        attachedOrderDetailList.add(orderDetailListOrderDetailToAttach);
      }
      ordersCustomer.setOrderDetailList(attachedOrderDetailList);
      em.persist(ordersCustomer);
      if (customernumber != null) {
        customernumber.getOrdersCustomerList().add(ordersCustomer);
        customernumber = em.merge(customernumber);
      }
      for (Payment paymentListPayment : ordersCustomer.getPaymentList()) {
        OrdersCustomer oldOrdernumberOfPaymentListPayment = paymentListPayment.getOrdernumber();
        paymentListPayment.setOrdernumber(ordersCustomer);
        paymentListPayment = em.merge(paymentListPayment);
        if (oldOrdernumberOfPaymentListPayment != null) {
          oldOrdernumberOfPaymentListPayment.getPaymentList().remove(paymentListPayment);
          oldOrdernumberOfPaymentListPayment = em.merge(oldOrdernumberOfPaymentListPayment);
        }
      }
      for (OrderDetail orderDetailListOrderDetail : ordersCustomer.getOrderDetailList()) {
        OrdersCustomer oldOrdersCustomerOfOrderDetailListOrderDetail = orderDetailListOrderDetail.getOrdersCustomer();
        orderDetailListOrderDetail.setOrdersCustomer(ordersCustomer);
        orderDetailListOrderDetail = em.merge(orderDetailListOrderDetail);
        if (oldOrdersCustomerOfOrderDetailListOrderDetail != null) {
          oldOrdersCustomerOfOrderDetailListOrderDetail.getOrderDetailList().remove(orderDetailListOrderDetail);
          oldOrdersCustomerOfOrderDetailListOrderDetail = em.merge(oldOrdersCustomerOfOrderDetailListOrderDetail);
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(OrdersCustomer ordersCustomer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      OrdersCustomer persistentOrdersCustomer = em.find(OrdersCustomer.class, ordersCustomer.getOrdernumber());
      Customer customernumberOld = persistentOrdersCustomer.getCustomernumber();
      Customer customernumberNew = ordersCustomer.getCustomernumber();
      List<Payment> paymentListOld = persistentOrdersCustomer.getPaymentList();
      List<Payment> paymentListNew = ordersCustomer.getPaymentList();
      List<OrderDetail> orderDetailListOld = persistentOrdersCustomer.getOrderDetailList();
      List<OrderDetail> orderDetailListNew = ordersCustomer.getOrderDetailList();
      List<String> illegalOrphanMessages = null;
      for (Payment paymentListOldPayment : paymentListOld) {
        if (!paymentListNew.contains(paymentListOldPayment)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Payment " + paymentListOldPayment + " since its ordernumber field is not nullable.");
        }
      }
      for (OrderDetail orderDetailListOldOrderDetail : orderDetailListOld) {
        if (!orderDetailListNew.contains(orderDetailListOldOrderDetail)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailListOldOrderDetail + " since its ordersCustomer field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (customernumberNew != null) {
        customernumberNew = em.getReference(customernumberNew.getClass(), customernumberNew.getCustomernumber());
        ordersCustomer.setCustomernumber(customernumberNew);
      }
      List<Payment> attachedPaymentListNew = new ArrayList<Payment>();
      for (Payment paymentListNewPaymentToAttach : paymentListNew) {
        paymentListNewPaymentToAttach = em.getReference(paymentListNewPaymentToAttach.getClass(), paymentListNewPaymentToAttach.getCardnumber());
        attachedPaymentListNew.add(paymentListNewPaymentToAttach);
      }
      paymentListNew = attachedPaymentListNew;
      ordersCustomer.setPaymentList(paymentListNew);
      List<OrderDetail> attachedOrderDetailListNew = new ArrayList<OrderDetail>();
      for (OrderDetail orderDetailListNewOrderDetailToAttach : orderDetailListNew) {
        orderDetailListNewOrderDetailToAttach = em.getReference(orderDetailListNewOrderDetailToAttach.getClass(), orderDetailListNewOrderDetailToAttach.getOrderDetailPK());
        attachedOrderDetailListNew.add(orderDetailListNewOrderDetailToAttach);
      }
      orderDetailListNew = attachedOrderDetailListNew;
      ordersCustomer.setOrderDetailList(orderDetailListNew);
      ordersCustomer = em.merge(ordersCustomer);
      if (customernumberOld != null && !customernumberOld.equals(customernumberNew)) {
        customernumberOld.getOrdersCustomerList().remove(ordersCustomer);
        customernumberOld = em.merge(customernumberOld);
      }
      if (customernumberNew != null && !customernumberNew.equals(customernumberOld)) {
        customernumberNew.getOrdersCustomerList().add(ordersCustomer);
        customernumberNew = em.merge(customernumberNew);
      }
      for (Payment paymentListNewPayment : paymentListNew) {
        if (!paymentListOld.contains(paymentListNewPayment)) {
          OrdersCustomer oldOrdernumberOfPaymentListNewPayment = paymentListNewPayment.getOrdernumber();
          paymentListNewPayment.setOrdernumber(ordersCustomer);
          paymentListNewPayment = em.merge(paymentListNewPayment);
          if (oldOrdernumberOfPaymentListNewPayment != null && !oldOrdernumberOfPaymentListNewPayment.equals(ordersCustomer)) {
            oldOrdernumberOfPaymentListNewPayment.getPaymentList().remove(paymentListNewPayment);
            oldOrdernumberOfPaymentListNewPayment = em.merge(oldOrdernumberOfPaymentListNewPayment);
          }
        }
      }
      for (OrderDetail orderDetailListNewOrderDetail : orderDetailListNew) {
        if (!orderDetailListOld.contains(orderDetailListNewOrderDetail)) {
          OrdersCustomer oldOrdersCustomerOfOrderDetailListNewOrderDetail = orderDetailListNewOrderDetail.getOrdersCustomer();
          orderDetailListNewOrderDetail.setOrdersCustomer(ordersCustomer);
          orderDetailListNewOrderDetail = em.merge(orderDetailListNewOrderDetail);
          if (oldOrdersCustomerOfOrderDetailListNewOrderDetail != null && !oldOrdersCustomerOfOrderDetailListNewOrderDetail.equals(ordersCustomer)) {
            oldOrdersCustomerOfOrderDetailListNewOrderDetail.getOrderDetailList().remove(orderDetailListNewOrderDetail);
            oldOrdersCustomerOfOrderDetailListNewOrderDetail = em.merge(oldOrdersCustomerOfOrderDetailListNewOrderDetail);
          }
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = ordersCustomer.getOrdernumber();
        if (findOrdersCustomer(id) == null) {
          throw new NonexistentEntityException("The ordersCustomer with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      OrdersCustomer ordersCustomer;
      try {
        ordersCustomer = em.getReference(OrdersCustomer.class, id);
        ordersCustomer.getOrdernumber();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The ordersCustomer with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      List<Payment> paymentListOrphanCheck = ordersCustomer.getPaymentList();
      for (Payment paymentListOrphanCheckPayment : paymentListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This OrdersCustomer (" + ordersCustomer + ") cannot be destroyed since the Payment " + paymentListOrphanCheckPayment + " in its paymentList field has a non-nullable ordernumber field.");
      }
      List<OrderDetail> orderDetailListOrphanCheck = ordersCustomer.getOrderDetailList();
      for (OrderDetail orderDetailListOrphanCheckOrderDetail : orderDetailListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This OrdersCustomer (" + ordersCustomer + ") cannot be destroyed since the OrderDetail " + orderDetailListOrphanCheckOrderDetail + " in its orderDetailList field has a non-nullable ordersCustomer field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Customer customernumber = ordersCustomer.getCustomernumber();
      if (customernumber != null) {
        customernumber.getOrdersCustomerList().remove(ordersCustomer);
        customernumber = em.merge(customernumber);
      }
      em.remove(ordersCustomer);
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<OrdersCustomer> findOrdersCustomerEntities() {
    return findOrdersCustomerEntities(true, -1, -1);
  }

  public List<OrdersCustomer> findOrdersCustomerEntities(int maxResults, int firstResult) {
    return findOrdersCustomerEntities(false, maxResults, firstResult);
  }

  private List<OrdersCustomer> findOrdersCustomerEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(OrdersCustomer.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public OrdersCustomer findOrdersCustomer(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(OrdersCustomer.class, id);
    } finally {
      em.close();
    }
  }

  public int getOrdersCustomerCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<OrdersCustomer> rt = cq.from(OrdersCustomer.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
