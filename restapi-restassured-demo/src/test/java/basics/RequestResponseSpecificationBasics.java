package basics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/*
 * Problem at hand - Duplicate Code in both Request and Response Objects
 * RequestSpecification - is an interface that allows you to specify how the request will look like.
 * RequestSpecBuilder - is a class in Rest Assured, which contains a lot of methods to construct a RequestSpecification.
 * ResponseSpecification - Allows you to specify how the expected response must look like in order for a test to pass.
 * ResponseSpecBuilder - is a class in Rest Assured, which contains a lot of methods to construct a ResponseSpecification.
 */

public class RequestResponseSpecificationBasics {
	
	RequestSpecification httpRequest;
	ResponseSpecification httpResponse;
	
	@BeforeClass
	public void setup() {
		// STEP 1. Create RequestSpecification Reference - Using RequestSpecification Interface
		
//		httpRequest = given()
//						.baseUri("http://localhost:5002/")
//						.basePath("/api/members")
//						.auth()
//						.basic("admin", "admin")
//						.header("Accept", "application/json");
		
		// OR
		// STEP 1. Create RequestSpecification Reference - Using RequestSpecBuilder Class
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		httpRequest = reqBuilder
							.setBaseUri("http://localhost:5002/")
							.setBasePath("/api/members")
							.setAuth(RestAssured.basic("admin", "admin"))
							.addHeader("Accept", "application/json")
							.build();
		
//		httpRequest.get() - Cannot get property ‘assertionClosure’ on null object” Exception
//		given().spec(httpRequest).get() -- PASS
//		given(httpRequest).get() -- PASS
				
		
		// STEP 2. Create ResponseSpecification Reference
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		httpResponse = resBuilder
								.expectStatusCode(200)
								.expectHeader("Content-Type", equalTo("application/json; charset=utf-8"))
								.build();
		
//		RestAssured.requestSpecification = httpRequest;
//		RestAssured.responseSpecification = httpResponse;
	}
	
	
	@Test(enabled = false)
	public void getMembers() {
		
	given()
		.baseUri("http://localhost:5002/")
		.basePath("/api/members")
		.auth()
		.basic("admin", "admin")
		.header("Accept", "application/json")
	.when()
		.get()
	.then()
		.statusCode(200)
		.header("Content-Type", equalTo("application/json; charset=utf-8"));
		
	}
	
	@Test(enabled = false)
	public void getFemaleMembers() {
		
		given()
			.baseUri("http://localhost:5002/")
			.basePath("/api/members")
			.auth()
			.basic("admin", "admin")
			.header("Accept", "application/json")
			.queryParam("gender", "Female")
		.when()
			.get()
		.then()
			.statusCode(200)
			.header("Content-Type", equalTo("application/json; charset=utf-8"));
	}
	
	@Test(enabled = true)
	public void getMembersUsingSpecs() {
		given()
			.spec(httpRequest)
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("size()", greaterThan(2))
			.header("Access-Control-Allow-Origin", "*");
	}

	@Test(enabled = true)
	public void getFemaleMembersUsingSpecs() {
		given(httpRequest)
			.queryParam("gender", "Female")
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("size()", equalTo(4))
			.header("Access-Control-Allow-Origin", "*");
	}
}
