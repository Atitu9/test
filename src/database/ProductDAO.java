package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean insertFreshProduct(FreshProduct product) {
        String sql = "INSERT INTO product (name, price, quantity, type, shelf_life_days, packaging_type) " +
                "VALUES (?, ?, ?, 'Fresh', ?, NULL)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getShelfLifeDays());
            int rowsInserted = statement.executeUpdate();
            statement.close();
            if (rowsInserted > 0) {
                System.out.println(" Fresh product inserted: " + product.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println(" Insert FreshProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return false;
    }
    public boolean insertPackagedProduct(PackagedProduct product) {
        String sql = "INSERT INTO product (name, price, quantity, type, shelf_life_days, packaging_type) " +
                "VALUES (?, ?, ?, 'Packaged', NULL, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getPackagingType());
            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println(" Packaged product inserted: " + product.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println(" Insert PackagedProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return false;
    }
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) products.add(product);
            }
            resultSet.close();
            statement.close();
            System.out.println(" Retrieved " + products.size() + " products from database");
        } catch (SQLException e) {
            System.out.println(" Select all products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return products;
    }
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                resultSet.close();
                statement.close();

                if (product != null) System.out.println(" Found product with ID: " + productId);
                return product;
            }
            resultSet.close();
            statement.close();
            System.out.println(" No product found with ID: " + productId);
        } catch (SQLException e) {
            System.out.println(" Select by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return null;
    }
    public List<FreshProduct> getAllFreshProducts() {
        List<FreshProduct> freshProducts = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE type = 'Fresh' ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return freshProducts;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product instanceof FreshProduct) freshProducts.add((FreshProduct) product);
            }
            resultSet.close();
            statement.close();

            System.out.println(" Retrieved " + freshProducts.size() + " fresh products");
        } catch (SQLException e) {
            System.out.println(" Select fresh products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return freshProducts;
    }
    public List<PackagedProduct> getAllPackagedProducts() {
        List<PackagedProduct> packagedProducts = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE type = 'Packaged' ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return packagedProducts;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product instanceof PackagedProduct) packagedProducts.add((PackagedProduct) product);
            }
            resultSet.close();
            statement.close();
            System.out.println(" Retrieved " + packagedProducts.size() + " packaged products");
        } catch (SQLException e) {
            System.out.println(" Select packaged products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return packagedProducts;
    }
    public boolean updateFreshProduct(FreshProduct product, int dbProductId) {
        String sql = "UPDATE product SET name = ?, price = ?, quantity = ?, type = 'Fresh', shelf_life_days = ?, packaging_type = NULL " +
                "WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getShelfLifeDays());
            statement.setInt(5, dbProductId);
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println(" Fresh product updated (ID: " + dbProductId + ")");
                return true;
            } else {
                System.out.println(" No product found to update (ID: " + dbProductId + ")");
            }
        } catch (SQLException e) {
            System.out.println(" Update FreshProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return false;
    }
    public boolean updatePackagedProduct(PackagedProduct product, int dbProductId) {
        String sql = "UPDATE product SET name = ?, price = ?, quantity = ?, type = 'Packaged', shelf_life_days = NULL, packaging_type = ? " +
                "WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getPackagingType());
            statement.setInt(5, dbProductId);
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println(" Packaged product updated (ID: " + dbProductId + ")");
                return true;
            } else {
                System.out.println(" No product found to update (ID: " + dbProductId + ")");
            }
        } catch (SQLException e) {
            System.out.println(" Update PackagedProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return false;
    }
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            if (rowsDeleted > 0) {
                System.out.println(" Product deleted (ID: " + productId + ")");
                return true;
            } else {
                System.out.println(" No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            System.out.println(" Delete product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return false;
    }
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) products.add(product);
            }
            resultSet.close();
            statement.close();
            System.out.println(" Found " + products.size() + " products matching '" + name + "'");
        } catch (SQLException e) {
            System.out.println(" Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return products;
    }
    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price ASC";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) products.add(product);
            }
            resultSet.close();
            statement.close();
            System.out.println(" Found " + products.size() + " products in price range " + minPrice + " - " + maxPrice);
        } catch (SQLException e) {
            System.out.println(" Search by price range failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return products;
    }
    public List<Product> searchByMinQuantity(int minQuantity) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE quantity >= ? ORDER BY quantity DESC";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minQuantity);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) products.add(product);
            }
            resultSet.close();
            statement.close();
            System.out.println(" Found " + products.size() + " products with quantity >= " + minQuantity);
        } catch (SQLException e) {
            System.out.println(" Search by quantity failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
        return products;
    }
    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        String type = resultSet.getString("type");
        if ("Fresh".equalsIgnoreCase(type)) {
            int shelf = resultSet.getInt("shelf_life_days");
            return new FreshProduct(id, name, price, quantity, shelf);
        }
        if ("Packaged".equalsIgnoreCase(type)) {
            String packaging = resultSet.getString("packaging_type");
            return new PackagedProduct(id, name, price, quantity, packaging == null ? "Unknown" : packaging);
        }
        return new PackagedProduct(id, name, price, quantity, "Unknown");
    }
    public void displayAllProducts() {
        List<Product> products = getAllProducts();

        System.out.println("\n========================================");
        System.out.println("   ALL PRODUCTS FROM DATABASE");
        System.out.println("========================================");
        if (products.isEmpty()) {
            System.out.println("No products in database.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.print((i + 1) + ". ");
                System.out.println(p.toString());
            }
        }
        System.out.println("========================================\n");
    }
    public void demonstratePolymorphism() {
        List<Product> products = getAllProducts();

        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM: Products from Database");
        System.out.println("========================================");
        if (products.isEmpty()) {
            System.out.println("No products to demonstrate.");
        } else {
            for (Product p : products) {
                p.displayInfo();
            }
        }
        System.out.println("========================================\n");
    }
}
