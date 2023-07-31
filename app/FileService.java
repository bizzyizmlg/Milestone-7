package app;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class FileService {

	// Instance of ObjectMapper to handle JSON serialization and deserialization
	private static ObjectMapper objectMapper = new ObjectMapper();

	// This method will write JSON content to our file
	public static void writeJsonToFile(String filename, String jsonContent) {
		try (FileWriter fileWriter = new FileWriter(filename)) {
			fileWriter.write(jsonContent);
		} catch (IOException e) {
			System.out.println("Failed to write JSON to file: " + e.getMessage());
		}
	}

	// Method to read JSON content from our file and deserialize it into our list of
	// objects
	public static <T> List<T> readFromFile(String filename, TypeReference<List<T>> typeReference) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			File file = new File(filename);
			if (!file.exists()) {
				return new ArrayList<>();
			}
			// Reading the JSON file and converting to List of objects
			return objectMapper.readValue(file, typeReference);
		} catch (IOException e) {
			System.out.println("Failed to read JSON from file: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	// Our method to convert our List of objects to JSON format
		public static <T> String convertToJson(T product) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
				return objectMapper.writeValueAsString(product);
			} catch (JsonProcessingException e) {
				System.out.println("Failed to convert product to JSON: " + e.getMessage());
				return "";
			}
		} 
}