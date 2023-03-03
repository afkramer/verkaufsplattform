package verkaufsplattform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtility {

	private JsonUtility() {}
	
	public static String toJSON(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			System.out.println("Could not write customer to JSON");
			jsonString = "";
		}
		return jsonString;
	}
	
	public static Customer customerFromJSON(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, Customer.class);
		} catch (JsonProcessingException e) {
			System.out.println("Could not create customer from JSON string");
			return null;
		}
	}
	
	public Address addressFromJSON(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, Address.class);
		} catch (JsonProcessingException e) {
			System.out.println("Problem creating address from JSON!");
			return null;
		}
	}
}
