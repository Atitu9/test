package model;

public class ProductSale implements Sellable {
    private Product product;

    public ProductSale(Product product) {
        this.product = product;
    }
    @Override
    public void sell(Customer customer, int quantity) {
        Sale sale = new Sale(product, customer, quantity);
        sale.completeSale(); // выбрасывает исключения при ошибке
    }
}
