package com.pims.gui;

import com.pims.database.Connection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class UserManagementScreen extends JPanel {

    private static final Color DEEP_TEAL =
            new Color(18, 70, 73);

    private static final Color DARK_TEAL =
            new Color(11, 48, 50);

    private static final Color GOLD =
            new Color(217, 164, 65);

    private static final Color SOFT_BACKGROUND =
            new Color(244, 247, 245);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color DARK_TEXT =
            new Color(36, 50, 52);

    private static final Color MUTED_TEXT =
            new Color(105, 119, 120);

    private static final Color DELETE_RED =
            new Color(184, 62, 75);

    private static final Color TABLE_LINE =
            new Color(224, 231, 228);

    private JTable userTable;
    private DefaultTableModel tableModel;

    private JTextField usernameField;
    private JTextField fullNameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    private int selectedUserId = -1;

    public UserManagementScreen() {

        setLayout(
                new BorderLayout(
                        12,
                        12
                )
        );

        setBackground(
                SOFT_BACKGROUND
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        28,
                        20,
                        28
                )
        );

        create_header();
        create_form();
        create_table();

        load_users();
    }

    private void create_header() {

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(false);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        8,
                        0
                )
        );

        JPanel titleArea =
                new JPanel();

        titleArea.setOpaque(false);

        titleArea.setLayout(
                new BoxLayout(
                        titleArea,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "User Management"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        titleLabel.setForeground(
                DARK_TEAL
        );

        JLabel descriptionLabel =
                new JLabel(
                        "Create and maintain administrator and cashier accounts."
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                MUTED_TEXT
        );

        titleArea.add(
                titleLabel
        );

        titleArea.add(
                Box.createVerticalStrut(5)
        );

        titleArea.add(
                descriptionLabel
        );

        JPanel accent =
                new JPanel();

        accent.setBackground(
                GOLD
        );

        accent.setPreferredSize(
                new Dimension(
                        5,
                        48
                )
        );

        accent.setMaximumSize(
                new Dimension(
                        5,
                        48
                )
        );

        headerPanel.add(
                accent,
                BorderLayout.WEST
        );

        headerPanel.add(
                titleArea,
                BorderLayout.CENTER
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );
    }

    private void create_form() {

        JPanel formCard =
                new JPanel(
                        new BorderLayout(
                                15,
                                12
                        )
                );

        formCard.setBackground(
                WHITE
        );

        formCard.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                TABLE_LINE,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                12,
                                10
                        )
                );

        formPanel.setOpaque(false);

        usernameField =
                create_text_field();

        fullNameField =
                create_text_field();

        passwordField =
                create_password_field();

        roleComboBox =
                new JComboBox<>(
                        new String[]{
                                "Cashier",
                                "Admin"
                        }
                );

        style_combo_box(
                roleComboBox
        );

        formPanel.add(
                create_field_panel(
                        "Username",
                        usernameField
                )
        );

        formPanel.add(
                create_field_panel(
                        "Full Name",
                        fullNameField
                )
        );

        formPanel.add(
                create_field_panel(
                        "Password",
                        passwordField
                )
        );

        formPanel.add(
                create_field_panel(
                        "Access Role",
                        roleComboBox
                )
        );

        formCard.add(
                formPanel,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton addButton =
                create_action_button(
                        "Add User",
                        DEEP_TEAL
                );

        JButton updateButton =
                create_action_button(
                        "Update User",
                        GOLD
                );

        JButton deleteButton =
                create_action_button(
                        "Delete User",
                        DELETE_RED
                );

        JButton clearButton =
                create_action_button(
                        "Clear",
                        new Color(
                                91,
                                106,
                                107
                        )
                );

        buttonPanel.add(
                addButton
        );

        buttonPanel.add(
                updateButton
        );

        buttonPanel.add(
                deleteButton
        );

        buttonPanel.add(
                clearButton
        );

        formCard.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(
                formCard,
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> add_user()
        );

        updateButton.addActionListener(
                e -> update_user()
        );

        deleteButton.addActionListener(
                e -> delete_user()
        );

        clearButton.addActionListener(
                e -> clear_fields()
        );
    }

    private JPanel create_field_panel(
            String labelText,
            JComponent field) {

        JPanel panel =
                new JPanel();

        panel.setOpaque(false);

        panel.setLayout(
                new BorderLayout(
                        0,
                        5
                )
        );

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(
                DARK_TEXT
        );

        panel.add(
                label,
                BorderLayout.NORTH
        );

        panel.add(
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JTextField create_text_field() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setForeground(
                DARK_TEXT
        );

        field.setBackground(
                WHITE
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        202,
                                        214,
                                        211
                                ),
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                6,
                                9,
                                6,
                                9
                        )
                )
        );

        return field;
    }

    private JPasswordField create_password_field() {

        JPasswordField field =
                new JPasswordField();

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setForeground(
                DARK_TEXT
        );

        field.setBackground(
                WHITE
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        202,
                                        214,
                                        211
                                ),
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                6,
                                9,
                                6,
                                9
                        )
                )
        );

        return field;
    }

    private void style_combo_box(
            JComboBox<String> comboBox) {

        comboBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        comboBox.setForeground(
                DARK_TEXT
        );

        comboBox.setBackground(
                WHITE
        );

        comboBox.setBorder(
                new LineBorder(
                        new Color(
                                202,
                                214,
                                211
                        ),
                        1,
                        true
                )
        );
    }

    private JButton create_action_button(
            String text,
            Color background) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setPreferredSize(
                new Dimension(
                        115,
                        34
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private void create_table() {

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Username",
                                "Full Name",
                                "Role"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        userTable =
                new JTable(
                        tableModel
                );

        userTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        userTable.setRowHeight(30);

        userTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        userTable.setForeground(
                DARK_TEXT
        );

        userTable.setBackground(
                WHITE
        );

        userTable.setGridColor(
                TABLE_LINE
        );

        userTable.setShowGrid(true);

        userTable.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        userTable.setSelectionBackground(
                new Color(
                        222,
                        236,
                        232
                )
        );

        userTable.setSelectionForeground(
                DARK_TEXT
        );

        userTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        userTable
                .getTableHeader()
                .setForeground(
                        WHITE
                );

        userTable
                .getTableHeader()
                .setBackground(
                        DARK_TEAL
                );

        userTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                34
                        )
                );

        userTable
                .getTableHeader()
                .setReorderingAllowed(false);

        userTable.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component
                    getTableCellRendererComponent(
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

                        setBorder(
                                BorderFactory.createEmptyBorder(
                                        0,
                                        8,
                                        0,
                                        8
                                )
                        );

                        if (isSelected) {

                            component.setBackground(
                                    new Color(
                                            222,
                                            236,
                                            232
                                    )
                            );

                            component.setForeground(
                                    DARK_TEXT
                            );

                        } else if (row % 2 == 0) {

                            component.setBackground(
                                    WHITE
                            );

                            component.setForeground(
                                    DARK_TEXT
                            );

                        } else {

                            component.setBackground(
                                    new Color(
                                            248,
                                            250,
                                            249
                                    )
                            );

                            component.setForeground(
                                    DARK_TEXT
                            );
                        }

                        return component;
                    }
                }
        );

        userTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {
                                load_selected_user();
                            }
                        }
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        userTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        TABLE_LINE,
                        1
                )
        );

        scrollPane
                .getViewport()
                .setBackground(
                        WHITE
                );

        JPanel tableContainer =
                new JPanel(
                        new BorderLayout()
                );

        tableContainer.setBackground(
                SOFT_BACKGROUND
        );

        tableContainer.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        0,
                        0,
                        0
                )
        );

        tableContainer.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                tableContainer,
                BorderLayout.SOUTH
        );
    }

    private void load_users() {

        tableModel.setRowCount(0);

        String sql =
                "SELECT user_id, username, full_name, role " +
                        "FROM users ORDER BY user_id";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                tableModel.addRow(
                        new Object[]{
                                resultSet.getInt(
                                        "user_id"
                                ),
                                resultSet.getString(
                                        "username"
                                ),
                                resultSet.getString(
                                        "full_name"
                                ),
                                resultSet.getString(
                                        "role"
                                )
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load users.\n"
                            + e.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void add_user() {

        String username =
                usernameField.getText().trim();

        String fullName =
                fullNameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String role =
                roleComboBox
                        .getSelectedItem()
                        .toString();

        if (username.isEmpty()
                || fullName.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields.",
                    "Missing information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "INSERT INTO users " +
                        "(username, password, role, full_name) " +
                        "VALUES (?, ?, ?, ?)";

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

            statement.setString(
                    2,
                    password
            );

            statement.setString(
                    3,
                    role
            );

            statement.setString(
                    4,
                    fullName
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "User has been added successfully.",
                    "User saved",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_users();

        } catch (
                SQLIntegrityConstraintViolationException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "This username is already in use.",
                    "Duplicate username",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not add user.\n"
                            + e.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void update_user() {

        if (selectedUserId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a user first.",
                    "No user has been selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String username =
                usernameField.getText().trim();

        String fullName =
                fullNameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String role =
                roleComboBox
                        .getSelectedItem()
                        .toString();

        if (username.isEmpty()
                || fullName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username and full name are required.",
                    "Missing information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql;

        if (password.isEmpty()) {

            sql =
                    "UPDATE users SET " +
                            "username = ?, " +
                            "full_name = ?, " +
                            "role = ? " +
                            "WHERE user_id = ?";

        } else {

            sql =
                    "UPDATE users SET " +
                            "username = ?, " +
                            "full_name = ?, " +
                            "password = ?, " +
                            "role = ? " +
                            "WHERE user_id = ?";
        }

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (password.isEmpty()) {

                statement.setString(
                        1,
                        username
                );

                statement.setString(
                        2,
                        fullName
                );

                statement.setString(
                        3,
                        role
                );

                statement.setInt(
                        4,
                        selectedUserId
                );

            } else {

                statement.setString(
                        1,
                        username
                );

                statement.setString(
                        2,
                        fullName
                );

                statement.setString(
                        3,
                        password
                );

                statement.setString(
                        4,
                        role
                );

                statement.setInt(
                        5,
                        selectedUserId
                );
            }

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "User has been updated successfully.",
                    "User updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_users();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not update user.\n"
                            + e.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void delete_user() {

        if (selectedUserId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a user first.",
                    "No user has been selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this user?",
                        "Confirm to remove this user",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM users WHERE user_id = ?";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    selectedUserId
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "User deleted successfully.",
                    "User has been removed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_users();

        } catch (
                SQLIntegrityConstraintViolationException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "This user cannot be deleted because "
                            + "they have existing sales records.",
                    "Deletion not allowed",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not delete user.\n"
                            + e.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void load_selected_user() {

        int selectedRow =
                userTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        selectedUserId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        selectedRow,
                                        0
                                )
                                .toString()
                );

        usernameField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                1
                        )
                        .toString()
        );

        fullNameField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                2
                        )
                        .toString()
        );

        roleComboBox.setSelectedItem(
                tableModel
                        .getValueAt(
                                selectedRow,
                                3
                        )
                        .toString()
        );

        passwordField.setText("");
    }

    private void clear_fields() {

        selectedUserId = -1;

        usernameField.setText("");
        fullNameField.setText("");
        passwordField.setText("");

        roleComboBox.setSelectedItem(
                "Cashier"
        );

        userTable.clearSelection();

        usernameField.requestFocus();
    }
}