/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import model.OrderDetail;
import model.OrderDetailPK;
import model.OrdersCustomer;
import model.Product;

/**
 *
 * @author Mild-TN
 */
public class OrderDetailJpaController implements Serializable {

    public OrderDetailJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderDetail orderDetail) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (orderDetail.getOrderDetailPK() == null) {
            orderDetail.setOrderDetailPK(new OrderDetailPK());
        }
        orderDetail.getOrderDetailPK().setProductcode(orderDetail.getProduct().getProductcode());
        orderDetail.getOrderDetailPK().setOrdernumber(orderDetail.getOrdersCustomer().getOrdernumber());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrdersCustomer ordersCustomer = orderDetail.getOrdersCustomer();
            if (ordersCustomer != null) {
                ordersCustomer = em.getReference(ordersCustomer.getClass(), ordersCustomer.getOrdernumber());
                orderDetail.setOrdersCustomer(ordersCustomer);
            }
            Product product = orderDetail.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getProductcode());
                orderDetail.setProduct(product);
            }
            em.persist(orderDetail);
            if (ordersCustomer != null) {
                ordersCustomer.getOrderDetailList().add(orderDetail);
                ordersCustomer = em.merge(ordersCustomer);
            }
            if (product != null) {
                product.getOrderDetailList().add(orderDetail);
                product = em.merge(product);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOrderDetail(orderDetail.getOrderDetailPK()) != null) {
                throw new PreexistingEntityException("OrderDetail " + orderDetail + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderDetail orderDetail) throws NonexistentEntityException, RollbackFailureException, Exception {
        orderDetail.getOrderDetailPK().setProductcode(orderDetail.getProduct().getProductcode());
        orderDetail.getOrderDetailPK().setOrdernumber(orderDetail.getOrdersCustomer().getOrdernumber());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrderDetail persistentOrderDetail = em.find(OrderDetail.class, orderDetail.getOrderDetailPK());
            OrdersCustomer ordersCustomerOld = persistentOrderDetail.getOrdersCustomer();
            OrdersCustomer ordersCustomerNew = orderDetail.getOrdersCustomer();
            Product productOld = persistentOrderDetail.getProduct();
            Product productNew = orderDetail.getProduct();
            if (ordersCustomerNew != null) {
                ordersCustomerNew = em.getReference(ordersCustomerNew.getClass(), ordersCustomerNew.getOrdernumber());
                orderDetail.setOrdersCustomer(ordersCustomerNew);
            }
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getProductcode());
                orderDetail.setProduct(productNew);
            }
            orderDetail = em.merge(orderDetail);
            if (ordersCustomerOld != null && !ordersCustomerOld.equals(ordersCustomerNew)) {
                ordersCustomerOld.getOrderDetailList().remove(orderDetail);
                ordersCustomerOld = em.merge(ordersCustomerOld);
            }
            if (ordersCustomerNew != null && !ordersCustomerNew.equals(ordersCustomerOld)) {
                ordersCustomerNew.getOrderDetailList().add(orderDetail);
                ordersCustomerNew = em.merge(ordersCustomerNew);
            }
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getOrderDetailList().remove(orderDetail);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getOrderDetailList().add(orderDetail);
                productNew = em.merge(productNew);
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
                OrderDetailPK id = orderDetail.getOrderDetailPK();
                if (findOrderDetail(id) == null) {
                    throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OrderDetailPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrderDetail orderDetail;
            try {
                orderDetail = em.getReference(OrderDetail.class, id);
                orderDetail.getOrderDetailPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.", enfe);
            }
            OrdersCustomer ordersCustomer = orderDetail.getOrdersCustomer();
            if (ordersCustomer != null) {
                ordersCustomer.getOrderDetailList().remove(orderDetail);
                ordersCustomer = em.merge(ordersCustomer);
            }
            Product product = orderDetail.getProduct();
            if (product != null) {
                product.getOrderDetailList().remove(orderDetail);
                product = em.merge(product);
            }
            em.remove(orderDetail);
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

    public List<OrderDetail> findOrderDetailEntities() {
        return findOrderDetailEntities(true, -1, -1);
    }

    public List<OrderDetail> findOrderDetailEntities(int maxResults, int firstResult) {
        return findOrderDetailEntities(false, maxResults, firstResult);
    }

    private List<OrderDetail> findOrderDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderDetail.class));
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

    public OrderDetail findOrderDetail(OrderDetailPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderDetail> rt = cq.from(OrderDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
