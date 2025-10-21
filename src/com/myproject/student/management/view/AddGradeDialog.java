package com.myproject.student.management.view;

import com.myproject.student.management.model.Grade;
import com.myproject.student.management.model.Student;
import com.myproject.student.management.model.Subject;
import com.myproject.student.management.service.GradeService;
import com.myproject.student.management.service.StudentService;
import com.myproject.student.management.service.SubjectService;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AddGradeDialog extends JDialog {

    // --- Các thành phần giao diện ---
    private JComboBox<Student> cmbStudent;
    private JComboBox<Subject> cmbSubject;
    private JTextField txtMidtermScore, txtFinalScore;
    private JDateChooser dateChooser;
    private JTextArea txtComments;
    private JButton btnSave, btnCancel;

    // --- Các lớp Service ---
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final GradeService gradeService;

    private Grade gradeToEdit;

    /**
     * Constructor cho chế độ THÊM MỚI.
     */
    public AddGradeDialog(Frame owner) {
        super(owner, "Thêm Điểm Mới", true);
        this.gradeToEdit = null;
        
        // Khởi tạo các service
        this.studentService = new StudentService();
        this.subjectService = new SubjectService();
        this.gradeService = new GradeService();
        
        // Dựng giao diện, gắn sự kiện, tải dữ liệu
        initUI();
        initActions();
        loadComboBoxData();
        
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(owner);
    }
    
    /**
     * Constructor cho chế độ SỬA.
     */
    public AddGradeDialog(Frame owner, Grade gradeToEdit) {
        // Gọi constructor Thêm Mới ở trên để dựng toàn bộ giao diện và logic trước
        this(owner); 
        
        setTitle("Cập nhật Điểm số");
        this.gradeToEdit = gradeToEdit;
        
        // Sau khi giao diện đã được tạo, tải dữ liệu cũ lên form
        loadDataForEdit();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // Hàng 1: Sinh viên
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Chọn Sinh viên:"), gbc);
        gbc.gridx = 1; cmbStudent = new JComboBox<>(); cmbStudent.setFont(font); add(cmbStudent, gbc);

        // Hàng 2: Môn học
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Chọn Môn học:"), gbc);
        gbc.gridx = 1; cmbSubject = new JComboBox<>(); cmbSubject.setFont(font); add(cmbSubject, gbc);

        // Hàng 3: Điểm giữa kỳ
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Điểm giữa kỳ:"), gbc);
        gbc.gridx = 1; txtMidtermScore = new JTextField(); txtMidtermScore.setFont(font); add(txtMidtermScore, gbc);

        // Hàng 4: Điểm cuối kỳ
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Điểm cuối kỳ:"), gbc);
        gbc.gridx = 1; txtFinalScore = new JTextField(); txtFinalScore.setFont(font); add(txtFinalScore, gbc);

        // Hàng 5: Ngày thi
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Ngày thi:"), gbc);
        gbc.gridx = 1; dateChooser = new JDateChooser(); dateChooser.setDateFormatString("dd-MM-yyyy"); dateChooser.setFont(font); add(dateChooser, gbc);

        // Hàng 6: Ghi chú
        gbc.gridx = 0; gbc.gridy = 5; gbc.anchor = GridBagConstraints.NORTHWEST;
        add(new JLabel("Ghi chú:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        txtComments = new JTextArea(4, 20); txtComments.setFont(font); add(new JScrollPane(txtComments), gbc);

        // Hàng 7: Nút bấm
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnSave); buttonPanel.add(btnCancel);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE; gbc.weighty = 0;
        add(buttonPanel, gbc);
    }
    
    private void loadComboBoxData() {
        try {
            // Tải và hiển thị danh sách sinh viên
            List<Student> students = studentService.getAllStudents();
            for (Student student : students) {
                cmbStudent.addItem(student);
            }
            cmbStudent.setRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(value == null ? "" : value.getFullName()));

            // Tải và hiển thị danh sách môn học
            List<Subject> subjects = subjectService.getAllSubjects();
            for (Subject subject : subjects) {
                cmbSubject.addItem(subject);
            }
            cmbSubject.setRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(value == null ? "" : value.getSubjectName()));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu cho form: " + e.getMessage());
        }
    }
    
    private void loadDataForEdit() {
        if (gradeToEdit != null) {
            txtMidtermScore.setText(String.valueOf(gradeToEdit.getMidtermScore()));
            txtFinalScore.setText(String.valueOf(gradeToEdit.getFinalScore()));
            if (gradeToEdit.getExamDate() != null) {
                dateChooser.setDate(Date.from(gradeToEdit.getExamDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            txtComments.setText(gradeToEdit.getComments());
            
            selectItemInComboBox(cmbStudent, gradeToEdit.getStudentId());
            selectItemInComboBox(cmbSubject, gradeToEdit.getSubjectId());
        }
    }

    private <T> void selectItemInComboBox(JComboBox<T> comboBox, String idToSelect) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            Object item = comboBox.getItemAt(i);
            if (item instanceof Student && ((Student) item).getId().equals(idToSelect)) {
                comboBox.setSelectedIndex(i); return;
            }
            if (item instanceof Subject && ((Subject) item).getSubjectId().equals(idToSelect)) {
                comboBox.setSelectedIndex(i); return;
            }
        }
    }

    private void initActions() {
        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> {
            try {
                Student selectedStudent = (Student) cmbStudent.getSelectedItem();
                Subject selectedSubject = (Subject) cmbSubject.getSelectedItem();
                double midterm = Double.parseDouble(txtMidtermScore.getText());
                double finalterm = Double.parseDouble(txtFinalScore.getText());
                Date examDate = dateChooser.getDate();
                String comments = txtComments.getText();
                
                if (selectedStudent == null || selectedSubject == null) {
                    throw new IllegalArgumentException("Vui lòng chọn sinh viên và môn học.");
                }
                if (examDate == null) {
                    throw new IllegalArgumentException("Vui lòng chọn ngày thi.");
                }

                Grade grade = (gradeToEdit == null) ? new Grade() : gradeToEdit;
                grade.setStudentId(selectedStudent.getId());
                grade.setSubjectId(selectedSubject.getSubjectId());
                grade.setMidtermScore(midterm);
                grade.setFinalScore(finalterm);
                grade.setExamDate(examDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                grade.setComments(comments);
                
                if (gradeToEdit == null) { // Chế độ Thêm
                    gradeService.addGrade(grade);
                    JOptionPane.showMessageDialog(this, "Thêm điểm thành công!");
                } else { // Chế độ Sửa
                    gradeService.updateGrade(grade);
//                    JOptionPane.showMessageDialog(this, "Cập nhật điểm thành công!");
                }
                dispose();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Điểm phải là một con số.", "Lỗi Định Dạng", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}