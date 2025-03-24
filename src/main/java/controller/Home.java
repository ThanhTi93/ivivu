package controller;

import Dao.DaoTourPackages;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Home extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        DaoTourPackages daoTourPackages = new DaoTourPackages();

        // Lấy danh sách tour và ảnh
        List<Map<String, Object>> toursWithImages = daoTourPackages.getAllToursWithImages();

        // Truyền dữ liệu sang JSP
        request.setAttribute("toursWithImages", toursWithImages);

        // Chuyển hướng tới trang Home.jsp
        request.getRequestDispatcher("/WEB-INF/client/home/Home.jsp").forward(request, response);
    }
}
