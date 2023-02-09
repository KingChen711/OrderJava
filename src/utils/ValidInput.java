package utils;

import java.util.Scanner;

public class ValidInput {

    private ValidInput() {

    }

    public static String inputString(String inputName) {
        return inputString(inputName, "not empty");
    }

    public static String inputString(String inputName, String condition) {
        String result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(" + condition + "): ");
            result = sc.nextLine().trim();
            if ("".equals(result)) {
                System.out.println("Your " + inputName + " is empty. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputStringLength(String inputName, int minLength, int maxLength) {
        String result;
        while (true) {
            result = inputString(inputName, "length from " + minLength + " to " + maxLength + " characters");
            if (result.length() < minLength || result.length() > maxLength) {
                System.out.println("Your " + inputName + " is too short or too long. Try again!");
                continue;
            }
            return result;
        }
    }

    public interface CheckUnique {
        public boolean check(String checkedId);
    }

    public static String inputId(String inputName, String condition, CheckUnique checkUnique) {
        String result;
        while (true) {
            result = inputString(inputName, condition);
            if (!checkUnique.check(result)) {
                System.out.println("Your " + inputName + " is not unique. Try again!");
                continue;
            }
            return result;
        }
    }

    public static int inputPositiveInteger(String inputName) {
        int result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(greater than 0, integer): ");
            if (!sc.hasNextInt()) {
                System.out.println("Your " + inputName + " is not a number or not integer. Try again!");
                continue;
            }
            result = sc.nextInt();
            if (result <= 0) {
                System.out.println("Your " + inputName + " is 0 or less than 0. Try again!");
                continue;
            }
            return result;
        }
    }

    public static boolean inputBoolean(String inputName) {
        boolean result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(true or false): ");
            if (!sc.hasNextBoolean()) {
                System.out.println("Your " + inputName + "is not a number or not integer. Try again!");
                continue;
            }
            result = sc.nextBoolean();
            return result;
        }
    }
}
