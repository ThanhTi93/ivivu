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
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author titranthanh
 */
@WebServlet({"/login"})
public class LoginController extends HttpServlet{
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/client/auth/Login.jsp").forward(request, response);
    }
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailOrUsername = request.getParameter("emailOrUsername");
        String password = request.getParameter("password");

        DaoAccount userDAO = new DaoAccount();
        Account account = userDAO.login(emailOrUsername, password);

        if (account != null) {
            // Đăng nhập thành công, tạo session
            HttpSession session = request.getSession();
            session.setAttribute("user", account);

            response.sendRedirect("home"); // Chuyển hướng về trang chủ
        } else {
            // Đăng nhập thất bại, báo lỗi
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("/WEB-INF/client/auth/Login.jsp").forward(request, response);
        }
    }
}
