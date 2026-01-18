public class Sale {
    private Product product;
    private Customer customer;
    private int quantity;

    public Sale(Product product, Customer customer, int quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }
    public void completeSale() {
        double totalPrice = product.getPrice() * quantity;

        if (!customer.canPay(totalPrice)) {
            System.out.println("Not enough balance!");
            return;
        }

        if (product.getQuantity() < quantity) {
            System.out.println("Not enough product in stock!");
            return;
        }
        customer.pay(totalPrice);
        product.setQuantity(product.getQuantity() - quantity);

        System.out.println("Sale completed!");
    }
}

