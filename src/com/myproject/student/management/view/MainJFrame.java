package com.myproject.student.management.view;


import com.myproject.student.management.view.Login;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class MainJFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainJFrame.class.getName());
    // --- Khai báo các biến logic ---
    private CardLayout cardLayout;
    
    // Khai báo các panel chức năng thực sự (sẽ dùng để thay thế các panel tạm)
    private StudentManagementPanel studentManagementPanel;
    private GradeManagementPanel gradeManagementPanel;
    private RelativeManagementPanel relativeManagementPanel;
    
    
    
    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {  
        initComponents(); 
        // Gọi hàm của chúng ta để thêm logic động
        initCustomLogic();
   
    }
    
    /**
     * Phương thức này chứa logic để thiết lập CardLayout và các panel chức năng.
     * Nó được gọi sau khi các component đã được tạo bởi initComponents().
     */
    private void initCustomLogic() {
        // Lấy đối tượng CardLayout đã được set cho contentPanel trong trình Design
        cardLayout = (CardLayout) contentPanel.getLayout();

        // --- Khởi tạo các panel chức năng thực sự ---
        // JPanel "DashboardPanel" đã được tạo trong Design, chúng ta sẽ dùng nó làm trang chủ.
        // Tô màu cho nó để dễ nhận biết
        DashboardPanel.setBackground(java.awt.Color.WHITE);
        DashboardPanel.add(new JLabel("Đây là Trang Chủ (Dashboard)"));
        
        // Khởi tạo các panel chức năng khác từ các lớp riêng của chúng
        studentManagementPanel = new StudentManagementPanel();
        gradeManagementPanel = new GradeManagementPanel();
        relativeManagementPanel = new RelativeManagementPanel();

        // --- Thêm các panel chức năng vào CardLayout với tên định danh ---
        // Xóa các panel tạm do NetBeans tạo ra (nếu có) và thêm panel thật vào
        contentPanel.add(DashboardPanel, "DASHBOARD_PANEL");
        contentPanel.add(studentManagementPanel, "STUDENT_PANEL");
        contentPanel.add(gradeManagementPanel, "GRADE_PANEL");
        contentPanel.add(relativeManagementPanel, "RELATIVE_PANEL");
        
        // Quan trọng: Đổi tên cho card của DashboardPanel để code dễ đọc hơn
        // NetBeans có thể đã tự đặt tên là "card2", "card3"...
        // Chúng ta sẽ thêm lại nó với tên chuẩn "DASHBOARD_PANEL"
        contentPanel.add(DashboardPanel, "DASHBOARD_PANEL");

        // Hiển thị trang đầu tiên khi ứng dụng khởi động
        cardLayout.show(contentPanel, "DASHBOARD_PANEL");
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sidebarPanel = new javax.swing.JPanel();
        refreshButton = new javax.swing.JLabel();
        lblTrangChu = new javax.swing.JLabel();
        lblQuanLySinhVien = new javax.swing.JLabel();
        lblQuanLyDiemSo = new javax.swing.JLabel();
        lblQuanLyNhanThan = new javax.swing.JLabel();
        lblDangXuat = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        DashboardPanel = new javax.swing.JPanel();
        StudentManagementPanel = new javax.swing.JPanel();
        GradeManagementPanel = new javax.swing.JPanel();
        RelativeManagementPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        sidebarPanel.setBackground(new java.awt.Color(102, 255, 51));
        sidebarPanel.setLayout(new java.awt.GridLayout(6, 0));

        refreshButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        refreshButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refreshButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\refresh.png")); // NOI18N
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });
        sidebarPanel.add(refreshButton);

        lblTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTrangChu.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\home.png")); // NOI18N
        lblTrangChu.setText(" TRANG CHỦ");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
        });
        sidebarPanel.add(lblTrangChu);

        lblQuanLySinhVien.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblQuanLySinhVien.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\student mana.png")); // NOI18N
        lblQuanLySinhVien.setText(" QUẢN LÝ SINH VIÊN");
        lblQuanLySinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLySinhVienMouseClicked(evt);
            }
        });
        sidebarPanel.add(lblQuanLySinhVien);

        lblQuanLyDiemSo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblQuanLyDiemSo.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\scoring.png")); // NOI18N
        lblQuanLyDiemSo.setText(" QUẢN LÝ ĐIỂM SỐ");
        lblQuanLyDiemSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLyDiemSoMouseClicked(evt);
            }
        });
        sidebarPanel.add(lblQuanLyDiemSo);

        lblQuanLyNhanThan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblQuanLyNhanThan.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\pesonal mana.png")); // NOI18N
        lblQuanLyNhanThan.setText("QUẢN LÝ NHÂN THÂN");
        lblQuanLyNhanThan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLyNhanThanMouseClicked(evt);
            }
        });
        sidebarPanel.add(lblQuanLyNhanThan);

        lblDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDangXuat.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\logout.png")); // NOI18N
        lblDangXuat.setText("ĐĂNG XUẤT");
        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseClicked(evt);
            }
        });
        sidebarPanel.add(lblDangXuat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(sidebarPanel, gridBagConstraints);

        contentPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout DashboardPanelLayout = new javax.swing.GroupLayout(DashboardPanel);
        DashboardPanel.setLayout(DashboardPanelLayout);
        DashboardPanelLayout.setHorizontalGroup(
            DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        DashboardPanelLayout.setVerticalGroup(
            DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        contentPanel.add(DashboardPanel, "card2");

        javax.swing.GroupLayout StudentManagementPanelLayout = new javax.swing.GroupLayout(StudentManagementPanel);
        StudentManagementPanel.setLayout(StudentManagementPanelLayout);
        StudentManagementPanelLayout.setHorizontalGroup(
            StudentManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        StudentManagementPanelLayout.setVerticalGroup(
            StudentManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        contentPanel.add(StudentManagementPanel, "card2");

        javax.swing.GroupLayout GradeManagementPanelLayout = new javax.swing.GroupLayout(GradeManagementPanel);
        GradeManagementPanel.setLayout(GradeManagementPanelLayout);
        GradeManagementPanelLayout.setHorizontalGroup(
            GradeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        GradeManagementPanelLayout.setVerticalGroup(
            GradeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        contentPanel.add(GradeManagementPanel, "card2");

        javax.swing.GroupLayout RelativeManagementPanelLayout = new javax.swing.GroupLayout(RelativeManagementPanel);
        RelativeManagementPanel.setLayout(RelativeManagementPanelLayout);
        RelativeManagementPanelLayout.setHorizontalGroup(
            RelativeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        RelativeManagementPanelLayout.setVerticalGroup(
            RelativeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        contentPanel.add(RelativeManagementPanel, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(contentPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
cardLayout.show(contentPanel, "DASHBOARD_PANEL");
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void lblQuanLySinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLySinhVienMouseClicked
cardLayout.show(contentPanel, "STUDENT_PANEL");
    }//GEN-LAST:event_lblQuanLySinhVienMouseClicked

    private void lblQuanLyDiemSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyDiemSoMouseClicked
cardLayout.show(contentPanel, "GRADE_PANEL");
    }//GEN-LAST:event_lblQuanLyDiemSoMouseClicked

    private void lblQuanLyNhanThanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyNhanThanMouseClicked
cardLayout.show(contentPanel, "RELATIVE_PANEL");
    }//GEN-LAST:event_lblQuanLyNhanThanMouseClicked

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        int choice = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            this.dispose();
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_lblDangXuatMouseClicked

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        // TODO add your handling code here:
        // Lặp qua tất cả các "trang" (component) trong contentPanel
        for (Component comp : contentPanel.getComponents()) {

            // Kiểm tra xem trang nào đang được hiển thị
            if (comp.isVisible()) {

                // Kiểm tra xem trang đó có phải là một đối tượng "Refreshable" hay không
                if (comp instanceof Refreshable) {

                    // Nếu đúng, ép kiểu nó về Refreshable và gọi hàm refreshData()
                    ((Refreshable) comp).refreshData();

                    // Hiển thị thông báo (tùy chọn)
                    JOptionPane.showMessageDialog(this, "Dữ liệu đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    // Dừng vòng lặp vì đã tìm thấy trang cần làm mới
                    return; 
                }
            }
        }
    }//GEN-LAST:event_refreshButtonMouseClicked
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JPanel GradeManagementPanel;
    private javax.swing.JPanel RelativeManagementPanel;
    private javax.swing.JPanel StudentManagementPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblQuanLyDiemSo;
    private javax.swing.JLabel lblQuanLyNhanThan;
    private javax.swing.JLabel lblQuanLySinhVien;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel refreshButton;
    private javax.swing.JPanel sidebarPanel;
    // End of variables declaration//GEN-END:variables
}
