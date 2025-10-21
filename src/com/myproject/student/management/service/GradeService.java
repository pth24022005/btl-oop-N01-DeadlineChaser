package com.myproject.student.management.service;

import com.myproject.student.management.dao.GradeDAO;
import com.myproject.student.management.model.Grade;
import java.sql.SQLException;
import java.util.List;

public class GradeService {
    private GradeDAO gradeDAO;

    public GradeService() {
        this.gradeDAO = new GradeDAO();
    }
    
    public List<Grade> searchGrades(String keyword) {
        return gradeDAO.searchGrades(keyword);
    }
    
    public void addGrade(Grade grade) throws SQLException {
        // --- LOGIC TÍNH ĐIỂM ---
        if (grade.getMidtermScore() < 0 || grade.getMidtermScore() > 10 || grade.getFinalScore() < 0 || grade.getFinalScore() > 10) {
            throw new IllegalArgumentException("Điểm số phải nằm trong khoảng từ 0 đến 10.");
        }
        
        // Giả sử: 30% giữa kỳ, 70% cuối kỳ
        double gpa10 = (grade.getMidtermScore() * 0.3) + (grade.getFinalScore() * 0.7);
        // Làm tròn đến 2 chữ số thập phân
        gpa10 = Math.round(gpa10 * 100.0) / 100.0;
        grade.setGpa10(gpa10);
        
        // Chuyển đổi sang hệ 4
        double gpa4;
        if (gpa10 >= 8.5) gpa4 = 4.0;
        else if (gpa10 >= 7.0) gpa4 = 3.0;
        else if (gpa10 >= 5.5) gpa4 = 2.0;
        else if (gpa10 >= 4.0) gpa4 = 1.0;
        else gpa4 = 0.0;
        grade.setGpa4(gpa4);

        gradeDAO.addGrade(grade);
    }
    
    
    public Grade findGradeById(int gradeId) {
        return gradeDAO.findGradeById(gradeId);
    }
    public void updateGrade(Grade grade) throws SQLException {
        // Tái sử dụng logic tính điểm
        if (grade.getMidtermScore() < 0 || grade.getMidtermScore() > 10 || grade.getFinalScore() < 0 || grade.getFinalScore() > 10) {
            throw new IllegalArgumentException("Điểm số phải nằm trong khoảng từ 0 đến 10.");
        }
        
        double gpa10 = (grade.getMidtermScore() * 0.3) + (grade.getFinalScore() * 0.7);
        gpa10 = Math.round(gpa10 * 100.0) / 100.0;
        grade.setGpa10(gpa10);
        
        double gpa4;
        if (gpa10 >= 8.5) gpa4 = 4.0;
        else if (gpa10 >= 7.0) gpa4 = 3.0;
        else if (gpa10 >= 5.5) gpa4 = 2.0;
        else if (gpa10 >= 4.0) gpa4 = 1.0;
        else gpa4 = 0.0;
        grade.setGpa4(gpa4);
        
        gradeDAO.updateGrade(grade);
    }

    public void deleteGrade(int gradeId) throws SQLException {
        gradeDAO.deleteGrade(gradeId);
    }
}