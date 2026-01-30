package model;

public class Customer {
    private int customerId;
    private String name;
    private double balance;

    public Customer(int customerId, String name, double balance) {
        setName(name);
        setBalance(balance);
        this.customerId = customerId;
    }
    public boolean canPay(double amount) {
        return balance >= amount;
    }
    public void pay(double amount) {
        if (!canPay(amount)) throw new IllegalArgumentException("Customer cannot pay: " + amount);
        balance -= amount;
    }
    public String getName() { return name; }

    public double getBalance() { return balance; }


    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty");
        this.name = name;
    }
    public void setBalance(double balance) {
        if (balance < 0) throw new IllegalArgumentException("Balance cannot be negative");
        this.balance = balance;
    }
}
