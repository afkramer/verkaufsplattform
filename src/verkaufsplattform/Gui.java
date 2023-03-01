package verkaufsplattform;
import java.util.Date;

public final class Gui {
	public static final String CYAN_TEXT = "\u001B[36m";
    public static final String GREEN_TEXT = "\u001B[32m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String PURPLE_TEXT = "\u001B[35m";
    public static final String BLUE_TEXT = "\u001B[34m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String ANSCI_RESET = "\u001B[0m";
    public static final java.util.Scanner sc = new java.util.Scanner(System.in);
    
    private Gui() {}
    
    public static void showWelcomeScreen() {
        System.out.print(CYAN_TEXT);
        System.out.println("\n\n\n************************************************************");
        System.out.print(GREEN_TEXT);
        System.out.println("******                                                ******");
        System.out.print(YELLOW_TEXT);
        System.out.println("****                                                    ****");
        System.out.println("**         \u263A        Welcome to Autozone          \u263A        **");
        System.out.println("****                                                    ****");
        System.out.print(GREEN_TEXT);
        System.out.println("******                                                ******");
        System.out.print(CYAN_TEXT);
        System.out.println("************************************************************\n\n");

        Date date = new Date();
        System.out.printf("Today's date is: %1$tA, %1$tB %1$td %1$tY%n%n", date);
        //System.out.println("Please enter your customer number.\n");

        System.out.print(ANSCI_RESET);
    }
    
    /**
     * Method to determine whether the customer wants to register or login
     * @return	1 to login, 2 to register
     */
    public static Customer loginOrRegister() {
        String customerInput;
    	while (true) {
            System.out.print(PURPLE_TEXT);
            System.out.println("\n\nEnter 1 to login or 2 to register:");
            System.out.print(ANSCI_RESET);

            customerInput = sc.nextLine();

            if (customerInput.equals("1")){
                return Utility.login();
            } else if(customerInput.equals("2")) {
                return Utility.register();
            } else {
                System.out.print(RED_TEXT);
                System.out.println("\n\nInvalid input! Please try again!");
                System.out.print(ANSCI_RESET);
            }
        }
    }
    
    public static void showPossibleCustomerNumbers(Customer[] customers) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("\n\nPossible customer numbers: ");
    	for (int i = 0; i < customers.length; i++) {
    		sb.append(customers[i].getCustomerNumber());
    		sb.append(" ");
    	}
    	System.out.println(sb);
    }
    
    public static int getCustomerNumber() {
        while(true) {
            try {
                System.out.print(CYAN_TEXT);
                System.out.println("\n\nPlease enter your customer number: ");
                System.out.print(ANSCI_RESET);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.print(RED_TEXT);
                System.out.println("\nA customer number consists of 5 digits. Please try again.\n");
                System.out.print(ANSCI_RESET);
            }
        }
    }
    
    public static void greetCustomer(Customer customer) {
        System.out.println("\n\nWelcome, customer " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("\n\nPlease press <Enter> to continue.");
        sc.nextLine();
    }
    
    public static void showTriesLeft(int tries) {
    	System.out.print(RED_TEXT);
        System.out.print("\n\nThat is not a known customer number. Remaining attempts: " + (2 - tries));
        System.out.println(ANSCI_RESET);
    }
    
    public static void showBlocked() {
    	System.out.println("\n\nSorry, you have been blocked for too many tries. Good bye.\n\n");
    }
    
    public static void showMainMenu() {
    	while(true) {
    		System.out.print(PURPLE_TEXT);
        	System.out.println("\n\nPlease select an option:");
        	System.out.println("1) Show available products");
        	System.out.println("2) Change your data");
        	System.out.println("3) Logout");
        	System.out.println(ANSCI_RESET);
        	try {
        		
        	} catch (NumberFormatException nfe) {
        		
        	}
    	}
    	
    }
    
    public static String getCustomerFirstName() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("First name: ");
    	System.out.print(ANSCI_RESET);
    	return sc.nextLine();
    }
    
    public static String getCustomerLastName() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("Last name: ");
    	System.out.print(ANSCI_RESET);
    	return sc.nextLine();
    }
    
    // TODO: error handling in case the customer enters a string or nothing
    public static String getStreet() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("Street name: ");
    	System.out.print(ANSCI_RESET);
    	return sc.nextLine();
    }
    
    public static int getHouseNumber() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("House number: ");
    	System.out.print(ANSCI_RESET);
    	return Integer.parseInt(sc.nextLine());
    }
    
    public static String getCity() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("City: ");
    	System.out.print(ANSCI_RESET);
    	return sc.nextLine();
    }
    
    public static String getState() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("State: ");
    	System.out.print(ANSCI_RESET);
    	return sc.nextLine();
    }
    
    public static String getZipcode() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("Zip code: ");
    	System.out.print(ANSCI_RESET);
    	return sc.nextLine();
    }
    
    public static void showRegistrationResults(Customer customer) {
    	System.out.print(CYAN_TEXT);
        System.out.println("\n\nThanks for registering, " + customer.getFirstName() + "! Your customer number is: " + customer.getCustomerNumber() + "\n\n");
        System.out.println("Please press <Enter> to continue.");
        System.out.print(ANSCI_RESET);
        sc.nextLine();
    }
    
    public static void showProductsIntroduction() {
    	System.out.print(PURPLE_TEXT);
    	System.out.println("\n\n\n***************************************************");
    	System.out.println("**  ");
    	System.out.println("**  These are our available products: ");
    	System.out.print(ANSCI_RESET);
    }
    
    // Change how the description is shown -> now it is really long
    public static void showAvailableProduct(int index, Product product) {
    	System.out.print(PURPLE_TEXT);
		System.out.println("\n--------------------------------------------");
		System.out.println("**  ");
		System.out.println("**  Product #"+ index +": " + product.getProductName());
		//System.out.println("**  Description: " + product.getProductDescription());
		//System.out.println("**  ");
		System.out.println("**  Product number: " +  product.getProductNumber());
		System.out.println("**  Purchase price: " + product.getProductPrice());
		System.out.println("**  ");
		System.out.println("--------------------------------------------");
		System.out.print(ANSCI_RESET);
    }
    
    public static void showProductsEnd() {
    	System.out.print(PURPLE_TEXT);
    	System.out.println("\n***************************************************\n\n\n");
    	System.out.print(ANSCI_RESET);
    }
    
    public static Product getDesiredProduct() {
    	System.out.print(BLUE_TEXT);
    	System.out.println("Which product would you like to buy?");
    	System.out.print(ANSCI_RESET);
    	int input;
    	int productsLength = Utility.getProducts().length;
    	while (true) {
    		try {
    			input = Integer.parseInt(sc.nextLine());
    			if (input > 0 && input <= productsLength) {
    				return Utility.getProductAt(input - 1);
    			} else {
    				System.out.print(RED_TEXT);
                    System.out.println("\nPlease enter a number between 1 and " + productsLength + ".\n\n");
                    System.out.print(ANSCI_RESET);
    			}
    			
    		} catch (NumberFormatException nfe) {
    			System.out.print(RED_TEXT);
                System.out.println("\nPlease enter a valid product number.\n\n");
                System.out.print(ANSCI_RESET);
    		}
    	}
    	
    }

    public static int getDesiredQuantity(Product product) {
        System.out.println("\n\n\nHow many of product " + product.getProductName() + " would you like to buy?");
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe){
                System.out.print(RED_TEXT);
                System.out.println("\nPlease enter a valid quantity.\n\n");
                System.out.print(ANSCI_RESET);
            } 
        }
    }
    
    public static void showInvoice(Customer customer, Product product) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(String.format("%n%n%s %s%n", customer.getFirstName(), customer.getLastName()));
    	sb.append(String.format("%d %s%n", customer.getAddress().getHouseNumber(), customer.getAddress().getStreet()));
    	sb.append(String.format("%s %s, %s%n%n%n", customer.getAddress().getCity(), customer.getAddress().getState(), customer.getAddress().getZipCode()));
    	sb.append(String.format("Thanks %s for your purchase!%n", customer.getFirstName()));
    	sb.append("We are charging you for the following positions:\n");
    	sb.append(String.format((new String(new char[100])).replace("\0", "_")));
    	sb.append("\n");
    	sb.append(String.format("%-7s", "Pos."));
    	sb.append(String.format("%-20s", "Description"));
    	sb.append(String.format("%-7s", "Qty."));
    	sb.append(String.format("%-12s","Price"));
    	sb.append(String.format("%-12s%n", "Total price"));
    	sb.append(String.format("%03d", 1));
    	sb.append(String.format("%4s", ""));
    	sb.append(String.format("%-20s", product.getProductName()));
    	sb.append(String.format("%-7d", product.getDesiredQuantity()));
    	sb.append(String.format("%-,12.2f€", product.getProductPrice()));
    	sb.append(String.format("%-,12.2f€%n%n", product.getTotalPrice()));
    	addLongTextLines(100, sb, product.getProductDescription());
    	sb.append("\n");
    	sb.append(String.format((new String(new char[100])).replace("\0", "_")));
    	sb.append("\n");
    	sb.append(String.format("%27s", ""));
    	sb.append(String.format("%19s", "Total w/o tax:"));
    	sb.append(String.format("%,12.2f€", product.getTotalPrice() - product.getIncludedTax()));
    	sb.append("\n");
    	sb.append(String.format("%27s", ""));
    	sb.append(String.format("%19s", "Value added tax:"));
    	sb.append(String.format("%,12.2f€", product.getIncludedTax()));
    	sb.append("\n");
    	sb.append(String.format("%27s", ""));
    	sb.append(String.format((new String(new char[35])).replace("\0", "_")));
    	sb.append("\n");
    	sb.append(String.format("%27s", ""));
    	sb.append(String.format("%19s", "Total:"));
    	sb.append(String.format("%,12.2f€", product.getTotalPrice()));
    	sb.append("\n\n\n");
    	System.out.print(sb);
    }
    
    public static void addLongTextLines(int desiredLineLength, StringBuilder sb, String longText) {
    	String[] words = longText.split(" ");
    	int currentWordIndex = 0;
    	String currentWord = "";
    	int currentLineLength = 0;
    	while (true) {
    		if (currentWordIndex < words.length) {
    			currentWord = words[currentWordIndex];
    			if ((currentLineLength + currentWord.length()) < desiredLineLength) {
        			sb.append(words[currentWordIndex]);
        			sb.append(" ");
        			currentWordIndex++;
        			currentLineLength += currentWord.length() + 1;
        		} else {
        			sb.append("\n");
        			currentLineLength = 0;
        		} 
    		} else {
    			break;
    		}
    	}
    }
    
    public static void endSession () {
        System.out.println("Please press <Enter> to end your visit.");
        sc.nextLine();
    }
    
    public static void farewellCustomer(Customer customer){
        System.out.print(PURPLE_TEXT);
        System.out.println("\n\n***************************************************");
        System.out.println("**");
        System.out.print(CYAN_TEXT);
        System.out.println("** Thanks for shopping at AutoZone, " + customer.getFirstName());
        System.out.print(PURPLE_TEXT);
        System.out.println("**");
        System.out.println("***************************************************\n\n");
        System.out.print(ANSCI_RESET);
    }
}
