
// Shahd Dar Assi
// ID : 1230652
// section : 5L

public class Electronic extends StockItem implements Cloneable{

	// Protected attributes
	private String version;
	private String type;
	private double power;

	// default constructor
	public Electronic() {
		// call constructor StockItem
		super();
		this.version = "";
		this.type = "";
		this.power = 0.0;

	}
	
	// constructor that accepts all required arguments (6 parameters)
	public Electronic(String brand, double discount, double price, String version, String type, double power) {
		// call constructor StockItem
		super(brand, discount, price);
		this.version = version;
		this.type = type;
		this.power = power;
	}

	// Getter and Setter for version
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	// Getter and Setter for type
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// Getter and Setter for power
	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	// returns a double after tax is calculated with a constant of 16.5%
	@Override
	public double calcuateTax() {
		return ((PriceAfterDiscount() * 0.165) + PriceAfterDiscount());
	}

	// Method returns the price after the discount
	public double PriceAfterDiscount() {
		return (price - (price * (discount / 100)));
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// Perform a shallow clone using super.clone()
		return super.clone();
	}

	// toString method to return the desired string format
	@Override
	public String toString() {

		if (discount > 0) {
			return brand + "(" + price + ")  with a discount of (" + discount + "% ) (" + type + ") " + power
					+ " Watt. After discount price is " + (Math.ceil(PriceAfterDiscount() * 100.0) / 100.0)
					+ " after tax included ( " + (Math.ceil(calcuateTax() * 100) / 100.0) + " ).";
		}
		return brand + "(" + price + ") No discount (" + type + ") " + power + "Watt , after tax included ( "
				+ (Math.ceil(calcuateTax() * 100.0) / 100.0) + " ).";
	}

}
