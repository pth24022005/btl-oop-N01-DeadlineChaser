package com.myproject.student.management.dao;

import com.myproject.student.management.model.Relative;
import com.myproject.student.management.util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelativeDAO {
    
    /**
     * Lấy toàn bộ danh sách nhân thân từ cơ sở dữ liệu.
     * @return một List các đối tượng Relative.
     */
    public List<Relative> getAllRelatives() {
        List<Relative> relatives = new ArrayList<>();
        String sql = "SELECT * FROM relatives";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                relatives.add(mapResultSetToRelative(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatives;
    }
    
    /**
     * Tìm một nhân thân dựa vào ID.
     * @param relativeId ID của nhân thân cần tìm.
     * @return đối tượng Relative nếu tìm thấy, ngược lại trả về null.
     */
    public Relative findRelativeById(int relativeId) {
        String sql = "SELECT * FROM relatives WHERE relative_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, relativeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRelative(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Thêm một nhân thân mới vào cơ sở dữ liệu.
     * @param relative Đối tượng Relative chứa thông tin cần thêm.
     */
    public void addRelative(Relative relative) throws SQLException {
        String sql = "INSERT INTO relatives (relationship, full_name, date_of_birth, hometown, phone_number, national_id, student_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, relative.getRelationship());
            pstmt.setString(2, relative.getFullName());
            if (relative.getDateOfBirth() != null) {
                pstmt.setDate(3, Date.valueOf(relative.getDateOfBirth()));
            } else {
                pstmt.setNull(3, Types.DATE);
            }
            pstmt.setString(4, relative.getHometown());
            pstmt.setString(5, relative.getPhoneNumber());
            pstmt.setString(6, relative.getNationalId());
            pstmt.setString(7, relative.getStudentId());
            
            pstmt.executeUpdate();
        }
    }

    /**
     * Cập nhật thông tin của một nhân thân đã có.
     * @param relative Đối tượng Relative chứa thông tin cần cập nhật.
     */
    public void updateRelative(Relative relative) throws SQLException {
        String sql = "UPDATE relatives SET relationship = ?, full_name = ?, date_of_birth = ?, hometown = ?, phone_number = ?, national_id = ?, student_id = ? WHERE relative_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, relative.getRelationship());
            pstmt.setString(2, relative.getFullName());
            if (relative.getDateOfBirth() != null) {
                pstmt.setDate(3, Date.valueOf(relative.getDateOfBirth()));
            } else {
                pstmt.setNull(3, Types.DATE);
            }
            pstmt.setString(4, relative.getHometown());
            pstmt.setString(5, relative.getPhoneNumber());
            pstmt.setString(6, relative.getNationalId());
            pstmt.setString(7, relative.getStudentId());
            pstmt.setInt(8, relative.getRelativeId()); // Tham số cho WHERE clause
            
            pstmt.executeUpdate();
        }
    }

    /**
     * Xóa một nhân thân khỏi cơ sở dữ liệu dựa trên ID.
     * @param relativeId ID của nhân thân cần xóa.
     */
    public void deleteRelative(int relativeId) throws SQLException {
        String sql = "DELETE FROM relatives WHERE relative_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, relativeId);
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Phương thức private helper để chuyển đổi dữ liệu từ ResultSet sang đối tượng Relative.
     * Giúp tránh lặp lại code.
     */
    private Relative mapResultSetToRelative(ResultSet rs) throws SQLException {
        Relative relative = new Relative();
        relative.setRelativeId(rs.getInt("relative_id"));
        relative.setRelationship(rs.getString("relationship"));
        relative.setFullName(rs.getString("full_name"));
        
        Date dobSQL = rs.getDate("date_of_birth");
        if (dobSQL != null) {
            relative.setDateOfBirth(dobSQL.toLocalDate());
        }
        
        relative.setHometown(rs.getString("hometown"));
        relative.setPhoneNumber(rs.getString("phone_number"));
        relative.setNationalId(rs.getString("national_id"));
        relative.setStudentId(rs.getString("student_id"));
        
        return relative;
    }
}