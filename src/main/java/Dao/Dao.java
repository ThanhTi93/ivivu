/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import connect.BDworkben;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Location;

/**
 *
 * @author titranthanh
 */
public class Dao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Location> getLocationsByPage(String searchQuery, int start, int total) {
        List<Location> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn SQL có tìm kiếm
            String query = "SELECT * FROM location WHERE (name LIKE ? OR description LIKE ?) AND status = 1 LIMIT ?, ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, "%" + searchQuery + "%"); // Tìm kiếm theo name
            ps.setString(2, "%" + searchQuery + "%"); // Tìm kiếm theo description
            ps.setInt(3, start);
            ps.setInt(4, total);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Location(
                        rs.getInt("id"),
                        rs.getString("img_url"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("id_category"),
                        rs.getInt("status") == 1
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
            String query = "SELECT COUNT(*) FROM location WHERE name LIKE ? OR description LIKE ?";
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

    public void addLocation(Location location) {
        String sql = "INSERT INTO location (name, description, img_url,id_category) VALUES (?, ?, ?,?)";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, location.getName());
            stmt.setString(2, location.getDescription());
            stmt.setString(3, location.getImgUrl());
            stmt.setInt(4, location.getCategory_id());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Location> getAllLocation() {
        List<Location> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM location";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Location(
                        rs.getInt("id"),
                        rs.getString("img_url"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("id_category"),
                        rs.getInt("status") == 1));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Location getLocationById(int id) {
        Location location = null;
        String sql = "SELECT * FROM location WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                location = new Location(
                        rs.getInt("id"),
                        rs.getString("img_url"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("id_category"),
                        rs.getInt("status") == 1
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public void updateLocation(Location location) {
        String sql = "UPDATE location SET  img_url = ?,  name = ?, description = ?, id_category = ? WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, location.getImgUrl());
            stmt.setString(2, location.getName());
            stmt.setString(3, location.getDescription());
            stmt.setInt(4, location.getCategory_id());
            stmt.setInt(5, location.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteLocation(int id) {
        String sql = "UPDATE location SET status = 0 WHERE id = ?";
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
