/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AccountJpaController;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.RollbackFailureException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

/**
 *
 * @author Mild-TN
 */
public class ResetpassServlet extends HttpServlet {

   @PersistenceUnit(unitName = "ProjectTimeto4UPU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;
    
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    String email = request.getParameter("email");
    String password = request.getParameter("pass");
    String conPass = request.getParameter("confirmpass");
    if (session != null) {
      if (email != null && email.length() > 0 && password != null && password.length() > 0 && conPass != null && conPass.length() > 0) {
        AccountJpaController accountJpaCtrl = new AccountJpaController(utx, emf);
        Account ac = accountJpaCtrl.findByEmail(email);
        if (password.equals(conPass)) {
          password = cryptWithMD5(conPass);
          String checkPass = ac.checkPass(password);
          System.out.println("---------------" + ac + checkPass);
          try {
            accountJpaCtrl.edit(ac);
            session.setAttribute("NewPass", ac);
            System.out.println("/////////////////////" + ac);
            getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
          } catch (NonexistentEntityException ex) {
            Logger.getLogger(ResetpassServlet.class.getName()).log(Level.SEVERE, null, ex);
          } catch (RollbackFailureException ex) {
            Logger.getLogger(ResetpassServlet.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
            Logger.getLogger(ResetpassServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else {
          session.setAttribute("messageError", "Invalid password");
        }

        session.setAttribute("messageError", "Something Wrong,please try again!!");

      }
    }
    getServletContext().getRequestDispatcher("/Resetpassword.jsp").forward(request, response);
  }

  public static String cryptWithMD5(String pass) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] passBytes = pass.getBytes();
      md.reset();
      byte[] digested = md.digest(passBytes);
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < digested.length; i++) {
        sb.append(Integer.toHexString(0xff & digested[i]));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
      System.out.println(ex);
    }
    return null;
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
