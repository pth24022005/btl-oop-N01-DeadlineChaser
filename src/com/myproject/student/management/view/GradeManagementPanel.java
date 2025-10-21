package com.myproject.student.management.view;

import com.myproject.student.management.components.CustomTable;
import com.myproject.student.management.model.Grade;
import com.myproject.student.management.service.GradeService;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GradeManagementPanel extends JPanel implements Refreshable{
    private CustomTable gradeTable;
    private JButton btnAdd, btnApplyFilter, btnDelete, btnEdit;
    private JTextField txtSearch;
    private GradeService gradeService;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public GradeManagementPanel() {
        gradeService = new GradeService();
        initUI();
        initActions();
        loadTableData(""); // Tải toàn bộ dữ liệu ban đầu
    }
    
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 248, 251));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
    }
    
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(20, 0));
        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("QUẢN LÝ ĐIỂM SỐ");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filterPanel.setOpaque(false);
        filterPanel.add(new JLabel("Tìm kiếm:"));
        txtSearch = new JTextField(30);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnApplyFilter = new JButton("Lọc");
        filterPanel.add(txtSearch);
        filterPanel.add(btnApplyFilter);
        
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setOpaque(false);
        btnAdd = new JButton("Thêm Mới");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        actionPanel.add(btnAdd);
        actionPanel.add(btnEdit);
        actionPanel.add(btnDelete);
        
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(filterPanel, BorderLayout.CENTER);
        topPanel.add(actionPanel, BorderLayout.EAST);
        
        return topPanel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        String[] columnNames = {"ID", "Tên Sinh viên", "Môn học", "Điểm GK", "Điểm CK", "Điểm 10", "Điểm 4", "Ngày Thi"};
        gradeTable = new CustomTable(columnNames, 0, 0);
        
        panel.add(gradeTable, BorderLayout.CENTER);
        return panel;
    }
    
    private void initActions() {
        btnApplyFilter.addActionListener(e -> loadTableData(txtSearch.getText()));
        
        btnAdd.addActionListener(e -> {
        // Lấy cửa sổ cha (MainJFrame)
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
        
        // Tạo và hiển thị dialog
        AddGradeDialog dialog = new AddGradeDialog(owner);
        dialog.setVisible(true);
        
        // Sau khi dialog đóng, tải lại dữ liệu cho bảng
        loadTableData(""); 
        });
        
        // --- LOGIC NÚT XÓA ---
        btnDelete.addActionListener(e -> {
            int selectedRow = gradeTable.getSelectedRowIndex();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi điểm để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa điểm này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    int gradeId = (int) gradeTable.getValueAt(selectedRow, 0);
                    gradeService.deleteGrade(gradeId);
                    loadTableData("");
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        // --- LOGIC NÚT SỬA ---
        btnEdit.addActionListener(e -> {
            int selectedRow = gradeTable.getSelectedRowIndex();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi điểm để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int gradeId = (int) gradeTable.getValueAt(selectedRow, 0);
                Grade gradeToEdit = gradeService.findGradeById(gradeId);

                if (gradeToEdit != null) {
                    // Mở dialog ở chế độ "Sửa" và truyền đối tượng cần sửa vào
                    AddGradeDialog dialog = new AddGradeDialog((Frame) SwingUtilities.getWindowAncestor(this), gradeToEdit);
                    dialog.setVisible(true);
                    loadTableData(""); // Tải lại bảng sau khi dialog đóng
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu điểm để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void loadTableData(String keyword) {
        try {
            List<Grade> grades = gradeService.searchGrades(keyword);
            List<Object[]> data = new ArrayList<>();
            
            for (Grade grade : grades) {
                Object[] row = {
                    grade.getGradeId(),
                    grade.getStudentName(),
                    grade.getSubjectName(),
                    grade.getMidtermScore(),
                    grade.getFinalScore(),
                    grade.getGpa10(),
                    grade.getGpa4(),
                    grade.getExamDate() != null ? grade.getExamDate().format(dateFormatter) : ""
                };
                data.add(row);
            }
            
            // Dòng code quan trọng nhất để hiển thị dữ liệu
            gradeTable.setData(data);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu điểm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void refreshData() {
        System.out.println("Refreshing Grade Data..."); // Dòng này để debug
        loadTableData(""); // Gọi hàm tải dữ liệu với từ khóa rỗng để lấy tất cả
    }
}