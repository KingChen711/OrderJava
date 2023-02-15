package utils;

public class Pause {

  private Pause() {
  }

  public static void pause() {

    System.out.println("\nPress Enter To Return To Main Menu...");
    new java.util.Scanner(System.in).nextLine();
  }
}