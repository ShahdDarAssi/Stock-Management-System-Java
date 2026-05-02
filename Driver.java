
// Shahd Dar Assi
// ID : 1230652
// section : 5L

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// Create a stock object named “Al-Mashhadawi” with the address (Lacasa Mall,
		// Arehan, Ramallah)
		Location location = new Location("Lacasa Mall", "Arehan", "Ramallah");
		Stock stock = new Stock("Al-Mashhadawi", location);

		int option = 0;
		do {

			try {
				menu();

				option = scanner.nextInt();

				switch (option) {

				case 1:

					// Call the method that fills the inventory with groceries and electronics read
					// from the data.txt file
					readFromFile(stock, "data.txt");
					break;

				case 2:

					// Allow the user to enter Grocery or Electronics items to the ArrayList and
					// save the new stock items to the data file
					int choice = 0;

					while (choice != -1) {
						try {
							// Prompt the user to enter a choice to add either a Grocery item an
							// Electronics item or exit (-1)
							System.out.println(
									"Enter 1 to add a Grocery item or 2 to add an Electronics item or ( -1 )to finish : ");
							// Read user input for the choice
							choice = scanner.nextInt();

							switch (choice) {

							case 1:

								System.out.print("enter the brand : ");
								String brand = scanner.next();
								System.out.print("enter the discount : ");
								double discount = scanner.nextDouble();
								// Skip to the next iteration if the discount is invalid
								if (!validDiscount(discount)) {
									System.out.println("Try again the discount is invalid ");
									continue;
								}
								System.out.print("enter the price : ");
								double price = scanner.nextDouble();
								// Skip to the next iteration if the price is invalid
								if (!validPrice(price)) {
									System.out.println("Try again the price is invalid ");
									continue;
								}
								System.out.print("enter the name of grocery : ");
								String name = scanner.next();
								System.out.print("enter the day of expirydate : ");
								int day = scanner.nextInt();
								System.out.print("enter the month of expirydate : ");
								int month = scanner.nextInt();
								System.out.print("enter the year of expirydate : ");
								int year = scanner.nextInt();
								// Skip to the next iteration if the expiry date is invalid
								if (!isValidDate(year, month, day)) {
									System.out.println("Try again the expiry date you entered is invalid");
									continue;
								}
								System.out.print("enter the weight : ");
								float weight = scanner.nextFloat();
								Calendar calendar = new GregorianCalendar(year, (month - 1), day);

								// Add the new Grocery item to the stock
								stock.addStockItem(new Grocery(brand, discount, price, name, calendar, weight));
								System.out.println("The Grocery item has been successfully added to the stock");
								break;

							case 2:

								System.out.print("enter the version of electronic : ");
								String version = scanner.next();
								System.out.print("enter the brand : ");
								String brand1 = scanner.next();
								System.out.print("enter the discount : ");
								double discountForElectronic = scanner.nextDouble();
								// Skip to the next iteration if the discount is invalid
								if (!validDiscount(discountForElectronic)) {
									System.out.println("Try again the discount is invalid ");
									continue;
								}
								System.out.print("enter the price : ");
								double priceForElectronic = scanner.nextDouble();
								// Skip to the next iteration if the price is invalid
								if (!validPrice(priceForElectronic)) {
									System.out.println("Try again the price is invalid ");
									continue;
								}
								System.out.print("enter the type of electronic : ");
								String typee = scanner.next();
								System.out.print("enter the power of electronic : ");
								double power = scanner.nextDouble();

								// Add the new Electronics item to the stock
								stock.addStockItem(new Electronic(brand1, discountForElectronic, priceForElectronic,
										version, typee, power));
								System.out.println("The Electronic item has been successfully added to the stock");
								break;

							// finish
							case -1:
								break;

							// If an incorrect option is entered
							default:
								System.out.println("Please enter the correct choice : ");
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid input please enter a valid value, please try again");
							// To ensure that the next entry will be read correctly
							scanner.nextLine();
						}
					}
					// Call the SaveItemToFile method to save the item to the file.
					SaveItemToFile(stock, "data.txt");

					break;

				case 3:
					// Check if the stock is empty and print a message if no items are available for
					// checking expired groceries
					if (stock.getItems().isEmpty()) {
						System.out.println("The stock is empty, no expired grocery items found");
					} else {
						// Call the method that prints all expired food items (those with an expiration
						// date less than the current date)
						allExpiredGrocery(stock);
					}
					break;

				case 4:
					// Check if the stock is empty and print a message
					if (stock.getItems().isEmpty()) {
						System.out.println("The stock is empty ");
					} else {
						// Call the method that gets the brands, type and price after discount for
						// electronic items that have a discount.
						getElectronicsItems(stock);
					}
					break;

				case 5:

					// Check if the stock list is empty before proceeding with sorting
					if (stock.getItems().isEmpty()) {
						System.out.println("The stock is empty, no items to display");
					} else {
						// Print all stock items sorted by brand in descending order print the result
						// Sort the list of in-stock items by brand in descending order using a custom
						// Comparable
						Collections.sort(stock.getItems());
						// Loop through inventory items and print toString for each item
						for (int i = 0; i < stock.getItems().size(); i++) {
							System.out.println(stock.getItems().get(i).toString());
						}
					}
					break;

				case 6:
					// Check if the stock list is empty before proceeding with sorting
					if (stock.getItems().isEmpty()) {
						System.out.println("The stock is empty, no items to display");
					} else {
						// Sort the list of items in the stock based on price in descending order using
						// a custom comparator
						Collections.sort(stock.getItems(), new SortByPrice());
						// Loop through inventory items and print toString for each item
						for (int i = 0; i < stock.getItems().size(); i++) {
							System.out.println(stock.getItems().get(i).toString());
						}
					}
					break;

				case 7:
					// Check if the stock list is empty
					if (stock.getItems().isEmpty()) {
						System.out.println("The stock is empty, no items to display");
					} else {
						System.out.println("Please enter the required discount value : ");
						// Read the discount value entered by the user from the console
						double discount = scanner.nextDouble();
						// Validate the discount value
						if (!validDiscount(discount)) {
							// If the discount is invalid, display an error message
							System.out.println("Try again the discount is invalid ");
							break;
						}
						// A way to display all stock items that have the same or lesser discount value
						// as the user specified
						displayDiscountedtoSckItems(stock, discount);
					}
					break;
				case 8:
					// Clone the ArrayList of stock you created
					try {
						// Clone the Stock object
						Stock clonedStock = (Stock) stock.clone();
						System.out.println("The stock cloning process has been completed successfully.");
	
					}
					// Handle the case where the clone operation is not supported for the Stock
					// class
					catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					break;

				case 9:
					System.out.println("Thank you ");
					break;

				// If an incorrect option is entered
				default:
					System.out.println("Please enter the correct choice : ");
					break;

				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input please enter a valid value, please try again");
				// To ensure that the next entry will be read correctly
				scanner.nextLine();
			}

		} while (option != 9);

	}

	// Method for displaying the menu
	public static void menu() {

		System.out.println("Menu Options : ");
		System.out.println("1. Load stock with Grocery and Electronics items from data.txt.");
		System.out.println("2. Add new Grocery or Electronics items to stock and save to data.txt.");
		System.out.println("3. Display all expired Grocery items .");
		System.out.println("4. Show brand, type, and discounted price for Electronics items on discount.");
		System.out.println("5. Print all stock items sorted by brand (descending) .");
		System.out.println("6. Print all stock items sorted by price (descending) .");
		System.out.println("7. Display all stock items with discounts.");
		System.out.println("8. Clone the stock ArrayList.");
		System.out.println("9. Exit ");
		System.out.println("_______________________________________________");
		System.out.print("please choose an option : ");
	}

	// Method to check if the discount is correct
	public static boolean validDiscount(double discount) {
		return discount >= 0 && discount <= 100;
	}

	// Method to check that the given price is not negative.
	public static boolean validPrice(double price) {
		return price >= 0;
	}

	// Method to validate if a given date (year, month, day) is a valid calendar
	// date Uses
	public static boolean isValidDate(int year, int month, int day) {
		Calendar calendar = new GregorianCalendar(year, (month - 1), day);
		// Checks if the calendar's month and day match the input ensuring a valid date
		return calendar.get(Calendar.MONTH) == (month - 1) && calendar.get(Calendar.DAY_OF_MONTH) == day;
	}

	// Method populates inventory with groceries and electronics read from
	// (data.txt) file.
	public static void readFromFile(Stock stock, String fileName) {

		// Create an object from the file
		File file = new File(fileName);

		// Check if the file is empty by verifying its length
		if (file.length() == 0) {
			System.out.println("The file is empty ");
			// Exit the method early as there's no content to process
			return;
		}

		// Declare a Scanner object to read input
		Scanner scanner;
		// A variable indicating whether the operation completed without exceptions
		// (true if no exceptions occurred)
		boolean noExceptionOccurs = true;
		// Initialize the counter variable to keep track of the number of lines being
		// read
		int lineCount = 1;
		try {
			// Initialize the Scanner object to read from the specified file
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				try {
					// read the next line of the file
					String readLine = scanner.nextLine();
					// Divide the line into parts using a comm
					String[] readParts = readLine.split(", ");
					// Check if the first element is grocery
					if (readParts[0].equalsIgnoreCase("Grocery")) {
						// Extract and parse grocery item details from readParts array
						// Item name
						String name = readParts[1];
						// Item brand
						String brand = readParts[2];
						// Discount on item parsed as a double
						double discount = Double.parseDouble(readParts[3]);

						// Validate the entered discount If the discount is invalid print the message
						// and skip the line
						if (!validDiscount(discount)) {
							System.out.println("Line " + lineCount + " was skipped due to invalid discount value ");
							lineCount++;
							continue;
						}

						// Price of the item parsed as a double
						double price = Double.parseDouble(readParts[4]);

						// Validate the entered price If the price is invalid print the message and skip
						// the line
						if (!validPrice(price)) {
							System.out.println("Line " + lineCount + " was skipped due to invalid price value ");
							lineCount++;
							continue;

						}
						// Expiry date as a string
						String expiryDate = readParts[5];
						// Parse expiry date string into day, month, and year
						// Split date by "/" to get day, month, and year
						String[] date = expiryDate.split("/");
						// Parse day as integer
						int day = Integer.parseInt(date[0]);
						// Parse month as integer
						int month = Integer.parseInt(date[1]);
						// Parse year as integer
						int year = Integer.parseInt(date[2]);

						// Validate the entered expiration date. If the date is invalid, print the
						// message and skip the line
						if (!isValidDate(year, month, day)) {
							System.out.println("Line " + lineCount + " was skipped due to incorrect expiration date ");
							lineCount++;
							continue;
						}

						// Weight of the item parsed as a float
						float weight = Float.parseFloat(readParts[6]);
						// Create a Calendar object representing the expiry date
						// Month is zero based in Calendar
						Calendar calendar = new GregorianCalendar(year, (month - 1), day);
						// Add the grocery item to the inventory system using the parsed details
						stock.addStockItem(new Grocery(brand, discount, price, name, calendar, weight));
						lineCount++;

					}
					// Check if the first element is electronics
					else if (readParts[0].equalsIgnoreCase("Electronics")) {
						// Extract and parse electronics item details from readParts array
						// Item version
						String version = readParts[1];
						// Item brand
						String brand = readParts[2];
						// Discount on item parsed as a double
						double discount = Double.parseDouble(readParts[3]);

						// Validate the entered discount If the discount is invalid print the message
						// and skip the line
						if (!validDiscount(discount)) {
							System.out.println("Line " + lineCount + " was skipped due to invalid discount value ");
							lineCount++;
							continue;
						}

						// Price of the item, parsed as a double
						double price = Double.parseDouble(readParts[4]);

						// Validate the entered price If the price is invalid print the message and skip
						// the line
						if (!validPrice(price)) {
							System.out.println("Line " + lineCount + " was skipped due to invalid price value ");
							lineCount++;
							continue;

						}
						// Item type
						String type = readParts[5];
						// power of the item parsed as a double
						double watts = Double.parseDouble(readParts[6]);
						// Add the electronics item to the inventory system using the parsed details
						stock.addStockItem(new Electronic(brand, discount, price, version, type, watts));
						lineCount++;
					} else {
						System.out.println("Line " + lineCount
								+ " of the file was skipped. It should only contain items of the electronic or grocery type ");
						lineCount++;
						continue;

					}

				}
				// This message is printed if an InputMismatchException occurs, indicating the
				// input does not match the expected type
				catch (InputMismatchException e) {
					System.out.println(
							"Line " + lineCount + " was skipped due to input mismatch. Please check the file format "
									+ "\n" + "(" + e + ")");
					lineCount++;
					continue;
				}
				// Catches NumberFormatException when trying to convert an invalid string to a
				// number
				catch (NumberFormatException e) {
					System.out.println("Line " + lineCount
							+ " was skipped. Invalid number format. Please ensure that the data in the file is valid and try again."
							+ "( " + e + " )");
					lineCount++;
					continue;
				}
			}
			// When you finish reading, close
			scanner.close();
			// The file was read successfully
			System.out.println("The process of reading from the file succeeded ");

		} catch (FileNotFoundException e) {
			// Inform the user if the file is not found
			System.out.print("\"The file ( " + fileName
					+ " )could not be found please check if the file exists in the specified location and try again "
					+ e.getMessage());

		}

	}

	// Method to save the item to the specified file
	public static void SaveItemToFile(Stock stock, String fileName) {
		// Create an object from the file
		File file = new File(fileName);
		// Create an object from the PrintWriter
		try {
			// Create an object from the PrintWriter
			PrintWriter output = new PrintWriter(file);
			// Loop through each item in the stock's list of items
			for (int i = 0; i < stock.getItems().size(); i++) {
				// Check if the current item is of type Grocery
				if (stock.getItems().get(i) instanceof Grocery) {
					// Cast the item to a Grocery type and save its details
					Grocery grocery = (Grocery) stock.getItems().get(i);
					// Print the details of the grocery item in the file in line format
					output.println("Grocery" + ", " + grocery.getName() + ", " + grocery.getBrand() + ", "
							+ grocery.getDiscound() + ", " + grocery.getPrice() + ", " + grocery.getFormattedDate()
							+ ", " + grocery.getWeight());
				}
				// Check if the current item is of type Electronic
				else if (stock.getItems().get(i) instanceof Electronic) {
					// Cast the item to an Electronic type and save its details
					Electronic electronic = (Electronic) stock.getItems().get(i);
					// Print the details of the electronic item in the file in line format
					output.println("Electronics" + ", " + electronic.getVersion() + ", " + electronic.getBrand() + ", "
							+ electronic.getDiscound() + ", " + electronic.getPrice() + ", " + electronic.getType()
							+ ", " + electronic.getPower());
				}
			}
			// Close the output
			output.close();
			// Inform the user that data has been saved successfully
			System.out.println("Data saved in the file successfully ");
		} catch (FileNotFoundException e) {
			// Inform the user if the file is not found
			System.out.print("\"The file ( " + fileName
					+ " )could not be found please check if the file exists in the specified location and try again "
					+ e.getMessage());
		}
	}

	// Print all expired Grocery items (those with an expiry date less than the
	// current date)
	public static void allExpiredGrocery(Stock stock) {

		// Initialize a counter to track the number of expired items
		int expiredItemCount = 0;
		// Loop through the items in the stock
		for (int i = 0; i < stock.getItems().size(); i++) {
			// Check if the current item is an instance of Grocery
			if (stock.getItems().get(i) instanceof Grocery) {
				// Cast the current item to Grocery type
				Grocery grocery = (Grocery) stock.getItems().get(i);
				// Get the current date
				Calendar today = Calendar.getInstance();
				// Check if the grocery item's expiry date is before the current date
				if (grocery.getExpiryDate().before(today)) {
					// Print the grocery item's details if it is expired
					System.out.println(grocery.toString());
					// Increment the expired item count
					expiredItemCount++;
				}

			}
		}
		// If no expired items were found, print a message to the user
		if (expiredItemCount == 0) {
			System.out.println("No expired grocery items found");
		}
	}

	// Get the brands, type, and price after discount of Electronics items having a
	// discount
	public static void getElectronicsItems(Stock stock) {
		// Variable to track if any electronic item with a discount was found
		boolean foundElectronicWithDiscount = false;
		// Loop through the items in the stock
		for (int i = 0; i < stock.getItems().size(); i++) {
			// Check if the current item is an instance of electronic
			if (stock.getItems().get(i) instanceof Electronic) {
				// Cast the current item to type electronic
				Electronic electronic = (Electronic) stock.getItems().get(i);
				// if electronics items having a discount
				if (electronic.getDiscound() != 0) {
					System.out
							.println("The Brand = ( " + electronic.getBrand() + " ) , type = ( " + electronic.getType()
									+ " ) , price after discount = ( " + electronic.PriceAfterDiscount() + " )");
					// Set the flag to true, indicating that we found an electronic item with a
					// discount
					foundElectronicWithDiscount = true;
				}
			}
		}
		// If no electronic item with a discount was found, print a message
		if (!foundElectronicWithDiscount) {
			System.out.println("No electronic items with a discount found ");
		}
	}

	// A way to display all stock items that have the same or lesser discount value
	// as the user specified
	public static void displayDiscountedtoSckItems(Stock stock, double discount) {

		// Loop through the items in the stock
		for (int i = 0; i < stock.getItems().size(); i++) {
			// if stock items having a discount
			if (stock.getItems().get(i).getDiscound() != 0 && stock.getItems().get(i).getDiscound() <= discount) {
				System.out.println(stock.getItems().get(i).toString());
			}

		}

	}

}
