package verkaufsplattform;

public class SalesProgram {
	
	private Customer customer;
	
	public void run() {
		Utility.initData();
		Gui.showWelcomeScreen();
		customer = Gui.loginOrRegister();
		
		if (customer != null) {
			Utility.showAvailableProducts();
			Product product = Gui.getDesiredProduct();
			product.setDesiredQuantity(Gui.getDesiredQuantity(product));
			product.calculateTotal();
			product.calculateVAT();
			sellItem(customer, product);
			endSession(customer);
		}
	}
	
	public void sellItem(Customer customer, Product product){
		Gui.showInvoice(customer, product);
    }
	
	public void endSession(Customer customer) {
        Gui.endSession();
        Gui.farewellCustomer(customer);
	}
	
}
