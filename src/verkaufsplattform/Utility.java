package verkaufsplattform;

public final class Utility {
	private static final int CUSTOMER_NUMBER_LENGTH = 6;
	public static final String LOGIN_FLAG = "1";
	public static final String REGISTER_FLAG = "2";
	public static final String QUIT_FLAG = "q";
	
	private Utility() {}
	
	// QUESTION: best practice for accessing customers from the sales program?
	// Or would it be OK to set up the customers in the utility?
	
	/**
	 * Creates some existing customers so that it's possible to log in
	 * 
	 * @return	Returns an array of customers
	 */
	public static Customer[] setUpCustomers() {
		Address address1 = new Address(58, "West 2nd Street", "Corning", "NY", "14830");
		Customer customer1 = new Customer("Jennifer", "Fais", address1);
		
		Address address2 = new Address(2525, "County Route 24", "Cameron Mills", "NY", "14820");
		Customer customer2 = new Customer("Noel", "Sylvester", address2);
		
		Address address3 = new Address(170, "Cedar Street", "Corning", "NY", "14830");
		Customer customer3 = new Customer("Amelia", "Cueva", address3);
		
		return assignCustomerNumbers(customer1, customer2, customer3);
	}
	
	
	/** Creates random customer numbers for each example customer
	 * 
	 * @param customers		vararg to save a customer number to as many customers as necessary
	 * @return Array of customers set up with customer numbers
	 */
	private static Customer[] assignCustomerNumbers(Customer... customers) {
		for(int i = 0; i < customers.length; i++) {
			(customers[i]).setCustomerNumber(createNewCustomerNumber(CUSTOMER_NUMBER_LENGTH, customers));
		}
		
		return customers;
	}

	
	private static int createNewCustomerNumber(int decimalPlaces, Customer[] customers){
        int customerNumber;
        int minNumber = (int) Math.pow(10, decimalPlaces - 1);
        int maxNumber = (int) Math.pow(10, decimalPlaces);

        while(true){
            customerNumber = (int) Math.round(Math.random() * maxNumber);
            if (!isCustomerNumberInUse(customerNumber, customers) && customerNumber > minNumber && customerNumber < maxNumber) {
                break;
            }
        }
        return customerNumber;
    }
	
	
	private static boolean isCustomerNumberInUse(int customerNumber, Customer[] customers) {
        boolean customerNumberIsValid = false;
        for (int i = 0; i < customers.length; i++){
            if (customers[i] != null && (customers[i]).getCustomerNumber() == customerNumber){
                customerNumberIsValid = true;
            } 
        } 
        return customerNumberIsValid;
    }
	
	
	/**
     *  Method to allow the customer to login
     * @return	Returns the correct customer based on given customer number
     */
    public static Customer login(Customer[] customers){
    	Customer customer;
        int customerNumber;
        Gui.showPossibleCustomerNumbers(customers);
        for (int tries = 0; tries < 3; tries++) {
            customerNumber = Gui.getCustomerNumber();
            if (isCustomerNumberInUse(customerNumber, customers)) {
            	customer = findCustomer(customerNumber, customers);
            	Gui.greetCustomer(customer);
            	return customer;
            } else if (tries < 2) {
                Gui.showTriesLeft(tries);
            }
        }
        Gui.showBlocked();
        return null;
    }
    
    
    /*
	 * Returns the customer data for a given customer number
	 * The customer number must be checked first to make sure that it is valid
	 */
	public static Customer findCustomer(int customerNumber, Customer[] customers) {
		Customer foundCustomer = null;
		for (int i = 0; i < customers.length; i++) {
			if((customers[i]).getCustomerNumber() == customerNumber) {
				foundCustomer = customers[i];
			}
		}
		return foundCustomer;
	}
	
    
    public static Customer[] register(Customer[] customers){
        int customerNumber = createNewCustomerNumber(CUSTOMER_NUMBER_LENGTH, customers);
        String firstName = Gui.getCustomerFirstName();
        String lastName = Gui.getCustomerLastName();
        int houseNumber = Gui.getHouseNumber();
        String street = Gui.getStreet();
        String city = Gui.getCity();
        String state = Gui.getState();
        String zipCode = Gui.getZipcode();
        Address address = new Address(houseNumber, street, city, state, zipCode);
        Customer newCustomer = new Customer(customerNumber, firstName, lastName, address);
        addCustomer(newCustomer, customers);
        
        Gui.showRegistrationResults(newCustomer);
        return customers;
    }
	
	
	public static Customer[] addCustomer(Customer customer, Customer[] customers){
        Customer[] newCustomers = new Customer[customers.length + 1];
        for (int i = 0; i < customers.length; i++){
            newCustomers[i] = customers[i];
        }
        newCustomers[newCustomers.length - 1] = customer;
        return newCustomers;
    }
	
	
	/**
	 * Creates valid products that the customer can choose from
	 */
	public static Product[] setUpProducts() {
		String prod1Description = "A classic look, a modern performance, a masterstroke of sophistication. With elegant exterior styling that features an aerodynamic fastback design and a 300-hp turbocharged engine, you’ll find the Arteon delivers a premium driving experience all its own. The first look is long, followed by a second one of close-to-equal length. It’s part automobile, part sculpture, and it’s all yours to appreciate.";
		Product product1 = new Product(72723618, "Arteon", 43010.99, prod1Description);
		String prod2Description = "It has an inner fun compass. A joy-o-meter. An excitement to take the road to never been there, never done that. Even the wrong turn is the right turn. And bumps in the road are goosebumps. The ID. Buzz is going to electrify the world with curiosity and anticipation. The US reveal is coming in 2023. Stay tuned for a smiling bus that will have you grinning. And buzzing.";
		Product product2 = new Product(23231099, "ID Buzz", 80999.99, prod2Description);
		String prod3Description = "With its compelling tech, modern looks, and powerful turbocharged engine with 6-speed manual transmission, is it surprising that the industry continues to recognize this model? Not for Golf GTI owners. Just check their smiles. 40 years ago, the Golf GTI began carving out its fun-to-drive reputation in the U.S. Today’s eighth generation packs exhilarating power, and the latest, driver-focused technology into a concentrated, sporty design, delivering on the promise we made–to keep it fun, always.";
		Product product3 = new Product(45678123, "Golf 8 GTI", 32999.99, prod3Description);
		
		Product[] products = new Product[3];
		products[0] = product1;
		products[1] = product2;
		products[2] = product3;
		return products;
	}
	
	
	public static Customer quit() {
		Gui.showQuit();
		return null;
	}
}
