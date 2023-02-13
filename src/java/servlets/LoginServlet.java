/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author nguye
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.User;
import models.AccountService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String logout = request.getParameter("logout");
    if (logout != null) {
      session.invalidate();
      request.setAttribute("message", "You have successfully logged out");
    }
    request.getRequestDispatcher("/Login.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      request.setAttribute("message", "Username and password cannot be empty");
      request.getRequestDispatcher("/Login.jsp").forward(request, response);
      return;
    }

    AccountService service = new AccountService();
    User user = service.login(username, password);
    if (user == null) {
      request.setAttribute("message", "Invalid username or password");
      request.getRequestDispatcher("/Login.jsp").forward(request, response);
      return;
    }

    HttpSession session = request.getSession();
    session.setAttribute("username", username);
    response.sendRedirect(request.getContextPath() + "/Home");
  }

}

