package controller;

import Dao.DaoTourTransportation;
import config.CloudinaryUtils;
import model.TourTransportation;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet({"/tourtransportation", "/addtourtransportation", "/edittourtransportation", "/deletetourtransportation"})
public class TourTransportationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        DaoTourTransportation dao = new DaoTourTransportation();

        if ("/addtourtransportation".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/admin/tourtransportation/AddTourTransportation.jsp").forward(request, response);
            return;
        }

        if ("/edittourtransportation".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            TourTransportation transportation = dao.getTourTransportById(id);
            request.setAttribute("transportation", transportation);
            request.getRequestDispatcher("/WEB-INF/admin/tourtransportation/EditTourTransportation.jsp").forward(request, response);
            return;
        }

        String searchQuery = request.getParameter("search") != null ? request.getParameter("search") : "";
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<TourTransportation> transportations = dao.getTourTransportsByPage(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        int totalRecords = dao.getTotalTourTransports(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("transportations", transportations);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/admin/tourtransportation/TourTransportation.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
      DaoTourTransportation dao = new DaoTourTransportation();

        if ("/addtourtransportation".equals(path)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String iconUrl = request.getParameter("icon");

            TourTransportation transportation = new TourTransportation(name, description, iconUrl,true);
            dao.addTourTransport(transportation);

            response.sendRedirect("tourtransportation");
            return;
        }

        if ("/edittourtransportation".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Part filePart = request.getPart("icon");

            String iconUrl = null;
            if (filePart != null && filePart.getSize() > 0) {
                iconUrl = CloudinaryUtils.uploadImage(filePart);
            }

            dao.updateTourTransport(new TourTransportation(id, name, description, iconUrl,true));
            response.sendRedirect("tourtransportation");
        }

        if ("/deletetourtransportation".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteTourTransport(id);
            response.sendRedirect("tourtransportation");
        }
    }
}
