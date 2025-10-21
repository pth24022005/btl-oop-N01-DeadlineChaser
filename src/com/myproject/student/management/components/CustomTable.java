package com.myproject.student.management.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class CustomTable extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public CustomTable(String[] columnNames, int width, int height) {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 248, 251) : Color.WHITE);
                }
                return c;
            }
        };

        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(220, 220, 220));
        table.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(34, 40, 49));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 40));
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        add(scrollPane, BorderLayout.CENTER);

        if (width > 0 && height > 0) {
            setPreferredSize(new Dimension(width, height));
        }
    }

    public JTable getTable() {
        return table;
    }

    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    public Object getValueAt(int row, int col) {
        return table.getValueAt(row, col);
    }

    // PHƯƠNG THỨC QUAN TRỌNG NHẤT
    public void setData(List<Object[]> data) {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // Thêm dữ liệu mới
        if (data != null) {
            for (Object[] row : data) {
                tableModel.addRow(row);
            }
        }
    }
}