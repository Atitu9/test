package model;

public class Sale {
    private Product product;
    private Customer customer;
    private int quantity;

    public Sale(Product product, Customer customer, int quantity) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");

        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }
    public void completeSale() throws IllegalArgumentException {
        double totalPrice = product.getPrice() * quantity;

        if (!customer.canPay(totalPrice)) throw new IllegalArgumentException("Not enough balance!");
        if (product.getQuantity() < quantity) throw new IllegalArgumentException("Not enough product in stock!");

        customer.pay(totalPrice);
        product.setQuantity(product.getQuantity() - quantity);
    }
    public Product getProduct() { return product; }
    public Customer getCustomer() { return customer; }
    public int getQuantity() { return quantity; }
}
