package lists;

import java.util.ArrayList;

import models.User;
import utils.ValidInput;

final public class UserManagement {

  private static UserManagement instance;

  private UserManagement() {

  }

  public static UserManagement getInstance() {
    if (instance == null) {
      instance = new UserManagement();
    }
    return instance;
  }

  private ArrayList<User> userList = initialUsers();

  private User currentUser = login();

  public User getCurrentUser() {
    return currentUser;
  }

  public User login() {
    System.out.println("==============Login==============");
    while (true) {
      String username = ValidInput.inputString("user's id");
      String password = ValidInput.inputString("password");

      for (User user : userList) {
        if (username.equals(user.getId()) && password.equals(user.getPassword())) {
          System.out.println("\nSuccess: login successful");
          return user;
        }
      }

      System.out.println("\nFail: login fail");
      System.out.println("User's ID or Password is incorrect\n");
    }
  }

  private static ArrayList<User> initialUsers() {
    ArrayList<User> result = new ArrayList<>();
    result.add(new User("U001", "123", "admin"));
    result.add(new User("U002", "456", "user"));
    result.add(new User("U003", "789", "user"));
    return result;
  }

}
