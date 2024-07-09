package in.innoskrit;

import in.innoskrit.model.*;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.addToCart(new Product(100, "A"));
        shoppingCart1.addToCart(new PercentageDiscount(new Product(200, "B"), 10));
        shoppingCart1.addToCart(new FixedDiscount(new PercentageDiscount(new Product(300, "C"), 10), 20));
        System.out.println(shoppingCart1.calculateTotalPrice());
    }
}