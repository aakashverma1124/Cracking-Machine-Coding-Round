package in.innoskrit.model;

public class FixedDiscount implements DiscountDecorator {
    private final CartItem cartItem;
    private final double fixedDiscountAmount;

    public FixedDiscount(CartItem cartItem, double fixedDiscountAmount) {
        this.cartItem = cartItem;
        this.fixedDiscountAmount = fixedDiscountAmount;
    }

    public double getPrice() {
        return cartItem.getPrice() - this.fixedDiscountAmount;
    }
}
