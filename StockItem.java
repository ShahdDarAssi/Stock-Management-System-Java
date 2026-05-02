
// Shahd Dar Assi
// ID : 1230652
// section : 5L

public abstract class StockItem implements Comparable<StockItem>, Cloneable {

	// Protected attributes
	// Brand Name
	protected String brand;
	// Discount rate
	protected double discount;
	// Product price
	protected double price;

	// default constructor
	public StockItem() {
		// set a default value for the brand
		this.brand = "";
		// set default discount value
		this.discount = 0.0;
		// set default price value
		this.price = 0.0;
	}

	// constructor that accepts all required arguments
	public StockItem(String brand, double discount, double price) {
		// Setting input values ​​for attributes
		this.brand = brand;
		this.discount = discount;
		this.price = price;
	}

	// Getter and Setter for brand
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	// Getter and Setter for discound
	public double getDiscound() {
		return discount;
	}

	public void setDiscound(double discound) {
		this.discount = discound;
	}

	// Getter and Setter for price
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Method for calculating tax on a product
	public abstract double calcuateTax();

	// this method returns true when two objects of stock items have exactly the
	// same content
	@Override
	public boolean equals(Object obj) {
		// Check if the current object (this) is the same as the obj using the same
		// memory reference
		if (this == obj) {
			// If both references are the same return true
			return true;
		}
		// Check if obj is null or not an instance of StockItem
		if (obj == null || !(obj instanceof StockItem)) {
			// If obj is null or not a StockItem return false
			return false;
		}
		//// Cast obj to a StockItem object
		StockItem stockItem = (StockItem) obj;
		// Compare individual properties(price, discount, and brand)
		// Use Double.compare for safe comparison of decimal values
		// and equals for string comparison
		return Double.compare(stockItem.price, price) == 0 && Double.compare(stockItem.discount, discount) == 0
				&& brand.equals(stockItem.brand);
	}

	@Override
	public int compareTo(StockItem o) {
		//// Compare the brand of the current object (this) with the brand of the other
		//// object (o) in descending order
		return o.brand.compareTo(this.brand);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// Shallow clone of the simple fields
		return super.clone();
	}

	// toString method to return the desired string format
	@Override
	public String toString() {
		if (discount > 0) {
			return brand + " ($" + price + ") has a discount of " + discount + "%.";
		} else {
			return brand + " ($" + price + ").";
		}
	}

}
