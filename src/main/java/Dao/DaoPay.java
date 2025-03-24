/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import connect.BDworkben;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Paypal;

/**
 *
 * @author titranthanh
 */
public class DaoPay {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
     public void addLocation(Paypal paypal) {
        String sql = "INSERT INTO paypal (price, quantity , phone) VALUES (?, ?, ?)";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paypal.getPrice());
            stmt.setInt(2, paypal.getQuantity());
            stmt.setString(3, paypal.getPhone());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
