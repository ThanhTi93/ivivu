package Dao;

import connect.BDworkben;
import model.TourTransportation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoTourTransportation {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<TourTransportation> getTourTransportsByPage(String searchQuery, int start, int total) {
        List<TourTransportation> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM tour_transportation WHERE (name LIKE ? OR description LIKE ?) AND status = 1 LIMIT ?, ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");
            ps.setInt(3, start);
            ps.setInt(4, total);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourTransportation(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("img_logo"),
                        rs.getInt("status") == 1
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalTourTransports(String searchQuery) {
        int total = 0;
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT COUNT(*) FROM tour_transportation WHERE name LIKE ? OR description LIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    // Lấy tất cả TourService
    public List<TourTransportation> getAllTourServices() {
        List<TourTransportation> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM tour_transportation WHERE status = 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TourTransportation(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("img_logo"),
                        rs.getInt("status") == 1
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void addTourTransport(TourTransportation transport) {
        String sql = "INSERT INTO tour_transportation (name, description, img_logo) VALUES (?, ?, ?)";
        try {
            conn = new BDworkben().getConnect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, transport.getName());
            ps.setString(2, transport.getDescription());
            ps.setString(3, transport.getIcon());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TourTransportation getTourTransportById(int id) {
        TourTransportation transport = null;
        String sql = "SELECT * FROM tour_transportation WHERE id = ?";
        try {
            conn = new BDworkben().getConnect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                transport = new TourTransportation(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("icon"),
                        rs.getInt("status") == 1
                );
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transport;
    }

    public void updateTourTransport(TourTransportation transport) {
        String sql = "UPDATE tour_transportation SET name = ?, description = ?, icon = ? WHERE id = ?";
        try {
            conn = new BDworkben().getConnect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, transport.getName());
            ps.setString(2, transport.getDescription());
            ps.setString(3, transport.getIcon());
            ps.setInt(4, transport.getId());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTourTransport(int id) {
        String sql = "UPDATE tour_transportation SET status = 0 WHERE id = ?";
        try {
            conn = new BDworkben().getConnect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
