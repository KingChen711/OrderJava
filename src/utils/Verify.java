package utils;

import lists.UserManagement;

public class Verify {

  private Verify() {

  }

  public static boolean isAdmin() {
    if (!UserManagement.getInstance().getCurrentUser().getRole().equals("admin")) {
      System.out.println("\nThis feature is only used by admin\n");
      return false;
    }
    return true;
  }
}
