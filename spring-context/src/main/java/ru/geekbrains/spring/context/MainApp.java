package ru.geekbrains.spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStreamReader;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Cart cart = context.getBean("cart", Cart.class);
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("exit for exit, cart to get new cart, show is for show, add to add product to cart," +
                " delete to remove first obj by id, deleteAll to remove all by id");
        String currentCmd = scanner.nextLine();
        while (!currentCmd.equals("exit")) {
            if (currentCmd.equals("cart")) {
                cart = context.getBean("cart", Cart.class);
            } else if (currentCmd.equals("show")) {
                cart.showProductsInCart();
            } else if (currentCmd.startsWith("add ")) {
                try {
                    Long id = Long.parseLong(currentCmd.split(" ")[1]);
                    cart.addProductById(id);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong id, type only numbers");
                    e.printStackTrace();
                }
            } else if (currentCmd.startsWith("delete ")) {
                try {
                    Long id = Long.parseLong(currentCmd.split(" ")[1]);
                    cart.deleteProductById(id);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong id, type only numbers");
                    e.printStackTrace();
                }
            } else if (currentCmd.startsWith("deleteAll ")) {
                try {
                    Long id = Long.parseLong(currentCmd.split(" ")[1]);
                    cart.deleteAllProductsById(id);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong id, type only numbers");
                    e.printStackTrace();
                }
            }
            currentCmd = scanner.nextLine();
        }

        scanner.close();


        context.close();
    }

}
