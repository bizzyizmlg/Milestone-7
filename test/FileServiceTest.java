package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import app.FileService;
import app.SalableProduct;

public class FileServiceTest {

	@Test
	public void testWriteJsonToFile() {
		// Create a list of SalableProduct objects (you can add your products here)
		List<SalableProduct> products = new ArrayList<>();
		products.add(new SalableProduct("Product 1", "Description 1", 10.0, 100, "001"));
		products.add(new SalableProduct("Product 2", "Description 2", 20.0, 50, "002"));

		// Convert the list of products to JSON string
		String jsonContent = FileService.convertToJson(products);

		// Specify the file name for testing
		String filename = "testProducts.json";

		// Call the writeJsonToFile method to write JSON content to the file
		FileService.writeJsonToFile(filename, jsonContent);

		// Check if the file was created successfully
		File file = new File(filename);
		assertTrue(file.exists());
	}

	@Test
	public void testReadFromFile() {
		// Specify the file name from which to read
		String filename = "testProducts.json";

		// Call the readFromFile method to read JSON content from the file and deserialize it into a list of SalableProduct objects
		List<SalableProduct> products = FileService.readFromFile(filename, new TypeReference<List<SalableProduct>>() {});

		// Assert that the list of products is not null and contains the expected number of products
		assertNotNull(products);
		assertEquals(2, products.size());

		// You can add additional assertions to check the contents of the list, for example:
		assertEquals("Product 1", products.get(0).getName());
		assertEquals(10.0, products.get(0).getPrice(), 0.01);
		assertEquals("Description 2", products.get(1).getDescription());
	}

	@Test
	public void testConvertToJson() {
		// Create a new SalableProduct object (you can add your product details here)
		SalableProduct product = new SalableProduct("Product Name", "Product Description", 10.0, 100, "001");

		// Call the convertToJson method to convert the product to JSON string
		String jsonContent = FileService.convertToJson(product);

		// Assert that the JSON content is not empty
		assertNotNull(jsonContent);

		// You can also add assertions to check the specific structure of the JSON content if needed
		// For example, you can check if the JSON contains certain fields and values.
		assertTrue(jsonContent.contains("\"name\":\"Product Name\""));
		assertTrue(jsonContent.contains("\"price\":10.0"));
	}
}
