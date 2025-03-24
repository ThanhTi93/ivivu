/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.DaoCategories;
import Dao.DaoTourPackages;
import Dao.DaoTourStartTime;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TourPackages;
import model.TourStartTime;

/**
 *
 * @author titranthanh
 */
@WebServlet({"/tourstarttime", "/addtourstarttime", "/edittourstarttime", "/deletetourstarttime"})
public class TourStartTimeController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        DaoTourPackages dao = new DaoTourPackages();

        if ("/addtourstarttime".equals(path)) {
            List<TourPackages> tourPackages = dao.getAllTourPackages();
            request.setAttribute("tourPackages", tourPackages);
            request.getRequestDispatcher("/WEB-INF/admin/tourstarttime/AddTourStartTime.jsp").forward(request, response);
            return;
        }

    }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // Lấy dữ liệu từ form
        String dateStr = request.getParameter("date");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        int tourId = Integer.parseInt(request.getParameter("categoryId"));

        System.out.println("Date từ form: " + dateStr);

        // Kiểm tra và định dạng ngày tháng
        LocalDate dateStart = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateStart = LocalDate.parse(dateStr, formatter);

            System.out.println("Date sau khi parse: " + dateStart);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi định dạng ngày tháng!");
            return;
        }

        // Mặc định status là true
        boolean status = true;

        // Tạo đối tượng TourStartTime
        TourStartTime tourStartTime = new TourStartTime(dateStart, tourId, price, quantity, discount, status);

        System.out.println("TourStartTime object: " + tourStartTime);

        // Thêm vào DB
        DaoTourStartTime tourStartTimeDAO = new DaoTourStartTime();
        tourStartTimeDAO.addTourStartTime(tourStartTime);

        // Chuyển hướng về trang quản lý
        response.sendRedirect("managetours");
    } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().println("Lỗi thêm lịch khởi hành: " + e.getMessage());
    }
}


}
