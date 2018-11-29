package servlet;

import controller.CustomerJpaController;
import controller.OrderDetailJpaController;
import controller.OrdersCustomerJpaController;
import controller.PaymentJpaController;
import controller.exceptions.RollbackFailureException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Account;
import model.Cart;
import model.Customer;
import model.LineItem;
import model.OrdersCustomer;
import model.Payment;

/**
 *
 * @author Mild-TN
 */
public class PaymentServlet extends HttpServlet {

  @PersistenceUnit(unitName = "ProjectTimeto4UPU")
  EntityManagerFactory emf;
  @Resource
  UserTransaction utx;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    OrdersCustomerJpaController orderCusJpaCtrl = new OrdersCustomerJpaController(utx, emf);
    OrdersCustomer orderCus = (OrdersCustomer) session.getAttribute("oderCustomer");
    OrdersCustomer ordersCustomer = orderCusJpaCtrl.findOrdersCustomer(orderCus.getOrdernumber());
    Cart cart = (Cart) session.getAttribute("All");
    String cardHolder = request.getParameter("cardholder");
    String cardNo = request.getParameter("cardno");
    String exp = request.getParameter("exp");
    String cvv = request.getParameter("cvv");
    PaymentJpaController paymentJpaCtrl = new PaymentJpaController(utx, emf);
    Payment payment = paymentJpaCtrl.findPayment(cardNo);
    System.out.println(payment);
    if (payment != null) {
      if (payment.getCardnumber().equals(cardNo) && payment.getCardholder().equals(cardHolder)) {
        String status = "paid";
        ordersCustomer.editCus(status);
        payment.payMent(orderCus, cart.getTotalPrice());
        System.out.println("orderCusStatusgjsdbgisbigsigaisdog" + orderCus.getStatus());
        if (cvv.equals(payment.getCvv())) {
          try {
            session.setAttribute("Orderscustomer", orderCus);
            session.getAttribute("shoppingCart");
            session.removeAttribute("shoppingCart");
            session.setAttribute("All", cart);
            paymentJpaCtrl.edit(payment);
            orderCusJpaCtrl.edit(ordersCustomer);
            response.sendRedirect("Receipt.jsp");
            return;
          } catch (RollbackFailureException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else {
          session.setAttribute("messagePayment", "CVV Wrong!!");
        }
      } else {
        session.setAttribute("messagePayment", "Card Number Wrong!!");
      }
    }
//    if (session != null) {
//      PaymentJpaController paymentJpaCtrl = new PaymentJpaController(utx, emf);
//      Payment payment = paymentJpaCtrl.findPayment(cardNo);
//      System.out.println(payment);
//      if (payment != null) {
//        if (cardNo != null && cardNo.length() > 0 && cardNo.length() == 16 && cardNo.equals(payment.getCardnumber())) {
//          if (cardHolder != null && cardHolder.length() > 0 && cardHolder.equalsIgnoreCase(payment.getCardholder())) {
//            if (exp != null && exp.equals(payment.getExpireMonth() + payment.getExpireYear())) {
//              if (cvv != null && cvv.length() > 0 && cvv.equals(payment.getCvv())) {
//                boolean checkPay = payment.payMent(orderCus, orderCus.getTotalprice());
//                if (checkPay) {
//                  boolean checkStatus = orderCus.paidStatus(checkPay);
//                  String status = "paid";
//                  ordersCustomer.editCus(status);
//                  if (checkStatus) {
//                    System.out.println("orderCusStatusgjsdbgisbigsigaisdog" + orderCus.getStatus());
//                    try {
//                      session.setAttribute("Orderscustomer", orderCus);
//                      session.getAttribute("shoppingCart");
//                      session.removeAttribute("shoppingCart");
//                      paymentJpaCtrl.edit(payment);
//                      System.out.println("testtttt----");
//                      orderCusJpaCtrl.edit(ordersCustomer);
//                      response.sendRedirect("Receipt.jsp");
//                      return;
//                    } catch (RollbackFailureException ex) {
//                      Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (Exception ex) {
//                      Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                  } else {
//                    session.setAttribute("messagePayment", "Unsucessful Pay!!!");
//                  }
//                }
//              } else {
//                session.setAttribute("messagePayment", "CVV Wrong!!");
//              }
//            } else {
//              session.setAttribute("messagePayment", "EXP Wrong!!");
//            }
//          } else {
//            session.setAttribute("messagePayment", "Card Number Wrong!!");
//          }
//        } else {
//          session.setAttribute("messagePayment", "Card Holder Wrong!!");
//        }
//      } else {
//        session.setAttribute("messagePayment", "Card Number Wrong!!");
//      }
//      getServletContext().getRequestDispatcher("/Payment.jsp").forward(request, response);
//    } else {
//      getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
//    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
