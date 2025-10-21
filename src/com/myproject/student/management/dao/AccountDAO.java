package com.myproject.student.management.dao;

import com.myproject.student.management.model.Accounts; // Import lớp model User của bạn
import com.myproject.student.management.util.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    
    public Accounts checkLogin(String username, String password) {
        // Câu lệnh SQL để tìm tài khoản khớp với username và password
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        
        // Sử dụng try-with-resources để đảm bảo tài nguyên được đóng đúng cách
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Gán giá trị cho các tham số '?' trong câu lệnh SQL
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Lưu ý: trong thực tế, mật khẩu nên được băm (hashed)

            // Thực thi câu lệnh
            try (ResultSet rs = pstmt.executeQuery()) {
                // Nếu tìm thấy một dòng kết quả
                if (rs.next()) {
                    // Tạo một đối tượng User từ dữ liệu lấy được
                    Accounts user = new Accounts();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    
                    // Trả về đối tượng user, báo hiệu đăng nhập thành công
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Nếu không tìm thấy hoặc có lỗi, trả về null
        return null;
    }
}