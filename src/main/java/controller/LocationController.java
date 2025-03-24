package controller;

import Dao.Dao;
import Dao.DaoCategories;
import config.CloudinaryUtils;
import model.Location;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Category;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet({"/location", "/addlocation", "/editlocation", "/deletelocation"})
public class LocationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        Dao dao = new Dao(); // Tạo đối tượng DAO để làm việc với CSDL
        DaoCategories daoCategory = new DaoCategories();
        List<Category> listCategory = daoCategory.getAllCategories();
        if ("/addlocation".equals(path)) {

            request.setAttribute("listCategory", listCategory);
            request.getRequestDispatcher("/WEB-INF/admin/locations/AddLocation.jsp").forward(request, response);
            return;
        }

        if ("/editlocation".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Location location = dao.getLocationById(id);
            request.setAttribute("location", location);
            request.setAttribute("listCategory", listCategory);
            request.getRequestDispatcher("/WEB-INF/admin/locations/EditLocation.jsp").forward(request, response);
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
        List<Location> locations = dao.getLocationsByPage(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        int totalRecords = dao.getTotalLocations(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        // Gửi dữ liệu tới JSP
        request.setAttribute("locations", locations);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/admin/locations/Location.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        Dao dao = new Dao();

        if ("/addlocation".equals(path)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Part filePart = request.getPart("image");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String imgUrl = "";
            if (filePart != null && filePart.getSize() > 0) {
                imgUrl = CloudinaryUtils.uploadImage(filePart);
            }

            Location location = new Location(imgUrl, name, description, categoryId, true);
            dao.addLocation(location);

            response.sendRedirect("location");
            return;
        }

        if ("/editlocation".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Part filePart = request.getPart("image");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            // Upload ảnh mới lên Cloudinary nếu có
            String imgUrl = null;
            if (filePart != null && filePart.getSize() > 0) {
                imgUrl = CloudinaryUtils.uploadImage(filePart);
            }

            dao.updateLocation(new Location(id,imgUrl, name,description, categoryId, true));
            response.sendRedirect("location");
            return;
        }
        if ("/deletelocation".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteLocation(id);
            response.sendRedirect("location");
        }
    }

}
