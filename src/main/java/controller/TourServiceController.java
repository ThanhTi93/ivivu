package controller;

import Dao.DaoTourService;
import model.TourService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/tourservice", "/addtourservice", "/edittourservice","/deletetourservice"})
public class TourServiceController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        DaoTourService dao = new DaoTourService(); // Tạo đối tượng DAO

        if ("/addtourservice".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/admin/tourservice/AddTourService.jsp").forward(request, response);
            return;
        }

        if ("/edittourservice".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            TourService service = dao.getTourServiceById(id);
            request.setAttribute("tourservice", service);
            request.getRequestDispatcher("/WEB-INF/admin/tourservice/EditTourService.jsp").forward(request, response);
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

        // Lấy danh sách tour services có tìm kiếm
        List<TourService> tourServices = dao.getTourServicesByPage(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        int totalRecords = dao.getTotalTourServices(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        // Gửi dữ liệu tới JSP
        request.setAttribute("tourservices", tourServices);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/admin/tourservice/TourService.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        DaoTourService dao = new DaoTourService();

        if ("/addtourservice".equals(path)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            TourService service = new TourService(name, description,true);
            dao.addTourService(service);

            response.sendRedirect("tourservice");
            return;
        }

        if ("/edittourservice".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            dao.updateTourService(new TourService(id, name, description,true));
            response.sendRedirect("tourservice");
            return;
        }
        
        if ("/deletetourservice".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteTourService(id);
            response.sendRedirect("tourservice");
        }
    }
}
