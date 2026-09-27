package com.pims.model;

public class SaleItem {

    private int saleItemId;
    private int saleId;
    private int medicineId;
    private String medicineName;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    public SaleItem() {
    }

    public SaleItem(
            int saleItemId,
            int saleId,
            int medicineId,
            String medicineName,
            int quantity,
            double unitPrice,
            double subtotal) {

        this.saleItemId = saleItemId;
        this.saleId = saleId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
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

    public int get_quantity() {
        return quantity;
    }

    public void set_quantity(int quantity) {
        this.quantity = quantity;
    }

    public double get_unit_price() {
        return unitPrice;
    }

    public void set_unit_price(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double get_subtotal() {
        return subtotal;
    }

    public void set_subtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int get_sale_id() {
        return saleId;
    }

    public void set_sale_id(int saleId) {
        this.saleId = saleId;
    }

    public int get_sale_item_id() {
        return saleItemId;
    }

    public void set_sale_item_id(int saleItemId) {
        this.saleItemId = saleItemId;
    }
}