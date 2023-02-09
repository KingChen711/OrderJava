package lists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import models.Customer;
import utils.Menu;
import utils.ValidInput;
import utils.Verify;

final public class CustomerManagement {

  private static CustomerManagement instance;

  private CustomerManagement() {

  }

  public static CustomerManagement getInstance() {
    if (instance == null) {
      instance = new CustomerManagement();
    }
    return instance;
  }

  private ArrayList<Customer> customerList = readCustomerList();

  public void listAllCustomers() {

    customerList.forEach(product -> {
      System.out.println(product.toString());
    });

  }

  public void searchById() {
    String searchId = ValidInput.inputString("customer's id");
    Customer foundCustomer = findById(searchId);

    if (foundCustomer == null) {
      System.out.println("\nFail: This customer does not exist\n");
    } else {
      System.out.println(foundCustomer.toString());
    }
  }

  public void addCustomer() {

    if (!Verify.isAdmin())
      return;

    while (true) {
      Customer newCustomer = new Customer();
      newCustomer.input();
      customerList.add(newCustomer);
      System.out.println("\nCreate new customer successfully!\n");

      System.out.println("Do you want to add one more customer?");
      String confirm = Menu.getConfirm();
      if (confirm == "no")
        break;
    }
  }

  public void updateCustomer() {

    if (!Verify.isAdmin())
      return;

    String searchedId = ValidInput.inputString("Customer's id");
    Customer foundCustomer = findById(searchedId);

    if (foundCustomer == null) {
      System.out.println("\nFail: Customer’s id does not exist\n");
      return;
    }

    foundCustomer.update();
    System.out.println("\nSuccess: Update customer successfully!\n");
  }

  public void saveCustomerList() {

    if (!Verify.isAdmin())
      return;

    try {
      ClassLoader classLoader = ProductManagement.class.getClassLoader();
      File file = new File(classLoader.getResource("resources/customers.txt").getFile());
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);

      for (Customer customer : customerList) {
        bw.write(customer.toString());
        bw.newLine();
      }

      bw.close();
      fw.close();
    } catch (IOException e) {
      System.out.println("\nFail: Save Customer list fail!\n");
      System.out.println(e);
    }
    System.out.println("\nSuccess: Save Customer list successfully!\n");
  }

  public Customer findById(String id) {
    Customer foundCustomer = null;
    for (Customer customer : customerList) {
      if (customer.getId().equals(id)) {
        foundCustomer = customer;
        break;
      }
    }
    return foundCustomer;
  }

  public HashMap<String, String> getMapIdToName() {
    HashMap<String, String> result = new HashMap<>();
    customerList.forEach(customer -> {
      result.put(customer.getId(), customer.getName());
    });
    return result;
  }

  public ValidInput.CheckUnique checkUniqueId = new ValidInput.CheckUnique() {
    @Override
    public boolean check(String checkedId) {
      return !customerList.stream().anyMatch(customer -> customer.getId().equals(checkedId));
    }
  };

  public ArrayList<String> getCustomerIds() {
    ArrayList<String> results = new ArrayList<>();
    customerList.forEach(customer -> results.add(customer.getId()));
    return results;
  }

  private ArrayList<Customer> readCustomerList() {
    ArrayList<Customer> customers = new ArrayList<>();
    try {
      ClassLoader classLoader = ProductManagement.class.getClassLoader();
      File file = new File(classLoader.getResource("resources/customers.txt").getFile());
      System.out.println(file.getPath());
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String line;

      while (true) {
        line = br.readLine();
        if (line == null) {
          break;
        }
        String[] texts = line.split(",");
        Customer newCustomer = new Customer();
        newCustomer.setId(texts[0]);
        newCustomer.setName(texts[1]);
        newCustomer.setAddress(texts[2]);
        newCustomer.setPhone(texts[3]);
        customers.add(newCustomer);
      }
      br.close();
      fr.close();
    } catch (IOException | NumberFormatException e) {
      System.out.println(e);
    }
    return customers;
  }
}
