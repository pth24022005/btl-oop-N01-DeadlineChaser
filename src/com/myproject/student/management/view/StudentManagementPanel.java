package com.myproject.student.management.view;

import com.myproject.student.management.components.CustomTable;
import com.myproject.student.management.model.Class;
import com.myproject.student.management.model.Student;
import com.myproject.student.management.service.ClassService;
import com.myproject.student.management.service.StudentService;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementPanel extends JPanel implements Refreshable {

    private StudentFormPanel studentFormPanel;
    private CustomTable customTable;
    private StudentService studentService;
    private ClassService classService;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public StudentManagementPanel() {
        studentService = new StudentService();
        classService = new ClassService();
        initUI();
        initActions();
        loadTableData();
        loadClassData(); // **ĐẢM BẢO HÀM NÀY ĐƯỢC GỌI**
    }
    
    private void initUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 248, 251));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        studentFormPanel = new StudentFormPanel();
        add(studentFormPanel, BorderLayout.WEST);
        add(createTablePanel(), BorderLayout.CENTER);
    }
    
    // Trong file StudentManagementPanel.java

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        String[] columnNames = {"Mã SV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Địa chỉ", "Email", "SĐT", "Lớp"};
        customTable = new CustomTable(columnNames, 0, 0);

        JTable table = customTable.getTable();
        table.getSelectionModel().addListSelectionListener(e -> {
            // Khi chọn một hàng, hiển thị thông tin lên form
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                // --- PHẦN LOGIC ĐÃ ĐƯỢC HOÀN THIỆN ---

                // 1. Lấy mã sinh viên từ hàng được chọn
                String studentId = customTable.getValueAt(table.getSelectedRow(), 0).toString();

                // 2. Dùng service để tìm đối tượng Student đầy đủ thông tin
                Student student = studentService.findStudentById(studentId);

                // 3. Gọi phương thức của form để hiển thị thông tin của đối tượng đó
                studentFormPanel.setStudentToForm(student);
            }
        });

        panel.add(customTable, BorderLayout.CENTER);
        return panel;
    }

    private void initActions() {
        studentFormPanel.getBtnAdd().addActionListener(e -> addStudent());
        studentFormPanel.getBtnUpdate().addActionListener(e -> updateStudent());
        studentFormPanel.getBtnDelete().addActionListener(e -> deleteStudent());
        
    }
    
    // ... các hàm addStudent, updateStudent, deleteStudent giữ nguyên ...
    private void addStudent() {
        try {
            Student student = studentFormPanel.getStudentFromForm();
            studentService.addStudent(student);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!");
            studentFormPanel.clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Logic cho chức năng Sửa Sinh viên.
     */
    private void updateStudent() {
        int selectedRow = customTable.getSelectedRowIndex();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sinh viên để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật thông tin này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Student updatedStudent = studentFormPanel.getStudentFromForm();
                studentService.updateStudent(updatedStudent);
                loadTableData();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                studentFormPanel.clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Logic cho chức năng Xóa Sinh viên.
     */
    private void deleteStudent() {
        int selectedRow = customTable.getSelectedRowIndex();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sinh viên để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sinh viên này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String studentId = customTable.getValueAt(selectedRow, 0).toString();
                studentService.deleteStudent(studentId);
                loadTableData();
                JOptionPane.showMessageDialog(this, "Đã xóa sinh viên thành công!");
                studentFormPanel.clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    private void loadTableData() {
        try {
            List<Student> students = studentService.getAllStudents();
            List<Object[]> data = new ArrayList<>();
            for (Student student : students) {
                Object[] row = {
                    student.getId(), student.getFullName(), student.getDateOfBirth().format(dateFormatter),
                    student.getGender(), student.getAddress(), student.getEmail(),
                    student.getPhoneNumber(), student.getClassId()
                };
                data.add(row);
            }
            customTable.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu sinh viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // **PHƯƠNG THỨC ĐÃ ĐƯỢC HOÀN THIỆN**
    private void loadClassData() {
        try {
            List<Class> classList = classService.getAllClasses(); 
            studentFormPanel.setClassComboBoxData(classList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách lớp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // THÊM PHƯƠNG THỨC NÀY VÀO CUỐI FILE
    @Override
    public void refreshData() {
        System.out.println("Refreshing Student Data..."); // Dòng này để debug
        loadTableData();
    }
}