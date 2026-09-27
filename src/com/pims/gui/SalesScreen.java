package com.pims.gui;

import com.pims.database.Connection;
import com.pims.model.Medicine;
import com.pims.model.SaleItem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesScreen extends JPanel {

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

    private static final Color SUCCESS_GREEN =
            new Color(39, 125, 90);

    private static final Color DELETE_RED =
            new Color(184, 62, 75);

    private static final Color TABLE_LINE =
            new Color(224, 231, 228);

    private JComboBox<String> medicineComboBox;
    private JTextField quantityField;

    private JTable cartTable;
    private DefaultTableModel cartTableModel;

    private JLabel totalLabel;

    private final List<Medicine> medicines;
    private final List<SaleItem> cartItems;

    private final int userId;

    private int nextSaleItemId = 1;

    private JPanel mainPanel;

    public SalesScreen(int userId) {

        this.userId = userId;

        medicines = new ArrayList<>();
        cartItems = new ArrayList<>();

        setLayout(new BorderLayout());
        setBackground(SOFT_BACKGROUND);

        create_header();
        create_main_panel();
        create_bottom_panel();

        load_medicines();
        update_medicine_box();
        refresh_cart();
    }

    private void create_main_panel() {

        mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                SOFT_BACKGROUND
        );

        create_sales_form();
        create_cart_table();

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    private void create_header() {

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(DEEP_TEAL);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JPanel textPanel =
                new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel("Point of Sale");

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        titleLabel.setForeground(WHITE);

        JLabel descriptionLabel =
                new JLabel(
                        "Process medicine sales and manage customer purchases."
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                new Color(220, 233, 232)
        );

        JPanel accentPanel =
                new JPanel();

        accentPanel.setBackground(GOLD);

        accentPanel.setPreferredSize(
                new Dimension(
                        5,
                        55
                )
        );

        accentPanel.setMaximumSize(
                new Dimension(
                        5,
                        Integer.MAX_VALUE
                )
        );

        textPanel.add(titleLabel);

        textPanel.add(
                Box.createVerticalStrut(5)
        );

        textPanel.add(descriptionLabel);

        JPanel titleWrapper =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        titleWrapper.setOpaque(false);

        titleWrapper.add(
                accentPanel,
                BorderLayout.WEST
        );

        titleWrapper.add(
                textPanel,
                BorderLayout.CENTER
        );

        headerPanel.add(
                titleWrapper,
                BorderLayout.WEST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );
    }

    private void create_cart_table() {

        String[] columns = {
                "Medicine",
                "Quantity",
                "Unit Price",
                "Subtotal"
        };

        cartTableModel =
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

        cartTable =
                new JTable(cartTableModel);

        cartTable.setRowHeight(30);

        cartTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        cartTable.setForeground(DARK_TEXT);
        cartTable.setBackground(WHITE);
        cartTable.setGridColor(TABLE_LINE);
        cartTable.setShowGrid(true);

        cartTable.setSelectionBackground(
                new Color(
                        224,
                        235,
                        232
                )
        );

        cartTable.setSelectionForeground(
                DARK_TEXT
        );

        cartTable.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        cartTable.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        cartTable.getTableHeader().setBackground(
                DARK_TEAL
        );

        cartTable.getTableHeader().setForeground(
                WHITE
        );

        cartTable.getTableHeader().setPreferredSize(
                new Dimension(
                        0,
                        34
                )
        );

        cartTable.setAutoCreateRowSorter(true);

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        cartTable.getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );

        DefaultTableCellRenderer rightRenderer =
                new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        cartTable.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        rightRenderer
                );

        cartTable.getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        rightRenderer
                );

        JScrollPane scrollPane =
                new JScrollPane(cartTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        TABLE_LINE
                )
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
                        25,
                        10,
                        25
                )
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );
    }

    private void create_sales_form() {

        JPanel formPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                12
                        )
                );

        formPanel.setBackground(WHITE);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                TABLE_LINE
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        JLabel medicineLabel =
                new JLabel("Medicine");

        medicineLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        medicineLabel.setForeground(DARK_TEXT);

        medicineComboBox =
                new JComboBox<>();

        medicineComboBox.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );

        medicineComboBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JLabel quantityLabel =
                new JLabel("Quantity");

        quantityLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        quantityLabel.setForeground(DARK_TEXT);

        quantityField =
                new JTextField();

        quantityField.setPreferredSize(
                new Dimension(
                        85,
                        34
                )
        );

        quantityField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JButton addButton =
                create_button(
                        "Add to Sale",
                        DEEP_TEAL
                );

        addButton.setPreferredSize(
                new Dimension(
                        125,
                        34
                )
        );

        JButton clearButton =
                create_button(
                        "Clear Cart",
                        DELETE_RED
                );

        clearButton.setPreferredSize(
                new Dimension(
                        115,
                        34
                )
        );

        formPanel.add(medicineLabel);
        formPanel.add(medicineComboBox);
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);
        formPanel.add(addButton);
        formPanel.add(clearButton);

        JPanel wrapperPanel =
                new JPanel(
                        new BorderLayout()
                );

        wrapperPanel.setBackground(
                SOFT_BACKGROUND
        );

        wrapperPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        25,
                        10,
                        25
                )
        );

        wrapperPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                wrapperPanel,
                BorderLayout.NORTH
        );

        addButton.addActionListener(
                e -> add_to_cart()
        );

        clearButton.addActionListener(
                e -> clear_cart()
        );
    }

    private void create_bottom_panel() {

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setBackground(
                DARK_TEAL
        );

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        25,
                        15,
                        25
                )
        );

        totalLabel =
                new JLabel(
                        "Total: R 0.00"
                );

        totalLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        totalLabel.setForeground(WHITE);

        JButton completeSaleButton =
                create_button(
                        "Complete Sale",
                        SUCCESS_GREEN
                );

        completeSaleButton.setPreferredSize(
                new Dimension(
                        160,
                        42
                )
        );

        completeSaleButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        bottomPanel.add(
                totalLabel,
                BorderLayout.WEST
        );

        bottomPanel.add(
                completeSaleButton,
                BorderLayout.EAST
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        completeSaleButton.addActionListener(
                e -> complete_sale()
        );
    }

    private void refresh_cart() {

        cartTableModel.setRowCount(0);

        double total = 0;

        for (SaleItem item : cartItems) {

            cartTableModel.addRow(
                    new Object[]{
                            item.get_medicine_name(),
                            item.get_quantity(),
                            String.format(
                                    "R %.2f",
                                    item.get_unit_price()
                            ),
                            String.format(
                                    "R %.2f",
                                    item.get_subtotal()
                            )
                    }
            );

            total += item.get_subtotal();
        }

        totalLabel.setText(
                String.format(
                        "Total: R %.2f",
                        total
                )
        );
    }

    private void add_to_cart() {

        if (medicineComboBox.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a medicine.",
                    "Missing Medicine",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String selectedMedicine =
                medicineComboBox
                        .getSelectedItem()
                        .toString();

        int quantity;

        try {

            quantity =
                    Integer.parseInt(
                            quantityField
                                    .getText()
                                    .trim()
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid quantity.",
                    "Invalid Quantity",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (quantity <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be greater than zero.",
                    "Invalid Quantity",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Medicine selectedMedicineObject = null;

        for (Medicine medicine : medicines) {

            if (medicine.get_medicine_name()
                    .equals(selectedMedicine)) {

                selectedMedicineObject =
                        medicine;

                break;
            }
        }

        if (selectedMedicineObject == null) {
            return;
        }

        int quantityAlreadyInCart = 0;

        for (SaleItem item : cartItems) {

            if (item.get_medicine_id()
                    == selectedMedicineObject.get_medicine_id()) {

                quantityAlreadyInCart +=
                        item.get_quantity();
            }
        }

        if (quantity + quantityAlreadyInCart >
                selectedMedicineObject.get_quantity()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Insufficient stock.\n"
                            + "Available quantity: "
                            + selectedMedicineObject.get_quantity(),
                    "Insufficient Stock",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        double unitPrice =
                selectedMedicineObject.get_unit_price();

        double subtotal =
                quantity * unitPrice;

        SaleItem saleItem =
                new SaleItem(
                        nextSaleItemId,
                        0,
                        selectedMedicineObject.get_medicine_id(),
                        selectedMedicine,
                        quantity,
                        unitPrice,
                        subtotal
                );

        cartItems.add(saleItem);

        nextSaleItemId++;

        refresh_cart();

        quantityField.setText("");
        quantityField.requestFocus();
    }

    private void update_medicine_box() {

        medicineComboBox.removeAllItems();

        for (Medicine medicine : medicines) {

            if (medicine.get_quantity() > 0) {

                medicineComboBox.addItem(
                        medicine.get_medicine_name()
                );
            }
        }
    }

    private void load_medicines() {

        String sql =
                "SELECT medicine_id, name, medicine_type, company, " +
                        "expiry_date, quantity_in_stock, reorder_level, " +
                        "price, supplier_id " +
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

                medicines.add(medicine);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load medicines from database:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void complete_sale() {

        if (cartItems.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "The cart is empty.",
                    "No Items",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        double total = 0;

        for (SaleItem item : cartItems) {
            total += item.get_subtotal();
        }

        String saleSql =
                "INSERT INTO sales (total_amount, user_id) " +
                        "VALUES (?, ?)";

        String itemSql =
                "INSERT INTO sale_items " +
                        "(sale_id, medicine_id, quantity_sold, price_at_sale) " +
                        "VALUES (?, ?, ?, ?)";

        String stockSql =
                "UPDATE medicines " +
                        "SET quantity_in_stock = quantity_in_stock - ? " +
                        "WHERE medicine_id = ? " +
                        "AND quantity_in_stock >= ?";

        java.sql.Connection connection = null;

        try {

            connection =
                    Connection.getConnection();

            connection.setAutoCommit(false);

            int saleId;

            try (
                    PreparedStatement saleStatement =
                            connection.prepareStatement(
                                    saleSql,
                                    java.sql.Statement.RETURN_GENERATED_KEYS
                            )
            ) {

                saleStatement.setDouble(
                        1,
                        total
                );

                saleStatement.setInt(
                        2,
                        userId
                );

                saleStatement.executeUpdate();

                try (
                        ResultSet generatedKeys =
                                saleStatement.getGeneratedKeys()
                ) {

                    if (!generatedKeys.next()) {

                        throw new SQLException(
                                "Unable to create sale ID."
                        );
                    }

                    saleId =
                            generatedKeys.getInt(1);
                }
            }

            try (
                    PreparedStatement itemStatement =
                            connection.prepareStatement(itemSql);

                    PreparedStatement stockStatement =
                            connection.prepareStatement(stockSql)
            ) {

                for (SaleItem item : cartItems) {

                    itemStatement.setInt(
                            1,
                            saleId
                    );

                    itemStatement.setInt(
                            2,
                            item.get_medicine_id()
                    );

                    itemStatement.setInt(
                            3,
                            item.get_quantity()
                    );

                    itemStatement.setDouble(
                            4,
                            item.get_unit_price()
                    );

                    itemStatement.executeUpdate();

                    stockStatement.setInt(
                            1,
                            item.get_quantity()
                    );

                    stockStatement.setInt(
                            2,
                            item.get_medicine_id()
                    );

                    stockStatement.setInt(
                            3,
                            item.get_quantity()
                    );

                    int updatedRows =
                            stockStatement.executeUpdate();

                    if (updatedRows == 0) {

                        throw new SQLException(
                                "Insufficient stock for "
                                        + item.get_medicine_name()
                        );
                    }
                }
            }

            connection.commit();

            String receipt =
                    generate_receipt(
                            saleId,
                            total
                    );

            show_receipt(
                    saleId,
                    receipt
            );

            cartItems.clear();

            refresh_cart();

            load_medicines();
            update_medicine_box();

        } catch (SQLException e) {

            if (connection != null) {

                try {
                    connection.rollback();

                } catch (SQLException rollbackError) {
                    rollbackError.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to complete sale:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            if (connection != null) {

                try {

                    connection.setAutoCommit(true);
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String generate_receipt(
            int saleId,
            double total) {

        StringBuilder receipt =
                new StringBuilder();

        String dateTime =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd HH:mm:ss"
                                )
                        );

        receipt.append(
                "========================================\n"
        );

        receipt.append(
                "             PIMS PHARMACY\n"
        );

        receipt.append(
                " Pharmacy Inventory Management System\n"
        );

        receipt.append(
                "========================================\n"
        );

        receipt.append(
                "Sale ID: "
                        + saleId
                        + "\n"
        );

        receipt.append(
                "Date: "
                        + dateTime
                        + "\n"
        );

        receipt.append(
                "Cashier ID: "
                        + userId
                        + "\n"
        );

        receipt.append(
                "----------------------------------------\n"
        );

        receipt.append(
                String.format(
                        "%-20s %5s %10s\n",
                        "Medicine",
                        "Qty",
                        "Amount"
                )
        );

        receipt.append(
                "----------------------------------------\n"
        );

        for (SaleItem item : cartItems) {

            receipt.append(
                    String.format(
                            "%-20s %5d R%9.2f\n",
                            item.get_medicine_name(),
                            item.get_quantity(),
                            item.get_subtotal()
                    )
            );
        }

        receipt.append(
                "----------------------------------------\n"
        );

        receipt.append(
                String.format(
                        "TOTAL: R %.2f\n",
                        total
                )
        );

        receipt.append(
                "========================================\n"
        );

        receipt.append(
                "       Thank you for your purchase!\n"
        );

        receipt.append(
                "========================================\n"
        );

        return receipt.toString();
    }

    private void show_receipt(
            int saleId,
            String receipt) {

        JTextArea receiptArea =
                new JTextArea(receipt);

        receiptArea.setEditable(false);

        receiptArea.setBackground(
                new Color(
                        250,
                        251,
                        250
                )
        );

        receiptArea.setForeground(DARK_TEXT);

        receiptArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        receiptArea.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JScrollPane receiptScrollPane =
                new JScrollPane(
                        receiptArea
                );

        receiptScrollPane.setPreferredSize(
                new Dimension(
                        500,
                        450
                )
        );

        receiptScrollPane.setBorder(
                BorderFactory.createLineBorder(
                        TABLE_LINE
                )
        );

        JButton saveButton =
                create_button(
                        "Save Bill",
                        DEEP_TEAL
                );

        JButton printButton =
                create_button(
                        "Print Bill",
                        GOLD
                );

        printButton.setForeground(DARK_TEXT);

        JButton closeButton =
                create_button(
                        "Close",
                        DELETE_RED
                );

        JPanel receiptButtonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                8
                        )
                );

        receiptButtonPanel.setBackground(
                SOFT_BACKGROUND
        );

        receiptButtonPanel.add(saveButton);
        receiptButtonPanel.add(printButton);
        receiptButtonPanel.add(closeButton);

        JPanel receiptPanel =
                new JPanel(
                        new BorderLayout()
                );

        receiptPanel.setBackground(
                SOFT_BACKGROUND
        );

        receiptPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        receiptPanel.add(
                receiptScrollPane,
                BorderLayout.CENTER
        );

        receiptPanel.add(
                receiptButtonPanel,
                BorderLayout.SOUTH
        );

        JDialog receiptDialog =
                new JDialog(
                        SwingUtilities.getWindowAncestor(this),
                        "PIMS - Sale Receipt",
                        Dialog.ModalityType.APPLICATION_MODAL
                );

        receiptDialog.setLayout(
                new BorderLayout()
        );

        receiptDialog.getContentPane()
                .setBackground(
                        SOFT_BACKGROUND
                );

        receiptDialog.add(
                receiptPanel,
                BorderLayout.CENTER
        );

        receiptDialog.setSize(
                550,
                550
        );

        receiptDialog.setLocationRelativeTo(this);

        saveButton.addActionListener(
                e -> save_receipt(
                        receiptDialog,
                        receipt,
                        saleId
                )
        );

        printButton.addActionListener(
                e -> print_receipt(
                        receiptDialog,
                        receiptArea
                )
        );

        closeButton.addActionListener(
                e -> receiptDialog.dispose()
        );

        receiptDialog.setVisible(true);
    }

    private void clear_cart() {

        if (cartItems.isEmpty()) {
            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to clear the cart?",
                        "Clear Cart",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice == JOptionPane.YES_OPTION) {

            cartItems.clear();

            refresh_cart();
        }
    }

    private void save_receipt(
            JDialog receiptDialog,
            String receipt,
            int saleId) {

        JFileChooser fileChooser =
                new JFileChooser();

        fileChooser.setDialogTitle(
                "Save Bill"
        );

        fileChooser.setSelectedFile(
                new File(
                        "PIMS_Bill_"
                                + saleId
                                + ".txt"
                )
        );

        int result =
                fileChooser.showSaveDialog(
                        receiptDialog
                );

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file =
                fileChooser.getSelectedFile();

        try (
                FileWriter writer =
                        new FileWriter(file)
        ) {

            writer.write(receipt);

            JOptionPane.showMessageDialog(
                    receiptDialog,
                    "Bill saved successfully.",
                    "Bill Saved",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    receiptDialog,
                    "Unable to save bill:\n"
                            + e.getMessage(),
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void print_receipt(
            JDialog receiptDialog,
            JTextArea receiptArea) {

        try {

            boolean printed =
                    receiptArea.print();

            if (printed) {

                JOptionPane.showMessageDialog(
                        receiptDialog,
                        "Bill sent to printer successfully.",
                        "Print Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (java.awt.print.PrinterException e) {

            JOptionPane.showMessageDialog(
                    receiptDialog,
                    "Unable to print bill:\n"
                            + e.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private JButton create_button(
            String text,
            Color background) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(WHITE);
        button.setBackground(background);
        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        7,
                        14,
                        7,
                        14
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}