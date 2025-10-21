package com.myproject.student.management.dao;

import com.myproject.student.management.model.Grade;
import com.myproject.student.management.util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    // Sửa lại câu lệnh SQL để JOIN thêm bảng classes và lấy đúng tên cột
    private final String SELECT_QUERY = 
        "SELECT g.grade_id, g.student_id, g.subject_id, g.midterm_score, g.final_score, g.gpa_10, g.gpa_4, g.exam_date, " +
        "s.full_name, c.class_name, sub.subject_name " +
        "FROM grades g " +
        "JOIN students s ON g.student_id = s.student_id " +
        "JOIN subjects sub ON g.subject_id = sub.subject_id " +
        "JOIN classes c ON s.class_id = c.class_id";

    public List<Grade> searchGrades(String keyword) {
        List<Grade> grades = new ArrayList<>();
        String sql = SELECT_QUERY;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " WHERE s.full_name LIKE ? OR sub.subject_name LIKE ? OR c.class_name LIKE ?";
        }
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (keyword != null && !keyword.trim().isEmpty()) {
                String searchKeyword = "%" + keyword + "%";
                pstmt.setString(1, searchKeyword);
                pstmt.setString(2, searchKeyword);
                pstmt.setString(3, searchKeyword);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    grades.add(mapResultSetToGrade(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    public void addGrade(Grade grade) throws SQLException {
        String sql = "INSERT INTO grades (student_id, subject_id, midterm_score, final_score, gpa_10, gpa_4, exam_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, grade.getStudentId());
            pstmt.setString(2, grade.getSubjectId());
            pstmt.setDouble(3, grade.getMidtermScore());
            pstmt.setDouble(4, grade.getFinalScore());
            pstmt.setDouble(5, grade.getGpa10());
            pstmt.setDouble(6, grade.getGpa4());

            if (grade.getExamDate() != null) {
                pstmt.setDate(7, java.sql.Date.valueOf(grade.getExamDate()));
            } else {
                pstmt.setNull(7, java.sql.Types.DATE);
            }

            pstmt.executeUpdate();
        }
    }
    // Trong file GradeDAO.java

    // Thêm phương thức này vào file GradeDAO.java

    public Grade findGradeById(int gradeId) {
        // Sử dụng lại câu lệnh SELECT_QUERY đã có
        String sql = SELECT_QUERY + " WHERE g.grade_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gradeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Dùng lại hàm mapResultSetToGrade để tạo đối tượng
                    return mapResultSetToGrade(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Trả về null nếu không tìm thấy
    }

    public void updateGrade(Grade grade) throws SQLException {
        String sql = "UPDATE grades SET student_id = ?, subject_id = ?, midterm_score = ?, final_score = ?, gpa_10 = ?, gpa_4 = ?, exam_date = ? WHERE grade_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, grade.getStudentId());
            pstmt.setString(2, grade.getSubjectId());
            pstmt.setDouble(3, grade.getMidtermScore());
            pstmt.setDouble(4, grade.getFinalScore());
            pstmt.setDouble(5, grade.getGpa10());
            pstmt.setDouble(6, grade.getGpa4());
            pstmt.setDate(7, java.sql.Date.valueOf(grade.getExamDate()));
            pstmt.setInt(8, grade.getGradeId());
            pstmt.executeUpdate();
        }
    }

    public void deleteGrade(int gradeId) throws SQLException {
        String sql = "DELETE FROM grades WHERE grade_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gradeId);
            pstmt.executeUpdate();
        }
    }

    private Grade mapResultSetToGrade(ResultSet rs) throws SQLException {
        Grade grade = new Grade();
        grade.setGradeId(rs.getInt("grade_id"));
        grade.setStudentId(rs.getString("student_id"));
        grade.setSubjectId(rs.getString("subject_id"));
        grade.setMidtermScore(rs.getDouble("midterm_score"));
        grade.setFinalScore(rs.getDouble("final_score"));
        grade.setGpa10(rs.getDouble("gpa_10"));
        grade.setGpa4(rs.getDouble("gpa_4"));
        
        if (rs.getDate("exam_date") != null) {
            grade.setExamDate(rs.getDate("exam_date").toLocalDate());
        }
        
        grade.setStudentName(rs.getString("full_name"));
        grade.setSubjectName(rs.getString("subject_name"));
        grade.setMajorName(rs.getString("class_name")); // Lấy tên ngành/lớp
        
        return grade;
    }
}