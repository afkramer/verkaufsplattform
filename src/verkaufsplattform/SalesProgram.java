package verkaufsplattform;

public class SalesProgram {
	
	private Customer[] customers;
	private Product[] products;
	private Customer customer;
	
	public void run() {
		Utility.initData();
		Gui.showWelcomeScreen();
		String customerInput;
		while (true) {
			customerInput = Gui.getLoginRegisterOrQuit();
			
			if (customerInput.equals(Utility.LOGIN_FLAG)) {
				customer = Utility.login();
				if (customer == null) {
					Gui.showQuit();
					break;
				} else {
					Gui.showMainMenu(customer);
				}
			} else if (customerInput.equals(Utility.REGISTER_FLAG)) {
				Utility.register();
			} else if (customerInput.equals(Utility.QUIT_FLAG)) {
				Gui.showQuit();
			} else {
				Gui.showInvalidInput();
			}
		}
		
	}
	
	public void sellItem(Customer customer, Product product){
		Gui.showInvoice(customer, product);
    }
	
	public void endSession(Customer customer) {
        Gui.endSession();
        Gui.farewellCustomer(customer);
	}
	
	public Customer[] getCustomers() {
		return this.customers;
	}
	
	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}
	
	public Product[] getProducts() {
		return this.products;
	}
	
	public void setProducts(Product[] products) {
		this.products = products;
	}
	
}
