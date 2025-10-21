package com.myproject.student.management.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    private Color hoverBackgroundColor = new Color(72, 181, 255);
    private Color defaultBackgroundColor = new Color(0, 122, 204);
    private Color pressedBackgroundColor = new Color(32, 102, 190);

    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBackground(defaultBackgroundColor);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 16));
//        setPreferredSize(new Dimension(150, 40));
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(150, 200, 255), 1),
                    BorderFactory.createEmptyBorder(10, 25, 10, 25)
                ));
                setShadow(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackgroundColor);
                setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
                setShadow(false); 
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedBackgroundColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverBackgroundColor);
            }
        });
    }


    private void setShadow(boolean enable) {
        if (enable) {
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 2), 
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
            ));
        } else {
            setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); 

        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {

    }
}
