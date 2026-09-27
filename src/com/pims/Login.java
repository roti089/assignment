package com.pims;

import com.pims.database.Connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private static final Color DEEP_TEAL =
            new Color(18, 70, 72);

    private static final Color DARK_TEAL =
            new Color(11, 45, 47);

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

    public Login() {

        setTitle(
                "HealthFirst Pharmacy Inventory Management System"
        );

        setSize(
                850,
                520
        );

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

        JPanel brandingPanel =
                create_branding_panel();

        mainPanel.add(
                brandingPanel,
                BorderLayout.WEST
        );

        JPanel loginArea =
                create_login_area();

        mainPanel.add(
                loginArea,
                BorderLayout.CENTER
        );

        JPanel footerPanel =
                create_footer();

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        loginButton.addActionListener(
                e -> login()
        );

        passwordField.addActionListener(
                e -> login()
        );

        getRootPane().setDefaultButton(
                loginButton
        );

        setVisible(true);
    }

    private JPanel create_branding_panel() {

        JPanel brandingPanel =
                new JPanel();

        brandingPanel.setPreferredSize(
                new Dimension(
                        340,
                        520
                )
        );

        brandingPanel.setBackground(
                DEEP_TEAL
        );

        brandingPanel.setLayout(
                new BoxLayout(
                        brandingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        brandingPanel.setBorder(
                new EmptyBorder(
                        55,
                        45,
                        45,
                        45
                )
        );

        JLabel logoLabel =
                new JLabel(
                        "HealthFirst"
                );

        logoLabel.setForeground(
                Color.WHITE
        );

        logoLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        48
                )
        );

        logoLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(logoLabel);

        JPanel accentLine =
                new JPanel();

        accentLine.setBackground(
                GOLD
        );

        accentLine.setMaximumSize(
                new Dimension(
                        65,
                        4
                )
        );

        accentLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(
                Box.createVerticalStrut(10)
        );

        brandingPanel.add(accentLine);

        brandingPanel.add(
                Box.createVerticalStrut(30)
        );

        JLabel pharmacyLabel =
                new JLabel(
                        "<html>PHARMACY<br>OPERATIONS</html>"
                );

        pharmacyLabel.setForeground(
                Color.WHITE
        );

        pharmacyLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        pharmacyLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(pharmacyLabel);

        brandingPanel.add(
                Box.createVerticalStrut(18)
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html><div style='width:220px;'>"
                                + "A centralized platform for managing "
                                + "medicine inventory, suppliers, sales "
                                + "and pharmacy reports."
                                + "</div></html>"
                );

        descriptionLabel.setForeground(
                new Color(
                        220,
                        235,
                        232
                )
        );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        descriptionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(descriptionLabel);

        brandingPanel.add(
                Box.createVerticalGlue()
        );

        JLabel inventoryLabel =
                create_feature_label(
                        "INVENTORY MANAGEMENT"
                );

        JLabel salesLabel =
                create_feature_label(
                        "POINT OF SALE"
                );

        JLabel reportsLabel =
                create_feature_label(
                        "BUSINESS REPORTING"
                );

        brandingPanel.add(inventoryLabel);

        brandingPanel.add(
                Box.createVerticalStrut(8)
        );

        brandingPanel.add(salesLabel);

        brandingPanel.add(
                Box.createVerticalStrut(8)
        );

        brandingPanel.add(reportsLabel);

        brandingPanel.add(
                Box.createVerticalStrut(20)
        );

        JLabel versionLabel =
                new JLabel(
                        "PIMS • SYSTEM ACCESS"
                );

        versionLabel.setForeground(
                GOLD_LIGHT
        );

        versionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        versionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(versionLabel);

        return brandingPanel;
    }

    private JPanel create_login_area() {

        JPanel loginArea =
                new JPanel(
                        new GridBagLayout()
                );

        loginArea.setBackground(
                BACKGROUND
        );

        loginArea.setBorder(
                new EmptyBorder(
                        35,
                        45,
                        35,
                        45
                )
        );

        JPanel loginCard =
                new JPanel();

        loginCard.setBackground(
                CARD
        );

        loginCard.setPreferredSize(
                new Dimension(
                        390,
                        390
                )
        );

        loginCard.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                35,
                                40,
                                30,
                                40
                        )
                )
        );

        loginCard.setLayout(
                new BoxLayout(
                        loginCard,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel accessLabel =
                new JLabel(
                        "STAFF ACCESS"
                );

        accessLabel.setForeground(
                DEEP_TEAL
        );

        accessLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        accessLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        loginCard.add(accessLabel);

        loginCard.add(
                Box.createVerticalStrut(6)
        );

        JLabel welcomeLabel =
                new JLabel(
                        "Sign in to continue to PIMS"
                );

        welcomeLabel.setForeground(
                MUTED_TEXT
        );

        welcomeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        welcomeLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        loginCard.add(welcomeLabel);

        loginCard.add(
                Box.createVerticalStrut(28)
        );

        JLabel usernameLabel =
                create_field_label(
                        "USERNAME"
                );

        loginCard.add(usernameLabel);

        loginCard.add(
                Box.createVerticalStrut(7)
        );

        usernameField =
                new JTextField();

        style_text_field(
                usernameField
        );

        loginCard.add(usernameField);

        loginCard.add(
                Box.createVerticalStrut(18)
        );

        JLabel passwordLabel =
                create_field_label(
                        "PASSWORD"
                );

        loginCard.add(passwordLabel);

        loginCard.add(
                Box.createVerticalStrut(7)
        );

        passwordField =
                new JPasswordField();

        style_text_field(
                passwordField
        );

        loginCard.add(passwordField);

        loginCard.add(
                Box.createVerticalStrut(25)
        );

        loginButton =
                new JButton(
                        "SIGN IN"
                );

        loginButton.setPreferredSize(
                new Dimension(
                        100,
                        45
                )
        );

        loginButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        loginButton.setBackground(
                DEEP_TEAL
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        loginButton.setFocusPainted(false);

        loginButton.setBorderPainted(false);

        loginButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        loginCard.add(loginButton);

        loginCard.add(
                Box.createVerticalStrut(20)
        );

        JLabel securityLabel =
                new JLabel(
                        "Authorized pharmacy personnel only"
                );

        securityLabel.setForeground(
                MUTED_TEXT
        );

        securityLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        securityLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginCard.add(securityLabel);

        loginArea.add(loginCard);

        return loginArea;
    }

    private JPanel create_footer() {

        JLabel footerLabel =
                new JLabel(
                        "Pharmacy Inventory Management System"
                );

        footerLabel.setForeground(
                MUTED_TEXT
        );

        footerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        10
                )
        );

        footerLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(
                BACKGROUND
        );

        footerPanel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );

        footerPanel.add(
                footerLabel,
                BorderLayout.CENTER
        );

        return footerPanel;
    }

    private JLabel create_feature_label(
            String text) {

        JLabel label =
                new JLabel(
                        "•  " + text
                );

        label.setForeground(
                new Color(
                        208,
                        226,
                        222
                )
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    private JLabel create_field_label(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setForeground(
                TEXT
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    private void style_text_field(
            JTextField field) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setForeground(
                TEXT
        );

        field.setBackground(
                new Color(
                        250,
                        252,
                        251
                )
        );

        field.setCaretColor(
                DEEP_TEAL
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                7,
                                10,
                                7,
                                10
                        )
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your username and password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "SELECT user_id, username, password, role, full_name " +
                        "FROM users WHERE username = ?";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                int userId =
                        resultSet.getInt(
                                "user_id"
                        );

                String storedPassword =
                        resultSet.getString(
                                "password"
                        );

                String role =
                        resultSet.getString(
                                "role"
                        );

                String fullName =
                        resultSet.getString(
                                "full_name"
                        );

                boolean validPassword = false;

                if (username.equals("admin")
                        && password.equals("admin123")) {

                    validPassword =
                            role.equals("Admin");

                } else if (username.equals("cashier")
                        && password.equals("cash123")) {

                    validPassword =
                            role.equals("Cashier");
                }

                if (validPassword) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Login successful!\nWelcome, "
                                    + fullName,
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    dispose();

                    new Dashboard(
                            role,
                            userId
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid username or password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );

                    passwordField.setText("");

                    passwordField.requestFocus();
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                passwordField.setText("");

                passwordField.requestFocus();
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database connection error:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
}