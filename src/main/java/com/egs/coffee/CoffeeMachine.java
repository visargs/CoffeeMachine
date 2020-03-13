package com.egs.coffee;

import com.egs.coffee.model.Command;
import com.egs.coffee.model.Product;
import com.egs.coffee.service.ServiceImpl;

import java.util.Scanner;

public class CoffeeMachine {
    private static int balance;
    private static Product product;
    private static ServiceImpl service = new ServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        service.init();
        boolean isRun = true;
        while (true) {
            System.out.println("Please choose product");
            System.out.println("----------------------");
            commands();
            Command command;
            try {
                command = Command.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                command = Command.EXIT;
            }
            switch (command) {
                case EXIT:
                    System.out.println("Application is done");
                    System.exit(0);
                    break;
                case COFFEE:
                case LOTTE:
                case CAPPUCCINO:
                    product = service.getByName(String.valueOf(command));
                    amountChecker();
                    System.out.println("----------------------");
                    break;
                case ADD:
                    add();
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private static void commands() {
        System.out.println("Input " + Command.COFFEE + " for Coffee" + " price is 100");
        System.out.println("Input " + Command.LOTTE + " for Lotte" + " price is 100");
        System.out.println("Input " + Command.CAPPUCCINO + " for Cappuccino" + " price is 100");
        System.out.println("----------------------");
    }

    private static void amountChecker() {
        System.out.println("Enter amount");
        try {
            balance = Integer.parseInt(scanner.nextLine());
            priceChecker(balance);
        } catch (NumberFormatException e) {
            System.out.println("Wrong command, please try again");
            amountChecker();
        }
    }

    /**
     * This method is for admin I don't add it to command.
     */
    private static void add() {
        System.out.println("Input name, price ");
        try {
            String add = scanner.nextLine();
            String[] addSt = add.split(",");
            Product product = new Product();
            product.setName(addSt[0]);
            product.setPrice(Integer.parseInt(addSt[1]));
            service.add(product);
            System.out.println("The product is added");
            System.out.println("----------------------");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough symbol");
            add();
        }
    }

    /**
     * This method is check 3 ways to paid.
     *
     * @param amount
     */
    private static void priceChecker(int amount) {
        int change;
        if (product.getPrice() == amount) {
            balance += product.getPrice();
            System.out.println("Your order is ready");
        } else if (product.getPrice() < amount) {
            change = amount - product.getPrice();
            System.out.println("Please your change " + change);
            System.out.println("Your order is ready");
        } else if (product.getPrice() > amount) {
            System.out.println("Not enough money!");
        }
    }
}
