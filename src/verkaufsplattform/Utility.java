package verkaufsplattform;

public final class Utility {
	// TODO: Tuan said that it doesn't make sense to have getters and setters in a utility class
	// So where should I have these attributes?
	private static Customer[] customers;
	private static Product[] products;
	private static int customerNumberLength = 6;
	
	private Utility() {}
	
	public static Product[] getProducts() {
		return products;
	}
	
	public static Product getProductAt(int i) {
		return products[i];
	}
	
	public static void initData() {
		setUpCustomers();
		setUpProducts();
	}
	
	
	/**
	 * Creates valid customers so that it's possible to log in
	 */
	private static void setUpCustomers() {
		Address address1 = new Address(58, "West 2nd Street", "Corning", "NY", "14830");
		Customer customer1 = new Customer("Jennifer", "Fais", address1);
		
		Address address2 = new Address(2525, "County Route 24", "Cameron Mills", "NY", "14820");
		Customer customer2 = new Customer("Noel", "Sylvester", address2);
		
		Address address3 = new Address(170, "Cedar Street", "Corning", "NY", "14830");
		Customer customer3 = new Customer("Amelia", "Cueva", address3);
		
		createCustomerNumbers(customer1, customer2, customer3);
	}
	
	
	/** Creates random customer numbers for each example customer
	 * 
	 * @param customers		vararg to save a customer number to as many customers as necessary
	 */
	private static void createCustomerNumbers(Customer... customersInput) {
		customers = new Customer[customersInput.length];
		for(int i = 0; i < customersInput.length; i++) {
			customers[i] = customersInput[i];
			(customers[i]).setCustomerNumber(createNewCustomerNumber(customerNumberLength));
		}
	}
	
	
	private static boolean isCustomerNumberInUse(int customerNumber) {
        boolean customerNumberIsValid = false;
        for (int i = 0; i < customers.length; i++){
            if (customers[i] != null && (customers[i]).getCustomerNumber() == customerNumber){
                customerNumberIsValid = true;
            } 
        } 
        return customerNumberIsValid;
    }
	
	
	private static int createNewCustomerNumber(int decimalPlaces){
        int customerNumber;
        int minNumber = (int) Math.pow(10, decimalPlaces - 1);
        int maxNumber = (int) Math.pow(10, decimalPlaces);

        while(true){
            customerNumber = (int) Math.round(Math.random() * maxNumber);
            if (!isCustomerNumberInUse(customerNumber) && customerNumber > minNumber && customerNumber < maxNumber) {
                break;
            }
        }
        return customerNumber;
    }
	
	
	/**
	 * Creates valid products that the customer can choose from
	 */
	private static void setUpProducts() {
		String prod1Description = "A classic look, a modern performance, a masterstroke of sophistication. With elegant exterior styling that features an aerodynamic fastback design and a 300-hp turbocharged engine, you’ll find the Arteon delivers a premium driving experience all its own. The first look is long, followed by a second one of close-to-equal length. It’s part automobile, part sculpture, and it’s all yours to appreciate.";
		Product product1 = new Product(72723618, "Arteon", 43010.99, prod1Description);
		String prod2Description = "It has an inner fun compass. A joy-o-meter. An excitement to take the road to never been there, never done that. Even the wrong turn is the right turn. And bumps in the road are goosebumps. The ID. Buzz is going to electrify the world with curiosity and anticipation. The US reveal is coming in 2023. Stay tuned for a smiling bus that will have you grinning. And buzzing.";
		Product product2 = new Product(23231099, "ID Buzz", 80999.99, prod2Description);
		String prod3Description = "With its compelling tech, modern looks, and powerful turbocharged engine with 6-speed manual transmission, is it surprising that the industry continues to recognize this model? Not for Golf GTI owners. Just check their smiles. 40 years ago, the Golf GTI began carving out its fun-to-drive reputation in the U.S. Today’s eighth generation packs exhilarating power, and the latest, driver-focused technology into a concentrated, sporty design, delivering on the promise we made–to keep it fun, always.";
		Product product3 = new Product(45678123, "Golf 8 GTI", 32999.99, prod3Description);
		
		products = new Product[3];
		products[0] = product1;
		products[1] = product2;
		products[2] = product3;
	}
	
	/**
     *  Method to allow the customer to login
     * @return	Returns the validated customer number
     */
    public static Customer login(){
    	Customer customer;
        int customerNumber;
        Gui.showPossibleCustomerNumbers(customers);
        for (int tries = 0; tries < 3; tries++) {
            customerNumber = Gui.getCustomerNumber();
            if (isCustomerNumberInUse(customerNumber)) {
            	customer = findCustomer(customerNumber);
            	Gui.greetCustomer(customer);
            	return customer;
            } else if (tries < 2) {
                Gui.showTriesLeft(tries);
            }
        }
        Gui.showBlocked();
        return null;
    }
    
    public static Customer register(){
        int customerNumber = createNewCustomerNumber(customerNumberLength);
        String firstName = Gui.getCustomerFirstName();
        String lastName = Gui.getCustomerLastName();
        int houseNumber = Gui.getHouseNumber();
        String street = Gui.getStreet();
        String city = Gui.getCity();
        String state = Gui.getState();
        String zipCode = Gui.getZipcode();
        Address address = new Address(houseNumber, street, city, state, zipCode);
        Customer newCustomer = new Customer(customerNumber, firstName, lastName, address);
        addCustomer(newCustomer);
        
        Gui.showRegistrationResults(newCustomer);
        return newCustomer;
    }
	

	
	/*
	 * Returns the customer data for a given customer number
	 * The customer number must be checked first to make sure that it is valid
	 */
	public static Customer findCustomer(int customerNumber) {
		Customer foundCustomer = null;
		for (int i = 0; i < customers.length; i++) {
			if((customers[i]).getCustomerNumber() == customerNumber) {
				foundCustomer = customers[i];
			}
		}
		return foundCustomer;
	}
	
	public static void addCustomer(Customer customer){
		//TODO -> is my array already full? If there is a position that is null, then save there
        Customer[] newCustomers = new Customer[customers.length + 1];
        for (int i = 0; i < customers.length; i++){
            newCustomers[i] = customers[i];
        }
        newCustomers[newCustomers.length - 1] = customer;
        customers = newCustomers;
    }
	
	public static void showAvailableProducts() {
		Gui.showProductsIntroduction();
		int productNumber;
		for (int i = 0; i < products.length; i++) {
			productNumber = i + 1;
			Gui.showAvailableProduct(productNumber, products[i]);
		}
		Gui.showProductsEnd();
	}
}
