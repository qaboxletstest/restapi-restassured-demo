package basics;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;

public class JSONSchemaValidation {
	
	RequestSpecification httpRequest;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members/{id}";
		Header acceptHeader = new Header("Accept", "application/json");
		httpRequest =  RestAssured.given().auth().basic("admin", "admin").header(acceptHeader).pathParam("id", 4);
	}
	
	@Test(enabled = true)
	public void singleMember() {

		httpRequest
				.when()
					.get()
				.then()
					.assertThat()
						.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONSchema/singleMember.json"));
				
	}

}
