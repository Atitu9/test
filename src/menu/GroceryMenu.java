package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GroceryMenu implements Menu {

    private ArrayList<Product> allProducts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public GroceryMenu() {
        allProducts.add(new FreshProduct(1, "Milk", 800, 10, 5));
        allProducts.add(new PackagedProduct(2, "Pasta", 1200, 20, "Plastic"));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n==== GROCERY STORE SYSTEM ====");
        System.out.println("1. Add Fresh Product");
        System.out.println("2. Add Packaged Product");
        System.out.println("3. View All Products");
        System.out.println("4. Sell Product");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addFreshProduct();
                    case 2 -> addPackagedProduct();
                    case 3 -> viewAllProducts();
                    case 4 -> sellProduct();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number input!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private void addFreshProduct() {
        try {
            System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Price: "); double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Quantity: "); int qty = Integer.parseInt(scanner.nextLine());
            System.out.print("Shelf life (days): "); int shelf = Integer.parseInt(scanner.nextLine());
            allProducts.add(new FreshProduct(id, name, price, qty, shelf));
            System.out.println("Fresh product added!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addPackagedProduct() {
        try {
            System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Price: "); double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Quantity: "); int qty = Integer.parseInt(scanner.nextLine());
            System.out.print("Packaging: "); String pack = scanner.nextLine();
            allProducts.add(new PackagedProduct(id, name, price, qty, pack));
            System.out.println("Packaged product added!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllProducts() {
        if (allProducts.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product p : allProducts) {
            p.displayInfo();
        }
    }

    private void sellProduct() {
        try {
            System.out.print("Enter product ID: "); int id = Integer.parseInt(scanner.nextLine());
            Product product = allProducts.stream()
                    .filter(p -> p.getProductId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            System.out.print("Enter customer name: "); String custName = scanner.nextLine();
            System.out.print("Enter customer balance: "); double balance = Double.parseDouble(scanner.nextLine());
            Customer customer = new Customer(1, custName, balance);

            System.out.print("Enter quantity to buy: "); int qty = Integer.parseInt(scanner.nextLine());
            if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive");

            double totalPrice = product.getPrice() * qty;
            if (!customer.canPay(totalPrice)) throw new IllegalArgumentException("Customer cannot pay");
            if (product.getQuantity() < qty) throw new IllegalArgumentException("Not enough stock");

            customer.pay(totalPrice);
            product.setQuantity(product.getQuantity() - qty);

            System.out.println("Sale completed!");
            System.out.println("Remaining product quantity: " + product.getQuantity());
            System.out.println("Customer remaining balance: " + customer.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
