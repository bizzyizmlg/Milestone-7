package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.CartItem;
import app.SalableProduct;

public class CartItemTest {

	@Test
	public void testCartItemSalableProductInt() {
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem = new CartItem(product, 5);
		
		// Assert that the product in the CartItem is the same as the provided SalableProduct
		assertEquals(product, cartItem.getProduct());
		
		// Assert that the quantity in the CartItem is the same as the provided quantity
		assertEquals(5, cartItem.getQuantity());
	}

	@Test
	public void testCartItem() {
		// Create a new CartItem with the default constructor
		CartItem cartItem = new CartItem();
		
		// Assert that the CartItem object is not null
		assertNotNull(cartItem);
	}

	@Test
	public void testGetProduct() {
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem = new CartItem(product, 5);
		
		// Call the getProduct method to get the SalableProduct from the CartItem
		SalableProduct retrievedProduct = cartItem.getProduct();
		
		// Assert that the retrieved SalableProduct is the same as the original SalableProduct
		assertEquals(product, retrievedProduct);
	}

	@Test
	public void testGetQuantity() {
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem = new CartItem(product, 5);
		
		// Call the getQuantity method to get the quantity from the CartItem
		int quantity = cartItem.getQuantity();
		
		// Assert that the retrieved quantity is the same as the original quantity
		assertEquals(5, quantity);
	}

	@Test
	public void testCalculateSubtotal() {
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Create a new CartItem with the SalableProduct and a quantity (you can add your quantity here)
		CartItem cartItem = new CartItem(product, 5);
		
		// Call the calculateSubtotal method to calculate the subtotal for the CartItem
		double subtotal = cartItem.calculateSubtotal();
		
		// Calculate the expected subtotal based on the product price and quantity
		double expectedSubtotal = 10.0 * 5;
		
		// Assert that the calculated subtotal is the same as the expected subtotal
		assertEquals(expectedSubtotal, subtotal, 0.01); // Use delta for double comparison
	}

}
