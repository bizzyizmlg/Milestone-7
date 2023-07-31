package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class StoreFront<T extends SalableProduct> {
	// Creating instance variables
	private String storeName;
	private String welcomeMessage;
	private InventoryManager<T> inventoryManager;
	private Scanner scanner;
	private Cart cart;
	private AdministrationService administrationService;

	// Constructor with arguments in parameters where we will need
	// a string for storeName and welcomeMessage
	public StoreFront(String storeName, String welcomeMessage) {
		// setting values
		this.storeName = storeName;
		this.welcomeMessage = welcomeMessage;
		this.inventoryManager = new InventoryManager<>();
		this.scanner = new Scanner(System.in);
		this.cart = new Cart();
//			this.weaponsList = new ArrayList<>();
	}

	// Method that will print the welcome message, but first the store name.
	public void displayWelcomeMessage() {
		System.out.println(storeName);
		System.out.println("===========================");
		System.out.println(welcomeMessage);
		System.out.println("===========================");
	}

	// Showing the possible numbers for user to choose from.
	public void displayActions() {
		System.out.println("Choose a number to pick an option.");
		System.out.println("1. View available items.");
		System.out.println("2. Buy an item.");
		System.out.println("3. Cancal purchase.");
		System.out.println("4. View Cart");
		System.out.println("5. Add New Products to Inventory");
		System.out.println("6. View All Products in Inventory");
		System.out.println("0. Exit.");
	}

	// Method that uses case when user enters in numbers and uses the methods.
	public void executeAction(int action) {
		switch (action) {

		case 1:
			displayProducts();
			break;
		case 2:
			purchaseProducts();
			break;
		case 3:
			cancelPurchase();
			break;
		case 4:
			cart.displayCart();
			break;
		// Commander for Admin User for letter U to update
		case 5:
			System.out.println("Enter the JSON data for new Salable Products: ");
			scanner.nextLine();
			String jsonData = scanner.nextLine();
			TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
			};
			List<T> newProducts;
			try {
				newProducts = JsonUtils.deserializeJsonList(jsonData, typeReference);
				inventoryManager.initializeInventory(newProducts);
				System.out.println("Inventory updated.");
			} catch (IOException e) {
				System.out.println("Error occurred while updating inventory: " + e.getMessage());
			}
			break;
		// Commander for Admin User for letter R to return all products
		case 6:
			List<T> allProducts = inventoryManager.getInventory();
			try {
				String jsonProducts = JsonUtils.serializeToJson(allProducts);
				System.out.println("All Salable Products:\n" + jsonProducts);
			} catch (JsonProcessingException e) {
				System.out.println("Error occured while serializing inventory to JSON, try again");
			}
			break;
		case 0:
			exitStore();
			break;
		default:
			// If user enters in incorrect number, show this.
			System.out.println("Incorrect selection. Please choose the correct option.");
			break;
		}
	}

	// Show all products in the inventory.
	private void displayProducts() {
		System.out.println("All available Products in the Store");
		System.out.println("===========================");

		// Get the list of products from the inventory manager.
		List<T> inventory = inventoryManager.getInventory();
		// Create a list to store JSON representations of products
		List<String> jsonItems = new ArrayList<>();
		// Looping through each product in inventory
		for (T product : inventory) {
			// Print details of the current product
			System.out.println(product);
			// Convert the product to its JSON and add it to the list
			jsonItems.add(FileService.convertToJson(product));
		}
		// Joining JSON and the products
		String jsonContent = "[" + String.join(",", jsonItems) + "]";
		// Writing JSON content to the inventory.json file
		FileService.writeJsonToFile("inventory.json", jsonContent);

		System.out.println("===========================");
	}

	// Method that allows user to purchase products.
	private void purchaseProducts() {
		// Ask the user to enter the product ID they want to buy.
		System.out.println("Enter the product ID # you want to buy: ");
		String productId = scanner.next();

		// Get the product from the inventory manager based on the entered ID.
		SalableProduct product = inventoryManager.getProductById(productId);

		// Check if the product exists in the inventory.
		if (product == null) {
			// If the product is not found, print a message.
			System.out.println("Product not found.");
		} else {
			// If the product is found, rest of the code for purchasing the product...

			// Check if the product is available (quantity greater than 0).
			if (product.getQuantity() > 0) {
				// Ask the user to enter the quantity they want to buy.
				System.out.println("Enter the quantity you want to buy: ");
				int quantityToBuy = scanner.nextInt();

				// Check if the quantity entered by the user is valid (greater than 0 and not
				// exceeding available quantity).
				if (quantityToBuy > 0 && quantityToBuy <= product.getQuantity()) {
					// Calculate the total cost of the purchase.
					double totalCost = product.getPrice() * quantityToBuy;

					// Ask the user to confirm the purchase.
					System.out.println("Are you sure you want to buy " + quantityToBuy + " units of "
							+ product.getName() + " for $" + totalCost + "? (Y/N)");
					String confirm = scanner.next();

					if (confirm.equalsIgnoreCase("Y")) {
						// Reduce the quantity of the product in the inventory.
						product.setQuantity(quantityToBuy);

						// Add the purchased item to the cart.
						CartItem cartItem = new CartItem(product, quantityToBuy);
						cart.addItem(cartItem);

						// Print a confirmation message.
						System.out.println("Purchase successful. " + quantityToBuy + " units of " + product.getName()
								+ " added to the cart.");
					} else {
						// If the user does not confirm the purchase, print a message.
						System.out.println("Purchase canceled.");
					}
				} else {
					// If the quantity entered by the user is invalid, print an error message.
					System.out.println("Invalid quantity. Please enter a valid quantity within the available range.");
				}
			} else {
				// If the product is not available (quantity is 0), print a message.
				System.out.println("Sorry, this product is currently out of stock.");
			}
		}
	}

	private void cancelPurchase() {
		System.out.println("Enter the product ID # you want to cancel: ");
		String productId = scanner.next();

		/*
		 * Renaming CartItem a new instance of itemToRemove use the get item by id
		 * method in cart class from the cart instance and it must match what the user
		 * entered
		 */
		CartItem itemToRemove = cart.getItemById(productId);

		// Check if item is found based on what user entered.
		if (itemToRemove != null) {
			// Remove the item from the cart
			cart.removeItem(itemToRemove);
			System.out.println("Product removed from the cart.");
		} else {
			// If no items match entered by user, print this.
			System.out.println("Product not found in the cart.");
		}
	}

	private void exitStore() {
		// Print this along with the storeName I give along with an exiting message
		System.out.println("...exiting store." + storeName + "! Hope to see you again");
		// Close scanner so user cannot enter no longer.
		scanner.close();
		// Close the program.
		System.exit(0);
	}

	// Setting the inventory manager class and giving the parameters an argument
	// so the it must have a value.
	public void setInventoryManager(InventoryManager<T> inventoryManager) {
		// Assigning instance to StoreFront class for use.
		this.inventoryManager = inventoryManager;
	}

	// Method to initialize the inventory from a JSON file
	public void initializeInventoryFromJSON(String jsonFilePath) {
		// Read the list of products from the JSON file using the FileService
		List<T> products = FileService.readFromFile(jsonFilePath, new TypeReference<List<T>>() {
		});
		// Initialize the inventory manager with the list of products read from the file
		inventoryManager.initializeInventory(products);
		System.out.println("Inventory initialized from JSON file.");
	}

	public void startStore(String jsonFilePath) {
		// Prompt user to choose sorting order
		System.out.println("Choose sorting order:");
		System.out.println("1. Ascending");
		System.out.println("2. Descending");
		int sortOrderOption = scanner.nextInt();

		// Sort products based on the chosen sorting order
		switch (sortOrderOption) {
		// Case 1: Sort products in ascending order based on their names
		case 1:
			Collections.sort(inventoryManager.getInventory(), new Comparator<SalableProduct>() {
				@Override
				public int compare(SalableProduct product1, SalableProduct product2) {
					// Compare products' names in ascending order
					return product1.getName().compareTo(product2.getName());
				}
			});
			break;
		case 2:
			// Sort products in descending order based on their names
			Collections.sort(inventoryManager.getInventory(), new Comparator<SalableProduct>() {
				@Override
				public int compare(SalableProduct product1, SalableProduct product2) {
					// Compare products' names in descending order
					return product2.getName().compareTo(product1.getName());
				}
			});
			break;
		// Default case: Invalid option, sort in ascending order by default
		default:
			System.out.println("Invalid option. Sorting in ascending order by default.");
			// Sort products in ascending order based on their names
			Collections.sort(inventoryManager.getInventory(), new Comparator<SalableProduct>() {
				@Override
				public int compare(SalableProduct product1, SalableProduct product2) {
					// Compare products' names in ascending order
					return product1.getName().compareTo(product2.getName());
				}
			});
			break;
		}

		// Load inventory from JSON file at the beginning
		initializeInventoryFromJSON(jsonFilePath);

		// Show the welcome message we created in this method.
		displayWelcomeMessage();

		populateInventoryJsonFile(jsonFilePath);

		// Initialize and start the Administation Service
		// Choosing available port number
		int adminPort = 5576;
		administrationService = new AdministrationService(inventoryManager);
		try {
			administrationService.startService(adminPort);
		} catch (IOException e) {
			System.out.println("Failed to sart the Administratoin Service: " + e.getMessage());
			return;
		}

		// Infinite loop to allow the user to interact with the store until conditions
		// are met.
		while (true) {
			// Call to this method.
			displayActions();

			// Asking the user to enter a number and giving the answers an instance call.
			System.out.println("Enter a number to choose which option: ");
			int action = scanner.nextInt();

			// Calling the executeAction method, passing the action argument and interacting
			// with what the user has entered.
			executeAction(action);

			// After each action, update the inventory JSON file with the latest changes

			inventoryManager.saveInventoryToJson(jsonFilePath);
		}
	}

	/*
	 * private void createInventoryJsonFile(String jsonFilePath) throws IOException
	 * { File inventoryFile = new File(jsonFilePath); inventoryFile.createNewFile();
	 * System.out.println("Inventory JSON file created: " + jsonFilePath); }
	 */
	private void populateInventoryJsonFile(String jsonFilePath) {
		List<T> inventory = inventoryManager.getInventory();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		// Converting the inventory list to JSON string using File Service class
		try {
			String jsonContent = objectMapper.writeValueAsString(inventory);
			// Write the JSON content to the inventory JSON file using the FileService

			FileService.writeJsonToFile(jsonFilePath, jsonContent);
			// Let me know if it's populated
			System.out.println("Inventory JSON file populated.");
		} catch (IOException e) {
			System.out.println("Failed to populate inventory JSON file: " + e.getMessage());
		}
	}

	public static void main(String[] args) {

		// Creating new instance of this class.
		InventoryManager<SalableProduct> inventoryManager = new InventoryManager<>();
		List<Weapons<SalableProduct>> weaponsList = new ArrayList<>();

		// Creating 2 different weapons and changing some values using new setters.
		Weapons<SalableProduct> fireSword = new Weapons<>("Fire Sword", "A sword blazign with fire", 5000.00, 10, "1");
		fireSword.setDamage(1);
		fireSword.setRange(200);
		fireSword.setLevelRequirement(20);

		Weapons<SalableProduct> astralWand = new Weapons<>("Astral Wand", "A wizard owned this wand", 1000.00, 15, "7");

		Weapons<SalableProduct> greatAxe = new Weapons<>("Great Axe", "Legendary Great Axe", 100000.00, 1, "2");
		greatAxe.setDamage(2);
		greatAxe.setRange(300);
		greatAxe.setLevelRequirement(50);

//			System.out.println(fireSword);

		// Creating 2 different health potions.
		Health<SalableProduct> healthPotion = new Health<>("regular health potion", "Restores a small amount of health",
				9.99, Integer.MAX_VALUE, "3", 20, 150);
		Health<SalableProduct> strongPotion = new Health<>("strong health potion", "Restores a large amount of health",
				24.99, Integer.MAX_VALUE, "4", 100, 550);
//			System.out.println(healthPotion);
//			System.out.println(strongPotion);

		// Creating 2 differnt Armors using the Armor class constructors
		Armor<SalableProduct> leafArmor = new Armor<>("Leaf Armor", "Armor built from leaves", 500.00, 12, "5", 5, 50);
		Armor<SalableProduct> plateArmor = new Armor<>("Plate Armor", "Armor built from the Cyclops", 1200.00, 12, "6",
				15, 100);
//			System.out.println(leafArmor);
//			System.out.println(plateArmor);

		// Adding these new items created to the inventory list.
		inventoryManager.addProduct(fireSword);
		inventoryManager.addProduct(greatAxe);
		inventoryManager.addProduct(healthPotion);
		inventoryManager.addProduct(strongPotion);
		inventoryManager.addProduct(leafArmor);
		inventoryManager.addProduct(plateArmor);
		inventoryManager.addProduct(astralWand);

		// Adding weapons to the weaponsList
		weaponsList.add(fireSword);
		weaponsList.add(astralWand);
		weaponsList.add(greatAxe);

		// Adding the sorted weapons to the inventory manager
		for (Weapons<SalableProduct> weapon : weaponsList) {
			inventoryManager.addProduct(weapon);
		}

		/*
		 * Creating new instance of this class as storeFront and entering in the
		 * argument from String storeName along with welcomeMessage String.
		 */
		StoreFront<SalableProduct> storeFront = new StoreFront<>("Bizzy Gaming Shop!",
				"Welcome to the Bizzy Game Shop!");
		/*
		 * Calling this method and setting argument in parameters to set it for
		 * storeFront.
		 */
		storeFront.setInventoryManager(inventoryManager);

		/*
		 * Calling this method to initiate the store functionalities and all that come
		 * with it.
		 */
		storeFront.startStore("inventory.json");
	}

	public Object getInventoryManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setInput(String string) {
		// TODO Auto-generated method stub
		
	}

}
