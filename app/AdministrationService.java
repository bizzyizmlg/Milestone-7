package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;


// Private instance variables for managing the server socket, running status, and inventory manager
public class AdministrationService<T extends SalableProduct> extends Thread {

	private ServerSocket serverSocket;
	private boolean running;
	private InventoryManager<T> inventoryManager;

	// Constructor to initialize the administration service with an inventory
	// manager
	public AdministrationService(InventoryManager<T> inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	public void startService(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		running = true;
		start();
		System.out.println("Administartation Service started on port " + port);
	}

	// Method to stop the admin service on the specified port
	public void stopService() throws IOException {
		running = false;
		serverSocket.close();
	}

	// Overridden run method to handle client requests in a separate thread
	@Override
	public void run() {
		while (running) {
			try {
				// Accepting incoming client connections and handle their request
				Socket clientSocket = serverSocket.accept();
				handleClientRequest(clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleClientRequest(Socket clientSocket) throws IOException {
		// Input and output streams to read and write data to the client
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

		String command = in.readLine();
		String response = "";

		// Read the command set by the client
		switch (command) {
		case "U":
			// Receive JSON data entry and update new inventory
			StringBuilder jsonData = new StringBuilder();
			String line;
			// Read the JSON data from the client line by line until an empty line is
			// encountered
			while ((line = in.readLine()) != null && !line.isEmpty()) {
				jsonData.append(line);
			}

			// Deserialize the JSON data to a list of new products using type reference
			TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
			};
			List<T> newProducts = JsonUtils.deserializeJson(jsonData.toString(), typeReference);
			// Initialize inventory with the new products received from the client
			inventoryManager.initializeInventory(newProducts);
			response = "Inventory updated successfully!";
			break;
		case "R":
			// Return all salable products in JSON format
			List<T> allProducts = inventoryManager.getInventory(); // Update this line
			response = JsonUtils.serializeToJson(allProducts);
			break;
		default:
			response = "Invalid response.";
		}

		// Send the respon back to the client
		out.println(response);

		// Close the input and output streams and the client socket
		in.close();
		out.close();
		clientSocket.close();
	}

}
