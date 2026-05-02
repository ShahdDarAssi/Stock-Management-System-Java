
// Shahd Dar Assi
// ID : 1230652
// section : 5L

public class Location {

	// Protected attributes
	private String street;
	private String district;
	private String city;

	// Default constructor
	public Location() {
		this.street = "";
		this.district = "";
		this.city = "";
	}

	// constructor that accepts all required arguments
	public Location(String street, String district, String city) {
		this.street = street;
		this.district = district;
		this.city = city;
	}

	// Getter and Setter for street
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	// Getter and Setter for district
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	// Getter and Setter for city
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// toString method to return the desired string format
	@Override
	public String toString() {
		return "Location street = " + street + " , district = " + district + " , city = " + city;
	}

}
