package app;

import java.util.Random;

public class Health<T extends SalableProduct> extends SalableProduct implements Comparable<SalableProduct> {

	// New private attributes that only the health class will carry.
		private int minHealing;
		private int maxHealing;

		// Constructor with super that is in SalableProduct
		// Setting minHealing and maxHealing.
		public Health(String name, String description, double price, int quantity, String idNumber, int minHealing, int maxHealing) {
			super(name, description, price, quantity, idNumber);
			this.minHealing = minHealing;
			this.maxHealing = maxHealing;
		}

		// Getters and setters 
		public int getMinHealing() {
			return minHealing;
		}

		public void setMinHealing(int minHealing) {
			this.minHealing = minHealing;
		}

		public int getMaxHealing() {
			return maxHealing;
		}

		public void setMaxHealing(int maxHealing) {
			this.maxHealing = maxHealing;
		}

		// These potion's will generate healing, and this method
		// randomly generates how much between each. 
		public int generateHealing() {
			Random random = new Random();
			return random.nextInt(maxHealing - minHealing + 1) + minHealing;
		}

		@Override
		public int compareTo(SalableProduct o) {
			// TODO Auto-generated method stub
			return 0;
		}	
	
}
