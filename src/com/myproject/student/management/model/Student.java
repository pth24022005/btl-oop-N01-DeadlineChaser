package com.myproject.student.management.model;

import java.time.LocalDate;

public class Student {

    // --- Attributes (Thuộc tính) ---
    private String id;          // Mã số sinh viên (ví dụ: "SE123456")
    private String fullName;    // Họ và tên
    private LocalDate dateOfBirth; // Ngày sinh
    private String gender;      // Giới tính (ví dụ: "Nam", "Nữ")
    private String address;     // Địa chỉ
    private String email;       // Email
    private String phoneNumber; // Số điện thoại
    private String classId;     // Mã lớp mà sinh viên thuộc về (khóa ngoại)

    public Student() {
    }

    
    public Student(String id, String fullName, LocalDate dateOfBirth, String gender, String address, String email, String phoneNumber, String classId) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classId = classId;
    }

    // --- Getters and Setters ---
    // Cung cấp các phương thức public để truy cập và thay đổi các thuộc tính private.
    // Đây là ví dụ về Tính Đóng Gói (Encapsulation).

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    // --- toString() method ---
    // Phương thức này rất hữu ích cho việc debug, cho phép in thông tin đối tượng ra console.
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", fullName=" + fullName + ", classId=" + classId + '}';
    }
}