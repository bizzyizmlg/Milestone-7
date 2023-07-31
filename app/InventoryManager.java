package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class InventoryManager<T extends SalableProduct> {

	// Private instance variable for storing the inventory
	private List<T> inventory;

	public InventoryManager() {
		// Initialize the inventory as an Array
		inventory = new ArrayList<>();
	}

	public void initializeInventory(List<T> products) {
		// Add all products to the inventory
		inventory.addAll(products);
	}

	public void addProduct(T product) {
		// Check if a product alaedy exist with same name in inventory
		for (T duplicateProduct : inventory) {
			if (duplicateProduct.getName().equalsIgnoreCase(product.getName())) {
				// Product with the same name found, just update quantity
				duplicateProduct.setQuantity(duplicateProduct.getQuantity() + product.getQuantity());
				System.out.println("Product quantity updated in inventory: " + product.getName());
			}
		}
		// Add a product to the inventory if no product was already found
		inventory.add(product);
		System.out.println("Porduct added to inventory: " + product.getName());
	}

	public void removeProduct(T product) {
		// Remove a product from the inventory
		inventory.remove(product);
	}

	public void updateQuantity(T product, int newQuantity) {
		// Update the quantity of the product
		product.setQuantity(newQuantity);
	}

	public T getProductById(String id) {
		for (T product : inventory) {
			if (product.getIdNumber().equals(id)) {
				return product;
			}
		}
		return null;
	}

	public List<T> getInventory() {
		// Get the inventory list
		return inventory;
	}

	// Method to save the inventory to a JSON file
	public void saveInventoryToJson(String filename) {
		// Get current inventory list
		List<T> inventoryList = getInventory();
		// Using HasSet to remove duplicate products based on their name
		HashSet<T> uniqueProducts = new HashSet<>(inventoryList);
		try {
			// Creating instance of Object Mapper to convert the inventory list
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			// Converting inventory list to JSON string
			List<T> uniqueInventoryList = new ArrayList<>(uniqueProducts);
			// Convert inventory list to JOSN string
			String jsonContent = objectMapper.writeValueAsString(uniqueInventoryList);
			// Writing JSON content to inventory JSON file using the FileService write etc
			// method
			FileService.writeJsonToFile(filename, jsonContent);
			System.out.println("Inventory saved to JSON file: " + filename);
		} catch (IOException e) {
			System.out.println("Failed to save inventory to JSON file: " + e.getMessage());
		}
	}

	// Method to load the inventory from a JSON file
	public void loadInventoryFromJson(String filename) throws IOException {
		// Create a TypeReference for the generic type T
		TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
		};
		// Read the inventory data from the JSON file using FileService
		inventory = FileService.readFromFile(filename, typeReference);
		System.out.println("Inventory loaded from JSON file: " + filename);
	}

	public void sortInventoryByName(boolean ascending) {
		if (ascending) {
			// Sort inventory by name in ascending order
			Collections.sort(inventory, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
		} else {
			Collections.sort(inventory, (p1, p2) -> p2.getName().compareToIgnoreCase(p1.getName())); // Sort inventory
																										// // descending
																										// // order
		}
	}

	public void sortInventoryByPrice(boolean ascending) {
		if (ascending) {
			// Sort inventory by price in ascending order
			Collections.sort(inventory, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
		} else {
			// Sort inventory by price in descending order
			Collections.sort(inventory, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
		}
	}
}