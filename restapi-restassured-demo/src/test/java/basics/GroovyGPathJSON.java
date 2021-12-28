package basics;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class GroovyGPathJSON {
	@Test(enabled = true)
	public void jsonPathWildCard() {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/JSONDocs/Vehicles.json");
		
		JsonPath jsonPath = new JsonPath(file);
		
		// Model of car's tyres
		
//		System.out.println("Model of car's tyres ----> " + jsonPath.getList("car.tyres[*].model"));
//		System.out.println("Model of car's tyres ----> " + jsonPath.getList("car.tyres.findAll{it}.model"));
		
		// Model of bus's tyres
		
//		System.out.println("Model of bus's tyres ----> " + jsonPath.getList("bus.tyres[*].model"));
//		System.out.println("Model of bus's tyres ----> " + jsonPath.getList("bus.tyres.findAll{it}.model"));
		
	}
}
