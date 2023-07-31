package app;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

	// Private static instance of ObjectMapper/ will be used o deserialize and
	// serialize
	private static final ObjectMapper objectMapper = new ObjectMapper();

	// Static block that runs when the class is loaded. Enables the indentation of
	// JSON output
	static {
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	// Method to serialize a Java object to a JSON string
	public static <T> String serializeToJson(T object) throws JsonProcessingException {
		// Use the ObjectMapper to convert the Java object into a JSON string and return
		// it
		return objectMapper.writeValueAsString(object);
	}

	// Method to deserialize a JSON string to a Java object of the specified class
	// type
	public static <T> T deserializeJson(String json, Class<T> valueType) throws IOException {
		// Use the ObjectMapper to read the JSON string and convert it into a Java
		// object of the specified class type
		return objectMapper.readValue(json, valueType);
	}

	// Method to deserialize a JSON string to a Java object using a TypeReference
	public static <T> T deserializeJson(String json, TypeReference<T> typeReference) throws IOException {
		// Use the ObjectMapper to read the JSON string and convert it into a Java
		// object using the provided TypeReference
		return objectMapper.readValue(json, typeReference);
	}

	// Method to deserialize a JSON string to a List of Java objects using a
	// TypeReference
	public static <T> List<T> deserializeJsonList(String json, TypeReference<List<T>> valueType) throws IOException {
		// Use the ObjectMapper to read the JSON string and convert it into a List of
		// Java objects using the provided TypeReference
		return objectMapper.readValue(json, valueType);
	}

}
