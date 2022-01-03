package basics;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class RequestResponseSpecificationBasics {
	
	RequestSpecification httpRequest;
	ResponseSpecification httpResponse;
	
	@BeforeClass
	public void setUp() {
		
		httpRequest = new RequestSpecBuilder()
									.setBaseUri("http://localhost:5002/")
									.setBasePath("/api/members")
									.setAuth(RestAssured.basic("admin", "admin"))
									.addHeader("Accept", "application/json")
									.build();
		
		// set a default RequestSpecification
//		RestAssured.requestSpecification = httpRequest;
		
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		httpResponse = resBuilder.expectStatusCode(200)
								 .expectContentType(ContentType.JSON)
								 .expectHeader("X-Powered-By", "QA BOX LET'S TEST")
								 .build();
		
		given()
		.spec(httpRequest)
	.when()
		.get()
	.then()
		.statusCode(200)
		.header("Content-Type", equalTo("application/json; charset=utf-8"));
		
	}
	
	
	@Test(enabled = false)
	public void getMembers() {
		
//		RequestSpecification httpRequest = given()
//											.baseUri("http://localhost:5002/")
//											.basePath("/api/members")
//											.auth()
//											.basic("admin", "admin")
//											.header("Accept", "application/json");
		
		RequestSpecification httpRequest = new RequestSpecBuilder()
															.setBaseUri("http://localhost:5002/")
															.setBasePath("/api/members")
															.setAuth(RestAssured.basic("admin", "admin"))
															.addHeader("Accept", "application/json")
															.build();
																
		
//		httpRequest
//				.when()
//					.get()
//				.then()
//					.log()
//					.all()
//					.statusCode(200)
//					.header("Content-Type", equalTo("application/json; charset=utf-8"));
		
		given()
			.spec(httpRequest)
		.when()
			.get()
		.then()
			.statusCode(200)
			.header("Content-Type", equalTo("application/json; charset=utf-8"));
	}
	
	@Test(enabled = false)
	public void getFemaleMembers() {
		
		RequestSpecification httpRequest = given()
											.baseUri("http://localhost:5002/")
											.basePath("/api/members")
											.auth()
											.basic("admin", "admin")
											.header("Accept", ContentType.JSON)
											.queryParam("gender", "Female");
		httpRequest
				.when()
					.get()
				.then()
					.statusCode(200)
					.header("Content-Type", equalTo("application/json; charset=utf-8"));
	}
	
	@Test(enabled = false)
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

	@Test
	public void getFemaleMembersUsingSpecs() {
		given(httpRequest)
			.queryParam("gender", "Female")
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("size()", equalTo(2))
			.header("Access-Control-Allow-Origin", "*");
	}
}
