package verkaufsplattform;

public class SalesProgram {
	
	private Customer[] customers;
	private Product[] products;
	private Customer customer;
	
	public void run() {
		initializeData();
		Gui.showWelcomeScreen();
		String customerInput;
		while (true) {
			customerInput = Gui.getLoginRegisterOrQuit();
			
			if (customerInput.equals(Utility.LOGIN_FLAG)) {
				customer = Utility.login(customers);
				if (customer == null) {
					Gui.showQuit();
					break;
				} else {
					Gui.showMainMenu(customer);
				}
			} else if (customerInput.equals(Utility.REGISTER_FLAG)) {
				Utility.register(customers);
			} else if (customerInput.equals(Utility.QUIT_FLAG)) {
				Gui.showQuit();
			} else {
				Gui.showInvalidInput();
			}
		}
		
	}
	
	public void initializeData() {
		products = Utility.setUpProducts();
		customers = Utility.setUpCustomers();
	}
	
	public void sellItem(Customer customer, Product product){
		Gui.showInvoice(customer, product);
    }
	
	public void endSession(Customer customer) {
        Gui.endSession();
        Gui.farewellCustomer(customer);
	}
	
}
