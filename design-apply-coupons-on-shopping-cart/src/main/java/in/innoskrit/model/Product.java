package in.innoskrit.model;

import java.util.UUID;

public class Product implements CartItem {
    private final String productId;
    private final double price;
    private String productName;

    public Product(double price, String productName) {
        this.productId = UUID.randomUUID().toString();
        this.price = price;
        this.productName = productName;
    }

    public double getPrice() {
        return this.price;
    }
}
