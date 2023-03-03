package verkaufsplattform;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class PersistenceUtility {
	
	private static final String CUSTOMERS_FILE_PATH = "verkaufsplattform/resources/customers.txt";
	private static final String PRODUCTS_FILE_PATH = "verkaufsplattform/resources/products.txt";
	
	private PersistenceUtility() {}
		
	public static void saveCustomersToFile(Customer[] customers) {
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(CUSTOMERS_FILE_PATH));){
			String jsonString;
			for(Customer customer : customers) {
				jsonString = JsonUtility.toJSON(customer);
				writer.write(jsonString);
				writer.write("\n");
			}
		} catch (IOException e) {
			System.out.println("Error in saving customers.");
		}
		
	}
	
	public static Customer[] createCustomersFromFile() {
		Customer[] customers = null;
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(CUSTOMERS_FILE_PATH));){
			Customer customer;
			String line;
			while (true) {
				line = reader.readLine();
				if (line == null) {
					break;
				} else {
					customer = JsonUtility.customerFromJSON(line);
					customers = Utility.addCustomer(customer, customers);
				}
			}
		} catch (IOException e) {
			System.out.println("Error in creating customers from file.");
		}
		return customers;
	}
	
	public static void saveProductsToFile(Product[] products) {
		String jsonString;
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(PRODUCTS_FILE_PATH));){
			for (Product product : products) {
				jsonString = JsonUtility.toJSON(product);
				writer.write(jsonString);
				writer.write("\n");
			}
		} catch (IOException e) {
			System.out.println("Error in saving products.");
		}
	}
	
	public static Product[] createProductsFromFile() {
		Product[] products = null;
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(PRODUCTS_FILE_PATH));){
			Product product;
			String line;
			while(true) {
				line = reader.readLine();
				if(line == null) {
					break;
				} else {
					product = JsonUtility.productFromJson(line);
					products = Utility.addProduct(product, products);
				}
			}
			
		} catch (IOException e) {
			System.out.println("Error in creating products from file.");
		}
		
		return products;
	}
}
