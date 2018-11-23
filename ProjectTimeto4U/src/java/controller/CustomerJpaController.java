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
import model.Account;
import model.OrdersCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import model.Customer;

/**
 *
 * @author Mild-TN
 */
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) throws RollbackFailureException, Exception {
        if (customer.getOrdersCustomerList() == null) {
            customer.setOrdersCustomerList(new ArrayList<OrdersCustomer>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account accountId = customer.getAccountId();
            if (accountId != null) {
                accountId = em.getReference(accountId.getClass(), accountId.getAccountId());
                customer.setAccountId(accountId);
            }
            List<OrdersCustomer> attachedOrdersCustomerList = new ArrayList<OrdersCustomer>();
            for (OrdersCustomer ordersCustomerListOrdersCustomerToAttach : customer.getOrdersCustomerList()) {
                ordersCustomerListOrdersCustomerToAttach = em.getReference(ordersCustomerListOrdersCustomerToAttach.getClass(), ordersCustomerListOrdersCustomerToAttach.getOrdernumber());
                attachedOrdersCustomerList.add(ordersCustomerListOrdersCustomerToAttach);
            }
            customer.setOrdersCustomerList(attachedOrdersCustomerList);
            em.persist(customer);
            if (accountId != null) {
                accountId.getCustomerList().add(customer);
                accountId = em.merge(accountId);
            }
            for (OrdersCustomer ordersCustomerListOrdersCustomer : customer.getOrdersCustomerList()) {
                Customer oldCustomernumberOfOrdersCustomerListOrdersCustomer = ordersCustomerListOrdersCustomer.getCustomernumber();
                ordersCustomerListOrdersCustomer.setCustomernumber(customer);
                ordersCustomerListOrdersCustomer = em.merge(ordersCustomerListOrdersCustomer);
                if (oldCustomernumberOfOrdersCustomerListOrdersCustomer != null) {
                    oldCustomernumberOfOrdersCustomerListOrdersCustomer.getOrdersCustomerList().remove(ordersCustomerListOrdersCustomer);
                    oldCustomernumberOfOrdersCustomerListOrdersCustomer = em.merge(oldCustomernumberOfOrdersCustomerListOrdersCustomer);
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

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer persistentCustomer = em.find(Customer.class, customer.getCustomernumber());
            Account accountIdOld = persistentCustomer.getAccountId();
            Account accountIdNew = customer.getAccountId();
            List<OrdersCustomer> ordersCustomerListOld = persistentCustomer.getOrdersCustomerList();
            List<OrdersCustomer> ordersCustomerListNew = customer.getOrdersCustomerList();
            List<String> illegalOrphanMessages = null;
            for (OrdersCustomer ordersCustomerListOldOrdersCustomer : ordersCustomerListOld) {
                if (!ordersCustomerListNew.contains(ordersCustomerListOldOrdersCustomer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdersCustomer " + ordersCustomerListOldOrdersCustomer + " since its customernumber field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accountIdNew != null) {
                accountIdNew = em.getReference(accountIdNew.getClass(), accountIdNew.getAccountId());
                customer.setAccountId(accountIdNew);
            }
            List<OrdersCustomer> attachedOrdersCustomerListNew = new ArrayList<OrdersCustomer>();
            for (OrdersCustomer ordersCustomerListNewOrdersCustomerToAttach : ordersCustomerListNew) {
                ordersCustomerListNewOrdersCustomerToAttach = em.getReference(ordersCustomerListNewOrdersCustomerToAttach.getClass(), ordersCustomerListNewOrdersCustomerToAttach.getOrdernumber());
                attachedOrdersCustomerListNew.add(ordersCustomerListNewOrdersCustomerToAttach);
            }
            ordersCustomerListNew = attachedOrdersCustomerListNew;
            customer.setOrdersCustomerList(ordersCustomerListNew);
            customer = em.merge(customer);
            if (accountIdOld != null && !accountIdOld.equals(accountIdNew)) {
                accountIdOld.getCustomerList().remove(customer);
                accountIdOld = em.merge(accountIdOld);
            }
            if (accountIdNew != null && !accountIdNew.equals(accountIdOld)) {
                accountIdNew.getCustomerList().add(customer);
                accountIdNew = em.merge(accountIdNew);
            }
            for (OrdersCustomer ordersCustomerListNewOrdersCustomer : ordersCustomerListNew) {
                if (!ordersCustomerListOld.contains(ordersCustomerListNewOrdersCustomer)) {
                    Customer oldCustomernumberOfOrdersCustomerListNewOrdersCustomer = ordersCustomerListNewOrdersCustomer.getCustomernumber();
                    ordersCustomerListNewOrdersCustomer.setCustomernumber(customer);
                    ordersCustomerListNewOrdersCustomer = em.merge(ordersCustomerListNewOrdersCustomer);
                    if (oldCustomernumberOfOrdersCustomerListNewOrdersCustomer != null && !oldCustomernumberOfOrdersCustomerListNewOrdersCustomer.equals(customer)) {
                        oldCustomernumberOfOrdersCustomerListNewOrdersCustomer.getOrdersCustomerList().remove(ordersCustomerListNewOrdersCustomer);
                        oldCustomernumberOfOrdersCustomerListNewOrdersCustomer = em.merge(oldCustomernumberOfOrdersCustomerListNewOrdersCustomer);
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
                Integer id = customer.getCustomernumber();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
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
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getCustomernumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrdersCustomer> ordersCustomerListOrphanCheck = customer.getOrdersCustomerList();
            for (OrdersCustomer ordersCustomerListOrphanCheckOrdersCustomer : ordersCustomerListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the OrdersCustomer " + ordersCustomerListOrphanCheckOrdersCustomer + " in its ordersCustomerList field has a non-nullable customernumber field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Account accountId = customer.getAccountId();
            if (accountId != null) {
                accountId.getCustomerList().remove(customer);
                accountId = em.merge(accountId);
            }
            em.remove(customer);
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

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
