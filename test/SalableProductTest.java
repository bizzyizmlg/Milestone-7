package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import app.SalableProduct;

public class SalableProductTest {

	@Test
	public void testSalableProductStringStringDoubleIntString() {
		// Create a SalableProduct object using the constructor
		SalableProduct product = new SalableProduct("Product1", "Description", 10.99, 5, "ID001");

		// Use assertions to check that the attributes are set correctly
		Assert.assertEquals("Product1", product.getName());
		Assert.assertEquals("Description", product.getDescription());
		// The last parameter is the delta for double comparison
		Assert.assertEquals(10.99, product.getPrice(), 0.001);
		Assert.assertEquals(5, product.getQuantity());
		Assert.assertEquals("ID001", product.getIdNumber());
	}

	@Test
	public void testGetName() {
		SalableProduct product = new SalableProduct();
		product.setName("Product2");
		assertEquals("Product2", product.getName());
	}

	@Test
	public void testSetName() {
		SalableProduct product = new SalableProduct();
		product.setName("Product3");
		assertEquals("Product3", product.getName());
	}

	@Test
	public void testGetDescription() {
		SalableProduct product = new SalableProduct();
		product.setDescription("New Description");
		assertEquals("New Description", product.getDescription());
	}

	@Test
	public void testSetDescription() {
		SalableProduct product = new SalableProduct();
		product.setDescription("Another Description");
		assertEquals("Another Description", product.getDescription());
	}

	@Test
	public void testGetPrice() {
		SalableProduct product = new SalableProduct();
		product.setPrice(19.99);
		assertEquals(19.99, product.getPrice(), 0.001);
	}

	@Test
	public void testSetPrice() {
		SalableProduct product = new SalableProduct();
		product.setPrice(29.99);
		assertEquals(29.99, product.getPrice(), 0.001);
	}

	@Test
	public void testGetQuantity() {
		SalableProduct product = new SalableProduct();
		product.setQuantity(3);
		assertEquals(3, product.getQuantity());
	}

	@Test
	public void testSetQuantity() {
		SalableProduct product = new SalableProduct();
		product.setQuantity(7);
		assertEquals(7, product.getQuantity());
	}

	@Test
	public void testGetIdNumber() {
		SalableProduct product = new SalableProduct();
		product.setIdNumber("ID003");
		assertEquals("ID003", product.getIdNumber());
	}

	@Test
	public void testSetIdNumber() {
		SalableProduct product = new SalableProduct();
		product.setIdNumber("ID004");
		assertEquals("ID004", product.getIdNumber());
	}

	@Test
	public void testToString() {
		SalableProduct product = new SalableProduct("Product4", "Description", 15.99, 2, "ID002");
		String expectedToString = "Name: Product4\nDescription: Description\nPrice: 15.99\nQuantity: 2\nID #: ID002\n==================";
		assertEquals(expectedToString, product.toString());
	}

}
