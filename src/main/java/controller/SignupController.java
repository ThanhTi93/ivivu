/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.DaoAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author titranthanh
 */
@WebServlet({"/signup"})
public class SignupController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/client/auth/Signup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu không trùng khớp!");
            request.getRequestDispatcher("/WEB-INF/client/auth/Signup.jsp").forward(request, response);
            return;
        }

        DaoAccount userDAO = new DaoAccount();
        boolean isRegistered = userDAO.isAccountExist(username, email);

        if (isRegistered) {
            request.setAttribute("error", "Email hoặc username đã tồn tại!");
            request.getRequestDispatcher("/WEB-INF/client/auth/Signup.jsp").forward(request, response);
            return;
        }
        userDAO.singup(username, password, email);
        
    }
}
