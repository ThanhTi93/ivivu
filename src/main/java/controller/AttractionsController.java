/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.DaoAttractions;
import config.CloudinaryUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Attraction;
/**
 *
 * @author titranthanh
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet({"/attraction", "/addattraction", "/editattraction","/deleteattraction"})
public class AttractionsController extends HttpServlet {
    
     private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        DaoAttractions dao = new DaoAttractions(); // Tạo đối tượng DAO để làm việc với CSDL

        if ("/addattraction".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/admin/attractions/AddAttraction.jsp").forward(request, response);
            return;
        }

        if ("/editattraction".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Attraction attraction = dao.getAttractionById(id);
            request.setAttribute("attraction", attraction);
            request.getRequestDispatcher("/WEB-INF/admin/attractions/EditAttraction.jsp").forward(request, response);
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

        // Lấy danh sách location có tìm kiếm
        List<Attraction> attractions = dao.getAttractionsByPage(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        int totalRecords = dao.getTotalAttractions(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        // Gửi dữ liệu tới JSP
        request.setAttribute("attractions", attractions);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/admin/attractions/Attraction.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        DaoAttractions dao = new DaoAttractions();

        if ("/addattraction".equals(path)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Part filePart = request.getPart("image");

            String imgUrl = "";
            if (filePart != null && filePart.getSize() > 0) {
                imgUrl = CloudinaryUtils.uploadImage(filePart);
            }

            Attraction attraction = new Attraction(imgUrl, name, description);
            dao.addAttraction(attraction);

            response.sendRedirect("attraction");
            return;
        }

        if ("/editattraction".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Part filePart = request.getPart("image");

            // Upload ảnh mới lên Cloudinary nếu có
            String imgUrl = null;
            if (filePart != null && filePart.getSize() > 0) {
                imgUrl = CloudinaryUtils.uploadImage(filePart);
            }

            dao.updateAttraction(new Attraction(id, description, imgUrl,name));
            response.sendRedirect("attraction");
            return;
        }
        if ("/deleteattraction".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteAttraction(id);
            response.sendRedirect("attraction");
        }
    }
}
