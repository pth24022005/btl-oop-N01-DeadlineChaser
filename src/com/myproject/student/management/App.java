package com.myproject.student.management;

import com.myproject.student.management.view.Login;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Lớp chính (Main Class) để khởi chạy toàn bộ ứng dụng.
 * Đây là điểm vào của chương trình.
 */
public class App {

    public static void main(String[] args) {
        // Sử dụng SwingUtilities.invokeLater để đảm bảo tất cả các tác vụ
        // liên quan đến giao diện Swing được thực hiện trên Event Dispatch Thread (EDT).
        // Đây là cách làm chuẩn để tránh các lỗi liên quan đến đa luồng trong Swing.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Cài đặt Look and Feel "Nimbus" để giao diện trông hiện đại hơn
                    // so với giao diện mặc định của Java.
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    // Nếu Nimbus không có sẵn, chương trình sẽ dùng giao diện mặc định.
                    // Không cần xử lý gì thêm ở đây.
                    e.printStackTrace();
                }

                // Khởi tạo và hiển thị cửa sổ Đăng nhập đầu tiên.
                new Login().setVisible(true);
            }
        });
    }
}