/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.Dao;
import Dao.DaoTourPackages;
import Dao.DaoTourService;
import Dao.DaoTourTransportation;
import config.CloudinaryUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Location;
import model.PackageService;
import model.TourImg;
import model.TourPackages;
import model.TourService;
import model.TourTransportation;

/**
 *
 * @author titranthanh
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet({"/tourpackages", "/addtourpackages", "/edittourpackages"})
public class TourPackagesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        DaoTourPackages daoTourPackages = new DaoTourPackages();
        if ("/addtourpackages".equals(path)) {
            Dao dao = new Dao();
            List<Location> locations = dao.getAllLocation();
            DaoTourService daoService = new DaoTourService();
            List<TourService> listTourService = daoService.getAllTourServices();
            DaoTourTransportation daoTransportation = new DaoTourTransportation();
            List<TourTransportation> listTransportation = daoTransportation.getAllTourServices();
            request.setAttribute("listTourService", listTourService);
            request.setAttribute("locations", locations);
            request.setAttribute("listTransportation", listTransportation);
            request.getRequestDispatcher("/WEB-INF/admin/tourpackages/AddTourPackages.jsp").forward(request, response);
            return;
        }
        
        if ("/edittourpackages".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            TourPackages tourPackage = daoTourPackages.getTourPackagesById(id);
            List<Location> locations = new Dao().getAllLocation();
            List<TourService> listTourService = new DaoTourService().getAllTourServices();
            List<TourTransportation> listTransportation = new DaoTourTransportation().getAllTourServices();
            List<TourImg> images = daoTourPackages.getAllTourImgByPackages(id);
            List<PackageService>  listPackageService = daoTourPackages.getAllPackageServiceByPackages(id);
            
            request.setAttribute("listPackageService", listPackageService);
            request.setAttribute("tourPackage", tourPackage);
            request.setAttribute("locations", locations);
            request.setAttribute("listTourService", listTourService);
            request.setAttribute("listTransportation", listTransportation);
            request.setAttribute("images", images);
            request.getRequestDispatcher("/WEB-INF/admin/tourpackages/EditTourPackages.jsp").forward(request, response);
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
        List<TourPackages> tourpackages = daoTourPackages.getTourPackagesByPage(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        int totalRecords = daoTourPackages.getTotalLocations(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        // Gửi dữ liệu tới JSP
        request.setAttribute("tourpackages", tourpackages);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/admin/tourpackages/TourPackages.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        DaoTourPackages dao = new DaoTourPackages();
        if ("/addtourpackages".equals(path)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int locationId = Integer.parseInt(request.getParameter("locationId"));
            String[] selectedServices = request.getParameterValues("tourService[]");
            String[] selectedTransportations = request.getParameterValues("tourTransportation[]");
            Part[] fileParts = request.getParts().stream()
                    .filter(part -> "images".equals(part.getName()) && part.getSize() > 0)
                    .toArray(Part[]::new);
            System.out.println(fileParts);
            List<String> imageUrls = new ArrayList<>();

            for (Part filePart : fileParts) {
                String imageUrl = CloudinaryUtils.uploadImage(filePart);
                imageUrls.add(imageUrl);
            }

            TourPackages tourpackages = new TourPackages(name, description, locationId);
            dao.addTourPackages(tourpackages, selectedServices, selectedTransportations, imageUrls);

        }
    }
}
