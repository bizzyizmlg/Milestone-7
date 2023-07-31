package test;

import static org.junit.Assert.*;


import org.junit.Test;

import app.Cart;
import app.CartItem;
import app.SalableProduct;

public class CartTest {

	@Test
	public void testCart() {
		// Create a new instance of the Cart
		Cart cart = new Cart();
		
		// Assert that the cart is not null
		assertNotNull(cart);
	}

	@Test
	public void testAddItem() {
		// Create a new instance of the Cart
		Cart cart = new Cart();
		
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem = new CartItem(product, 5);
		
		// Call the addItem method to add the CartItem to the cart
		cart.addItem(cartItem);
		
		// Since there is no getCartItems method, we can check if the cart contains the added item directly
		assertTrue(cart.getItemById("001") != null);
	}

	@Test
	public void testRemoveItem() {
		// Create a new instance of the Cart
		Cart cart = new Cart();
		
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem = new CartItem(product, 5);
		
		// Add the CartItem to the cart
		cart.addItem(cartItem);
		
		// Call the removeItem method to remove the CartItem from the cart
		cart.removeItem(cartItem);
		
		// Since there is no getCartItems method, we can check if the cart does not contain the removed item directly
		assertFalse(cart.getItemById("001") != null);
	}

	@Test
	public void testGetItemById() {
		// Create a new instance of the Cart
		Cart cart = new Cart();
		
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem = new CartItem(product, 5);
		
		// Add the CartItem to the cart
		cart.addItem(cartItem);
		
		// Call the getItemById method to get the CartItem by its product ID
		String productId = "001";
		CartItem retrievedCartItem = cart.getItemById(productId);
		
		// Assert that the retrieved CartItem is the same as the original CartItem
		assertEquals(cartItem, retrievedCartItem);
	}

	@Test
	public void testDisplayCart() {
		// Since the displayCart method only prints the contents, we can test it manually by observing the output
		// This test case can be used to visually verify the displayCart method's output
		Cart cart = new Cart();
		SalableProduct product1 = new SalableProduct("Product 1", "Product Description 1", 10.0, 100, "001");
		SalableProduct product2 = new SalableProduct("Product 2", "Product Description 2", 20.0, 50, "002");
		CartItem cartItem1 = new CartItem(product1, 5);
		CartItem cartItem2 = new CartItem(product2, 3);
		cart.addItem(cartItem1);
		cart.addItem(cartItem2);
		cart.displayCart();
	}

	@Test
	public void testCalculateTotal() {
		// Create a new instance of the Cart
		Cart cart = new Cart();
		
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product1 = new SalableProduct("Product 1", "Product Description 1", 10.0, 100, "001");
		SalableProduct product2 = new SalableProduct("Product 2", "Product Description 2", 20.0, 50, "002");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem1 = new CartItem(product1, 5);
		CartItem cartItem2 = new CartItem(product2, 3);
		
		// Add the CartItems to the cart
		cart.addItem(cartItem1);
		cart.addItem(cartItem2);
		
		// Call the calculateTotal method to calculate the total price of the cart
		double total = cart.calculateTotal();
		
		// Calculate the expected total based on the product prices and quantities
		double expectedTotal = (10.0 * 5) + (20.0 * 3);
		
		// Assert that the calculated total is the same as the expected total
		assertEquals(expectedTotal, total, 0.01); // Use delta for double comparison
	}

}
