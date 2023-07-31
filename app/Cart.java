package app;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	// Declaring private instance variable named items.
		private List<CartItem> items;

		public Cart() {
			// Initializing cart by creating a new arraylist named items to store the
			// CartItem objects
			items = new ArrayList<>();
		}

		// Method for adding items to cart.
		// Adding CartItem object to the cart's list of items.
		public void addItem(CartItem item) {
			items.add(item);
		}

		// Same with this just removing the items from cart.
		public void removeItem(CartItem item) {
			items.remove(item);
		}

		// Getting item by id after in cart and using the getter methods
		// in the parent class SalableProduct.
		public CartItem getItemById(String itemId) {
			for (CartItem item : items) {
				// Checking if the id's matches that the user enters.
				if (item.getProduct().getIdNumber().equals(itemId)) {
					return item;
				}
			}
			return null;
		}

		public void displayCart() {
			// if the list in cart is empty, show this statement that its empty.
			if (items.isEmpty()) {
				System.out.println("Your cart is empty.");
				// If there are items, display these values of the attributes.
			} else {
				for (CartItem item : items) {
					System.out.println("Product ID: " + item.getProduct().getIdNumber());
					System.out.println("Name: " + item.getProduct().getName());
					System.out.println("Price: " + item.getProduct().getPrice());
					System.out.println("Quantity: " + item.getQuantity());
					System.out.println("Subtotal: " + item.calculateSubtotal());
					System.out.println("===========================");
				}
				// Give a total of the items using the calculateTotal method
				System.out.println("Total: " + calculateTotal());
			}
		}

		// Method to calculate the total.
		public double calculateTotal() {
			// Set the current number to start off as 0.
			double total = 0.0;
			// For loop to add each item in the cart items list and returning it.
			for (CartItem item : items) {
				total += item.calculateSubtotal();
			}
			return total;
		}
	}
