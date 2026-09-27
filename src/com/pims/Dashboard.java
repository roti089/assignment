package com.pims;

import com.pims.gui.MedicineScreen;
import com.pims.gui.SupplierScreen;
import com.pims.gui.SalesScreen;
import com.pims.gui.StockCheckScreen;
import com.pims.gui.UserManagementScreen;
import com.pims.gui.ReportsScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Dashboard extends JFrame {

    private JPanel contentPanel;
    private String userRole;
    private int userId;

    private static final Color DEEP_TEAL =
            new Color(27, 107, 111);

    private static final Color DARK_TEAL =
            new Color(10, 37, 39);

    private static final Color GOLD =
            new Color(224, 169, 70);

    private static final Color GOLD_LIGHT =
            new Color(244, 218, 163);

    private static final Color BACKGROUND =
            new Color(244, 248, 246);

    private static final Color CARD =
            Color.WHITE;

    private static final Color TEXT =
            new Color(35, 43, 44);

    private static final Color MUTED_TEXT =
            new Color(103, 116, 117);

    private static final Color BORDER =
            new Color(214, 224, 220);

    private static final Color SUCCESS =
            new Color(39, 125, 90);

    private static final Color WARNING =
            new Color(216, 137, 50);

    public Dashboard(String role, int userId) {

        userRole = role;
        this.userId = userId;

        setTitle(
                "HealthFirst Pharmacy Inventory Management System"
        );

        setSize(1100, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        JPanel sidebar =
                create_sidebar();

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        show_dashboard();

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        setVisible(true);
    }

    private JPanel create_sidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        235,
                        700
                )
        );

        sidebar.setBackground(
                DEEP_TEAL
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(
                        35,
                        20,
                        20,
                        20
                )
        );

        JLabel logoLabel =
                new JLabel("PIMS");

        logoLabel.setForeground(
                Color.WHITE
        );

        logoLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        34
                )
        );

        logoLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(logoLabel);

        JPanel accentLine =
                new JPanel();

        accentLine.setBackground(
                GOLD
        );

        accentLine.setMaximumSize(
                new Dimension(
                        55,
                        4
                )
        );

        accentLine.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(accentLine);

        JLabel subtitleLabel =
                new JLabel(
                        "PHARMACY OPERATIONS"
                );

        subtitleLabel.setForeground(
                GOLD_LIGHT
        );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        sidebar.add(subtitleLabel);

        JLabel roleLabel =
                new JLabel(
                        "Logged in as: " + userRole
                );

        roleLabel.setForeground(
                new Color(
                        210,
                        228,
                        224
                )
        );

        roleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        roleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(
                Box.createVerticalStrut(7)
        );

        sidebar.add(roleLabel);

        sidebar.add(
                Box.createVerticalStrut(30)
        );

        JButton dashboardButton =
                create_menu_button(
                        "Dashboard"
                );

        JButton medicineButton =
                create_menu_button(
                        "Medicines"
                );

        JButton supplierButton =
                create_menu_button(
                        "Suppliers"
                );

        JButton userManagementButton =
                create_menu_button(
                        "User Management"
                );

        JButton salesButton =
                create_menu_button(
                        "Sales / POS"
                );

        JButton stockCheckButton =
                create_menu_button(
                        "Stock Check"
                );

        JButton reportsButton =
                create_menu_button(
                        "Reports"
                );

        JButton logoutButton =
                create_menu_button(
                        "Logout"
                );

        if (userRole.equals("Admin")) {

            salesButton.setVisible(false);
            stockCheckButton.setVisible(false);

        } else if (userRole.equals("Cashier")) {

            medicineButton.setVisible(false);
            supplierButton.setVisible(false);
            userManagementButton.setVisible(false);
            reportsButton.setVisible(false);
        }

        sidebar.add(dashboardButton);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(medicineButton);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(supplierButton);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(userManagementButton);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(salesButton);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(stockCheckButton);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(reportsButton);

        sidebar.add(
                Box.createVerticalGlue()
        );

        sidebar.add(logoutButton);

        dashboardButton.addActionListener(
                e -> show_dashboard()
        );

        medicineButton.addActionListener(
                e -> show_medicine_panel()
        );

        supplierButton.addActionListener(
                e -> show_supplier_panel()
        );

        userManagementButton.addActionListener(
                e -> show_user_management_panel()
        );

        salesButton.addActionListener(
                e -> show_sales_panel()
        );

        stockCheckButton.addActionListener(
                e -> show_stock_check_panel()
        );

        reportsButton.addActionListener(
                e -> show_reports_panel()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        return sidebar;
    }

    private JButton create_menu_button(
            String text) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        195,
                        42
                )
        );

        button.setPreferredSize(
                new Dimension(
                        195,
                        42
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setForeground(
                new Color(
                        235,
                        245,
                        242
                )
        );

        button.setBackground(
                DEEP_TEAL
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        0,
                        15,
                        0,
                        10
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private JPanel create_statistic_card(
            String title,
            String value,
            Color accentColor) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                18,
                                15,
                                18,
                                15
                        )
                )
        );

        JPanel accentBar =
                new JPanel();

        accentBar.setBackground(
                accentColor
        );

        accentBar.setMaximumSize(
                new Dimension(
                        45,
                        4
                )
        );

        accentBar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(accentBar);

        card.add(
                Box.createVerticalStrut(12)
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                MUTED_TEXT
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(
                TEXT
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        valueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(7)
        );

        card.add(valueLabel);

        return card;
    }

    private void show_dashboard() {

        contentPanel.removeAll();

        JPanel dashboardPanel =
                new JPanel(
                        new BorderLayout()
                );

        dashboardPanel.setBackground(
                BACKGROUND
        );

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                BACKGROUND
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        30,
                        35,
                        15,
                        35
                )
        );

        JPanel titleArea =
                new JPanel();

        titleArea.setBackground(
                BACKGROUND
        );

        titleArea.setLayout(
                new BoxLayout(
                        titleArea,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Dashboard"
                );

        titleLabel.setForeground(
                TEXT
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        titleArea.add(titleLabel);

        titleArea.add(
                Box.createVerticalStrut(5)
        );

        JLabel welcomeLabel =
                new JLabel(
                        "HealthFirst operations overview"
                );

        welcomeLabel.setForeground(
                MUTED_TEXT
        );

        welcomeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        welcomeLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        titleArea.add(welcomeLabel);

        headerPanel.add(
                titleArea,
                BorderLayout.WEST
        );

        JLabel roleBadge =
                new JLabel(
                        userRole.toUpperCase()
                );

        roleBadge.setForeground(
                DEEP_TEAL
        );

        roleBadge.setBackground(
                GOLD_LIGHT
        );

        roleBadge.setOpaque(true);

        roleBadge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        roleBadge.setBorder(
                new EmptyBorder(
                        8,
                        14,
                        8,
                        14
                )
        );

        headerPanel.add(
                roleBadge,
                BorderLayout.EAST
        );

        dashboardPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                15
                        )
                );

        statisticsPanel.setBackground(
                BACKGROUND
        );

        statisticsPanel.setBorder(
                new EmptyBorder(
                        10,
                        35,
                        25,
                        35
                )
        );

        statisticsPanel.add(
                create_statistic_card(
                        "TOTAL MEDICINES",
                        "2",
                        DEEP_TEAL
                )
        );

        statisticsPanel.add(
                create_statistic_card(
                        "LOW STOCK",
                        "0",
                        WARNING
                )
        );

        statisticsPanel.add(
                create_statistic_card(
                        "SUPPLIERS",
                        "2",
                        GOLD
                )
        );

        statisticsPanel.add(
                create_statistic_card(
                        "TODAY'S SALES",
                        "R 0.00",
                        SUCCESS
                )
        );

        dashboardPanel.add(
                statisticsPanel,
                BorderLayout.CENTER
        );

        JPanel infoPanel =
                new JPanel();

        infoPanel.setBackground(
                BACKGROUND
        );

        infoPanel.setLayout(
                new BoxLayout(
                        infoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        infoPanel.setBorder(
                new EmptyBorder(
                        5,
                        35,
                        30,
                        35
                )
        );

        JLabel informationLabel =
                new JLabel(
                        "System Overview"
                );

        informationLabel.setForeground(
                TEXT
        );

        informationLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        informationLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        infoPanel.add(
                informationLabel
        );

        infoPanel.add(
                Box.createVerticalStrut(8)
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html>Use the navigation menu to manage "
                                + "medicines, suppliers, users, sales, "
                                + "stock levels and pharmacy reports."
                                + "</html>"
                );

        descriptionLabel.setForeground(
                MUTED_TEXT
        );

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

        infoPanel.add(
                descriptionLabel
        );

        infoPanel.add(
                Box.createVerticalStrut(18)
        );

        JPanel divider =
                new JPanel();

        divider.setBackground(
                GOLD
        );

        divider.setMaximumSize(
                new Dimension(
                        55,
                        3
                )
        );

        divider.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        infoPanel.add(divider);

        dashboardPanel.add(
                infoPanel,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                dashboardPanel,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void show_medicine_panel() {

        contentPanel.removeAll();

        contentPanel.add(
                new MedicineScreen(),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void show_supplier_panel() {

        contentPanel.removeAll();

        contentPanel.add(
                new SupplierScreen(),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void show_sales_panel() {

        contentPanel.removeAll();

        contentPanel.add(
                new SalesScreen(userId),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void show_stock_check_panel() {

        contentPanel.removeAll();

        contentPanel.add(
                new StockCheckScreen(),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void show_reports_panel() {

        contentPanel.removeAll();

        contentPanel.add(
                new ReportsScreen(),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void show_user_management_panel() {

        contentPanel.removeAll();

        contentPanel.add(
                new UserManagementScreen(),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void show_message(
            String section) {

        contentPanel.removeAll();

        JLabel label =
                new JLabel(
                        section,
                        SwingConstants.CENTER
                );

        label.setForeground(
                TEXT
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        contentPanel.add(
                label,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            dispose();

            new Login();
        }
    }
}