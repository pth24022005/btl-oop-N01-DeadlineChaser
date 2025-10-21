package com.myproject.student.management.components;
import java.awt.*;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
    private int radius;
    public RoundedBorder(int radius) { this.radius = radius; }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY); // Màu của đường viền
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8); // Padding bên trong
    }
}