package in.innoskrit.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public double calculateTotalPrice() {
        double totalAmount = 0;
        for(CartItem cartItem : cartItems) {
            totalAmount += (cartItem.getPrice());
        }
        return totalAmount;
    }
}
