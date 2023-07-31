package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.InventoryManager;
import app.SalableProduct;
import app.StoreFront;

public class StoreFrontTest {

	 private StoreFront<SalableProduct> storeFront;
	    private InventoryManager<SalableProduct> inventoryManager;

	    @Before
	    public void setup() {
	        // Create a new instance of InventoryManager
	        inventoryManager = new InventoryManager<>();

	        // Create a new instance of StoreFront
	        storeFront = new StoreFront<>("Bizzy Gaming Shop!", "Welcome to the Bizzy Game Shop!");

	        // Set the InventoryManager for the StoreFront
	        storeFront.setInventoryManager(inventoryManager);

	        // Add some products to the inventory
	        SalableProduct product1 = new SalableProduct("Product 1", "Description 1", 10.0, 100, "001");
	        SalableProduct product2 = new SalableProduct("Product 2", "Description 2", 20.0, 50, "002");
	        inventoryManager.addProduct(product1);
	        inventoryManager.addProduct(product2);
	    }

	    @Test
	    public void testStoreFront() {
	        // Assert that the InventoryManager is initially set to the expected value
	        assertEquals(inventoryManager, storeFront.getInventoryManager());
	    }

	    @Test
	    public void testSetInventoryManager() {
	        // Create a new InventoryManager
	        InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();

	        // Set the InventoryManager in the StoreFront
	        storeFront.setInventoryManager(inventoryManager);

	        // Assert that the InventoryManager is set correctly
	        assertEquals(inventoryManager, storeFront.getInventoryManager());
	    }

	    @Test
	    public void testInitializeInventoryFromJSON() {
	        // Create a list of SalableProduct objects (you can add your products here)
	        List<SalableProduct> products = new ArrayList<>();
	        products.add(new SalableProduct("Product 1", "Description 1", 10.0, 100, "001"));
	        products.add(new SalableProduct("Product 2", "Description 2", 20.0, 50, "002"));

	        // Call the initializeInventoryFromJSON method to initialize the inventory from the JSON file
	        storeFront.initializeInventoryFromJSON("testProducts.json");

	        // Assert that the inventory in the InventoryManager is not empty and contains the expected number of products
	        List<SalableProduct> inventory = inventoryManager.getInventory();
	        assertNotNull(inventory);
	        assertEquals(2, inventory.size());

	        // Assert that the loaded products are present in the inventory
	        assertTrue(inventory.contains(products.get(0)));
	        assertTrue(inventory.contains(products.get(1)));
	    }

	    @Test
	    public void testExecuteActionPurchaseSuccess() {
	        // Test a successful purchase
	        // Add a product to the inventory with quantity > 0
	        SalableProduct product = new SalableProduct("Product 3", "Description 3", 15.0, 10, "003");
	        inventoryManager.addProduct(product);

	        // Simulate user input for purchasing the product
	        int quantityToBuy = 5;
	        storeFront.executeAction(2); // Choose action 2 to buy a product
	        storeFront.setInput("003\n" + quantityToBuy + "\nY"); // Enter product ID, quantity, and confirm purchase

	        // Execute the purchase action
	        storeFront.executeAction(2);

	        // Assert that the product quantity has been reduced after the purchase
	        assertEquals(5, product.getQuantity());
	    }

	    @Test
	    public void testExecuteActionPurchaseFailureInvalidQuantity() {
	        // Test a purchase with an invalid quantity
	        // Add a product to the inventory with quantity > 0
	        SalableProduct product = new SalableProduct("Product 4", "Description 4", 25.0, 20, "004");
	        inventoryManager.addProduct(product);

	        // Simulate user input for purchasing the product with an invalid quantity
	        int quantityToBuy = -5; // Invalid quantity (negative)
	        storeFront.executeAction(2); // Choose action 2 to buy a product
	        storeFront.setInput("004\n" + quantityToBuy + "\nN"); // Enter product ID and invalid quantity, then cancel purchase

	        // Execute the purchase action
	        storeFront.executeAction(2);

	        // Assert that the product quantity remains unchanged after the invalid purchase attempt
	        assertEquals(20, product.getQuantity());
	    }

	    @Test
	    public void testExecuteActionCancelPurchase() {
	        // Test canceling a purchase
	        // Add a product to the inventory with quantity > 0
	        SalableProduct product = new SalableProduct("Product 5", "Description 5", 30.0, 15, "005");
	        inventoryManager.addProduct(product);

	        // Simulate user input for attempting to purchase the product but canceling the purchase
	        int quantityToBuy = 8;
	        storeFront.executeAction(2); // Choose action 2 to buy a product
	        storeFront.setInput("005\n" + quantityToBuy + "\nN"); // Enter product ID and quantity, then cancel purchase

	        // Execute the purchase action
	        storeFront.executeAction(2);

	        // Assert that the product quantity remains unchanged after canceling the purchase
	        assertEquals(15, product.getQuantity());
	    }

	    @Test
	    public void testExecuteActionProductNotFound() {
	        // Test purchasing a product that is not found in the inventory
	        // Simulate user input for attempting to purchase a product that does not exist
	        int quantityToBuy = 3;
	        storeFront.executeAction(2); // Choose action 2 to buy a product
	        storeFront.setInput("999\n" + quantityToBuy + "\nN"); // Enter non-existent product ID, quantity, then cancel purchase

	        // Execute the purchase action
	        storeFront.executeAction(2);

	        // Assert that the non-existent product did not affect the inventory
	        assertNull(inventoryManager.getProductById("999"));
	    }

}
