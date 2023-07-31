package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Armor;

public class ArmorTest {

	@Test
	public void testArmor() {
		// Create an Armor object and test its constructor
		Armor armor = new Armor("Plate Armor", "Armor built from the Cyclops", 1200.00, 12, "6", 15, 100);

		// Test getters for the attributes
		assertEquals("Plate Armor", armor.getName());
		assertEquals(1200.00, armor.getPrice(), 0.01); // Use delta for double comparison
		assertEquals(12, armor.getQuantity());
		assertEquals(15, armor.getDefense());
		assertEquals(100, armor.getHealthBoost());
	}

	@Test
	public void testGetDefense() {
		Armor armor = new Armor("Plate Armor", "Armor built from the Cyclops", 1200.00, 12, "6", 15, 100);
		assertEquals(15, armor.getDefense());
	}

	@Test
	public void testSetDefense() {
		Armor armor = new Armor("Plate Armor", "Armor built from the Cyclops", 1200.00, 12, "6", 15, 100);
		armor.setDefense(20);
		assertEquals(20, armor.getDefense());
	}

	@Test
	public void testGetHealthBoost() {
		Armor armor = new Armor("Plate Armor", "Armor built from the Cyclops", 1200.00, 12, "6", 15, 100);
		assertEquals(100, armor.getHealthBoost());
	}

	@Test
	public void testSetHealthBoost() {
		Armor armor = new Armor("Plate Armor", "Armor built from the Cyclops", 1200.00, 12, "6", 15, 100);
		armor.setHealthBoost(150);
		assertEquals(150, armor.getHealthBoost());
	}

}
