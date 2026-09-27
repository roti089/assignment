package com.pims.model;

public class Supplier {

    private int supplierId;
    private String supplierName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;

    public Supplier() {
    }

    public Supplier(
            int supplierId,
            String supplierName,
            String contactPerson,
            String phone,
            String email,
            String address) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String get_supplier_name() {
        return supplierName;
    }

    public void set_supplier_name(String supplierName) {
        this.supplierName = supplierName;
    }

    public String get_contact_person() {
        return contactPerson;
    }

    public void set_contact_person(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String get_phone() {
        return phone;
    }

    public void set_phone(String phone) {
        this.phone = phone;
    }

    public String get_email() {
        return email;
    }

    public void set_email(String email) {
        this.email = email;
    }

    public String get_address() {
        return address;
    }

    public void set_address(String address) {
        this.address = address;
    }

    public int get_supplier_id() {
        return supplierId;
    }

    public void set_supplier_id(int supplierId) {
        this.supplierId = supplierId;
    }
}