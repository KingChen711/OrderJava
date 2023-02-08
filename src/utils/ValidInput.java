/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Kingc
 */
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

    // public static String intputStringWithCustomLenght(String inputName, int minLength, int maxLength) {
    //     String result;
    //     while (true) {
    //         result = inputString(inputName, "length from " + minLength + " to " + maxLength + " characters");
    //         if (result.length() < minLength || result.length() > maxLength) {
    //             System.out.println("Your " + inputName + " is too short or too long. Try again!");
    //             continue;
    //         }
    //         return result;
    //     }
    // }

    public interface CheckUnique {

        public boolean check(String checkedId);
    }

    public interface CheckExist {

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

    public static String inputIdWithFormat(String inputName, String condition, CheckUnique checkUnique, String format) {
        String result;
        while (true) {
            result = ValidInput.inputId(inputName, condition, checkUnique);
            if (!result.matches(format)) {
                System.out.println("Your " + inputName + " is wrong format. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputExistId(String inputName, String condition, CheckExist CheckExist) {
        String result;
        while (true) {
            result = inputString(inputName, condition);
            if (!CheckExist.check(result)) {
                System.out.println("Your " + inputName + " is not exist. Try again!");
                continue;
            }
            return result;
        }
    }

    public static double inputPositiveNumber(String inputName) {
        double result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(greater than 0): ");
            if (!sc.hasNextDouble()) {
                System.out.println("Your price is not a number. Try again!");
                continue;
            }
            result = sc.nextDouble();
            if (result <= 0) {
                System.out.println("Your " + inputName + " is 0 or less than 0. Try again!");
                continue;
            }
            return result;
        }
    }

    // public static Status inputStatus(String inputName) {
    //     String result;
    //     while (true) {
    //         result = inputString(inputName, "'available' or 'not available' values");
    //         if (!result.equals("available") && !result.equals("not available")) {
    //             System.out.println("Your " + inputName + " is not 'available' or 'not available'. Try again!");
    //             continue;
    //         }
    //         return result.equals("available") ? Status.AVAILABLE : Status.NOT_AVAILABLE;
    //     }
    // }
}
