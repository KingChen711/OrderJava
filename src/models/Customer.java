package models;

import lists.CustomerManagement;
import utils.ValidInput;

public class Customer {

    private String id;
    private String name;
    private String address;
    private String phone;

    public Customer() {
    }

    public Customer(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void input() {
        id = ValidInput.inputIdWithFormat("customer's id", "not empty,unique,format: \"Cxxx\"",
                CustomerManagement.getInstance().checkUniqueId, "C[0-9]{3}");
        name = ValidInput.inputString("customer's name");
        address = ValidInput.inputString("customer's address");
        phone = ValidInput.inputStringLength("customer's phone", 10, 12);
    }

    public void update() {
        name = ValidInput.inputStringUpdate("customer's name", name);
        address = ValidInput.inputStringUpdate("customer's address", address);
        phone = ValidInput.inputStringLengthUpdate("customer's phone", 10, 12, phone);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + address + "," + phone;
    }

}
