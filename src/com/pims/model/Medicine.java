package com.pims.model;

public class Medicine {

    private int medicineId;
    private String medicineName;
    private String category;
    private String batchNumber;
    private String expiryDate;
    private int quantity;
    private int reorderLevel;
    private double unitPrice;
    private int supplierId;

    public Medicine() {
    }

    public Medicine(
            int medicineId,
            String medicineName,
            String category,
            String batchNumber,
            String expiryDate,
            int quantity,
            int reorderLevel,
            double unitPrice,
            int supplierId) {

        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.category = category;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.unitPrice = unitPrice;
        this.supplierId = supplierId;
    }

    public int get_medicine_id() {
        return medicineId;
    }

    public void set_medicine_id(int medicineId) {
        this.medicineId = medicineId;
    }

    public String get_medicine_name() {
        return medicineName;
    }

    public void set_medicine_name(String medicineName) {
        this.medicineName = medicineName;
    }

    public String get_category() {
        return category;
    }

    public void set_category(String category) {
        this.category = category;
    }

    public String get_batch_number() {
        return batchNumber;
    }

    public void set_batch_number(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String get_expiry_date() {
        return expiryDate;
    }

    public void set_expiry_date(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int get_quantity() {
        return quantity;
    }

    public void set_quantity(int quantity) {
        this.quantity = quantity;
    }

    public int get_reorder_level() {
        return reorderLevel;
    }

    public void set_reorder_level(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public double get_unit_price() {
        return unitPrice;
    }

    public void set_unit_price(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int get_supplier_id() {
        return supplierId;
    }

    public void set_supplier_id(int supplierId) {
        this.supplierId = supplierId;
    }
}