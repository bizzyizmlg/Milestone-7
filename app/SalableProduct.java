package app;

public class SalableProduct {
	
	// Private attributes this class will have.
		private String name;
		private String description;
		private double price;
		private int quantity;
		private String idNumber;

		// Constructor with which will be the super.
		public SalableProduct(String name, String description, double price, int quantity, String idNumber) {
			this.name = name;
			this.description = description;
			this.price = price;
			this.quantity = quantity;
			this.idNumber = idNumber;
		}
		
		// Default constructor
		public SalableProduct() {
			
		}

		// Getters and setters for each attributes.
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getIdNumber() {
			return idNumber;
		}

		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		// Auto generated toString method so we are able to call and get/set
		// these values from other classes such as StoreFront
		@Override
		public String toString() {
			return "Name: " + name + "\n" + "Description: " + description + "\n" + "Price: " + price + "\n" + "Quantity: "
					+ quantity + "\n" + "ID #: " + idNumber + "\n" + "==================";
		}

}
