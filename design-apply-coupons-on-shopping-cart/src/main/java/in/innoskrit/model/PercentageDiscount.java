package in.innoskrit.model;

public class PercentageDiscount implements DiscountDecorator {
    private final CartItem cartItem;
    private final double percentage;

    public PercentageDiscount(CartItem cartItem, double percentage) {
        this.cartItem = cartItem;
        this.percentage = percentage;
    }

    public double getPrice() {
        return this.cartItem.getPrice() * (1 - this.percentage/100);
    }
}
