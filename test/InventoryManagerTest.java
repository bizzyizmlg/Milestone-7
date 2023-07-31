package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import app.InventoryManager;
import app.SalableProduct;

public class InventoryManagerTest {

	@Test
	public void testInventoryManager() {
		// This is a test for the constructor of the InventoryManager class.
		// As the constructor only initializes an empty inventory list,
		// there is nothing specific to test here.
		// If you have any custom initialization or setup in the constructor,
		// you can add relevant test cases here.
	}

	@Test
	public void testInitializeInventory() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a list of SalableProduct objects (you can add your products here)
		List<SalableProduct> products = new ArrayList<>();
		// Add products to the list
		
		// Call the initializeInventory method to initialize the inventory
		inventoryManager.initializeInventory(products);
		
		// Assert that the inventory list is not empty after initialization
		assertFalse(inventoryManager.getInventory().isEmpty());
	}

	@Test
	public void testAddProduct() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a new product (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Call the addProduct method to add the product to the inventory
		inventoryManager.addProduct(product);
		
		// Assert that the inventory list contains the added product
		assertTrue(inventoryManager.getInventory().contains(product));
	}

	@Test
	public void testRemoveProduct() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a new product (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Add the product to the inventory
		inventoryManager.addProduct(product);
		
		// Call the removeProduct method to remove the product from the inventory
		inventoryManager.removeProduct(product);
		
		// Assert that the inventory list does not contain the removed product
		assertFalse(inventoryManager.getInventory().contains(product));
	}

	@Test
	public void testUpdateQuantity() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a new product (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Add the product to the inventory
		inventoryManager.addProduct(product);
		
		// Call the updateQuantity method to update the quantity of the product
		int newQuantity = 50;
		inventoryManager.updateQuantity(product, newQuantity);
		
		// Assert that the quantity of the product is updated
		assertEquals(newQuantity, product.getQuantity());
	}

	@Test
	public void testGetProductById() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a new product (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");
		
		// Add the product to the inventory
		inventoryManager.addProduct(product);
		
		// Call the getProductById method to get the product by its ID
		String productId = "001";
		SalableProduct retrievedProduct = inventoryManager.getProductById(productId);
		
		// Assert that the retrieved product is the same as the original product
		assertEquals(product, retrievedProduct);
	}

	@Test
	public void testGetInventory() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a list of SalableProduct objects (you can add your products here)
		List<SalableProduct> products = new ArrayList<>();
		// Add products to the list
		
		// Call the initializeInventory method to initialize the inventory
		inventoryManager.initializeInventory(products);
		
		// Call the getInventory method to get the inventory list
		List<SalableProduct> retrievedInventory = inventoryManager.getInventory();
		
		// Assert that the retrieved inventory is the same as the original inventory
		assertEquals(products, retrievedInventory);
	}

	@Test
	public void testSaveInventoryToJson() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a list of SalableProduct objects (you can add your products here)
		List<SalableProduct> products = new ArrayList<>();
		// Add products to the list
		
		// Call the initializeInventory method to initialize the inventory
		inventoryManager.initializeInventory(products);
		
		// Call the saveInventoryToJson method to save the inventory to a JSON file
		String filename = "inventory.json";
		inventoryManager.saveInventoryToJson(filename);
		
		// Add assertions here to check if the JSON file is created and contains the correct data
	}

	@Test
	public void testLoadInventoryFromJson() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Call the loadInventoryFromJson method to load the inventory from a JSON file
		String filename = "inventory.json";
		try {
			inventoryManager.loadInventoryFromJson(filename);
			
			// Add assertions here to check if the inventory is loaded correctly from the JSON file
		} catch (Exception e) {
			fail("Failed to load inventory from JSON file: " + e.getMessage());
		}
	}

	@Test
	public void testSortInventoryByName() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a list of SalableProduct objects (you can add your products here)
		List<SalableProduct> products = new ArrayList<>();
		// Add products to the list
		
		// Call the initializeInventory method to initialize the inventory
		inventoryManager.initializeInventory(products);
		
		// Call the sortInventoryByName method to sort the inventory by name (ascending)
		inventoryManager.sortInventoryByName(true);
		
		// Add assertions here to check if the inventory is sorted correctly by name
	}

	@Test
	public void testSortInventoryByPrice() {
		// Create a new instance of the InventoryManager
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		
		// Create a list of SalableProduct objects (you can add your products here)
		List<SalableProduct> products = new ArrayList<>();
		// Add products to the list
		
		// Call the initializeInventory method to initialize the inventory
		inventoryManager.initializeInventory(products);
		
		// Call the sortInventoryByPrice method to sort the inventory by price (ascending)
		inventoryManager.sortInventoryByPrice(true);
		
	}

}
