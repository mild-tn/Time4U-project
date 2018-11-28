package servlet;

import controller.CustomerJpaController;
import controller.OrderDetailJpaController;
import controller.OrdersCustomerJpaController;
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
import model.OrderDetail;
import model.OrderDetailPK;
import model.OrdersCustomer;

/**
 *
 * @author Mild-TN
 */
public class CheckoutServlet extends HttpServlet {

    @PersistenceUnit(unitName = "ProjectTimeto4UPU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        LineItem lineItems = (LineItem) session.getAttribute("line");
        System.out.println("product  " + lineItems.getProduct().getProductcode());
        OrdersCustomerJpaController ordercustomerJpaController = new OrdersCustomerJpaController(utx, emf);

        CustomerJpaController customerJpaController = new CustomerJpaController(utx, emf);
        Customer customer = customerJpaController.findCustomer(account.getAccountId());

        OrderDetailJpaController detailJpaController = new OrderDetailJpaController(utx, emf);
        OrdersCustomer orderCustomer = new OrdersCustomer(new Date(), null, "null", lineItems.getQuantity(), customer);

        System.out.println("Customer" + customer);

        if (account.getAccountId() != null) {
            try {
                ordercustomerJpaController.create(orderCustomer);
                OrderDetailPK detailPK = new OrderDetailPK(lineItems.getProduct().getProductcode());
                OrderDetail orderDetail = new OrderDetail(lineItems.getQuantity(), orderCustomer, lineItems.getProduct());
                System.out.println("--" + orderCustomer.getOrdernumber() + "--" + lineItems.getProduct().getProductcode() + "Dettail " + lineItems.getQuantity() + " " + detailPK);
                detailJpaController.create(orderDetail);
                session.setAttribute("oderCustomer", orderCustomer);
                getServletContext().getRequestDispatcher("/Payment.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
            session.setAttribute("error", "กรุณา Login");
        }
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
