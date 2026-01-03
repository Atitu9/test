public class Main {

    public static void main(String[] args) {

        System.out.println("=== Grocery Store Management System ===\n");

        Product p1 = new Product(1, "Milk", 450.0, 20, true);
        Product p2 = new Product(2, "Bread", 200.0, 0, false);
        Product p3 = new Product();

        Customer c1 = new Customer(101, "Alice Brown", "Regular", 5000.0, 50);
        Customer c2 = new Customer(102, "Bob Smith", "Gold", 15000.0, 120);

        Sale s1 = new Sale(1001, "Alice Brown", 0.0, "2025-12-24", "Pending");

        System.out.println("--- PRODUCTS ---");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println("\n--- CUSTOMERS ---");
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("\n--- SALES ---");
        System.out.println(s1);

        System.out.println("\n--- TESTING GETTERS ---");
        System.out.println("Product 1 name: " + p1.getName());
        System.out.println("Customer 1 points: " + c1.getLoyaltyPoints());
        System.out.println("Sale status: " + s1.getStatus());

        System.out.println("\n--- TESTING SETTERS ---");
        p3.setName("Eggs");
        p3.setPrice(600.0);
        p3.setStockQuantity(30);
        p3.setAvailable(true);
        System.out.println("Updated product: " + p3);

        System.out.println("\n--- TESTING METHODS ---");
        System.out.println("Bread in stock: " + p2.isInStock());
        p2.restock(15);
        System.out.println("Bread after restock: " + p2);

        s1.addItem(p1.getPrice());
        s1.addItem(p3.getPrice());
        s1.completeSale();
        System.out.println("Completed sale: " + s1);

        c1.addPurchase(s1.getTotalAmount());
        System.out.println("Customer after purchase: " + c1);
        System.out.println("Is VIP: " + c1.isVIP());

        System.out.println("\n=== Program Complete ===");
    }
}
