package models;


public class Order {
  private String id;
  private String customerId;
  private String productId;
  private String quantity;
  private String date;
  private boolean status;

  

  public Order() {
  }

  public Order(String id, String customerId, String productId, String quantity, String date, boolean status) {
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

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", customerId=" + customerId + ", productId=" + productId + ", quantity=" + quantity
        + ", date=" + date + ", status=" + status + "]";
  }

}
