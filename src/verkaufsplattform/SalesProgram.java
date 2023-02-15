package verkaufsplattform;

public class SalesProgram {
	private Customer[] customers;
	private Product[] products;
	private Gui gui = new Gui();
	private Customer customer;
	
	public void run() {
		setUpCustomers();
		setUpProducts();
		gui.showWelcomeScreen();
		loginOrRegister(gui.loginOrRegister());
		
		if (customer != null) {
			showAvailableProducts();
			Product product = chooseProduct();
			product.setDesiredQuantity(gui.getDesiredQuantity(product));
			product.calculateTotal();
			product.calculateVAT();
			sellItem(customer, product);
			endSession(customer);
		}
	}
	
	/**
	 * Creates valid customers so that it's possible to log in
	 */
	public void setUpCustomers() {
		Address address1 = new Address(58, "West 2nd Street", "Corning", "NY", "14830");
		Customer customer1 = new Customer("Jennifer", "Fais", address1);
		
		Address address2 = new Address(2525, "County Route 24", "Cameron Mills", "NY", "14820");
		Customer customer2 = new Customer("Noel", "Sylvester", address2);
		
		Address address3 = new Address(170, "Cedar Street", "Corning", "NY", "14830");
		Customer customer3 = new Customer("Amelia", "Cueva", address3);
		
		/*
		customers = new Customer[3];
		customers[0] = customer1;
		customers[1] = customer2;
		customers[2] = customer3;
		
		createCustomerNumbers(customers);
		*/
		
		createCustomerNumbers(customer1, customer2, customer3);
	}
	
	/** Creates random customer numbers for each example customer
	 * 
	 * @param customers		vararg to save a customer number to as many customers as necessary
	 */
	public void createCustomerNumbers(Customer... customersInput) {
		customers = new Customer[customersInput.length];
		for(int i = 0; i < customersInput.length; i++) {
			customers[i] = customersInput[i];
			(customers[i]).setCustomerNumber(createNewCustomerNumber());
		}
	}
	
	/**
	 * Creates valid products that the customer can choose from
	 */
	public void setUpProducts() {
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
	 * Executes the correct method based on whether customer wants to register or login
	 * @param startChoice	1 to login and 2 to register
	 */
	//TODO: if customer is not found, then register
	public void loginOrRegister(int startChoice) {
		// startChoice is 1 to login and 2 to register
		if (startChoice == 1) {
			login();
		} else {
			register();
		}
	}
	
	/**
     *  Method to allow the customer to login
     * @return	Returns the validated customer number
     */
    public void login(){
        int customerNumber;
        gui.showPossibleCustomerNumbers(customers);
        for (int tries = 0; tries < 3; tries++) {
            customerNumber = gui.getCustomerNumber();
            if (isCustomerNumberInUse(customerNumber)) {
            	customer = findCustomer(customerNumber);
            	gui.greetCustomer(customer);
            	return;
            } else if (tries < 2) {
                gui.showTriesLeft(tries);
            }
        }
        gui.showBlocked();
        customer = null;
    }
    
    public void register(){
        int customerNumber = createNewCustomerNumber();
        String firstName = gui.getCustomerFirstName();
        String lastName = gui.getCustomerLastName();
        int houseNumber = gui.getHouseNumber();
        String street = gui.getStreet();
        String city = gui.getCity();
        String state = gui.getState();
        String zipCode = gui.getZipcode();
        Address address = new Address(houseNumber, street, city, state, zipCode);
        Customer newCustomer = new Customer(customerNumber, firstName, lastName, address);
        addCustomer(newCustomer);
        
        gui.showRegistrationResults(newCustomer);
        customer = newCustomer;
    }
	
	
	public boolean isCustomerNumberInUse(int customerNumber) {
        boolean customerNumberIsValid = false;
        for (int i = 0; i < customers.length; i++){
            if (customers[i] != null && (customers[i]).getCustomerNumber() == customerNumber){
                customerNumberIsValid = true;
            } 
        } 
        return customerNumberIsValid;
    }
	
	public int createNewCustomerNumber(){
        int customerNumber;

        while(true){
            customerNumber = (int) Math.round(Math.random() * 99999);
            if (!isCustomerNumberInUse(customerNumber) && customerNumber > 9999 && customerNumber < 100000) {
                break;
            }
        }
        return customerNumber;
    }
	
	/*
	 * Returns the customer data for a given customer number
	 * The customer number must be checked first to make sure that it is valid
	 */
	public Customer findCustomer(int customerNumber) {
		Customer foundCustomer = null;
		for (int i = 0; i < customers.length; i++) {
			if((customers[i]).getCustomerNumber() == customerNumber) {
				foundCustomer = customers[i];
			}
		}
		return foundCustomer;
	}
	
	public void addCustomer(Customer customer){
		//TODO -> is my array already full? If there is a position that is null, then save there
        Customer[] newCustomers = new Customer[customers.length + 1];
        for (int i = 0; i < customers.length; i++){
            newCustomers[i] = customers[i];
        }
        newCustomers[newCustomers.length - 1] = customer;
        customers = newCustomers;
    }
	
	public void showAvailableProducts() {
		gui.showProductsIntroduction();
		int productNumber;
		for (int i = 0; i < products.length; i++) {
			productNumber = i + 1;
			gui.showAvailableProduct(productNumber, products[i]);
		}
		gui.showProductsEnd();
	}
	
	public Product chooseProduct() {
		int choice = gui.getDesiredProduct(products.length);
		int index = choice - 1;
		return products[index];
	}
	
	public void sellItem(Customer customer, Product product){
		gui.showInvoice(customer, product);
    }
	
	public void endSession(Customer customer) {
        gui.endSession();
        gui.farewellCustomer(customer);
	}
	
}
