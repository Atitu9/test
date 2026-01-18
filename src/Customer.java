public class Customer {
    private int customerId;
    private String name;
    private double balance;

    public Customer(int customerId, String name, double balance) {
        this.customerId = customerId;
        this.name = name;
        this.balance = balance >= 0 ? balance : 0;
    }
    public boolean canPay(double amount) {
        return balance >= amount;
    }
    public void pay(double amount) {
        if (canPay(amount)) {
            balance -= amount;
        }
    }
    public String getName() {
        return name;
    }
}

