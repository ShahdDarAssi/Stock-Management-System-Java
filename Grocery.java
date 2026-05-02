
// Shahd Dar Assi
// ID : 1230652
// section : 5L

import java.util.Calendar;

public class Grocery extends StockItem implements Cloneable{

	// Protected attributes
	private String name;
	private Calendar expiryDate;
	private float weight;

	// default constructor
	public Grocery() {
		// call constructor StockItem
		super();
		// set a default value for the name
		this.name = "";
		// set default weight value
		this.weight = 0.0f;
	}

	// constructor that accepts all required arguments (6 parameters)
	public Grocery(String brand, double discount, double price, String name, Calendar expirydate, float weight) {
		// call constructor StockItem
		super(brand, discount, price);
		this.name = name;
		this.expiryDate = expirydate;
		this.weight = weight;
	}

	// Getter and Setter for name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getter and Setter for expirydate
	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpirydate(Calendar expirydate) {
		if (expirydate != null)
			this.expiryDate = expirydate;
	}

	// Getter and Setter for weight
	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	// returns a double after tax is calculated with a constant of 5.75%
	@Override
	public double calcuateTax() {
		return ((PriceAfterDiscount() * 0.0575) + PriceAfterDiscount());
	}

	// Method returns the price after the discount
	public double PriceAfterDiscount() {
		return (price - (price * (discount / 100)));
	}

	// Return the date as "day/month/year"
	public String getFormattedDate() {
		int day = expiryDate.get(expiryDate.DAY_OF_MONTH);
		int month = expiryDate.get(expiryDate.MONTH) + 1;
		int year = expiryDate.get(expiryDate.YEAR);
		return String.format("%02d/%02d/%d", day, month, year);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// Perform a shallow clone using super.clone()
		Grocery clonedGrocery = (Grocery) super.clone();
		// Manually clone Calendar
		clonedGrocery.expiryDate = (Calendar) this.expiryDate.clone();
		return clonedGrocery;
	}

	// toString method to return the desired string format
	@Override
	public String toString() {

		if (discount > 0) {
			return brand + " (" + price + ") has a discount of " + discount + "% , expiry date " + getFormattedDate()
					+ "(" + weight + "kg)," + " after discount price is "
					+ (Math.ceil(PriceAfterDiscount() * 100.0) / 100.0) + ", after tax included ( "
					+ (Math.ceil(calcuateTax() * 100.0) / 100.0) + " ).";
		} else {
			return brand + " (" + price + "), expiry date " + getFormattedDate() + " (" + weight
					+ "kg), after tax included (" + (Math.ceil(calcuateTax() * 100.0) / 100.0) + " ).";
		}

	}

}
