package com.pims.gui;

import com.pims.database.Connection;
import com.pims.model.Medicine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineScreen extends JPanel {

    private JTextField medicineNameField;
    private JTextField categoryField;
    private JTextField batchNumberField;
    private JTextField expiryDateField;
    private JTextField quantityField;
    private JTextField reorderLevelField;
    private JTextField unitPriceField;

    private JComboBox<String> supplierComboBox;

    private JTable medicineTable;
    private DefaultTableModel tableModel;

    private List<Medicine> medicines;

    private int selectedMedicineId = -1;

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

    private static final Color SUCCESS =
            new Color(39, 125, 90);

    private static final Color DANGER =
            new Color(190, 66, 66);

    public MedicineScreen() {

        medicines = new ArrayList<>();

        setLayout(
                new BorderLayout()
        );

        setBackground(
                BACKGROUND
        );

        create_header();
        create_form();
        create_table();

        load_suppliers();
        load_medicines();
    }

    private void create_header() {

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                BACKGROUND
        );

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

        titlePanel.setBackground(
                BACKGROUND
        );

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Medicine Inventory"
                );

        titleLabel.setForeground(
                TEXT
        );

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

        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        JLabel descriptionLabel =
                new JLabel(
                        "Manage medicines, stock, prices and expiry data."
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

        titlePanel.add(
                descriptionLabel
        );

        JPanel accentBar =
                new JPanel();

        accentBar.setBackground(
                GOLD
        );

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

        titlePanel.add(
                Box.createVerticalStrut(10)
        );

        titlePanel.add(
                accentBar
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        JLabel sectionBadge =
                new JLabel(
                        "INVENTORY CONTROL"
                );

        sectionBadge.setForeground(
                DEEP_TEAL
        );

        sectionBadge.setBackground(
                GOLD_LIGHT
        );

        sectionBadge.setOpaque(true);

        sectionBadge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        sectionBadge.setBorder(
                new EmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        headerPanel.add(
                sectionBadge,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );
    }

    private void create_form() {

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                CARD
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        8,
                        5,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        medicineNameField =
                new JTextField();

        categoryField =
                new JTextField();

        batchNumberField =
                new JTextField();

        expiryDateField =
                new JTextField();

        quantityField =
                new JTextField();

        reorderLevelField =
                new JTextField();

        unitPriceField =
                new JTextField();

        supplierComboBox =
                new JComboBox<>();

        style_text_field(
                medicineNameField
        );

        style_text_field(
                categoryField
        );

        style_text_field(
                batchNumberField
        );

        style_text_field(
                expiryDateField
        );

        style_text_field(
                quantityField
        );

        style_text_field(
                reorderLevelField
        );

        style_text_field(
                unitPriceField
        );

        style_combo_box(
                supplierComboBox
        );

        add_form_field(
                formPanel,
                gbc,
                "Medicine Name",
                medicineNameField,
                0,
                0
        );

        add_form_field(
                formPanel,
                gbc,
                "Category",
                categoryField,
                2,
                0
        );

        add_form_field(
                formPanel,
                gbc,
                "Batch Number",
                batchNumberField,
                0,
                1
        );

        add_form_field(
                formPanel,
                gbc,
                "Expiry Date",
                expiryDateField,
                2,
                1
        );

        add_form_field(
                formPanel,
                gbc,
                "Quantity",
                quantityField,
                0,
                2
        );

        add_form_field(
                formPanel,
                gbc,
                "Reorder Level",
                reorderLevelField,
                2,
                2
        );

        add_form_field(
                formPanel,
                gbc,
                "Unit Price",
                unitPriceField,
                0,
                3
        );

        add_form_field(
                formPanel,
                gbc,
                "Supplier",
                supplierComboBox,
                2,
                3
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                4
                        )
                );

        buttonPanel.setBackground(
                CARD
        );

        JButton addButton =
                create_action_button(
                        "Add Medicine",
                        DEEP_TEAL
                );

        JButton updateButton =
                create_action_button(
                        "Update Medicine",
                        SUCCESS
                );

        JButton deleteButton =
                create_action_button(
                        "Delete Medicine",
                        DANGER
                );

        JButton clearButton =
                create_action_button(
                        "Clear",
                        new Color(
                                100,
                                112,
                                113
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

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.weightx = 1;

        formPanel.add(
                buttonPanel,
                gbc
        );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout()
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        centerPanel.setBorder(
                new EmptyBorder(
                        0,
                        30,
                        10,
                        30
                )
        );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> add_medicine()
        );

        updateButton.addActionListener(
                e -> update_medicine()
        );

        deleteButton.addActionListener(
                e -> delete_medicine()
        );

        clearButton.addActionListener(
                e -> clear_fields()
        );
    }

    private void create_table() {

        String[] columns = {
                "ID",
                "Medicine Name",
                "Category",
                "Batch Number",
                "Expiry Date",
                "Quantity",
                "Reorder Level",
                "Unit Price",
                "Supplier ID"
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

        medicineTable =
                new JTable(
                        tableModel
                );

        medicineTable.setRowHeight(
                30
        );

        medicineTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        medicineTable.setForeground(
                TEXT
        );

        medicineTable.setBackground(
                Color.WHITE
        );

        medicineTable.setSelectionBackground(
                GOLD_LIGHT
        );

        medicineTable.setSelectionForeground(
                DARK_TEAL
        );

        medicineTable.setGridColor(
                new Color(
                        232,
                        238,
                        235
                )
        );

        medicineTable.setShowVerticalLines(
                false
        );

        medicineTable.setShowHorizontalLines(
                true
        );

        medicineTable.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        medicineTable.setAutoCreateRowSorter(
                true
        );

        JTableHeader tableHeader =
                medicineTable.getTableHeader();

        tableHeader.setPreferredSize(
                new Dimension(
                        0,
                        36
                )
        );

        tableHeader.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        tableHeader.setForeground(
                Color.WHITE
        );

        tableHeader.setBackground(
                DEEP_TEAL
        );

        tableHeader.setReorderingAllowed(
                false
        );

        DefaultTableCellRenderer cellRenderer =
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

                        if (!isSelected) {

                            if (row % 2 == 0) {

                                component.setBackground(
                                        Color.WHITE
                                );

                            } else {

                                component.setBackground(
                                        new Color(
                                                248,
                                                251,
                                                249
                                        )
                                );
                            }

                            component.setForeground(
                                    TEXT
                            );
                        }

                        setBorder(
                                new EmptyBorder(
                                        0,
                                        6,
                                        0,
                                        6
                                )
                        );

                        return component;
                    }
                };

        medicineTable.setDefaultRenderer(
                Object.class,
                cellRenderer
        );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        medicineTable
                .getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        medicineTable
                .getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centerRenderer
                );

        medicineTable
                .getColumnModel()
                .getColumn(6)
                .setCellRenderer(
                        centerRenderer
                );

        medicineTable
                .getColumnModel()
                .getColumn(8)
                .setCellRenderer(
                        centerRenderer
                );

        medicineTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(45);

        medicineTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(150);

        medicineTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(90);

        medicineTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(110);

        medicineTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(100);

        medicineTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(70);

        medicineTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(90);

        medicineTable
                .getColumnModel()
                .getColumn(7)
                .setPreferredWidth(90);

        medicineTable
                .getColumnModel()
                .getColumn(8)
                .setPreferredWidth(80);

        medicineTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {
                                load_selected_medicine();
                            }
                        }
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        medicineTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        scrollPane.getViewport()
                .setBackground(
                        Color.WHITE
                );

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBackground(
                BACKGROUND
        );

        tablePanel.setBorder(
                new EmptyBorder(
                        0,
                        30,
                        25,
                        30
                )
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel parentPanel =
                (JPanel) getComponent(1);

        parentPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );
    }

    private void add_medicine() {

        MedicineInput input =
                read_form();

        if (input == null) {
            return;
        }

        String sql =
                "INSERT INTO medicines " +
                        "(name, company, medicine_type, price, " +
                        "quantity_in_stock, reorder_level, " +
                        "expiry_date, supplier_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                    input.name
            );

            statement.setString(
                    2,
                    input.batchNumber
            );

            statement.setString(
                    3,
                    input.category
            );

            statement.setDouble(
                    4,
                    input.price
            );

            statement.setInt(
                    5,
                    input.quantity
            );

            statement.setInt(
                    6,
                    input.reorderLevel
            );

            statement.setDate(
                    7,
                    Date.valueOf(
                            input.expiryDate
                    )
            );

            statement.setInt(
                    8,
                    input.supplierId
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine added successfully.",
                    "Medicine Added",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_medicines();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Expiry date must use this format:\n"
                            + "YYYY-MM-DD",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add medicine:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void update_medicine() {

        if (selectedMedicineId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a medicine from the table first.",
                    "No Medicine Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        MedicineInput input =
                read_form();

        if (input == null) {
            return;
        }

        String sql =
                "UPDATE medicines SET " +
                        "name = ?, " +
                        "company = ?, " +
                        "medicine_type = ?, " +
                        "price = ?, " +
                        "quantity_in_stock = ?, " +
                        "reorder_level = ?, " +
                        "expiry_date = ?, " +
                        "supplier_id = ? " +
                        "WHERE medicine_id = ?";

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
                    input.name
            );

            statement.setString(
                    2,
                    input.batchNumber
            );

            statement.setString(
                    3,
                    input.category
            );

            statement.setDouble(
                    4,
                    input.price
            );

            statement.setInt(
                    5,
                    input.quantity
            );

            statement.setInt(
                    6,
                    input.reorderLevel
            );

            statement.setDate(
                    7,
                    Date.valueOf(
                            input.expiryDate
                    )
            );

            statement.setInt(
                    8,
                    input.supplierId
            );

            statement.setInt(
                    9,
                    selectedMedicineId
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine updated successfully.",
                    "Medicine Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_medicines();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Expiry date must use this format:\n"
                            + "YYYY-MM-DD",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update medicine:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void delete_medicine() {

        if (selectedMedicineId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a medicine from the table first.",
                    "No Medicine Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this medicine?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM medicines " +
                        "WHERE medicine_id = ?";

        try (
                java.sql.Connection connection =
                        Connection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql
                        )
        ) {

            statement.setInt(
                    1,
                    selectedMedicineId
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine deleted successfully.",
                    "Medicine Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clear_fields();
            load_medicines();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "This medicine cannot be deleted if it is already linked to a sale.\n\n"
                            + e.getMessage(),
                    "Delete Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private MedicineInput read_form() {

        String name =
                medicineNameField
                        .getText()
                        .trim();

        String category =
                categoryField
                        .getText()
                        .trim();

        String batchNumber =
                batchNumberField
                        .getText()
                        .trim();

        String expiryDate =
                expiryDateField
                        .getText()
                        .trim();

        String quantityText =
                quantityField
                        .getText()
                        .trim();

        String reorderText =
                reorderLevelField
                        .getText()
                        .trim();

        String priceText =
                unitPriceField
                        .getText()
                        .trim();

        if (name.isEmpty()
                || category.isEmpty()
                || batchNumber.isEmpty()
                || expiryDate.isEmpty()
                || quantityText.isEmpty()
                || reorderText.isEmpty()
                || priceText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all medicine fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }

        if (supplierComboBox.getSelectedItem()
                == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a supplier.",
                    "Missing Supplier",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }

        try {

            int quantity =
                    Integer.parseInt(
                            quantityText
                    );

            int reorderLevel =
                    Integer.parseInt(
                            reorderText
                    );

            double price =
                    Double.parseDouble(
                            priceText
                    );

            if (quantity < 0
                    || reorderLevel < 0
                    || price < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quantity, reorder level and price cannot be negative.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );

                return null;
            }

            String supplierText =
                    supplierComboBox
                            .getSelectedItem()
                            .toString();

            int dashIndex =
                    supplierText.indexOf(
                            " - "
                    );

            int supplierId =
                    Integer.parseInt(
                            supplierText.substring(
                                    0,
                                    dashIndex
                            )
                    );

            Date.valueOf(
                    expiryDate
            );

            return new MedicineInput(
                    name,
                    category,
                    batchNumber,
                    expiryDate,
                    quantity,
                    reorderLevel,
                    price,
                    supplierId
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity and reorder level must be whole numbers.\n"
                            + "Unit price must be a valid number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return null;

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Expiry date must use this format:\n"
                            + "YYYY-MM-DD",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            return null;
        }
    }

    private void load_selected_medicine() {

        int selectedRow =
                medicineTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        int modelRow =
                medicineTable.convertRowIndexToModel(
                        selectedRow
                );

        selectedMedicineId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        modelRow,
                                        0
                                )
                                .toString()
                );

        Medicine selectedMedicine =
                null;

        for (Medicine medicine :
                medicines) {

            if (medicine.get_medicine_id()
                    == selectedMedicineId) {

                selectedMedicine =
                        medicine;

                break;
            }
        }

        if (selectedMedicine == null) {
            return;
        }

        medicineNameField.setText(
                selectedMedicine
                        .get_medicine_name()
        );

        categoryField.setText(
                selectedMedicine
                        .get_category()
        );

        batchNumberField.setText(
                selectedMedicine
                        .get_batch_number()
        );

        expiryDateField.setText(
                selectedMedicine
                        .get_expiry_date()
        );

        quantityField.setText(
                String.valueOf(
                        selectedMedicine
                                .get_quantity()
                )
        );

        reorderLevelField.setText(
                String.valueOf(
                        selectedMedicine
                                .get_reorder_level()
                )
        );

        unitPriceField.setText(
                String.valueOf(
                        selectedMedicine
                                .get_unit_price()
                )
        );

        select_supplier(
                selectedMedicine
                        .get_supplier_id()
        );
    }

    private void select_supplier(
            int supplierId) {

        for (int i = 0;
             i < supplierComboBox.getItemCount();
             i++) {

            String item =
                    supplierComboBox
                            .getItemAt(i);

            if (item.startsWith(
                    supplierId + " - ")) {

                supplierComboBox
                        .setSelectedIndex(
                                i
                        );

                break;
            }
        }
    }

    private void load_suppliers() {

        supplierComboBox.removeAllItems();

        String sql =
                "SELECT supplier_id, name " +
                        "FROM suppliers " +
                        "ORDER BY name";

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

            while (resultSet.next()) {

                supplierComboBox.addItem(
                        resultSet.getInt(
                                "supplier_id"
                        )
                                + " - "
                                + resultSet.getString(
                                "name"
                        )
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load suppliers:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void load_medicines() {

        String sql =
                "SELECT medicine_id, name, medicine_type, " +
                        "company, expiry_date, quantity_in_stock, " +
                        "reorder_level, price, supplier_id " +
                        "FROM medicines " +
                        "ORDER BY medicine_id";

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

            medicines.clear();

            while (resultSet.next()) {

                Medicine medicine =
                        new Medicine(
                                resultSet.getInt(
                                        "medicine_id"
                                ),

                                resultSet.getString(
                                        "name"
                                ),

                                resultSet.getString(
                                        "medicine_type"
                                ),

                                resultSet.getString(
                                        "company"
                                ),

                                resultSet.getString(
                                        "expiry_date"
                                ),

                                resultSet.getInt(
                                        "quantity_in_stock"
                                ),

                                resultSet.getInt(
                                        "reorder_level"
                                ),

                                resultSet.getDouble(
                                        "price"
                                ),

                                resultSet.getInt(
                                        "supplier_id"
                                )
                        );

                medicines.add(
                        medicine
                );
            }

            refresh_table();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load medicines:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void refresh_table() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);

        for (Medicine medicine :
                medicines) {

            tableModel.addRow(
                    new Object[]{
                            medicine.get_medicine_id(),
                            medicine.get_medicine_name(),
                            medicine.get_category(),
                            medicine.get_batch_number(),
                            medicine.get_expiry_date(),
                            medicine.get_quantity(),
                            medicine.get_reorder_level(),
                            String.format(
                                    "R %.2f",
                                    medicine.get_unit_price()
                            ),
                            medicine.get_supplier_id()
                    }
            );
        }
    }

    private void add_form_field(
            JPanel panel,
            GridBagConstraints gbc,
            String labelText,
            JComponent field,
            int x,
            int y) {

        JLabel label =
                new JLabel(
                        labelText
                );

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

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.weightx = 0;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = x + 1;
        gbc.weightx = 1;

        field.setPreferredSize(
                new Dimension(
                        160,
                        34
                )
        );

        panel.add(
                field,
                gbc
        );
    }

    private void style_text_field(
            JTextField field) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
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
                                5,
                                9,
                                5,
                                9
                        )
                )
        );
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
                TEXT
        );

        comboBox.setBackground(
                Color.WHITE
        );

        comboBox.setBorder(
                new LineBorder(
                        BORDER,
                        1
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

        button.setPreferredSize(
                new Dimension(
                        125,
                        34
                )
        );

        button.setBackground(
                background
        );

        button.setForeground(
                Color.WHITE
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

    private void clear_fields() {

        medicineNameField.setText("");
        categoryField.setText("");
        batchNumberField.setText("");
        expiryDateField.setText("");
        quantityField.setText("");
        reorderLevelField.setText("");
        unitPriceField.setText("");

        if (supplierComboBox.getItemCount()
                > 0) {

            supplierComboBox.setSelectedIndex(
                    0
            );
        }

        selectedMedicineId = -1;

        medicineTable.clearSelection();

        medicineNameField.requestFocus();
    }

    private static class MedicineInput {

        String name;
        String category;
        String batchNumber;
        String expiryDate;
        int quantity;
        int reorderLevel;
        double price;
        int supplierId;

        MedicineInput(
                String name,
                String category,
                String batchNumber,
                String expiryDate,
                int quantity,
                int reorderLevel,
                double price,
                int supplierId) {

            this.name = name;
            this.category = category;
            this.batchNumber = batchNumber;
            this.expiryDate = expiryDate;
            this.quantity = quantity;
            this.reorderLevel = reorderLevel;
            this.price = price;
            this.supplierId = supplierId;
        }
    }
}