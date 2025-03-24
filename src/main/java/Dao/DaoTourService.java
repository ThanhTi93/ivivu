package Dao;

import connect.BDworkben;
import model.TourService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoTourService {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy danh sách TourService có phân trang và tìm kiếm
    public List<TourService> getTourServicesByPage(String searchQuery, int start, int total) {
        List<TourService> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM tour_service WHERE (name LIKE ? OR description LIKE ?) AND status = 1 LIMIT ?, ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");
            ps.setInt(3, start);
            ps.setInt(4, total);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourService(
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

    // Lấy tổng số TourService theo từ khóa tìm kiếm
    public int getTotalTourServices(String searchQuery) {
        int total = 0;
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT COUNT(*) FROM tour_service WHERE name LIKE ? OR description LIKE ?";
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

    // Thêm TourService
    public void addTourService(TourService service) {
        String sql = "INSERT INTO tour_service (name, description) VALUES (?, ?)";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, service.getName());
            stmt.setString(2, service.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy tất cả TourService
    public List<TourService> getAllTourServices() {
        List<TourService> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM tour_service WHERE status = 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourService(
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

    // Lấy thông tin TourService theo ID
    public TourService getTourServiceById(int id) {
        TourService service = null;
        String sql = "SELECT * FROM tour_service WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                service = new TourService(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("status") == 1
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    // Cập nhật TourService
    public void updateTourService(TourService service) {
        conn = new BDworkben().getConnect();
        String sql = "UPDATE tour_service SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, service.getName());
            stmt.setString(2, service.getDescription());
            stmt.setInt(3, service.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa TourService theo ID
    public void deleteTourService(int id) {
        String sql = "UPDATE tour_service SET status = 0 WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
