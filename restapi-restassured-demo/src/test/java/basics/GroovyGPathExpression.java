package basics;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GroovyGPathExpression {

	Response response;

	@BeforeMethod
	public void setUp() {
		baseURI = "http://localhost:5002";
		basePath = "/api/vehicles/";
		Header acceptHeader = new Header("Accept", "application/json");
		RequestSpecification httpRequest = given().auth().basic("admin", "admin").header(acceptHeader);
		response = httpRequest.when().get().andReturn();
	}

	@Test(enabled = false)
	public void findGsonGpath() {
		// Write GPath expression to find the type of the Vehicle with id equal to One.
		
		// STEP 1 - Find List of Vehicles
//		List<?> vehicles = response.path("vehicles");
//		System.out.println(vehicles);
		
		// STEP 2 - Find List of Public transport (FALSE would be ignored)
//		HashMap<String, ?> publicTransports = response.path("vehicles.find { it.publicTransport } ");
//		System.out.println(publicTransports);
		
		// STEP 3 - find the type of the Vehicle with id equal to One
		String type = response.path("vehicles.find { it.id == 1 }.type");
		System.out.println(type);
				
	}
	
	@Test(enabled = false)
	public void findAllGsonGpath() {
		// Write GPath expression to find type of All Vehicles whose weight is lesser than 1500.
		
		List<String> types = response.path("vehicles.findAll { it.weight < 1500 }.type");
		System.out.println(types);
				
	}
	
	@Test(enabled = false)
	public void maxGsonGpath() {
		// Write GPath expression to find type of Heaviest Vehicles.
		
		String type = response.path("vehicles.max { it.weight }.type");
		System.out.println(type);
				
	}
	
	@Test(enabled = false)
	public void minGsonGpath() {
		// Write GPath expression to find type of Lightest Vehicles.
		
		String type = response.path("vehicles.min { it.weight }.type");
		System.out.println(type);
				
	}
	
	@Test(enabled = false)
	public void sumGsonGpath() {
		// Write GPath expression to find the total weight of all the Vehicles.
		
//		int weight = response.path("vehicles.findAll { it.weight }.sum()");
		int weight = response.path("vehicles.sum { it.weight }");
		System.out.println(weight);
				
	}
	
	@Test(enabled = false)
	public void chainGsonGpath() {
		// Write GPath expression to chain FindALL and Find.
		
		String type = response.path("vehicles.findAll { it.weight > 1500 }.find{ it.publicTransport == true }.type");
		System.out.println(type);
				
	}
	
	@Test(enabled = false)
	public void collectGsonGpath() {
		// Write GPath expression to transform type of All Private Transport Vehicles to uppercase.
		
		List<String> types = response.path("vehicles.findAll { it.publicTransport == false }.collect{ it.type.toUpperCase()}");
		System.out.println(types);
				
	}
	
	@Test(enabled = true)
	public void paramGsonGpath() {
		// Write GPath expression using Parameter.
		
		int weight = 1500;
		
		List<String> types = response.path("vehicles.findAll { it.weight < %s }.type", String.valueOf(weight));
		System.out.println(types);
				
	}
	
}
