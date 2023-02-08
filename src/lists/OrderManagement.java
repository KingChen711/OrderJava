package lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Order;

final public class OrderManagement {

  private static OrderManagement instance;

  private OrderManagement() {

  }

  public static OrderManagement getInstance() {
    if (instance == null) {
      instance = new OrderManagement();
    }
    return instance;
  }

  private ArrayList<Order> OrderList = readOrderList();

  public ArrayList<Order> readOrderList() {
    ArrayList<Order> orders = new ArrayList<>();
    try {
      FileReader fr = new FileReader("OrderList.txt");
      BufferedReader br = new BufferedReader(fr);
      String line;

      while (true) {
        line = br.readLine();
        if (line == null) {
          break;
        }
        String[] texts = line.split(",");
        Order newOrder = new Order();
        newOrder.setId(texts[0]);
        newOrder.setCustomerId(texts[1]);
        newOrder.setProductId(texts[2]);
        newOrder.setQuantity(texts[3]);
        newOrder.setDate((texts[4]));
        newOrder.setStatus(Boolean.parseBoolean(texts[5]));
        orders.add(newOrder);
      }
      br.close();
      fr.close();
    } catch (IOException | NumberFormatException e) {
      System.out.println(e);
    }
    return orders;
  }
}
