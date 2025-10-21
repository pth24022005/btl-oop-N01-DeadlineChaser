package com.myproject.student.management.dao;

import com.myproject.student.management.model.Student;
import com.myproject.student.management.util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    /**
     * Lấy toàn bộ danh sách sinh viên từ cơ sở dữ liệu.
     * @return một List các đối tượng Student.
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // Tạo đối tượng Student từ dữ liệu mỗi dòng đọc được
                Student student = new Student();
                student.setId(rs.getString("student_id"));
                student.setFullName(rs.getString("full_name"));
                
                // Chuyển đổi từ java.sql.Date sang java.time.LocalDate
                Date dobSQL = rs.getDate("date_of_birth");
                if (dobSQL != null) {
                    student.setDateOfBirth(dobSQL.toLocalDate());
                }
                
                student.setGender(rs.getString("gender"));
                student.setAddress(rs.getString("address"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setClassId(rs.getString("class_id"));
                
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trong ứng dụng thực tế, nên throw một exception tùy chỉnh ở đây
        }
        return students;
    }

    /**
     * Thêm một sinh viên mới vào cơ sở dữ liệu.
     * @param student Đối tượng Student chứa thông tin cần thêm.
     */
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, full_name, date_of_birth, gender, address, email, phone_number, class_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set các tham số cho câu lệnh INSERT
            pstmt.setString(1, student.getId());
            pstmt.setString(2, student.getFullName());
            
            // Chuyển đổi từ java.time.LocalDate sang java.sql.Date
            if (student.getDateOfBirth() != null) {
                pstmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            } else {
                pstmt.setNull(3, Types.DATE);
            }
            
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getAddress());
            pstmt.setString(6, student.getEmail());
            pstmt.setString(7, student.getPhoneNumber());
            pstmt.setString(8, student.getClassId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cập nhật thông tin của một sinh viên đã có.
     * @param student Đối tượng Student chứa thông tin cần cập nhật.
     */
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET full_name = ?, date_of_birth = ?, gender = ?, address = ?, email = ?, phone_number = ?, class_id = ? WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getFullName());
            if (student.getDateOfBirth() != null) {
                pstmt.setDate(2, Date.valueOf(student.getDateOfBirth()));
            } else {
                pstmt.setNull(2, Types.DATE);
            }
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getAddress());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getPhoneNumber());
            pstmt.setString(7, student.getClassId());
            pstmt.setString(8, student.getId()); // Tham số cho WHERE clause
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Xóa một sinh viên khỏi cơ sở dữ liệu dựa trên ID.
     * @param studentId Mã số của sinh viên cần xóa.
     */
    public void deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, studentId);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Thêm phương thức này vào lớp StudentDAO

    public Student findStudentById(String studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng Student từ dữ liệu đọc được
                    Student student = new Student();
                    student.setId(rs.getString("student_id"));
                    student.setFullName(rs.getString("full_name"));

                    Date dobSQL = rs.getDate("date_of_birth");
                    if (dobSQL != null) {
                        student.setDateOfBirth(dobSQL.toLocalDate());
                    }

                    student.setGender(rs.getString("gender"));
                    student.setAddress(rs.getString("address"));
                    student.setEmail(rs.getString("email"));
                    student.setPhoneNumber(rs.getString("phone_number"));
                    student.setClassId(rs.getString("class_id"));

                    return student;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }
}