/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import connect.BDworkben;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.PackageTransportation;

/**
 *
 * @author titranthanh
 */
public class DaoPackageTransportation {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public void addPackageTransportation(PackageTransportation packageTransportation) {
        String sql = "INSERT INTO package_transportation (id_tour_packages,id_tour_transportation) VALUES (?, ?)";
        conn = new BDworkben().getConnect();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, packageTransportation.getIdTourPackages());
            stmt.setInt(2, packageTransportation.getIdTourTransportation());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
