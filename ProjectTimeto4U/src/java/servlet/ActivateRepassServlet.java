/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.RegisterJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Register;

/**
 *
 * @author Mild-TN
 */
public class ActivateRepassServlet extends HttpServlet {

  @PersistenceUnit(unitName = "ProjectTimeto4UPU")
  EntityManagerFactory emf;
  @Resource
  UserTransaction utx;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    String email = request.getParameter("email");
    session.setAttribute("email", email);
    String activateKey = request.getParameter("activatekey");
    boolean isActivated = false;
    if (session != null) {
      if (email != null && activateKey != null && activateKey.length() > 0) {
        RegisterJpaController regJpaCtrl = new RegisterJpaController(utx, emf);
        Register register = regJpaCtrl.findByEmail(email);
        if (activateKey.equals(register.getActivatekey())) {
          isActivated = true;
          request.setAttribute("isActivated", isActivated);
          session.setAttribute("email", email);
          getServletContext().getRequestDispatcher("/Resetpassword.jsp").forward(request, response);
          return;
        } else {
          request.setAttribute("MsActivate", "Invalid Activate");
          getServletContext().getRequestDispatcher("/ActivateRepass.jsp").forward(request, response);

        }

      }
    }
    getServletContext().getRequestDispatcher("/Resetpassword.jsp").forward(request, response);
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
