package com.myproject.student.management.view;

import javax.swing.JLabel;

public class DashboardPanel extends javax.swing.JPanel {

    public DashboardPanel() {
        initComponents();
    }

    private void initComponents() {
        // Thêm một label đơn giản để nhận biết
        add(new JLabel("Đây là Trang Chủ (Dashboard)"));
    }
}