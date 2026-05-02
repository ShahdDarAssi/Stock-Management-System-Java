
// Shahd Dar Ass
// ID : 1230652
// section : 5L

import java.util.ArrayList;

public class Stock implements Cloneable {

	// name of the stock
	private String name;
	// location of the stock
	private Location location;
	// list of stock items
	private ArrayList<StockItem> item;

	// default constructor
	public Stock() {
		this.name = "";
	}

	// Constructor to initialize the stock with a name and location, and creates an
	// ArrayList for stock items
	public Stock(String name, Location location) {
		this.name = name;
		this.location = location;
		item = new ArrayList<>();
	}

	// Getter and Setter for name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getter and Setter for Location
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		if (location != null)
			this.location = location;
	}

	// Getter and Setter for Location
	// returns an array of stock items
	public ArrayList<StockItem> getItems() {
		return item;
	}

	// Creates a new copy of the passed list 'item'
	// This ensures that this.item is independent of the original 'item' list
	public void setItem(ArrayList<StockItem> item) {
		// Check if the provided list is null
		if (item == null) {
			// Assign an empty list to ensure the internal list is never null
			this.item = new ArrayList<>();
		} else {
			this.item = new ArrayList<>(item);
		}
	}

	// to add an Electronic/Grocery item to the arrayList
	public void addStockItem(Object obj) {
		// Check if the provided object is not null
		if (obj != null && (obj instanceof Electronic || obj instanceof Grocery)) {
			// Cast the object to StockItem and add it to the list
			item.add((StockItem) obj);
		} else {
			System.out.println("Cannot add null item");
		}

	}

	// returns the number of electronic items
	public int countElectronic() {
		int count = 0;
		for (int i = 0; i < item.size(); i++) {
			// Check if the current item is an instance of Electronic
			if (item.get(i) instanceof Electronic) {
				// Increment count if the item is Electronic
				count++;
			}
		}
		// Return the total count of Electronic items
		return count;
	}

	// returns the number of Grocery items
	public int countGrocery() {
		int count = 0;
		for (int i = 0; i < item.size(); i++) {
			// Check if the current item is an instance of Grocery
			if (item.get(i) instanceof Grocery) {
				// Increment count if the item is Grocery
				count++;
			}
		}
		// Return the total count of Grocery items
		return count;
	}

	// Override the clone method to enable cloning of the Stock object
	@Override
	public Object clone() throws CloneNotSupportedException {
		// Perform a shallow clone of the Stock object using super.clone()
		Stock clonedStock = (Stock) super.clone();
		// Deep clone the ArrayList<StockItem> to ensure independence from the original
		ArrayList<StockItem> clonedItems = new ArrayList<>();
		for (StockItem item : this.item) {
			// Deep clone each StockItem
			clonedItems.add((StockItem) item.clone());
		}
		// Assign deep cloned items to clonedStock
		clonedStock.item = clonedItems;
		return clonedStock;
	}

	// toString method to return the desired string format
	@Override
	public String toString() {
		return "Stock name = " + name + " , location = " + location.toString() + " , item = " + item;
	}

}
