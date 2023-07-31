package app;

public class Armor<T extends SalableProduct> extends SalableProduct implements Comparable<SalableProduct>{
	/* Private attributes of this class. Each armor will have a defense 
	  and a health boost to a person's health bar.*/
	private int defense;
	private int healthBoost;
	
	// Constructor with super. We add in our attributes and value
	public Armor(String name, String description, double price, 
			int quantity, String idNumber, int defense, int healthBoost) {
		super(name, description, price, quantity, idNumber);
		this.defense = defense;
		this.healthBoost = healthBoost;
	}

	// Getter and setter for both attributes. We dont need a toString
	// method because our parent class SalableProduct has it.
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getHealthBoost() {
		return healthBoost;
	}

	public void setHealthBoost(int healthBoost) {
		this.healthBoost = healthBoost;
	}

	@Override
	public int compareTo(SalableProduct o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
