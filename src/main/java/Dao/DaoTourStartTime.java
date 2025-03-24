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
import model.TourStartTime;

/**
 *
 * @author titranthanh
 */
public class DaoTourStartTime {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
public void addTourStartTime(TourStartTime tourStartTime) {
    String sql = "INSERT INTO tour_start_time (date_start, id_tour_packages, price, quantity, discount, status) VALUES (?, ?, ?, ?, ?, ?)";

    try {
        conn = new BDworkben().getConnect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(tourStartTime.getDateStart()));
            stmt.setInt(2, tourStartTime.getId_tour());
            stmt.setInt(3, tourStartTime.getPrice());
            stmt.setInt(4, tourStartTime.getQuantity());
            stmt.setInt(5, tourStartTime.getDiscount());
            stmt.setBoolean(6, tourStartTime.isStatus()); // Thêm status vào SQL

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm lịch khởi hành thành công!");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


}
