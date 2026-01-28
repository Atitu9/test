package database;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public void insertProduct(Product product, String type) {
        String sql = "INSERT INTO product (name, price, quantity, type) VALUES (?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setString(4, type);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Product inserted into DB");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(conn);
        }
    }
    public void getAllProducts() {
        String sql = "SELECT * FROM product";

        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n--- PRODUCTS FROM DATABASE ---");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("product_id") +
                                ", Name: " + rs.getString("name") +
                                ", Price: " + rs.getDouble("price") +
                                ", Qty: " + rs.getInt("quantity") +
                                ", Type: " + rs.getString("type")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(conn);
        }
    }
}
