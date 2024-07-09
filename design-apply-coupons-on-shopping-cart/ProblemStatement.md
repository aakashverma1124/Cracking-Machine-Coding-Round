# Apply Coupons on Shopping Cart Products

### Problem Statement
You are tasked with building a Shopping Cart Service API that calculates the total price for a given shopping cart, taking into account both products and discount coupons. The service should be capable of handling multiple products and discounts in a flexible manner.

The Cart API should support the following types of discount coupons:

- A percentage discount on a specific item.
- A percentage discount on the next item added to the cart.
- A fixed discount amount for a specific product type.

The API should accept input in the form of product and discount information provided via external sources, such as product and discount APIs. The goal is to design a system that can dynamically calculate the total price of a shopping cart based on the given product and discount data.

### Design Pattern Used

I've used Decorator Design Pattern here. It allows behavior to be added to an object dynamically. 

In our context, we should be able to apply multiple discounts on the products. 



