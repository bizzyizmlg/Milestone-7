package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Health;

public class HealthTest {

	public void testHealth() {

		// Create a Health object and test its constructor
		Health health = new Health("Health Potion", "Restores health", 9.99, 10, "1", 20, 150);

		// Test getters for the attributes
		assertEquals("Health Potion", health.getName());
		assertEquals(9.99, health.getPrice(), 0.01); // Use delta for double comparison
		assertEquals(10, health.getQuantity());
		assertEquals(20, health.getMinHealing());
		assertEquals(150, health.getMaxHealing());
	}

	@Test
	public void testGetMinHealing() {
		Health health = new Health("Health Potion", "Restores health", 9.99, 10, "1", 20, 150);
		assertEquals(20, health.getMinHealing());
	}

	@Test
	public void testSetMinHealing() {
		Health health = new Health("Health Potion", "Restores health", 9.99, 10, "1", 20, 150);
		health.setMinHealing(30);
		assertEquals(30, health.getMinHealing());
	}

	@Test
	public void testGetMaxHealing() {
		Health health = new Health("Health Potion", "Restores health", 9.99, 10, "1", 20, 150);
		assertEquals(150, health.getMaxHealing());
	}

	@Test
	public void testSetMaxHealing() {
		Health health = new Health("Health Potion", "Restores health", 9.99, 10, "1", 20, 150);
		health.setMaxHealing(200);
		assertEquals(200, health.getMaxHealing());
	}

	@Test
	public void testGenerateHealing() {
		Health health = new Health("Health Potion", "Restores health", 9.99, 10, "1", 20, 150);

		// Test that the generated healing value is within the expected range
		int generatedHealing = health.generateHealing();
		assertTrue(generatedHealing >= 20 && generatedHealing <= 150);
	}

	@Test
	public void testCompareTo() {
		Health health1 = new Health("Health Potion", "Restores health", 9.99, 10, "1", 20, 150);
		Health health2 = new Health("Strong Health Potion", "Restores health", 14.99, 5, "2", 50, 200);

		// Test compareTo method to ensure it compares healing values correctly
		assertTrue(health1.compareTo(health2) < 0); // Health Potion has lower max healing than Strong Health Potion
		assertTrue(health2.compareTo(health1) > 0); // Strong Health Potion has higher max healing than Health Potion
		assertEquals(0, health1.compareTo(health1)); // Same health potion has equal max healing
	}

}
