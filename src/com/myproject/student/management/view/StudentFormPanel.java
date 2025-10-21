package com.myproject.student.management.view;

import com.myproject.student.management.components.CustomButton;
import com.myproject.student.management.model.Class; // Import Class model
import com.myproject.student.management.model.Student;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class StudentFormPanel extends JPanel {

    private JTextField txtId, txtFullName, txtAddress, txtEmail, txtPhoneNumber;
    private JComboBox<String> cmbGender, cmbClass;
    private JDateChooser dateChooser;
    private JButton btnAdd, btnDelete, btnUpdate;

    public StudentFormPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(450, 0));
        setBackground(Color.WHITE);

        Border lineBorder = BorderFactory.createLineBorder(new Color(220, 220, 220));
        Border titledBorder = BorderFactory.createTitledBorder(
                lineBorder, " Thông tin Sinh viên ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 18),
                new Color(50, 50, 50));
        Border emptyBorder = new EmptyBorder(10, 15, 15, 15);
        setBorder(BorderFactory.createCompoundBorder(titledBorder, emptyBorder));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        txtId = new JTextField();
        txtFullName = new JTextField();
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        cmbGender = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
        txtAddress = new JTextField();
        txtEmail = new JTextField();
        txtPhoneNumber = new JTextField();
        cmbClass = new JComboBox<>();

        Component[] components = {txtId, txtFullName, dateChooser, cmbGender, txtAddress, txtEmail, txtPhoneNumber, cmbClass};
        for (Component comp : components) {
            comp.setFont(textFont);
        }

        add(createInputRow("Mã SV:", txtId, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Họ và tên:", txtFullName, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Ngày sinh:", dateChooser, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Giới tính:", cmbGender, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Địa chỉ:", txtAddress, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Email:", txtEmail, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Số điện thoại:", txtPhoneNumber, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Lớp:", cmbClass, labelFont));

        add(Box.createVerticalGlue());

        btnAdd = new CustomButton("Thêm");
        btnUpdate = new CustomButton("Sửa");
        btnDelete = new CustomButton("Xóa");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        add(buttonPanel);
    }

    private JPanel createInputRow(String labelText, Component inputComponent, Font labelFont) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setPreferredSize(new Dimension(120, 30));
        panel.add(label, BorderLayout.WEST);
        panel.add(inputComponent, BorderLayout.CENTER);
        return panel;
    }

    public Student getStudentFromForm() {
        String id = txtId.getText().trim();
        String fullName = txtFullName.getText().trim();

        if (id.isEmpty() && txtId.isEditable()) {
            throw new IllegalArgumentException("Mã SV không được để trống!");
        }
        if (fullName.isEmpty()){
            throw new IllegalArgumentException("Họ tên không được để trống!");
        }

        Date selectedDate = dateChooser.getDate();
        if (selectedDate == null) {
            throw new IllegalArgumentException("Ngày sinh không được để trống!");
        }
        LocalDate dob = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String gender = (String) cmbGender.getSelectedItem();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhoneNumber.getText().trim();
        
        Object selectedClass = cmbClass.getSelectedItem();
        if (selectedClass == null || selectedClass.toString().equals("-- Chọn lớp --")) {
            throw new IllegalArgumentException("Vui lòng chọn lớp học!");
        }
        String classId = selectedClass.toString();

        return new Student(id, fullName, dob, gender, address, email, phone, classId);
    }

    public void setStudentToForm(Student student) {
        if (student == null) {
            clearForm();
            return;
        }
        txtId.setText(student.getId());
        txtId.setEditable(false);
        txtFullName.setText(student.getFullName());
        if (student.getDateOfBirth() != null) {
            dateChooser.setDate(Date.from(student.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else {
            dateChooser.setDate(null);
        }
        cmbGender.setSelectedItem(student.getGender());
        txtAddress.setText(student.getAddress());
        txtEmail.setText(student.getEmail());
        txtPhoneNumber.setText(student.getPhoneNumber());
        cmbClass.setSelectedItem(student.getClassId());
    }

    public void clearForm() {
        txtId.setText("");
        txtId.setEditable(true);
        txtFullName.setText("");
        dateChooser.setDate(null);
        txtAddress.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        cmbGender.setSelectedIndex(0);
        cmbClass.setSelectedIndex(0); // Chọn item "-- Chọn lớp --"
    }
    
    // **PHƯƠNG THỨC MỚI ĐỂ ĐỔ DỮ LIỆU VÀO COMBOBOX**
    public void setClassComboBoxData(List<Class> classes) {
        cmbClass.removeAllItems();
        cmbClass.addItem("-- Chọn lớp --");
        for (Class cls : classes) {
            cmbClass.addItem(cls.getClassId());
        }
    }

    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }

}