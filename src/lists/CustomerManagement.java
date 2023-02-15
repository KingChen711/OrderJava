package lists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import interfaces.CheckUnique;
import interfaces.IdToName;
import models.Customer;
import utils.Menu;
import utils.Pause;
import utils.ValidInput;
import utils.Verify;

public final class CustomerManagement {

    private static CustomerManagement instance;

    private CustomerManagement() {

    }

    public static CustomerManagement getInstance() {
        if (instance == null) {
            instance = new CustomerManagement();
        }
        return instance;
    }

    private List<Customer> customerList = readCustomerList();

    public void listAllCustomers() {
        customerList.forEach(product -> System.out.println(product.toString()));

        Pause.pause();
    }

    public void searchById() {
        String searchId = ValidInput.inputString("customer's id");
        Customer foundCustomer = findById(searchId);

        if (foundCustomer == null) {
            System.out.println("\nFail: This customer does not exist\n");
        } else {
            System.out.println(foundCustomer.toString());
        }

        Pause.pause();
    }

    public void addCustomer() {

        if (!Verify.isAdmin()) {
            return;
        }

        while (true) {
            Customer newCustomer = new Customer();
            newCustomer.input();
            customerList.add(newCustomer);
            System.out.println("\nCreate new customer successfully!\n");

            System.out.println("Do you want to add one more customer?");
            if (!Menu.getConfirm()) {
                break;
            }
        }
    }

    public void updateCustomer() {

        if (!Verify.isAdmin()) {
            return;
        }

        String searchedId = ValidInput.inputString("Customer's id");
        Customer foundCustomer = findById(searchedId);

        if (foundCustomer == null) {
            System.out.println("\nFail: Customer’s id does not exist\n");
            return;
        }

        foundCustomer.update();
        System.out.println("\nSuccess: Update customer successfully!\n");

        Pause.pause();
    }

    public void saveCustomerList() {

        if (!Verify.isAdmin()) {
            return;
        }

        try {
            String currentWorkingDirectory = System.getProperty("user.dir");
            File file = new File(currentWorkingDirectory + "/src/resources/customers.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            for (Customer customer : customerList) {
                writer.write(customer.toString());
                writer.newLine();
            }

            writer.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("\nFail: Save Customer list fail!\n");
            System.out.println(e);
        }
        System.out.println("\nSuccess: Save Customer list successfully!\n");

        Pause.pause();
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

    public IdToName getMapIdToName() {
        IdToName result = new IdToName();
        customerList.forEach(customer -> result.put(customer.getId(), customer.getName()));
        return result;
    }

    public final CheckUnique checkUniqueId = checkedId -> customerList.stream()
            .noneMatch(customer -> customer.getId().equals(checkedId));

    public List<String> getCustomerIds() {
        List<String> results = new ArrayList<>();
        customerList.forEach(customer -> results.add(customer.getId()));
        return results;
    }

    private List<Customer> readCustomerList() {
        List<Customer> customers = new ArrayList<>();
        try {
            String currentWorkingDirectory = System.getProperty("user.dir");
            File file = new File(currentWorkingDirectory + "/src/resources/customers.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line;
            while (true) {
                line = reader.readLine();
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

            reader.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
        return customers;
    }

}
