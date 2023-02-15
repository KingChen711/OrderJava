package models;

import lists.OrderManagement;
import utils.Menu;
import utils.ValidInput;

public class Order {

    private String id;
    private String customerId;
    private String productId;
    private int quantity;
    private String date;
    private boolean status;

    public Order() {
    }

    public Order(String id, String customerId, String productId, int quantity, String date, boolean status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void input() {
        id = ValidInput.inputIdWithFormat("order's id", "not empty,unique,format: \"Dxxx\"",
                OrderManagement.getInstance().checkUniqueId, "D[0-9]{3}");
        customerId = Menu.getChosenCustomerId();
        productId = Menu.getChosenProductId();
        quantity = ValidInput.inputPositiveInteger("order's quantity");
        date = ValidInput.inputString("order's date");
        status = ValidInput.inputBoolean("order's status");
    }

    public void update() {
        customerId = Menu.getChosenCustomerId();
        productId = Menu.getChosenProductId();
        quantity = ValidInput.inputPositiveIntegerUpdate("order's quantity", quantity);
        date = ValidInput.inputStringUpdate("order's date", date);
        status = ValidInput.inputBooleanUpdate("order's status", status);
    }

    @Override
    public String toString() {
        return id + "," + customerId + "," + productId + "," + quantity
                + "," + date + "," + status;
    }

}
