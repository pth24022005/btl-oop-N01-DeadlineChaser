package com.myproject.student.management.model;

public class Class {
    private String classId;
    private String className;
    private String department;

    public Class() {
    }

    public Class(String classId, String className, String department) {
        this.classId = classId;
        this.className = className;
        this.department = department;
    }

    // --- Getters and Setters ---
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}