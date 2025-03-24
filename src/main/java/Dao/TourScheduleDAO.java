/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import connect.BDworkben;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Attraction;
import model.Location;
import model.TourSchedule;

/**
 *
 * @author titranthanh
 */
public class TourScheduleDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
   public List<TourSchedule> getTourScheduleByPage(String searchQuery, int start, int total) {
        List<TourSchedule> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn SQL có tìm kiếm
            String query = "SELECT * FROM tour_schedule WHERE (name LIKE ? OR description LIKE ?) AND status = 1 LIMIT ?, ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, "%" + searchQuery + "%"); // Tìm kiếm theo name
            ps.setString(2, "%" + searchQuery + "%"); // Tìm kiếm theo description
            ps.setInt(3, start);
            ps.setInt(4, total);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourSchedule(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("day"),
                        rs.getString("description"),
                        rs.getInt("id_tour"),
                        rs.getInt("status") == 1
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalTourSchedules(String searchQuery) {
        int total = 0;
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn SQL có tìm kiếm
            String query = "SELECT COUNT(*) FROM tour_schedule WHERE name LIKE ? OR description LIKE ?";
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

    public void addTourSchedule(TourSchedule tourschedule, String[] selectedAttractions) throws SQLException {
        int tourId = -1;
        String sql = "INSERT INTO tour_schedule (day, description,name ,id_tour) VALUES (?, ?, ?,?)";
        conn = new BDworkben().getConnect();
        conn.setAutoCommit(false); // Bắt đầu transaction
        // Thêm tour_packages và lấy ID
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, tourschedule.getDayTour());
            stmt.setString(2, tourschedule.getDescription());
            stmt.setString(3, tourschedule.getName());
            stmt.setInt(4, tourschedule.getId_tour());
            stmt.executeUpdate();

            // Lấy ID tour vừa thêm
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tourId = rs.getInt(1);
                }
            }
        }
            if (tourId > 0) {
                // Thêm vào bảng package_service nếu có dịch vụ chọn
                if (selectedAttractions != null && selectedAttractions.length > 0) {
                    String serviceSQL = "INSERT INTO tour_schedule_attractions (tour_schedule_id , attraction_id ) VALUES (?, ?)";
                    try (PreparedStatement serviceStmt = conn.prepareStatement(serviceSQL)) {
                        for (String attractionId : selectedAttractions) {
                            serviceStmt.setInt(1, tourId);
                            serviceStmt.setInt(2, Integer.parseInt(attractionId));
                            serviceStmt.executeUpdate();
                        }
                       
                    }
                }
                conn.commit();
           
            }
        
    }
    
   // Lấy tất cả lịch trình theo id tour, kèm danh sách attractions
    public List<TourSchedule> getAllTourScheduleByIdTour(int id) {
        List<TourSchedule> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn lấy tất cả lịch trình của tour theo id_tour
            String query = "SELECT * FROM tour_schedule WHERE id_tour = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int scheduleId = rs.getInt("id");
                
                // Lấy danh sách attractions của mỗi tour_schedule
                List<Attraction> attractions = getAttractionsByScheduleId(scheduleId);

                // Tạo đối tượng TourSchedule và truyền danh sách attractions vào
                list.add(new TourSchedule(
                        scheduleId,
                        rs.getString("name"),
                        rs.getInt("day"),
                        rs.getString("description"),
                        rs.getInt("id_tour"),
                        rs.getInt("status") == 1,
                        attractions
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    // Hàm phụ: Lấy danh sách các attraction từ tour_schedule_id
    private List<Attraction> getAttractionsByScheduleId(int scheduleId) {
        List<Attraction> attractions = new ArrayList<>();
        try {
            String query = """
                          SELECT a.*
                           FROM attractions a
                           JOIN tour_schedule_attractions ta ON a.id = ta.attraction_id
                           WHERE ta.tour_schedule_id = ?
                           """;

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, scheduleId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                attractions.add(new Attraction(
                        rs.getInt("id"),
                        rs.getString("img_url"),
                        rs.getString("name"),
                         rs.getString("description")
                ));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attractions;
    }
}
