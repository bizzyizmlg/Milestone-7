package app;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CartItem implements Serializable {

	// Version control during the serialization and deserialization process
	private static final long serialVersionUID = 1L;
	// Each cart item will have a product and a quantity that will differ
	// from the SalableProduct quantity and product.
	@JsonProperty("product")
	private SalableProduct product;

	@JsonProperty("quantity")
	private int quantity;

	/* Constructor that initializes the product and quantity instance variables with
	 the values provided. */
	public CartItem(SalableProduct product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public CartItem() {

	}

	// Getting method that will return the product inside the CartItem
	public SalableProduct getProduct() {
		return product;
	}

	// Method that will return the quantity in CartItem
	public int getQuantity() {
		return quantity;
	}

	// Calculates the quantity and the prices together to give total.
	public double calculateSubtotal() {
		return product.getPrice() * quantity;
	}

}
