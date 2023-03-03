package verkaufsplattform;

public class Product {
	private int productNumber;
	private String productName;
	private double productPrice;
	private String productDescription;
	private int desiredQuantity;
	private double totalPrice;
	private double includedTax;
	
	
	
	public Product(int productNumber, String productName, double productPrice, String productDescription,
			int desiredQuantity, double totalPrice, double includedTax) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.desiredQuantity = desiredQuantity;
		this.totalPrice = totalPrice;
		this.includedTax = includedTax;
	}

	public Product(int productNumber, String productName, double productPrice, String productDescription) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
	}
	
	public Product() {}
	
	public void setDesiredQuantity(int quantity) {
		desiredQuantity = quantity;
	}
	
	public int getDesiredQuantity() {
		return desiredQuantity;
	}
	
	public int getProductNumber() {
		return productNumber;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public double getProductPrice() {
		return productPrice;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public double getIncludedTax() {
		return includedTax;
	}
	
	public void calculateTotal() {
        totalPrice = Math.round(desiredQuantity * productPrice * 100) / 100.0;
    }
	
	public void calculateVAT(){
        includedTax = Math.round(totalPrice * Utility.VALUE_ADDED_TAX * 100) / 100.0;
    }
	
	
}
