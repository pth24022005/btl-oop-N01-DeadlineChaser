package com.myproject.student.management.service;

import com.myproject.student.management.dao.StudentDAO;
import com.myproject.student.management.model.Student;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public void addStudent(Student student) {
        // --- Nơi để thêm các logic nghiệp vụ ---
        // Ví dụ: Kiểm tra xem mã sinh viên đã tồn tại chưa
        // if (studentDAO.findById(student.getId()) != null) {
        //     throw new IllegalArgumentException("Mã sinh viên đã tồn tại!");
        // }
        
        // Ví dụ: Kiểm tra tuổi
        // if (student.getDateOfBirth().isAfter(LocalDate.now().minusYears(18))) {
        //     throw new IllegalArgumentException("Sinh viên phải đủ 18 tuổi!");
        // }

        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student) {
        // Logic kiểm tra tương tự
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(String studentId) {
        // Logic kiểm tra tương tự
        studentDAO.deleteStudent(studentId);
    }
    // Thêm phương thức này vào lớp StudentService

    public Student findStudentById(String studentId) {
        // Đơn giản là gọi đến DAO
        return studentDAO.findStudentById(studentId);
    }
}