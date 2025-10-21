
package com.myproject.student.management.view;

import com.myproject.student.management.dao.AccountDAO;
import com.myproject.student.management.model.Accounts;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
    // BIẾN ĐỂ LƯU ẢNH GỐC
    private javax.swing.ImageIcon originalIcon;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();  
        // 1. Đặt kích thước cho cửa sổ (ví dụ: rộng 1000 pixels, cao 650 pixels)
        setSize(1000, 650);

        // 2. Đặt cửa sổ hiển thị ở chính giữa màn hình
        setLocationRelativeTo(null);
        // Tải ảnh gốc MỘT LẦN DUY NHẤT và lưu vào biến
        try {
            // THAY ĐỔI ĐƯỜNG DẪN đến file ảnh của bạn
            originalIcon = new javax.swing.ImageIcon(getClass().getResource("/com/myproject/student/management/images/Login.png")); 
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi: Không thể tải hình ảnh!");
        }
    
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        formLoginPanel = new javax.swing.JPanel();
        username = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        password = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        passInput = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\user-solid-full.png")); // NOI18N
        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 51));
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        imageLabel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                imageLabelComponentResized(evt);
            }
        });
        getContentPane().add(imageLabel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(4, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("ĐĂNG NHẬP HỆ THỐNG");
        jPanel3.add(jLabel1, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel3);

        formLoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        formLoginPanel.setLayout(new java.awt.GridLayout(2, 0, 0, 20));

        username.setBackground(new java.awt.Color(255, 255, 255));
        username.setLayout(new javax.swing.BoxLayout(username, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\people.png")); // NOI18N
        username.add(jLabel2);

        usernameInput.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        usernameInput.setText("admin");
        usernameInput.setBorder(new com.myproject.student.management.components.RoundedBorder(20));
        usernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameInputActionPerformed(evt);
            }
        });
        username.add(usernameInput);

        formLoginPanel.add(username);

        password.setBackground(new java.awt.Color(255, 255, 255));
        password.setLayout(new javax.swing.BoxLayout(password, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\com\\myproject\\student\\management\\icons\\locked-computer.png")); // NOI18N
        password.add(jLabel4);

        passInput.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        passInput.setText("admin123");
        passInput.setBorder(new com.myproject.student.management.components.RoundedBorder(20));
        password.add(passInput);

        formLoginPanel.add(password);

        jPanel1.add(formLoginPanel);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        loginButton.setText("Đăng nhập");
        loginButton.setBorder(new com.myproject.student.management.components.RoundedBorder(20));
        loginButton.setBorderPainted(false);
        loginButton.setMargin(new java.awt.Insets(20, 14, 20, 14));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel4.add(loginButton, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imageLabelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_imageLabelComponentResized
        // TODO add your handling code here:
        // Kiểm tra xem ảnh gốc đã được tải thành công chưa
        if (originalIcon != null) {
            // Lấy kích thước hiện tại của JLabel
            int width = imageLabel.getWidth();
            int height = imageLabel.getHeight();

            // Chỉ thực hiện khi JLabel có kích thước hợp lệ
            if (width > 0 && height > 0) {
                // Lấy đối tượng Image từ ImageIcon gốc
                java.awt.Image originalImage = originalIcon.getImage();

                // Thay đổi kích thước ảnh để vừa khít với JLabel
                // Image.SCALE_SMOOTH giúp ảnh mượt hơn khi co dãn
                java.awt.Image resizedImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

                // Tạo một ImageIcon mới từ ảnh đã được co dãn
                javax.swing.ImageIcon resizedIcon = new javax.swing.ImageIcon(resizedImage);

                // Đặt icon mới này cho JLabel
                imageLabel.setIcon(resizedIcon);
            }
        }
    }//GEN-LAST:event_imageLabelComponentResized

    private void usernameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameInputActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        // 1. Lấy dữ liệu người dùng nhập vào từ giao diện
        String username = usernameInput.getText();
        // Lấy mật khẩu từ JPasswordField phải dùng getPassword()
        String password = new String(passInput.getPassword());

        // 2. Kiểm tra xem người dùng đã nhập đủ thông tin chưa
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng lại nếu chưa nhập đủ
        }

        // 3. Gọi DAO để xác thực
        AccountDAO accountDAO = new AccountDAO();
        Accounts account = accountDAO.checkLogin(username, password);

        // 4. Xử lý kết quả trả về
        if (account != null) {
            // Nếu user không phải là null -> Đăng nhập thành công
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Chào mừng " + account.getUsername());

            // Chuyển hướng đến trang chủ
            MainJFrame mainView = new MainJFrame(); // Thay MainView bằng tên JFrame trang chủ của bạn
            mainView.setExtendedState(MainJFrame.MAXIMIZED_BOTH);
            mainView.setVisible(true);

            // Đóng cửa sổ đăng nhập hiện tại
            this.dispose(); 

        } else {
            // Nếu user là null -> Đăng nhập thất bại
            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng.", "Lỗi Đăng Nhập", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel formLoginPanel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passInput;
    private javax.swing.JPanel password;
    private javax.swing.JPanel username;
    private javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
