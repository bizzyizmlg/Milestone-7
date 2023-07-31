package app;

public class Weapons <T extends SalableProduct> extends SalableProduct implements Comparable<SalableProduct> {

	// Private attributes that the Weapons class would only have.
		private int damage;
		private int range;
		private int ammoCapacity;
		private int levelRequirement;

		// Constructor with our super.
		public Weapons(String name, String description, double price, int quantity, String idNumber) {
			super(name, description, price, quantity, idNumber);
		}

		// Getters an
		public int getDamage() {
			return damage;
		}

		public void setDamage(int damage) {
			this.damage = damage;
		}

		public int getRange() {
			return range;
		}

		public void setRange(int range) {
			this.range = range;
		}

		public int getAmmoCapacity() {
			return ammoCapacity;
		}

		public void setAmmoCapacity(int ammoCapacity) {
			this.ammoCapacity = ammoCapacity;
		}

		public int getLevelRequirement() {
			return levelRequirement;
		}

		public void setLevelRequirement(int levelRequirement) {
			this.levelRequirement = levelRequirement;
		}

		@Override
		public int compareTo(SalableProduct o) {
			// TODO Auto-generated method stub
			if (o instanceof Weapons) {
		        Weapons otherWeapon = (Weapons) o;
		        return Integer.compare(this.damage, otherWeapon.damage);
		    }
		    // If the object is not a Weapons instance, consider them equal (return 0)
		    return 0;
		}
}
