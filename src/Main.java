import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Добавим тестовые данные
        allProducts.add(new Product(1, "Rice", 1500, 50));
        allProducts.add(new FreshProduct(2, "Milk", 800, 20, 5));
        allProducts.add(new PackagedProduct(3, "Pasta", 1200, 30, "Plastic"));

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1: addProduct(); break;
                case 2: addFreshProduct(); break;
                case 3: addPackagedProduct(); break;
                case 4: viewAllProducts(); break;
                case 5: demonstratePolymorphism(); break;
                case 6: viewFreshProducts(); break;
                case 7: viewPackagedProducts(); break;
                case 0: running = false; break;
                default: System.out.println("Invalid choice!"); break;
            }
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println(" GROCERY STORE SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Product (General)");
        System.out.println("2. Add Fresh Product");
        System.out.println("3. Add Packaged Product");
        System.out.println("4. View All Products");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View Fresh Products Only");
        System.out.println("7. View Packaged Products Only");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // Методы добавления продуктов
    private static void addProduct() {
        System.out.println("\n--- ADD PRODUCT ---");
        System.out.print("Enter ID: "); int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter name: "); String name = scanner.nextLine();
        System.out.print("Enter price: "); double price = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Enter quantity: "); int quantity = scanner.nextInt(); scanner.nextLine();
        allProducts.add(new Product(id, name, price, quantity));
        System.out.println("Product added successfully!");
    }
    private static void addFreshProduct() {
        System.out.println("\n--- ADD FRESH PRODUCT ---");
        System.out.print("Enter ID: "); int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter name: "); String name = scanner.nextLine();
        System.out.print("Enter price: "); double price = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Enter quantity: "); int quantity = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter shelf life (days): "); int shelfLife = scanner.nextInt(); scanner.nextLine();
        allProducts.add(new FreshProduct(id, name, price, quantity, shelfLife));
        System.out.println("Fresh product added successfully!");
    }
    private static void addPackagedProduct() {
        System.out.println("\n--- ADD PACKAGED PRODUCT ---");
        System.out.print("Enter ID: "); int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter name: "); String name = scanner.nextLine();
        System.out.print("Enter price: "); double price = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Enter quantity: "); int quantity = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter packaging type: "); String packaging = scanner.nextLine();
        allProducts.add(new PackagedProduct(id, name, price, quantity, packaging));
        System.out.println("Packaged product added successfully!");
    }

    // Просмотр всех продуктов (полиморфно)
    private static void viewAllProducts() {
        System.out.println("\n========================================");
        System.out.println(" ALL PRODUCTS (POLYMORPHIC LIST)");
        System.out.println("========================================");
        if (allProducts.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : allProducts) {
            p.displayInfo();
        }
    }
    private static void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        for (Product p : allProducts) {
            p.displayInfo(); // один метод, разные реализации!
        }
    }
    private static void viewFreshProducts() {
        System.out.println("\n--- FRESH PRODUCTS ---");
        int count = 0;
        for (Product p : allProducts) {
            if (p instanceof FreshProduct) {
                count++;
                FreshProduct fp = (FreshProduct) p;
                System.out.println(count + ". " + fp);
                if (fp.isExpiringSoon()) System.out.println(" Expiring soon!");
            }
        }
        if (count == 0) System.out.println("No fresh products found.");
    }
    private static void viewPackagedProducts() {
        System.out.println("\n--- PACKAGED PRODUCTS ---");
        int count = 0;
        for (Product p : allProducts) {
            if (p instanceof PackagedProduct) {
                count++;
                PackagedProduct pp = (PackagedProduct) p;
                System.out.println(count + ". " + pp);
                if (pp.isEcoFriendly()) System.out.println(" Eco-friendly!");
            }
        }
        if (count == 0) System.out.println("No packaged products found.");
    }
}
