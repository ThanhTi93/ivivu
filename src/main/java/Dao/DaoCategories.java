package Dao;

import connect.BDworkben;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class DaoCategories {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> getCategoriesByPage(String searchQuery, int start, int total) {
        List<Category> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();

            String query = "SELECT * FROM categories WHERE (name LIKE ? OR description LIKE ?) AND status = 1 LIMIT ?, ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");
            ps.setInt(3, start);
            ps.setInt(4, total);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("status") == 1
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalCategories(String searchQuery) {
        int total = 0;
        try {
            conn = new BDworkben().getConnect();

            String query = "SELECT COUNT(*) FROM categories WHERE name LIKE ? OR description LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public void addCategory(Category category) {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM categories WHERE status = 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("status") == 1));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Category getCategoryById(int id) {
        Category category = null;
        String sql = "SELECT * FROM categories WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("status") == 1
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

    public void updateCategory(Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(int id) {
        String sql = "UPDATE categories SET status = 0 WHERE id = ?";
        try {
            conn = new BDworkben().getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
