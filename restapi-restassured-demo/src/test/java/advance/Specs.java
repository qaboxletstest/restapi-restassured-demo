package advance;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {

	public static RequestSpecification buildRequestUsingRequestSpecification(String resourcePath) {

		baseURI = "http://localhost:5002";
		basePath = resourcePath;
		Header acceptHeader = new Header("Accept", "application/json");
		RequestSpecification httpRequest = given().auth().basic("admin", "admin").header(acceptHeader);
		return httpRequest;
	}

	public static RequestSpecification buildRequestUsingRequestSpecBuilder(String resourcePath) {

		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		RequestSpecification httpRequest = reqBuilder.setBaseUri("http://localhost:5002").setBasePath(resourcePath)
				.addHeader("Accept", "application/json").setAuth(RestAssured.basic("admin", "admin")).build();
		return httpRequest;
	}

	public static ResponseSpecification buildResponseUsingResponseSpecification (int statusCode) {

		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		ResponseSpecification httpResponse = resBuilder.expectStatusCode(statusCode)
														.expectContentType("application/json; charset=utf-8")
														.expectHeader("X-Powered-By", "QA BOX LET'S TEST")
														.build();
		return httpResponse;
	}

}
