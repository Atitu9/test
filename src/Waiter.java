public class Waiter extends Staff {

    private int tablesServed;

    public Waiter(int staffId, String name, double salary,
                  int experienceYears, int tablesServed) {
        super(staffId, name, salary, experienceYears);
        this.tablesServed = tablesServed;
    }
    public int getTablesServed() {
        return tablesServed;
    }
    @Override
    public void work() {
        System.out.println("Waiter " + name + " is serving tables.");
    }
    @Override
    public String getRole() {
        return "Waiter";
    }
    public void serveTable(int tableNumber) {
        System.out.println("Waiter " + name +
                " serves table #" + tableNumber);
        tablesServed++;
    }
    public boolean isTopWaiter() {
        return tablesServed > 100 && experienceYears >= 3;
    }
    @Override
    public String toString() {
        return super.toString() + " | Tables Served: " + tablesServed;
    }
}
