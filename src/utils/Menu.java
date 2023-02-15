package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import lists.CustomerManagement;
import lists.ProductManagement;

public class Menu {

    private static final List<String> MAIN_OPTIONS = new ArrayList<>(
            Arrays.asList(
                    "List all Products",
                    "List all Customers",
                    "Search a Customer based on his/her ID", "Add a Customer", "Update a Customer",
                    "Save Customers to the file, named customers.txt",
                    "List all Orders in ascending order of Customer name",
                    "List all pending Orders", "Add an Order", "Update an Order",
                    "Save Orders to file, named orders.txt",
                    "Quit"));

    private static final List<String> UPDATE_ORDER_OPTIONS = new ArrayList<>(
            Arrays.asList(
                    "Update order information",
                    "Delete order"));

    private static final List<String> CONFIRM_OPTIONS = new ArrayList<>(
            Arrays.asList(
                    "Yes",
                    "No"));

    private Menu() {

    }

    public static int getMainChoice() {
        return getChoice(MAIN_OPTIONS);
    }

    private static String chosenText(String message) {
        return "You have chosen " + message;
    }

    public static String getChosenCustomerId() {
        System.out.println("Choose a customer's id: ");
        List<String> customerIdOptions = CustomerManagement.getInstance().getCustomerIds();
        int choice = getChoice(customerIdOptions);
        String chosenCustomerId = customerIdOptions.get(choice - 1);
        System.out.println(chosenText(chosenCustomerId));
        return chosenCustomerId;
    }

    public static String getChosenProductId() {
        System.out.println("Choose a customer's id: ");
        List<String> productIdOptions = ProductManagement.getInstance().getProductIds();
        int choice = getChoice(productIdOptions);
        String chosenProductId = productIdOptions.get(choice - 1);
        System.out.println(chosenText(chosenProductId));
        return chosenProductId;
    }

    public static boolean getConfirm() {
        System.out.println("Confirm:");
        int choice = getChoice(CONFIRM_OPTIONS);
        String chosenConfirm = CONFIRM_OPTIONS.get(choice - 1);
        System.out.println(chosenText(chosenConfirm));
        return chosenConfirm.equals("Yes");
    }

    public static String getOptionUpdateOrder() {
        System.out.println("Choose a update option: ");
        int choice = getChoice(UPDATE_ORDER_OPTIONS);
        String chosenOption = UPDATE_ORDER_OPTIONS.get(choice - 1);
        System.out.println(chosenText(chosenOption));
        return chosenOption.equals("Delete order") ? "delete" : "updateInformation";
    }

    private static int getChoice(List<String> options) {
        System.out.println("\n================MENU================");
        for (int i = 0; i < options.size(); ++i) {
            System.out.println((i + 1) + "." + options.get(i));
        }
        System.out.println("====================================");
        int choice = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose 1 to " + options.size() + ": ");
            if (!sc.hasNextInt()) {
                System.out.println("Your input is not a number. Try again!");
                continue;
            }
            choice = sc.nextInt();
            if (choice < 1 || choice > options.size()) {
                System.out.println("Your input is less than 1 or greater than " + options.size() + ". Try again!");
                continue;
            }
            break;
        } while (true);
        return choice;
    }
}
