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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.PackageService;
import model.TourImg;
import model.TourPackages;
import model.TourSchedule;

/**
 *
 * @author titranthanh
 */
public class DaoTourPackages {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<TourPackages> getTourPackagesByPage(String searchQuery, int start, int total) {
        List<TourPackages> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn SQL có tìm kiếm
            String query = "SELECT * FROM tour_packages WHERE name LIKE ? OR description LIKE ? LIMIT ?, ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, "%" + searchQuery + "%"); // Tìm kiếm theo name
            ps.setString(2, "%" + searchQuery + "%"); // Tìm kiếm theo description
            ps.setInt(3, start);
            ps.setInt(4, total);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourPackages(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("location_id")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalLocations(String searchQuery) {
        int total = 0;
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn SQL có tìm kiếm
            String query = "SELECT COUNT(*) FROM tour_packages WHERE name LIKE ? OR description LIKE ?";
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
    public void addTourPackages(TourPackages tourpackages, String[] services, String[] transportations, List<String> imageUrls) {
        int tourId = -1;
        String sql = "INSERT INTO tour_packages (name, description, location_id) VALUES (?, ?, ?)";

        try {
            conn = new BDworkben().getConnect();
            conn.setAutoCommit(false); // Bắt đầu transaction
            // Thêm tour_packages và lấy ID
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, tourpackages.getName());
                stmt.setString(2, tourpackages.getDescription());
                stmt.setInt(3, tourpackages.getLocation_id());
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
                if (services != null && services.length > 0) {
                    String serviceSQL = "INSERT INTO package_service (id_tour_packages, id_tour_service) VALUES (?, ?)";
                    try (PreparedStatement serviceStmt = conn.prepareStatement(serviceSQL)) {
                        for (String serviceId : services) {
                            serviceStmt.setInt(1, tourId);
                            serviceStmt.setInt(2, Integer.parseInt(serviceId));
                            serviceStmt.executeUpdate();
                        }
                       
                    }
                }

                // Thêm vào bảng package_transportation nếu có phương tiện chọn
                if (transportations != null && transportations.length > 0) {
                    String transportSQL = "INSERT INTO package_transportation (id_tour_packages, id_tour_transportation) VALUES (?, ?)";
                    try (PreparedStatement transportStmt = conn.prepareStatement(transportSQL)) {
                        for (String transportId : transportations) {
                            transportStmt.setInt(1, tourId);
                            transportStmt.setInt(2, Integer.parseInt(transportId));
                            transportStmt.addBatch();  // Gộp batch để tối ưu
                        }
                        transportStmt.executeBatch();
                    }
                }
                if (imageUrls != null && imageUrls.size() > 0) {
                    String transportSQL = "INSERT INTO tour_img (id_tour_packages, img_url) VALUES (?, ?)";
                    try (PreparedStatement transportStmt = conn.prepareStatement(transportSQL)) {
                        for (String imageUrl : imageUrls) {
                            transportStmt.setInt(1, tourId);
                            transportStmt.setString(2, imageUrl);
                            transportStmt.addBatch();  // Gộp batch để tối ưu
                        }
                        transportStmt.executeBatch();
                    }
                }
            }

            // Commit nếu không lỗi
            conn.commit();
            System.out.println("✅ Thêm tour và bảng trung gian thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback nếu có lỗi
                }
                System.out.println("⛔ Rollback dữ liệu vì lỗi xảy ra!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<TourPackages> getAllTourPackages() {
        List<TourPackages> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM tour_packages";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourPackages(rs.getInt("id"),rs.getString("name"), rs.getString("description"), rs.getInt("location_id") ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
       public TourPackages getTourPackagesById(int id) {
        TourPackages tourPackages = null;
        String sql = "SELECT * FROM tour_packages WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tourPackages = new TourPackages(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("location_id")                                
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tourPackages;
    }
   
    public List<TourImg> getAllTourImgByPackages(int id) {
        List<TourImg> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM tour_img WHERE id_tour_packages = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourImg(rs.getInt("id"),rs.getInt("id_tour_packages"), rs.getString("img_url") ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<PackageService> getAllPackageServiceByPackages(int id) {
        List<PackageService> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM package_service WHERE id_tour_packages = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PackageService(rs.getInt("id"),rs.getInt("id_tour_packages"), rs.getInt("id_tour_service") ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
public List<Map<String, Object>> getAllToursWithImages() {
    List<Map<String, Object>> tours = new ArrayList<>();
    String query = """
        SELECT tp.*, ti.img_url, l.name as namelocation
        FROM tour_packages tp 
        LEFT JOIN tour_img ti ON tp.id = ti.id_tour_packages
        LEFT JOIN location l ON tp.location_id = l.id
    """;
    try {
        conn = new BDworkben().getConnect();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> tourData = new HashMap<>();
            tourData.put("id", rs.getInt("id"));
            tourData.put("name", rs.getString("name"));
            tourData.put("description", rs.getString("description"));
            tourData.put("namelocation", rs.getString("namelocation")); // Lấy tên location
            tourData.put("img_url", rs.getString("img_url"));
            tours.add(tourData);
        }
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return tours;
}
 
 

}
