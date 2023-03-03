package verkaufsplattform;

public class Main {
	public static void main (String[] args) {
		//SalesProgram salesProgram = new SalesProgram();
		//salesProgram.run();
		Address address = new Address(58, "West 2nd Street", "Corning", "NY", "14830");
		Customer customer = new Customer(123455, "Adriana", "Kramer", address);
		String jsonCustString = JsonUtility.toJSON(customer);
		System.out.println(jsonCustString);
		Customer customer2 = JsonUtility.customerFromJSON(jsonCustString);
		
		System.out.println(customer2.getFirstName());
		System.out.println(customer2.getLastName());
		System.out.println(customer2.getAddress().getZipCode());
	}

}
