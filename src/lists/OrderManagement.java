package lists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import interfaces.CheckUnique;
import models.Order;
import utils.Menu;
import utils.Pause;
import utils.ValidInput;
import utils.Verify;

public final class OrderManagement {

    private static OrderManagement instance;

    private OrderManagement() {

    }

    public static OrderManagement getInstance() {
        if (instance == null) {
            instance = new OrderManagement();
        }
        return instance;
    }

    private List<Order> orderList = readOrderList();

    public void printAllOrders() {
        Collections.sort(orderList, new CustomerNameComparator());
        orderList.forEach(order -> System.out.println(order.toString()));

        Pause.pause();
    }

    public void printAllPendingOrders() {
        orderList.forEach(order -> {
            if (!order.getStatus()) {
                System.out.println(order.toString());
            }
        });

        Pause.pause();
    }

    public void addOrder() {

        if (!Verify.isAdmin()) {
            return;
        }

        while (true) {
            Order newOrder = new Order();
            newOrder.input();
            orderList.add(newOrder);
            System.out.println("\nCreate new order successfully!\n");

            System.out.println("Do you want to add one more order?");
            if (!Menu.getConfirm()) {
                break;
            }
        }
    }

    public void updateOrder() {

        if (!Verify.isAdmin()) {
            return;
        }

        String optionUpdate = Menu.getOptionUpdateOrder();
        String searchedId = ValidInput.inputString("order's id");
        Order foundOrder = findById(searchedId);

        if (foundOrder == null) {
            System.out.println("\nFail: Customer’s id does not exist\n");
            return;
        }

        if (optionUpdate.equals("delete")) {
            if (Menu.getConfirm()) {
                orderList.remove(foundOrder);
                System.out.println("\nSuccess: Delete order successfully!\n");
            } else {
                System.out.println("\nFail: Delete order fail!\n");
                System.out.println("\nYou have refuse to delete order\n");
            }
        } else {
            foundOrder.update();
            System.out.println("\nSuccess: Update order information successfully!\n");
        }

        Pause.pause();
    }

    public void saveOrderList() {

        if (!Verify.isAdmin()) {
            return;
        }

        try {
            String currentWorkingDirectory = System.getProperty("user.dir");
            File file = new File(currentWorkingDirectory + "/src/resources/orders.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Order order : orderList) {
                bw.write(order.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("\nFail: Save Order list fail!\n");
            System.out.println(e);
        }
        System.out.println("\nSuccess: Save Order list successfully!\n");

        Pause.pause();
    }

    public Order findById(String id) {
        Order foundOrder = null;
        for (Order order : orderList) {
            if (order.getId().equals(id)) {
                foundOrder = order;
                break;
            }
        }
        return foundOrder;
    }

    private static class CustomerNameComparator implements Comparator<Order> {

        @Override
        public int compare(Order order1, Order order2) {
            HashMap<String, String> customerIdToName = CustomerManagement.getInstance().getMapIdToName();
            String customerName1 = customerIdToName.get(order1.getCustomerId());
            String customerName2 = customerIdToName.get(order2.getCustomerId());
            return customerName1.compareTo(customerName2);
        }
    }

    public final CheckUnique checkUniqueId = checkedId -> orderList.stream()
            .noneMatch(order -> order.getId().equals(checkedId));

    private List<Order> readOrderList() {
        List<Order> orders = new ArrayList<>();
        try {
            String currentWorkingDirectory = System.getProperty("user.dir");
            File file = new File(currentWorkingDirectory + "/src/resources/orders.txt");
            FileReader fr = new FileReader(file);
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
                newOrder.setQuantity(Integer.parseInt(texts[3]));
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
