package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import lists.CustomerManagement;
import lists.ProductManagement;

public class Menu {

  private static final ArrayList<String> ADMIN_OPTIONS = new ArrayList<String>(
      Arrays.asList(
          "List all Products",
          "List all Customers",
          "Search a Customer based on his/her ID", "Add a Customer", "Update a Customer",
          "Save Customers to the file, named customers.txt", "List all Orders in ascending order of Customer name",
          "List all pending Orders", "Add an Order", "Update an Order", "Save Orders to file, named orders.txt",
          "Quit"));

  private static final ArrayList<String> USER_OPTIONS = new ArrayList<String>(
      Arrays.asList(
          "List all Products",
          "List all Customers",
          "Search a Customer based on his/her ID",
          "List all Orders in ascending order of Customer name",
          "List all pending Orders",
          "Quit"));

  private static final ArrayList<String> UPDATE_ORDER_OPTIONS = new ArrayList<String>(
      Arrays.asList(
          "Update order information",
          "Delete order"));

  private static final ArrayList<String> CONFIRM_OPTIONS = new ArrayList<String>(
      Arrays.asList(
          "Yes",
          "No"));

  private static ArrayList<String> customerIdOptions = CustomerManagement.getInstance().getCustomerIds();
  private static ArrayList<String> productIdOptions = ProductManagement.getInstance().getProductIds();

  private Menu() {

  }

  public static int getAdminChoice() {
    return getChoice(ADMIN_OPTIONS);
  }

  public static int getUserChoice() {
    return getChoice(USER_OPTIONS);
  }

  public static String getChosenCustomerId() {
    System.out.println("Choose a customer's id: ");
    int choice = getChoice(customerIdOptions);
    String chosenCustomerId = customerIdOptions.get(choice - 1);
    System.out.println("You have chosen " + chosenCustomerId);
    return chosenCustomerId;
  }

  public static String getChosenProductId() {
    System.out.println("Choose a customer's id: ");
    int choice = getChoice(productIdOptions);
    String chosenProductId = productIdOptions.get(choice - 1);
    System.out.println("You have chosen " + chosenProductId);
    return chosenProductId;
  }

  public static String getConfirm() {
    System.out.println("Confirm   : ");
    int choice = getChoice(CONFIRM_OPTIONS);
    String chosenProductId = CONFIRM_OPTIONS.get(choice - 1);
    System.out.println("You have chosen " + chosenProductId);
    return chosenProductId == "Yes" ? "yes" : "no";
  }

  public static String getOptionUpdateOrder() {
    System.out.println("Choose a update option: ");
    int choice = getChoice(UPDATE_ORDER_OPTIONS);
    String chosenOption = UPDATE_ORDER_OPTIONS.get(choice - 1);
    System.out.println("You have chosen " + chosenOption);
    return chosenOption == "Delete order" ? "delete" : "updateInformation";
  }

  private static int getChoice(ArrayList<String> options) {
    System.out.println("================MENU================");
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
