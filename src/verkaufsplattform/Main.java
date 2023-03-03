package verkaufsplattform;

public class Main {
	public static void main (String[] args) {
//		SalesProgram salesProgram = new SalesProgram();
//		salesProgram.run();
		
		Customer[] customers = Utility.setUpCustomers();
		Product[] products = Utility.setUpProducts();
		PersistenceUtility.saveCustomersToFile(customers);
		PersistenceUtility.saveProductsToFile(products);
		Customer[] newCustomers = PersistenceUtility.createCustomersFromFile();
		Product[] newProducts = PersistenceUtility.createProductsFromFile();
		System.out.println("Number of new customers: " + newCustomers.length);
		System.out.println("Number of new products: " + newProducts.length);
		System.out.println("Customer 1 name: " + newCustomers[1].getFirstName());
		System.out.println("Product 1 name: "+ newProducts[1].getProductDescription());
	}

}
