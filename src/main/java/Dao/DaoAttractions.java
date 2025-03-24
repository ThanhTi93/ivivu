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
import model.Attraction;
import model.Location;

/**
 *
 * @author titranthanh
 */
public class DaoAttractions {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Attraction> getAttractionsByPage(String searchQuery, int start, int total) {
        List<Attraction> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn SQL có tìm kiếm
            String query = "SELECT * FROM attractions WHERE name LIKE ? OR description LIKE ? LIMIT ?, ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, "%" + searchQuery + "%"); // Tìm kiếm theo name
            ps.setString(2, "%" + searchQuery + "%"); // Tìm kiếm theo description
            ps.setInt(3, start);
            ps.setInt(4, total);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Attraction(
                        rs.getInt("id"),
                        rs.getString("img_url"),
                        rs.getString("name"),
                         rs.getString("description")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalAttractions(String searchQuery) {
        int total = 0;
        try {
            conn = new BDworkben().getConnect();

            // Truy vấn SQL có tìm kiếm
            String query = "SELECT COUNT(*) FROM attractions WHERE name LIKE ? OR description LIKE ?";
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

    public void addAttraction(Attraction attraction) {
        String sql = "INSERT INTO attractions (name, description, img_url) VALUES (?, ?, ?)";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attraction.getName());
            stmt.setString(2, attraction.getDescription());
            stmt.setString(3, attraction.getImgUrl());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Attraction> getAllAttraction() {
        List<Attraction> list = new ArrayList<>();
        try {
            conn = new BDworkben().getConnect();
            String query = "SELECT * FROM attractions";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Attraction(rs.getInt("id"),
                        rs.getString("img_url"),
                        rs.getString("name"),
                         rs.getString("description")));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Attraction getAttractionById(int id) {
        Attraction attraction = null;
        String sql = "SELECT * FROM attractions WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                attraction = new Attraction(
                        rs.getInt("id"),
                        rs.getString("img_url"),
                        rs.getString("name"),
                        rs.getString("description")
                                     
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attraction;
    }

    public void updateAttraction(Attraction attraction) {
        String sql = "UPDATE attractions SET  img_url = ?,  name = ?, description = ? WHERE id = ?";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attraction.getImgUrl());
            stmt.setString(2, attraction.getName());
            stmt.setString(3, attraction.getDescription());
            stmt.setInt(4, attraction.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAttraction(int id) {
        String sql = "DELETE FROM attractions WHERE id = ?";
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
