package com.myproject.student.management.view;

import com.myproject.student.management.components.CustomTable;
import com.myproject.student.management.model.Relative;
import com.myproject.student.management.model.Student;
import com.myproject.student.management.service.RelativeService;
import com.myproject.student.management.service.StudentService;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RelativeManagementPanel extends JPanel implements Refreshable {

    private RelativeFormPanel relativeFormPanel;
    private CustomTable customTable;
    private RelativeService relativeService;
    private StudentService studentService;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public RelativeManagementPanel() {
        relativeService = new RelativeService();
        studentService = new StudentService(); // Cần để lấy danh sách sinh viên
        initUI();
        initActions();
        loadTableData();
        loadStudentDataForForm(); // Tải danh sách SV cho ComboBox
    }
    
    private void initUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 248, 251));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        relativeFormPanel = new RelativeFormPanel();
        add(relativeFormPanel, BorderLayout.WEST);
        add(createTablePanel(), BorderLayout.CENTER);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        String[] columnNames = {"ID", "Họ Tên", "Quan hệ", "Ngày Sinh", "Quê Quán", "SĐT", "CCCD", "Mã SV"};
        customTable = new CustomTable(columnNames, 0, 0);

        JTable table = customTable.getTable();
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                // Lấy ID của nhân thân từ hàng được chọn
                int relativeId = (int) customTable.getValueAt(table.getSelectedRow(), 0);
                
                // Tìm nhân thân và hiển thị lên form
                Relative relative = relativeService.findRelativeById(relativeId);
                relativeFormPanel.setRelativeToForm(relative);
            }
        });

        panel.add(customTable, BorderLayout.CENTER);
        return panel;
    }
    
    private void initActions() {
        relativeFormPanel.getBtnAdd().addActionListener(e -> addRelative());
        relativeFormPanel.getBtnUpdate().addActionListener(e -> updateRelative());
        relativeFormPanel.getBtnDelete().addActionListener(e -> deleteRelative());
   
    }
    
    @Override
    public void refreshData() {
        loadTableData();
        loadStudentDataForForm();
    }
    
    private void loadTableData() {
        try {
            List<Relative> relatives = relativeService.getAllRelatives();
            List<Object[]> data = new ArrayList<>();
            for (Relative relative : relatives) {
                Object[] row = {
                    relative.getRelativeId(),
                    relative.getFullName(),
                    relative.getRelationship(),
                    relative.getDateOfBirth() != null ? relative.getDateOfBirth().format(dateFormatter) : "",
                    relative.getHometown(),
                    relative.getPhoneNumber(),
                    relative.getNationalId(),
                    relative.getStudentId()
                };
                data.add(row);
            }
            customTable.setData(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu nhân thân: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadStudentDataForForm() {
        try {
            List<Student> students = studentService.getAllStudents();
            relativeFormPanel.setStudentComboBoxData(students);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải danh sách sinh viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addRelative() {
        try {
            Relative relative = relativeFormPanel.getRelativeFromForm();
            relativeService.addRelative(relative);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Thêm nhân thân thành công!");
            relativeFormPanel.clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRelative() {
        int selectedRow = customTable.getSelectedRowIndex();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân thân để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật thông tin này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Relative updatedRelative = relativeFormPanel.getRelativeFromForm();
                relativeService.updateRelative(updatedRelative);
                loadTableData();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                relativeFormPanel.clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteRelative() {
        int selectedRow = customTable.getSelectedRowIndex();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân thân để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân thân này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int relativeId = (int) customTable.getValueAt(selectedRow, 0);
                relativeService.deleteRelative(relativeId);
                loadTableData();
                JOptionPane.showMessageDialog(this, "Đã xóa nhân thân thành công!");
                relativeFormPanel.clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}