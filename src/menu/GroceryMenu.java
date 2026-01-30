package menu;

import database.ProductDAO;
import model.*;

import java.util.List;
import java.util.Scanner;

public class GroceryMenu implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductDAO dao = new ProductDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n==== GROCERY STORE SYSTEM (WEEK 8) ====");
        System.out.println("1. Add Fresh Product ");
        System.out.println("2. Add Packaged Product ");
        System.out.println("3. View All Products ");
        System.out.println("4. View Fresh Products Only ");
        System.out.println("5. View Packaged Products Only ");
        System.out.println("6. Update Product ");
        System.out.println("7. Delete Product ");
        System.out.println("--SEARCH & FILTER-----------------");
        System.out.println("8. Search by Name ");
        System.out.println("9. Search by Price Range ");
        System.out.println("10. Search by Min Quantity ");
        System.out.println("--DEMO & OTHER-------------------");
        System.out.println("11. Polymorphism Demo ");
        System.out.println("12. Sell Product ");
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
                    case 1 -> addFreshProductDB();
                    case 2 -> addPackagedProductDB();
                    case 3 -> dao.displayAllProducts();
                    case 4 -> viewFreshProductsDB();
                    case 5 -> viewPackagedProductsDB();
                    case 6 -> updateProductDB();
                    case 7 -> deleteProductDB();
                    case 8 -> searchByNameDB();
                    case 9 -> searchByPriceRangeDB();
                    case 10 -> searchByMinQuantityDB();
                    case 11 -> dao.demonstratePolymorphism();
                    case 12 -> sellProductDB();
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

    private void addFreshProductDB() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        System.out.print("Shelf life (days): ");
        int shelf = Integer.parseInt(scanner.nextLine());

        FreshProduct product = new FreshProduct(0, name, price, qty, shelf);
        boolean ok = dao.insertFreshProduct(product);
        System.out.println(ok ? "Fresh product inserted!" : "Insert failed!");
    }

    private void addPackagedProductDB() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        System.out.print("Packaging type: ");
        String packaging = scanner.nextLine();

        PackagedProduct product = new PackagedProduct(0, name, price, qty, packaging);
        boolean ok = dao.insertPackagedProduct(product);
        System.out.println(ok ? "Packaged product inserted!" : "Insert failed!");
    }

    private void viewFreshProductsDB() {
        List<FreshProduct> list = dao.getAllFreshProducts();
        if (list.isEmpty()) {
            System.out.println("No fresh products found.");
            return;
        }
        for (FreshProduct p : list) {
            p.displayInfo();
            if (p.isExpiringSoon()) System.out.println(" Expiring soon!");
        }
    }

    private void viewPackagedProductsDB() {
        List<PackagedProduct> list = dao.getAllPackagedProducts();
        if (list.isEmpty()) {
            System.out.println("No packaged products found.");
            return;
        }
        for (PackagedProduct p : list) {
            p.displayInfo();
            if (p.isEcoFriendly()) System.out.println(" Eco-friendly!");
        }
    }

    private void updateProductDB() {
        System.out.print("Enter product ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product existing = dao.getProductById(id);
        if (existing == null) {
            System.out.println("No product found with ID: " + id);
            return;
        }

        System.out.println("Current Info:");
        System.out.println(existing.toString());

        System.out.print("New Name [" + existing.getName() + "]: ");
        String newName = scanner.nextLine();
        if (newName.trim().isEmpty()) newName = existing.getName();

        System.out.print("New Price [" + existing.getPrice() + "]: ");
        String priceInput = scanner.nextLine();
        double newPrice = priceInput.trim().isEmpty() ? existing.getPrice() : Double.parseDouble(priceInput);

        System.out.print("New Quantity [" + existing.getQuantity() + "]: ");
        String qtyInput = scanner.nextLine();
        int newQty = qtyInput.trim().isEmpty() ? existing.getQuantity() : Integer.parseInt(qtyInput);

        boolean ok;

        if (existing instanceof FreshProduct fp) {
            System.out.print("New Shelf life days [" + fp.getShelfLifeDays() + "]: ");
            String shelfInput = scanner.nextLine();
            int newShelf = shelfInput.trim().isEmpty() ? fp.getShelfLifeDays() : Integer.parseInt(shelfInput);

            FreshProduct updated = new FreshProduct(id, newName, newPrice, newQty, newShelf);
            ok = dao.updateFreshProduct(updated, id);

        } else if (existing instanceof PackagedProduct pp) {
            System.out.print("New Packaging type [" + pp.getPackagingType() + "]: ");
            String packInput = scanner.nextLine();
            String newPack = packInput.trim().isEmpty() ? pp.getPackagingType() : packInput;

            PackagedProduct updated = new PackagedProduct(id, newName, newPrice, newQty, newPack);
            ok = dao.updatePackagedProduct(updated, id);

        } else {
            PackagedProduct updated = new PackagedProduct(id, newName, newPrice, newQty, "Unknown");
            ok = dao.updatePackagedProduct(updated, id);
        }

        System.out.println(ok ? "Update successful!" : "Update failed!");
    }

    private void deleteProductDB() {
        System.out.print("Enter product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product existing = dao.getProductById(id);
        if (existing == null) {
            System.out.println("No product found with ID: " + id);
            return;
        }

        System.out.println("Product to delete:");
        System.out.println(existing.toString());

        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        boolean ok = dao.deleteProduct(id);
        System.out.println(ok ? "Deleted successfully!" : "Delete failed!");
    }

    private void searchByNameDB() {
        System.out.print("Enter name (partial): ");
        String name = scanner.nextLine();

        List<Product> list = dao.searchByName(name);
        if (list.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : list) p.displayInfo();
    }

    private void searchByPriceRangeDB() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());

        List<Product> list = dao.searchByPriceRange(min, max);
        if (list.isEmpty()) {
            System.out.println("No products found in range.");
            return;
        }
        for (Product p : list) p.displayInfo();
    }

    private void searchByMinQuantityDB() {
        System.out.print("Min quantity: ");
        int minQty = Integer.parseInt(scanner.nextLine());

        List<Product> list = dao.searchByMinQuantity(minQty);
        if (list.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : list) p.displayInfo();
    }

    private void sellProductDB() {
        System.out.print("Enter product ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product product = dao.getProductById(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Product:");
        System.out.println(product.toString());

        System.out.print("Enter customer name: ");
        String custName = scanner.nextLine();
        System.out.print("Enter customer balance: ");
        double balance = Double.parseDouble(scanner.nextLine());
        Customer customer = new Customer(1, custName, balance);

        System.out.print("Enter quantity to buy: ");
        int qty = Integer.parseInt(scanner.nextLine());
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive");

        double totalPrice = product.getPrice() * qty;
        if (!customer.canPay(totalPrice)) throw new IllegalArgumentException("Customer cannot pay");
        if (product.getQuantity() < qty) throw new IllegalArgumentException("Not enough stock");

        customer.pay(totalPrice);
        int newQty = product.getQuantity() - qty;

        boolean ok;
        if (product instanceof FreshProduct fp) {
            FreshProduct updated = new FreshProduct(fp.getProductId(), fp.getName(), fp.getPrice(), newQty, fp.getShelfLifeDays());
            ok = dao.updateFreshProduct(updated, id);
        } else if (product instanceof PackagedProduct pp) {
            PackagedProduct updated = new PackagedProduct(pp.getProductId(), pp.getName(), pp.getPrice(), newQty, pp.getPackagingType());
            ok = dao.updatePackagedProduct(updated, id);
        } else {
            PackagedProduct updated = new PackagedProduct(product.getProductId(), product.getName(), product.getPrice(), newQty, "Unknown");
            ok = dao.updatePackagedProduct(updated, id);
        }

        System.out.println(ok ? "Sale completed! Stock updated in DB." : "Sale failed (DB update failed).");
        System.out.println("Customer remaining balance: " + customer.getBalance());
    }
}
