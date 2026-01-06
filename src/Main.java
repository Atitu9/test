import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Staff> allStaff = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        allStaff.add(new Staff(1001, "Aibek", 400000, 5));
        allStaff.add(new Chef(2001, "Murat", 600000, 12, "Kazakh Cuisine"));
        allStaff.add(new Waiter(3001, "Dana", 300000, 4, 95));

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStaff();
                case 2 -> addChef();
                case 3 -> addWaiter();
                case 4 -> viewAllStaff();
                case 5 -> demonstratePolymorphism();
                case 6 -> viewChefsOnly();
                case 7 -> viewWaitersOnly();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
        System.out.println("Program finished.");
    }
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println(" RESTAURANT MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Staff Member (General)");
        System.out.println("2. Add Chef");
        System.out.println("3. Add Waiter");
        System.out.println("4. View All Staff (Polymorphic)");
        System.out.println("5. Make All Staff Work");
        System.out.println("6. View Chefs Only");
        System.out.println("7. View Waiters Only");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    private static void addStaff() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble(); scanner.nextLine();

        System.out.print("Experience years: ");
        int exp = scanner.nextInt(); scanner.nextLine();

        allStaff.add(new Staff(id, name, salary, exp));
        System.out.println("Staff added.");
    }
    private static void addChef() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble(); scanner.nextLine();

        System.out.print("Experience years: ");
        int exp = scanner.nextInt(); scanner.nextLine();

        System.out.print("Specialization: ");
        String spec = scanner.nextLine();

        Staff staff = new Chef(id, name, salary, exp, spec);
        allStaff.add(staff);
        System.out.println("Chef added.");
    }
    private static void addWaiter() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble(); scanner.nextLine();

        System.out.print("Experience years: ");
        int exp = scanner.nextInt(); scanner.nextLine();

        System.out.print("Tables served: ");
        int tables = scanner.nextInt(); scanner.nextLine();

        Staff staff = new Waiter(id, name, salary, exp, tables);
        allStaff.add(staff);
        System.out.println("Waiter added.");
    }
    private static void viewAllStaff() {
        System.out.println("\nALL STAFF:");
        for (Staff s : allStaff) {
            System.out.println(s);

            if (s instanceof Chef) {
                Chef c = (Chef) s;
                if (c.isMasterChef()) {
                    System.out.println(" Master Chef");
                }
            } else if (s instanceof Waiter) {
                Waiter w = (Waiter) s;
                if (w.isTopWaiter()) {
                    System.out.println(" Top Waiter");
                }
            }
        }
    }
    private static void demonstratePolymorphism() {
        System.out.println("\nPOLYMORPHISM DEMONSTRATION:");
        for (Staff s : allStaff) {
            s.work(); // SAME method, DIFFERENT behavior
        }
    }
    private static void viewChefsOnly() {
        System.out.println("\nCHEFS:");
        for (Staff s : allStaff) {
            if (s instanceof Chef) {
                Chef c = (Chef) s;
                System.out.println(c.getName() +
                        " | " + c.getSpecialization());
            }
        }
    }
    private static void viewWaitersOnly() {
        System.out.println("\nWAITERS:");
        for (Staff s : allStaff) {
            if (s instanceof Waiter) {
                Waiter w = (Waiter) s;
                System.out.println(w.getName() +
                        " | Tables: " + w.getTablesServed());
            }
        }
    }
}
