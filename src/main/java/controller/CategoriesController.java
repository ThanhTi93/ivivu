package controller;

import Dao.DaoCategories;
import model.Category;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/category", "/addcategory", "/editcategory", "/deletecategory"})
public class CategoriesController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        DaoCategories dao = new DaoCategories();

        if ("/addcategory".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/admin/categories/AddCategory.jsp").forward(request, response);
            return;
        }

        if ("/editcategory".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = dao.getCategoryById(id);
            request.setAttribute("category", category);
            request.getRequestDispatcher("/WEB-INF/admin/categories/EditCategory.jsp").forward(request, response);
            return;
        }

        String searchQuery = request.getParameter("search") != null ? request.getParameter("search") : "";

        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Category> categories = dao.getCategoriesByPage(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        int totalRecords = dao.getTotalCategories(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("categories", categories);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/admin/categories/Category.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        DaoCategories dao = new DaoCategories();

        if ("/addcategory".equals(path)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Category category = new Category(name, description, true);

            dao.addCategory(category);

            response.sendRedirect("category");
            return;
        }

        if ("/editcategory".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            dao.updateCategory(new Category(id, name, description,true));
            response.sendRedirect("category");
            return;
        }

        if ("/deletecategory".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteCategory(id);
            response.sendRedirect("category");
        }
    }
}
