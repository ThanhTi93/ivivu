/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import connect.BDworkben;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author titranthanh
 */
public class DaoAccount {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void singup(String user, String pass, String email) {
        String query = "INSERT INTO account(username , pass, email  )VALUES (?,?,?);";
        try {
            conn = new BDworkben().getConnect();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // Kiểm tra username hoặc email đã tồn tại chưa
    public boolean isAccountExist(String username, String email) {
        String query = "SELECT * FROM account WHERE username = ? OR email = ?";
        try {
            conn = new BDworkben().getConnect();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();

            // Nếu có kết quả => tài khoản đã tồn tại
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false; // Có lỗi xảy ra hoặc tài khoản không tồn tại
    }
 public Account login(String emailOrUsername, String password) {
        String query = "SELECT * FROM account WHERE (email = ? OR username = ?) AND pass = ?";
        try {
            conn = new BDworkben().getConnect();
            ps = conn.prepareStatement(query);
            ps.setString(1, emailOrUsername);
            ps.setString(2, emailOrUsername);
            ps.setString(3, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("imgUrl"),
                        rs.getString("pass"),
                        rs.getString("phone"),
                        rs.getString("roler"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
