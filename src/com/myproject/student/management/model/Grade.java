package com.myproject.student.management.model;

import java.time.LocalDate;

public class Grade {
    private int gradeId;
    private String studentId;
    private String subjectId;
    private double midtermScore;
    private double finalScore;
    private double gpa10;
    private double gpa4;
    private LocalDate examDate;
    private String comments;

    // Các trường phụ để hiển thị
    private String studentName;
    private String subjectName;
    private String majorName;

    public Grade() {
    }

    // --- Getters and Setters (Đã hoàn thiện) ---

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public double getMidtermScore() {
        return midtermScore;
    }

    public void setMidtermScore(double midtermScore) {
        this.midtermScore = midtermScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public double getGpa10() {
        return gpa10;
    }

    public void setGpa10(double gpa10) {
        this.gpa10 = gpa10;
    }

    public double getGpa4() {
        return gpa4;
    }

    public void setGpa4(double gpa4) {
        this.gpa4 = gpa4;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}