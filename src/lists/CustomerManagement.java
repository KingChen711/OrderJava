package lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Customer;
import utils.ValidInput;

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

  public void findById() {
    String searchId = ValidInput.inputString("customer's id");
    Customer foundCustomer = null;

    for (Customer customer : customerList) {
      if (customer.getId() == searchId) {
        foundCustomer = customer;
      }
    }

    if (foundCustomer == null) {
      System.out.println("\nThis customer does not exist\n");
    } else {
      System.out.println(foundCustomer.toString());
    }
  }

  public void addCustomer() {
    Customer newCustomer = new Customer();
    newCustomer.input();
    customerList.add(newCustomer);
    System.out.println("\nCreate new book successfully!\n");
  }

  public ValidInput.CheckUnique checkUniqueId = new ValidInput.CheckUnique() {
    @Override
    public boolean check(String checkedId) {
      return !customerList.stream().anyMatch(customer -> customer.getId().equals(checkedId));
    }
  };

  public ArrayList<Customer> readCustomerList() {
    ArrayList<Customer> customers = new ArrayList<>();
    try {
      FileReader fr = new FileReader("CustomerList.txt");
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
