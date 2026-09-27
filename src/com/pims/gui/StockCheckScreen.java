package com.pims.gui;

import com.pims.database.Connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockCheckScreen extends JPanel {

    private static final Color DEEP_TEAL = new Color(18, 70, 72);
    private static final Color DARK_TEAL = new Color(11, 45, 47);
    private static final Color GOLD = new Color(224, 169, 70);
    private static final Color GOLD_LIGHT = new Color(244, 218, 163);
    private static final Color BACKGROUND = new Color(244, 248, 246);
    private static final Color CARD = Color.WHITE;
    private static final Color TEXT = new Color(35, 43, 44);
    private static final Color MUTED_TEXT = new Color(103, 116, 117);
    private static final Color BORDER = new Color(214, 224, 220);
    private static final Color SUCCESS = new Color(39, 125, 90);
    private static final Color WARNING = new Color(214, 145, 42);
    private static final Color DANGER = new Color(190, 66, 66);

    private JTable stockTable;
    private DefaultTableModel tableModel;

    public StockCheckScreen() {

        setLayout(new BorderLayout());
        setBackground(BACKGROUND);

        create_table();
        create_header();
        load_stock();
    }

    private void create_header() {

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(BACKGROUND);

        headerPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        15,
                        30
                )
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setBackground(BACKGROUND);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel("Stock Check");

        titleLabel.setForeground(TEXT);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel descriptionLabel =
                new JLabel(
                        "Monitor medicine quantities, reorder levels and stock availability."
                );

        descriptionLabel.setForeground(MUTED_TEXT);

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JPanel accentBar =
                new JPanel();

        accentBar.setBackground(GOLD);

        accentBar.setPreferredSize(
                new Dimension(
                        55,
                        4
                )
        );

        accentBar.setMaximumSize(
                new Dimension(
                        55,
                        4
                )
        );

        accentBar.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        titlePanel.add(titleLabel);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(descriptionLabel);

        titlePanel.add(
                Box.createVerticalStrut(10)
        );

        titlePanel.add(accentBar);

        JLabel badge =
                new JLabel("STOCK MONITOR");

        badge.setForeground(DEEP_TEAL);
        badge.setBackground(GOLD_LIGHT);
        badge.setOpaque(true);

        badge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        badge.setBorder(
                new EmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                badge,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );
    }

    private void create_table() {

        String[] columns = {
                "Medicine",
                "Company",
                "Type",
                "Price",
                "Stock",
                "Reorder Level",
                "Status",
                "Expiry Date"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        stockTable =
                new JTable(tableModel);

        set_table_style();
        set_table_header();
        set_table_renderers();
        set_column_widths();
        create_table_container();
    }

    private void set_table_style() {

        stockTable.setRowHeight(32);

        stockTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        stockTable.setForeground(TEXT);
        stockTable.setBackground(CARD);

        stockTable.setSelectionBackground(
                GOLD_LIGHT
        );

        stockTable.setSelectionForeground(
                DARK_TEAL
        );

        stockTable.setGridColor(
                new Color(
                        232,
                        238,
                        235
                )
        );

        stockTable.setShowVerticalLines(false);
        stockTable.setShowHorizontalLines(true);

        stockTable.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        stockTable.setAutoCreateRowSorter(true);
    }

    private void set_table_header() {

        JTableHeader header =
                stockTable.getTableHeader();

        header.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        header.setForeground(Color.WHITE);
        header.setBackground(DEEP_TEAL);
        header.setReorderingAllowed(false);
    }

    private void set_table_renderers() {

        stockTable.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component component =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        if (isSelected) {

                            component.setBackground(
                                    GOLD_LIGHT
                            );

                            component.setForeground(
                                    DARK_TEAL
                            );

                        } else {

                            component.setBackground(
                                    row % 2 == 0
                                            ? Color.WHITE
                                            : new Color(
                                            248,
                                            251,
                                            249
                                    )
                            );

                            component.setForeground(TEXT);
                        }

                        setBorder(
                                new EmptyBorder(
                                        0,
                                        7,
                                        0,
                                        7
                                )
                        );

                        return component;
                    }
                }
        );

        stockTable.getColumnModel()
                .getColumn(6)
                .setCellRenderer(
                        create_status_renderer()
                );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        stockTable.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        centerRenderer
                );

        stockTable.getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        stockTable.getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centerRenderer
                );

        stockTable.getColumnModel()
                .getColumn(7)
                .setCellRenderer(
                        centerRenderer
                );
    }

    private DefaultTableCellRenderer create_status_renderer() {

        return new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                JLabel label =
                        (JLabel) super.getTableCellRendererComponent(
                                table,
                                value,
                                isSelected,
                                hasFocus,
                                row,
                                column
                        );

                label.setHorizontalAlignment(
                        SwingConstants.CENTER
                );

                label.setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                10
                        )
                );

                label.setBorder(
                        new EmptyBorder(
                                0,
                                5,
                                0,
                                5
                        )
                );

                if (isSelected) {

                    label.setForeground(
                            DARK_TEAL
                    );

                } else {

                    String status =
                            value == null
                                    ? ""
                                    : value.toString();

                    switch (status) {

                        case "Out of stock":
                            label.setForeground(DANGER);
                            break;

                        case "Low stock":
                            label.setForeground(WARNING);
                            break;

                        default:
                            label.setForeground(SUCCESS);
                            break;
                    }
                }

                return label;
            }
        };
    }

    private void set_column_widths() {

        stockTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(155);

        stockTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(130);

        stockTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(80);

        stockTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(85);

        stockTable.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(65);

        stockTable.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(100);

        stockTable.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(110);

        stockTable.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(100);
    }

    private void create_table_container() {

        JScrollPane scrollPane =
                new JScrollPane(stockTable);

        scrollPane.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        scrollPane.getViewport()
                .setBackground(Color.WHITE);

        JPanel tableContainer =
                new JPanel(
                        new BorderLayout()
                );

        tableContainer.setBackground(
                BACKGROUND
        );

        tableContainer.setBorder(
                new EmptyBorder(
                        0,
                        30,
                        25,
                        30
                )
        );

        tableContainer.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                tableContainer,
                BorderLayout.CENTER
        );
    }

    private void load_stock() {

        String sql =
                "SELECT name, company, medicine_type, price, " +
                        "quantity_in_stock, reorder_level, expiry_date " +
                        "FROM medicines " +
                        "ORDER BY name";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            tableModel.setRowCount(0);

            while (resultSet.next()) {

                String name =
                        resultSet.getString("name");

                String company =
                        resultSet.getString("company");

                String type =
                        resultSet.getString("medicine_type");

                double price =
                        resultSet.getDouble("price");

                int stock =
                        resultSet.getInt(
                                "quantity_in_stock"
                        );

                int reorderLevel =
                        resultSet.getInt(
                                "reorder_level"
                        );

                String expiryDate =
                        resultSet.getString(
                                "expiry_date"
                        );

                String status =
                        get_stock_status(
                                stock,
                                reorderLevel
                        );

                tableModel.addRow(
                        new Object[]{
                                name,
                                company,
                                type,
                                String.format(
                                        "R %.2f",
                                        price
                                ),
                                stock,
                                reorderLevel,
                                status,
                                expiryDate
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load stock information:\n"
                            + e.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private String get_stock_status(
            int stock,
            int reorderLevel) {

        if (stock == 0) {
            return "Out of stock";
        }

        if (stock <= reorderLevel) {
            return "Low stock";
        }

        return "In stock";
    }
}