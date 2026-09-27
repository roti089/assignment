package com.pims.gui;

import com.pims.database.Connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsScreen extends JPanel {

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

    private JTable salesTable;
    private JTable itemWiseTable;
    private JTable lowStockTable;
    private JTable expiryTable;

    private DefaultTableModel salesModel;
    private DefaultTableModel itemWiseModel;
    private DefaultTableModel lowStockModel;
    private DefaultTableModel expiryModel;

    private JLabel totalMedicinesLabel;
    private JLabel lowStockLabel;
    private JLabel suppliersLabel;
    private JLabel inventoryValueLabel;
    private JLabel salesLabel;
    private JLabel transactionsLabel;

    public ReportsScreen() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND);

        create_header();

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND);

        contentPanel.add(
                create_statistics_panel(),
                BorderLayout.NORTH
        );

        contentPanel.add(
                create_report_tabs(),
                BorderLayout.CENTER
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        create_bottom_panel();
        refresh_reports();
    }

    private void create_header() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND);
        headerPanel.setBorder(
                new EmptyBorder(22, 30, 10, 30)
        );

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(BACKGROUND);
        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel = new JLabel("Pharmacy Reports");
        titleLabel.setForeground(TEXT);
        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));

        JLabel descriptionLabel = new JLabel(
                "Review sales activity, stock conditions, inventory value and expiry information."
        );

        descriptionLabel.setForeground(MUTED_TEXT);
        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(descriptionLabel);
        titlePanel.add(Box.createVerticalStrut(10));

        JPanel accentBar = new JPanel();
        accentBar.setBackground(GOLD);
        accentBar.setPreferredSize(
                new Dimension(55, 4)
        );
        accentBar.setMaximumSize(
                new Dimension(55, 4)
        );
        accentBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(accentBar);

        JLabel badge = new JLabel("BUSINESS INSIGHTS");
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
                new EmptyBorder(8, 12, 8, 12)
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

    private JPanel create_statistics_panel() {
        JPanel statisticsPanel = new JPanel(
                new GridLayout(1, 6, 10, 0)
        );

        statisticsPanel.setBackground(BACKGROUND);
        statisticsPanel.setBorder(
                new EmptyBorder(5, 30, 15, 30)
        );

        totalMedicinesLabel = create_value_label("0");
        lowStockLabel = create_value_label("0");
        suppliersLabel = create_value_label("0");
        inventoryValueLabel = create_value_label("R 0.00");
        salesLabel = create_value_label("R 0.00");
        transactionsLabel = create_value_label("0");

        statisticsPanel.add(
                create_report_card(
                        "MEDICINES",
                        totalMedicinesLabel,
                        DEEP_TEAL
                )
        );

        statisticsPanel.add(
                create_report_card(
                        "LOW STOCK",
                        lowStockLabel,
                        WARNING
                )
        );

        statisticsPanel.add(
                create_report_card(
                        "SUPPLIERS",
                        suppliersLabel,
                        DEEP_TEAL
                )
        );

        statisticsPanel.add(
                create_report_card(
                        "INVENTORY VALUE",
                        inventoryValueLabel,
                        GOLD
                )
        );

        statisticsPanel.add(
                create_report_card(
                        "TOTAL SALES",
                        salesLabel,
                        SUCCESS
                )
        );

        statisticsPanel.add(
                create_report_card(
                        "TRANSACTIONS",
                        transactionsLabel,
                        DEEP_TEAL
                )
        );

        return statisticsPanel;
    }

    private JLabel create_value_label(String value) {
        JLabel label = new JLabel(value);

        label.setForeground(TEXT);
        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        label.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        return label;
    }

    private JPanel create_report_card(
            String title,
            JLabel valueLabel,
            Color accentColor) {

        JPanel card = new JPanel(
                new BorderLayout(0, 4)
        );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER, 1),
                        new EmptyBorder(9, 8, 9, 8)
                )
        );

        JPanel accent = new JPanel();
        accent.setBackground(accentColor);
        accent.setPreferredSize(
                new Dimension(0, 4)
        );

        card.add(
                accent,
                BorderLayout.NORTH
        );

        JLabel titleLabel = new JLabel(
                title,
                SwingConstants.CENTER
        );

        titleLabel.setForeground(MUTED_TEXT);
        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        9
                )
        );

        card.add(
                titleLabel,
                BorderLayout.CENTER
        );

        card.add(
                valueLabel,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel create_report_tabs() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        tabbedPane.setForeground(DARK_TEAL);

        salesModel = create_table_model(
                new String[]{
                        "Sale ID",
                        "Date",
                        "Cashier",
                        "Total Amount"
                }
        );

        salesTable = create_table(salesModel);

        tabbedPane.addTab(
                "Sales Report",
                new JScrollPane(salesTable)
        );

        itemWiseModel = create_table_model(
                new String[]{
                        "Medicine",
                        "Quantity Sold",
                        "Total Revenue"
                }
        );

        itemWiseTable = create_table(itemWiseModel);

        tabbedPane.addTab(
                "Item-Wise Report",
                new JScrollPane(itemWiseTable)
        );

        lowStockModel = create_table_model(
                new String[]{
                        "ID",
                        "Medicine",
                        "Company",
                        "Stock",
                        "Reorder Level",
                        "Status"
                }
        );

        lowStockTable = create_table(lowStockModel);

        tabbedPane.addTab(
                "Low Stock Report",
                new JScrollPane(lowStockTable)
        );

        expiryModel = create_table_model(
                new String[]{
                        "ID",
                        "Medicine",
                        "Company",
                        "Expiry Date",
                        "Stock",
                        "Days Remaining"
                }
        );

        expiryTable = create_table(expiryModel);

        tabbedPane.addTab(
                "Expiry Report",
                new JScrollPane(expiryTable)
        );

        lowStockTable.getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        create_status_renderer()
                );

        JPanel tabsContainer = new JPanel(
                new BorderLayout()
        );

        tabsContainer.setBackground(BACKGROUND);

        tabsContainer.setBorder(
                new LineBorder(BORDER, 1)
        );

        tabsContainer.add(
                tabbedPane,
                BorderLayout.CENTER
        );

        return tabsContainer;
    }

    private JTable create_table(
            DefaultTableModel model) {

        JTable table = new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        table.setForeground(TEXT);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(GOLD_LIGHT);
        table.setSelectionForeground(DARK_TEAL);

        table.setGridColor(
                new Color(232, 238, 235)
        );

        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);

        table.setIntercellSpacing(
                new Dimension(0, 1)
        );

        table.setAutoCreateRowSorter(true);

        JTableHeader header =
                table.getTableHeader();

        header.setPreferredSize(
                new Dimension(0, 36)
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

        table.setDefaultRenderer(
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

        return table;
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

                if (!isSelected) {
                    String status =
                            value == null
                                    ? ""
                                    : value.toString();

                    if (status.equals("OUT OF STOCK")) {
                        label.setForeground(DANGER);
                    } else {
                        label.setForeground(WARNING);
                    }
                }

                return label;
            }
        };
    }

    private DefaultTableModel create_table_model(
            String[] columns) {

        return new DefaultTableModel(
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
    }

    private void create_bottom_panel() {
        JPanel bottomPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        8,
                        8
                )
        );

        bottomPanel.setBackground(BACKGROUND);

        bottomPanel.setBorder(
                new EmptyBorder(
                        0,
                        25,
                        15,
                        25
                )
        );

        JButton refreshButton = create_button(
                "Refresh reports",
                DEEP_TEAL
        );

        JButton exportButton = create_button(
                "Export report",
                GOLD
        );

        bottomPanel.add(refreshButton);
        bottomPanel.add(exportButton);

        refreshButton.addActionListener(
                e -> refresh_reports()
        );

        exportButton.addActionListener(
                e -> export_report()
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    private JButton create_button(
            String text,
            Color background) {

        JButton button = new JButton(text);

        button.setPreferredSize(
                new Dimension(145, 36)
        );

        button.setBackground(background);

        button.setForeground(
                background.equals(GOLD)
                        ? DARK_TEAL
                        : Color.WHITE
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private void refresh_reports() {
        load_statistics();
        load_sales_report();
        load_item_wise_report();
        load_low_stock_report();
        load_expiry_report();
    }

    private void load_statistics() {
        String medicineSQL =
                "SELECT COUNT(*) AS total, " +
                        "COALESCE(SUM(quantity_in_stock * price), 0) AS value, " +
                        "SUM(CASE WHEN quantity_in_stock <= reorder_level " +
                        "THEN 1 ELSE 0 END) AS low_stock " +
                        "FROM medicines";

        String supplierSQL =
                "SELECT COUNT(*) AS total FROM suppliers";

        String salesSQL =
                "SELECT COUNT(*) AS transactions, " +
                        "COALESCE(SUM(total_amount), 0) AS total_sales " +
                        "FROM sales";

        try (
                java.sql.Connection connection =
                        Connection.getConnection()
        ) {

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    medicineSQL
                            );

                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {
                    totalMedicinesLabel.setText(
                            String.valueOf(
                                    resultSet.getInt("total")
                            )
                    );

                    lowStockLabel.setText(
                            String.valueOf(
                                    resultSet.getInt("low_stock")
                            )
                    );

                    inventoryValueLabel.setText(
                            String.format(
                                    "R %.2f",
                                    resultSet.getDouble("value")
                            )
                    );
                }
            }

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    supplierSQL
                            );

                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {
                    suppliersLabel.setText(
                            String.valueOf(
                                    resultSet.getInt("total")
                            )
                    );
                }
            }

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    salesSQL
                            );

                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {
                    transactionsLabel.setText(
                            String.valueOf(
                                    resultSet.getInt("transactions")
                            )
                    );

                    salesLabel.setText(
                            String.format(
                                    "R %.2f",
                                    resultSet.getDouble("total_sales")
                            )
                    );
                }
            }

        } catch (SQLException e) {
            show_database_error(e);
        }
    }

    private void load_sales_report() {
        salesModel.setRowCount(0);

        String sql =
                "SELECT s.sale_id, " +
                        "s.sale_date, " +
                        "u.full_name, " +
                        "s.total_amount " +
                        "FROM sales s " +
                        "INNER JOIN users u " +
                        "ON s.user_id = u.user_id " +
                        "ORDER BY s.sale_date DESC";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {
                salesModel.addRow(
                        new Object[]{
                                resultSet.getInt("sale_id"),
                                resultSet.getTimestamp("sale_date"),
                                resultSet.getString("full_name"),
                                String.format(
                                        "R %.2f",
                                        resultSet.getDouble(
                                                "total_amount"
                                        )
                                )
                        }
                );
            }

        } catch (SQLException e) {
            show_database_error(e);
        }
    }

    private void load_item_wise_report() {
        itemWiseModel.setRowCount(0);

        String sql =
                "SELECT m.name, " +
                        "SUM(si.quantity_sold) AS quantity_sold, " +
                        "SUM(si.quantity_sold * si.price_at_sale) " +
                        "AS total_revenue " +
                        "FROM sale_items si " +
                        "INNER JOIN medicines m " +
                        "ON si.medicine_id = m.medicine_id " +
                        "GROUP BY m.medicine_id, m.name " +
                        "ORDER BY quantity_sold DESC";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {
                itemWiseModel.addRow(
                        new Object[]{
                                resultSet.getString("name"),
                                resultSet.getInt("quantity_sold"),
                                String.format(
                                        "R %.2f",
                                        resultSet.getDouble(
                                                "total_revenue"
                                        )
                                )
                        }
                );
            }

        } catch (SQLException e) {
            show_database_error(e);
        }
    }

    private void load_low_stock_report() {
        lowStockModel.setRowCount(0);

        String sql =
                "SELECT medicine_id, name, company, " +
                        "quantity_in_stock, reorder_level " +
                        "FROM medicines " +
                        "WHERE quantity_in_stock <= reorder_level " +
                        "ORDER BY quantity_in_stock ASC";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {
                int quantity =
                        resultSet.getInt(
                                "quantity_in_stock"
                        );

                String status =
                        quantity == 0
                                ? "OUT OF STOCK"
                                : "LOW STOCK";

                lowStockModel.addRow(
                        new Object[]{
                                resultSet.getInt("medicine_id"),
                                resultSet.getString("name"),
                                resultSet.getString("company"),
                                quantity,
                                resultSet.getInt("reorder_level"),
                                status
                        }
                );
            }

        } catch (SQLException e) {
            show_database_error(e);
        }
    }

    private void load_expiry_report() {
        expiryModel.setRowCount(0);

        String sql =
                "SELECT medicine_id, name, company, " +
                        "expiry_date, quantity_in_stock, " +
                        "DATEDIFF(expiry_date, CURDATE()) " +
                        "AS days_remaining " +
                        "FROM medicines " +
                        "WHERE expiry_date >= CURDATE() " +
                        "AND expiry_date <= " +
                        "DATE_ADD(CURDATE(), INTERVAL 1 MONTH) " +
                        "ORDER BY expiry_date ASC";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {
                expiryModel.addRow(
                        new Object[]{
                                resultSet.getInt("medicine_id"),
                                resultSet.getString("name"),
                                resultSet.getString("company"),
                                resultSet.getDate("expiry_date"),
                                resultSet.getInt(
                                        "quantity_in_stock"
                                ),
                                resultSet.getInt(
                                        "days_remaining"
                                )
                        }
                );
            }

        } catch (SQLException e) {
            show_database_error(e);
        }
    }

    private void export_report() {
        JFileChooser fileChooser =
                new JFileChooser();

        fileChooser.setDialogTitle(
                "Export PIMS Reports"
        );

        fileChooser.setSelectedFile(
                new File("PIMS_Reports.txt")
        );

        int result =
                fileChooser.showSaveDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file =
                fileChooser.getSelectedFile();

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PrintWriter writer =
                        new PrintWriter(file)
        ) {

            writer.println(
                    "PHARMACY INVENTORY MANAGEMENT SYSTEM"
            );

            writer.println(
                    "===================================="
            );

            writer.println();

            writer.println("SALES REPORT");
            writer.println("------------");

            String salesSQL =
                    "SELECT s.sale_id, s.sale_date, " +
                            "u.full_name, s.total_amount " +
                            "FROM sales s " +
                            "INNER JOIN users u " +
                            "ON s.user_id = u.user_id " +
                            "ORDER BY s.sale_date DESC";

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    salesSQL
                            );

                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                while (resultSet.next()) {
                    writer.println(
                            "Sale ID: "
                                    + resultSet.getInt("sale_id")
                    );

                    writer.println(
                            "Date: "
                                    + resultSet.getTimestamp(
                                    "sale_date"
                            )
                    );

                    writer.println(
                            "Cashier: "
                                    + resultSet.getString(
                                    "full_name"
                            )
                    );

                    writer.printf(
                            "Total: R %.2f%n%n",
                            resultSet.getDouble(
                                    "total_amount"
                            )
                    );
                }
            }

            writer.println();
            writer.println("ITEM-WISE REPORT");
            writer.println("----------------");

            String itemSQL =
                    "SELECT m.name, " +
                            "SUM(si.quantity_sold) AS quantity_sold, " +
                            "SUM(si.quantity_sold * si.price_at_sale) " +
                            "AS total_revenue " +
                            "FROM sale_items si " +
                            "INNER JOIN medicines m " +
                            "ON si.medicine_id = m.medicine_id " +
                            "GROUP BY m.medicine_id, m.name " +
                            "ORDER BY quantity_sold DESC";

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    itemSQL
                            );

                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                while (resultSet.next()) {
                    writer.println(
                            "Medicine: "
                                    + resultSet.getString("name")
                    );

                    writer.println(
                            "Quantity Sold: "
                                    + resultSet.getInt(
                                    "quantity_sold"
                            )
                    );

                    writer.printf(
                            "Revenue: R %.2f%n%n",
                            resultSet.getDouble(
                                    "total_revenue"
                            )
                    );
                }
            }

            writer.println();
            writer.println("LOW STOCK REPORT");
            writer.println("----------------");

            String lowStockSQL =
                    "SELECT name, company, " +
                            "quantity_in_stock, reorder_level " +
                            "FROM medicines " +
                            "WHERE quantity_in_stock <= reorder_level " +
                            "ORDER BY quantity_in_stock ASC";

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    lowStockSQL
                            );

                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                while (resultSet.next()) {
                    writer.println(
                            "Medicine: "
                                    + resultSet.getString("name")
                    );

                    writer.println(
                            "Company: "
                                    + resultSet.getString("company")
                    );

                    writer.println(
                            "Stock: "
                                    + resultSet.getInt(
                                    "quantity_in_stock"
                            )
                    );

                    writer.println(
                            "Reorder level: "
                                    + resultSet.getInt(
                                    "reorder_level"
                            )
                    );

                    writer.println();
                }
            }

            writer.println();
            writer.println("EXPIRY REPORT - ONE MONTH");
            writer.println("-------------------------");

            String expirySQL =
                    "SELECT name, company, expiry_date, " +
                            "quantity_in_stock, " +
                            "DATEDIFF(expiry_date, CURDATE()) " +
                            "AS days_remaining " +
                            "FROM medicines " +
                            "WHERE expiry_date >= CURDATE() " +
                            "AND expiry_date <= " +
                            "DATE_ADD(CURDATE(), INTERVAL 1 MONTH) " +
                            "ORDER BY expiry_date ASC";

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    expirySQL
                            );

                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                while (resultSet.next()) {
                    writer.println(
                            "Medicine: "
                                    + resultSet.getString("name")
                    );

                    writer.println(
                            "Company: "
                                    + resultSet.getString("company")
                    );

                    writer.println(
                            "Expiry Date: "
                                    + resultSet.getDate(
                                    "expiry_date"
                            )
                    );

                    writer.println(
                            "Stock: "
                                    + resultSet.getInt(
                                    "quantity_in_stock"
                            )
                    );

                    writer.println(
                            "Days remaining: "
                                    + resultSet.getInt(
                                    "days_remaining"
                            )
                    );

                    writer.println();
                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Reports exported successfully.",
                    "Export completed",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Reports could not be exported.\n"
                            + e.getMessage(),
                    "Export error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void show_database_error(SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "The report data could not be loaded.\n\n"
                        + e.getMessage(),
                "Database error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}