package com.pims.gui;

import com.pims.database.Connection;
import com.pims.model.Supplier;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierScreen extends JPanel {

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

    private static final Font TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 25);

    private static final Font SUBTITLE_FONT =
            new Font("Segoe UI", Font.PLAIN, 13);

    private static final Font LABEL_FONT =
            new Font("Segoe UI", Font.BOLD, 12);

    private static final Font NORMAL_FONT =
            new Font("Segoe UI", Font.PLAIN, 13);

    private JTextField nameField;
    private JTextField contactField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;

    private JTable supplierTable;
    private DefaultTableModel tableModel;

    private final List<Supplier> suppliers;

    private int selectedSupplierId = -1;

    public SupplierScreen() {

        suppliers = new ArrayList<>();

        setLayout(
                new BorderLayout()
        );

        setBackground(
                SOFT_BACKGROUND
        );

        create_header();
        create_form();
        create_table();

        load_suppliers();
    }

    private void create_header() {

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                SOFT_BACKGROUND
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        22,
                        28,
                        12,
                        28
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
                        "Supplier Management"
                );

        titleLabel.setFont(
                TITLE_FONT
        );

        titleLabel.setForeground(
                DARK_TEAL
        );

        JLabel descriptionLabel =
                new JLabel(
                        "Manage pharmacy suppliers and maintain their contact information."
                );

        descriptionLabel.setFont(
                SUBTITLE_FONT
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

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                12,
                                12
                        )
                );

        formPanel.setBackground(
                WHITE
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                TABLE_LINE,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                16,
                                18,
                                16,
                                18
                        )
                )
        );

        nameField =
                create_field();

        contactField =
                create_field();

        phoneField =
                create_field();

        emailField =
                create_field();

        addressField =
                create_field();

        formPanel.add(
                create_label(
                        "Supplier name"
                )
        );

        formPanel.add(
                nameField
        );

        formPanel.add(
                create_label(
                        "Contact person"
                )
        );

        formPanel.add(
                contactField
        );

        formPanel.add(
                create_label(
                        "Phone number"
                )
        );

        formPanel.add(
                phoneField
        );

        formPanel.add(
                create_label(
                        "Email address"
                )
        );

        formPanel.add(
                emailField
        );

        formPanel.add(
                create_label(
                        "Address"
                )
        );

        formPanel.add(
                addressField
        );

        JButton addButton =
                create_button(
                        "Add supplier",
                        DEEP_TEAL
                );

        JButton updateButton =
                create_button(
                        "Update supplier",
                        GOLD
                );

        JButton deleteButton =
                create_button(
                        "Delete supplier",
                        DELETE_RED
                );

        JButton clearButton =
                create_button(
                        "Clear",
                        new Color(
                                91,
                                106,
                                107
                        )
                );

        formPanel.add(
                addButton
        );

        formPanel.add(
                updateButton
        );

        formPanel.add(
                deleteButton
        );

        formPanel.add(
                clearButton
        );

        JPanel container =
                new JPanel(
                        new BorderLayout()
                );

        container.setBackground(
                SOFT_BACKGROUND
        );

        container.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        28,
                        12,
                        28
                )
        );

        container.setPreferredSize(
                new Dimension(
                        0,
                        190
                )
        );

        container.add(
                formPanel,
                BorderLayout.CENTER
        );

        add(
                container,
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> add_supplier()
        );

        updateButton.addActionListener(
                e -> update_supplier()
        );

        deleteButton.addActionListener(
                e -> delete_supplier()
        );

        clearButton.addActionListener(
                e -> clear_fields()
        );
    }

    private void create_table() {

        String[] columns = {
                "ID",
                "Supplier name",
                "Contact person",
                "Phone",
                "Email",
                "Address"
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

        supplierTable =
                new JTable(
                        tableModel
                );

        supplierTable.setRowHeight(
                30
        );

        supplierTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        supplierTable.setForeground(
                DARK_TEXT
        );

        supplierTable.setBackground(
                WHITE
        );

        supplierTable.setGridColor(
                TABLE_LINE
        );

        supplierTable.setShowGrid(
                true
        );

        supplierTable.setSelectionBackground(
                new Color(
                        222,
                        236,
                        232
                )
        );

        supplierTable.setSelectionForeground(
                DARK_TEXT
        );

        supplierTable.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        JTableHeader header =
                supplierTable.getTableHeader();

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        header.setForeground(
                WHITE
        );

        header.setBackground(
                DARK_TEAL
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        34
                )
        );

        header.setReorderingAllowed(
                false
        );

        supplierTable.setDefaultRenderer(
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

                        setBorder(
                                BorderFactory.createEmptyBorder(
                                        0,
                                        7,
                                        0,
                                        7
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

        supplierTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {
                                load_selected_supplier();
                            }
                        }
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        supplierTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        TABLE_LINE,
                        1
                )
        );

        scrollPane.getViewport()
                .setBackground(
                        WHITE
                );

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBackground(
                SOFT_BACKGROUND
        );

        tablePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        28,
                        25,
                        28
                )
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                tablePanel,
                BorderLayout.SOUTH
        );
    }

    private void load_suppliers() {

        String sql =
                "SELECT supplier_id, name, contact_person, " +
                        "phone, email, address " +
                        "FROM suppliers " +
                        "ORDER BY supplier_id";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql
                        );

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            suppliers.clear();

            tableModel.setRowCount(
                    0
            );

            while (resultSet.next()) {

                Supplier supplier =
                        new Supplier(
                                resultSet.getInt(
                                        "supplier_id"
                                ),
                                resultSet.getString(
                                        "name"
                                ),
                                resultSet.getString(
                                        "contact_person"
                                ),
                                resultSet.getString(
                                        "phone"
                                ),
                                resultSet.getString(
                                        "email"
                                ),
                                resultSet.getString(
                                        "address"
                                )
                        );

                suppliers.add(
                        supplier
                );

                add_to_table(
                        supplier
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load suppliers:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void add_supplier() {

        String name =
                nameField
                        .getText()
                        .trim();

        String contact =
                contactField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String address =
                addressField
                        .getText()
                        .trim();

        if (!check_fields(
                name,
                contact,
                phone,
                email,
                address)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all supplier details.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "INSERT INTO suppliers " +
                        "(name, contact_person, phone, email, address) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql
                        )
        ) {

            statement.setString(
                    1,
                    name
            );

            statement.setString(
                    2,
                    contact
            );

            statement.setString(
                    3,
                    phone
            );

            statement.setString(
                    4,
                    email
            );

            statement.setString(
                    5,
                    address
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Supplier has been added successfully.",
                    "Supplier Captured",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_suppliers();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not add supplier:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void update_supplier() {

        if (selectedSupplierId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a supplier from the table first.",
                    "No Supplier Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String name =
                nameField
                        .getText()
                        .trim();

        String contact =
                contactField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String address =
                addressField
                        .getText()
                        .trim();

        if (!check_fields(
                name,
                contact,
                phone,
                email,
                address)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all supplier details.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "UPDATE suppliers SET " +
                        "name = ?, " +
                        "contact_person = ?, " +
                        "phone = ?, " +
                        "email = ?, " +
                        "address = ? " +
                        "WHERE supplier_id = ?";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql
                        )
        ) {

            statement.setString(
                    1,
                    name
            );

            statement.setString(
                    2,
                    contact
            );

            statement.setString(
                    3,
                    phone
            );

            statement.setString(
                    4,
                    email
            );

            statement.setString(
                    5,
                    address
            );

            statement.setInt(
                    6,
                    selectedSupplierId
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Supplier has been updated successfully.",
                    "Supplier Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_suppliers();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not update supplier:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void delete_supplier() {

        if (selectedSupplierId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a supplier from the table first.",
                    "No Supplier Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this supplier?",
                        "Confirm to Remove Supplier",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String checkSql =
                "SELECT COUNT(*) " +
                        "FROM medicines " +
                        "WHERE supplier_id = ?";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                checkSql
                        )
        ) {

            statement.setInt(
                    1,
                    selectedSupplierId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    int medicineCount =
                            resultSet.getInt(
                                    1
                            );

                    if (medicineCount > 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "This supplier cannot be deleted.\n\n"
                                        + medicineCount
                                        + " medicine(s) are currently linked to this supplier.\n\n"
                                        + "Please assign those medicines to another supplier "
                                        + "before deleting this supplier.",
                                "Supplier Is Occupied",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not check supplier usage:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();

            return;
        }

        String deleteSql =
                "DELETE FROM suppliers " +
                        "WHERE supplier_id = ?";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                deleteSql
                        )
        ) {

            statement.setInt(
                    1,
                    selectedSupplierId
            );

            int rowsDeleted =
                    statement.executeUpdate();

            if (rowsDeleted > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Supplier deleted successfully.",
                        "Supplier Has Been Removed",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clear_fields();
                load_suppliers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "The selected supplier could not be found.",
                        "Deletion Failed",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not delete supplier.\n\n"
                            + "The supplier may still be linked to existing records.",
                    "Delete Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void load_selected_supplier() {

        int selectedRow =
                supplierTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        selectedRow =
                supplierTable.convertRowIndexToModel(
                        selectedRow
                );

        selectedSupplierId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        selectedRow,
                                        0
                                )
                                .toString()
                );

        Supplier selectedSupplier =
                find_supplier(
                        selectedSupplierId
                );

        if (selectedSupplier == null) {
            return;
        }

        nameField.setText(
                selectedSupplier
                        .get_supplier_name()
        );

        contactField.setText(
                selectedSupplier
                        .get_contact_person()
        );

        phoneField.setText(
                selectedSupplier
                        .get_phone()
        );

        emailField.setText(
                selectedSupplier
                        .get_email()
        );

        addressField.setText(
                selectedSupplier
                        .get_address()
        );
    }

    private Supplier find_supplier(
            int supplierId) {

        for (Supplier supplier :
                suppliers) {

            if (supplier.get_supplier_id()
                    == supplierId) {

                return supplier;
            }
        }

        return null;
    }

    private void add_to_table(
            Supplier supplier) {

        tableModel.addRow(
                new Object[]{
                        supplier.get_supplier_id(),
                        supplier.get_supplier_name(),
                        supplier.get_contact_person(),
                        supplier.get_phone(),
                        supplier.get_email(),
                        supplier.get_address()
                }
        );
    }

    private boolean check_fields(
            String name,
            String contact,
            String phone,
            String email,
            String address) {

        return !name.isEmpty()
                && !contact.isEmpty()
                && !phone.isEmpty()
                && !email.isEmpty()
                && !address.isEmpty();
    }

    private JLabel create_label(
            String text) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                LABEL_FONT
        );

        label.setForeground(
                DARK_TEXT
        );

        return label;
    }

    private JTextField create_field() {

        JTextField field =
                new JTextField();

        field.setFont(
                NORMAL_FONT
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
                                5,
                                9,
                                5,
                                9
                        )
                )
        );

        return field;
    }

    private JButton create_button(
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

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private void clear_fields() {

        nameField.setText("");
        contactField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressField.setText("");

        selectedSupplierId = -1;

        supplierTable.clearSelection();

        nameField.requestFocus();
    }
}