package com.myproject.student.management.components; // Sửa lại package cho đúng

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Một component tìm kiếm tùy chỉnh, có thể tái sử dụng.
 * Bao gồm một ô nhập liệu có placeholder và một nút bấm icon.
 */
public class CustomSearchComponent extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private String placeholderText;

    public CustomSearchComponent(String placeholder) {
        this.placeholderText = placeholder;
        
        setOpaque(false); // Rất quan trọng, để chúng ta tự vẽ nền
        setLayout(new BorderLayout(5, 0)); // 5px khoảng cách giữa text và nút
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5)); // Padding cho toàn bộ component

        // --- Khởi tạo ô nhập liệu ---
        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(Color.GRAY);
        searchField.setText(placeholder);
        searchField.setOpaque(false); // Quan trọng: làm cho JTextField trong suốt
        searchField.setBorder(null); // Bỏ viền mặc định

        // Thêm listener để xử lý placeholder
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(placeholderText)) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText(placeholderText);
                }
            }
        });

        // --- Khởi tạo nút tìm kiếm ---
        // Sửa lại đường dẫn icon cho đúng với project của bạn
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("/com/myproject/student/management/icons/search.png"));
        searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(searchIcon.getIconWidth() + 10, searchIcon.getIconHeight()));
        searchButton.setBorder(null);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Thêm các component vào panel
        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }

    // Ghi đè phương thức paintComponent để vẽ nền và viền bo tròn
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Vẽ nền trắng
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        
        // Vẽ viền xám nhạt
        g2.setColor(new Color(220, 220, 220));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        
        g2.dispose();
        super.paintComponent(g);
    }

    /**
     * Lấy nội dung text từ ô tìm kiếm.
     * Sẽ trả về chuỗi rỗng nếu người dùng chưa nhập gì.
     * @return Text tìm kiếm.
     */
    public String getSearchText() {
        if (searchField.getText().equals(placeholderText)) {
            return "";
        }
        return searchField.getText().trim();
    }

    /**
     * Thêm một hành động (ActionListener) cho nút tìm kiếm.
     * @param listener Hành động sẽ được thực thi khi nhấn nút.
     */
    public void addSearchActionListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
}