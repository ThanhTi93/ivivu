/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.DaoAttractions;
import Dao.DaoTourPackages;
import Dao.TourScheduleDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Attraction;
import model.TourPackages;
import model.TourSchedule;


/**
 *
 * @author titranthanh
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet({"/tourschedule", "/addtourschedule", "/edittourschedule"})
public class TourScheduleController extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/addtourschedule".equals(path)) {
            DaoTourPackages dao = new DaoTourPackages();
            List<TourPackages> tourPackages = dao.getAllTourPackages();
            DaoAttractions daoAttractions = new DaoAttractions();
            List<Attraction> attractions = daoAttractions.getAllAttraction();
            request.setAttribute("tourPackages", tourPackages);
            request.setAttribute("attractions", attractions);
            
            request.getRequestDispatcher("/WEB-INF/admin/tourschedule/AddTourSchedule.jsp").forward(request, response);
            return;
        }
        
                // Lấy tham số tìm kiếm (nếu có)
        String searchQuery = request.getParameter("search") != null ? request.getParameter("search") : "";

        // Phân trang
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
         TourScheduleDAO scheduleDAO = new TourScheduleDAO();
        // Lấy danh sách location có tìm kiếm
        List<TourSchedule> tourSchedule = scheduleDAO.getTourScheduleByPage(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        int totalRecords = scheduleDAO.getTotalTourSchedules(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        // Gửi dữ liệu tới JSP
        request.setAttribute("tourSchedule", tourSchedule);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/admin/tourschedule/TourSchedule.jsp").forward(request, response);
     }
     
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int dayTour = Integer.parseInt(request.getParameter("dayTour"));
        int idTour = Integer.parseInt(request.getParameter("locationId"));
        String[] selectedAttractions = request.getParameterValues("selectedAttractions");
        TourSchedule  tourschedule   = new TourSchedule(name,dayTour,description,idTour,true);
        TourScheduleDAO scheduleDAO = new TourScheduleDAO();
        
         try {
             scheduleDAO.addTourSchedule(tourschedule, selectedAttractions);
         } catch (SQLException ex) {
             Logger.getLogger(TourScheduleController.class.getName()).log(Level.SEVERE, null, ex);
         }
        response.sendRedirect("schedulelist");
    }
}
