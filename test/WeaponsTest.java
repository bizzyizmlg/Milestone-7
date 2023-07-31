package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Weapons;

public class WeaponsTest {

	@Test
	public void testWeapons() {
		// Create a Weapons object and test its constructor
		Weapons weapon = new Weapons("Wand of Desctruction", "Legendary Wizard wand", 50.99, 10, "40");
		assertEquals("Wand of Desctruction", weapon.getName());
		assertEquals(50.99, weapon.getDamage(), 0.01); // Use delta for double comparison
		assertEquals(10, weapon.getLevelRequirement());
	}

	@Test
	public void testGetDamage() {
		Weapons weapon = new Weapons("Axe of Destruction", "Knights legendary axe", 75.55, 20, "41");
		assertEquals(75.55, weapon.getDamage(), 0.01); // Use delta for double comparison
	}

	@Test
	public void testSetDamage() {
		Weapons weapon = new Weapons("Arcane Bow", "Lord of the Ring bow", 39.99, 5, "42");
		weapon.setDamage(60);
		assertEquals(60, weapon.getDamage(), 0.01); // Use delta for double comparison
	}

	@Test
	public void testGetLevelRequirement() {
		Weapons weapon = new Weapons("Spirit Dagger", "Dagger of the spirits", 29.99, 8, "43");
		assertEquals(8, weapon.getLevelRequirement());
	}

	@Test
	public void testSetLevelRequirement() {
		Weapons weapon = new Weapons("Skull Staff", "Staff for mages", 24.99, 15, "44");
		weapon.setLevelRequirement(8);
		assertEquals(8, weapon.getLevelRequirement());
	}

	@Test
	public void testCompareTo() {
		Weapons weapon1 = new Weapons("Sword", "basic", 49.99, 10, "45");
		Weapons weapon2 = new Weapons("Axe", "basic", 74.99, 20, "46");
		Weapons weapon3 = new Weapons("Bow", "basic", 39.99, 5, "47");

		// Test compareTo method to ensure it compares damage correctly
		assertTrue(weapon1.compareTo(weapon2) < 0); // Sword has lower damage than Axe
		assertTrue(weapon2.compareTo(weapon3) > 0); // Axe has higher damage than Bow
		assertEquals(0, weapon1.compareTo(weapon1)); // Same weapon has equal damage
	}

}
