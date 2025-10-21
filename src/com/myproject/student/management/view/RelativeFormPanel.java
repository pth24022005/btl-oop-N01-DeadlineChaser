package com.myproject.student.management.view;

import com.myproject.student.management.components.CustomButton;
import com.myproject.student.management.model.Relative;
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

public class RelativeFormPanel extends JPanel {

    // --- Các thành phần giao diện ---
    private JComboBox<String> cmbRelationship;
    private JTextField txtFullName, txtHometown, txtPhoneNumber, txtNationalId;
    private JDateChooser dateChooser;
    private JComboBox<Student> cmbStudent; // Để chọn sinh viên liên quan
    private JButton btnAdd, btnUpdate, btnDelete;
    
    // Biến để lưu ID của nhân thân đang được sửa
    private int editingRelativeId = -1;

    public RelativeFormPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(450, 0));
        setBackground(Color.WHITE);

        Border lineBorder = BorderFactory.createLineBorder(new Color(220, 220, 220));
        Border titledBorder = BorderFactory.createTitledBorder(
                lineBorder, " Thông tin Nhân thân ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 18),
                new Color(50, 50, 50));
        Border emptyBorder = new EmptyBorder(10, 15, 15, 15);
        setBorder(BorderFactory.createCompoundBorder(titledBorder, emptyBorder));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        cmbRelationship = new JComboBox<>(new String[]{"Bố", "Mẹ", "Anh", "Chị", "Em", "Khác"});
        txtFullName = new JTextField();
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        txtHometown = new JTextField();
        txtPhoneNumber = new JTextField();
        txtNationalId = new JTextField();
        cmbStudent = new JComboBox<>();

        Component[] components = {cmbRelationship, txtFullName, dateChooser, txtHometown, txtPhoneNumber, txtNationalId, cmbStudent};
        for (Component comp : components) {
            comp.setFont(textFont);
        }

        add(createInputRow("Sinh viên:", cmbStudent, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Họ và tên:", txtFullName, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Quan hệ:", cmbRelationship, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Ngày sinh:", dateChooser, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Quê quán:", txtHometown, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Số điện thoại:", txtPhoneNumber, labelFont));
        add(Box.createVerticalStrut(10));
        add(createInputRow("Số CCCD:", txtNationalId, labelFont));

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

    public Relative getRelativeFromForm() {
        Student selectedStudent = (Student) cmbStudent.getSelectedItem();
        if (selectedStudent == null) {
            throw new IllegalArgumentException("Vui lòng chọn sinh viên.");
        }
        
        String fullName = txtFullName.getText().trim();
        if (fullName.isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống.");
        }
        
        Relative relative = new Relative();
        relative.setRelativeId(this.editingRelativeId); // Gán ID đang sửa, sẽ là -1 nếu là thêm mới
        relative.setStudentId(selectedStudent.getId());
        relative.setFullName(fullName);
        relative.setRelationship((String) cmbRelationship.getSelectedItem());
        
        Date dob = dateChooser.getDate();
        if (dob != null) {
            relative.setDateOfBirth(dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        
        relative.setHometown(txtHometown.getText().trim());
        relative.setPhoneNumber(txtPhoneNumber.getText().trim());
        relative.setNationalId(txtNationalId.getText().trim());
        
        return relative;
    }

    public void setRelativeToForm(Relative relative) {
        if (relative == null) {
            clearForm();
            return;
        }
        this.editingRelativeId = relative.getRelativeId();
        
        txtFullName.setText(relative.getFullName());
        cmbRelationship.setSelectedItem(relative.getRelationship());
        if (relative.getDateOfBirth() != null) {
            dateChooser.setDate(Date.from(relative.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else {
            dateChooser.setDate(null);
        }
        txtHometown.setText(relative.getHometown());
        txtPhoneNumber.setText(relative.getPhoneNumber());
        txtNationalId.setText(relative.getNationalId());
        
        // Chọn đúng sinh viên trong ComboBox
        for (int i = 0; i < cmbStudent.getItemCount(); i++) {
            if (cmbStudent.getItemAt(i).getId().equals(relative.getStudentId())) {
                cmbStudent.setSelectedIndex(i);
                break;
            }
        }
    }

    public void clearForm() {
        this.editingRelativeId = -1; // Reset ID
        cmbStudent.setSelectedIndex(-1);
        txtFullName.setText("");
        cmbRelationship.setSelectedIndex(0);
        dateChooser.setDate(null);
        txtHometown.setText("");
        txtPhoneNumber.setText("");
        txtNationalId.setText("");
    }
    
    public void setStudentComboBoxData(List<Student> students) {
        cmbStudent.removeAllItems();
        for (Student student : students) {
            cmbStudent.addItem(student);
        }
        // Tùy chỉnh cách JComboBox hiển thị đối tượng Student (chỉ hiện tên)
        cmbStudent.setRenderer((list, value, index, isSelected, cellHasFocus) -> 
            new JLabel(value == null ? "" : value.getFullName())
        );
    }
    
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }

}