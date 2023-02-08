package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

  private static final ArrayList<String> MAIN_OPTIONS = new ArrayList<String>(
      Arrays.asList(
          "List all Products",
          "List all Customers",
          "Search a Customer based on his/her ID", "Add a Customer", "Update a Customer",
          "Save Customers to the file, named customers.txt", "List all Orders in ascending order of Customer name",
          "List all pending Orders", "Add an Order", "Update an Order", "Save Orders to file, named orders.txt",
          "Quit"));

  private Menu() {

  }

  public static int getMainChoice() {
    return getChoice(MAIN_OPTIONS);
  }

  public static int getChoice(ArrayList<String> options) {
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
