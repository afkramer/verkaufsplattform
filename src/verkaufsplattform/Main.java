package verkaufsplattform;

public class Main {
	public static void main (String[] args) {
		//SalesProgram salesProgram = new SalesProgram();
		//salesProgram.run();
		Address address = new Address(58, "West 2nd Street", "Corning", "NY", "14830");
		System.out.println(address.addressToJSON());
	}

}
