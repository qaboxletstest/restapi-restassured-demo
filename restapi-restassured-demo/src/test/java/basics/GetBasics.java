package basics;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class GetBasics {
	
/*	RestAssured - Class - Main Class
	AuthenticationScheme - Interface -  Set an authentication scheme that should be used for each request. By default no authentication is used.
	RequestSpecification - Interface - Allows you to specify how the request will look like. Specify a default request specification that will be sent with each request
	Header - Class - Create a new header with the given name and value.
	Response - Interface - The response of a request made by REST Assured.
	ValidatableResponse - Class - Returns a validatable response that's lets you validate the response.
	Matchers - Class - Creates a matcher that matches if the examined object matches ALL of the specified matchers.
*/
	
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members";
	}
	
	@Test(enabled=false)
	public void getMember() {
		
		// Arrange
		
		// Request Line
//		RestAssured.baseURI = "http://localhost:5002";
//		RestAssured.basePath = "/api/members";
		
		
		// Headers
		RestAssured.authentication = RestAssured.basic("admin", "admin");
		RequestSpecification httpRequest =  RestAssured.given();
		Header header = new Header("Accept", "application/json");
		httpRequest.header(header);
		
		// Body - NA
		
		// Act
		Response response = httpRequest.request(Method.GET);
		
		// Assert
		
		// Status Line
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		// Headers
		Headers headers = response.getHeaders();
		for(Header hdr : headers) {
			System.out.println("Key : " + hdr.getName() + " ---- " + "Value : " + hdr.getValue());
		}
		
		// Body
		System.out.println(response.asPrettyString());
		
		
	}
	
	@Test(enabled=false)
	public void getMemberBDD() {
		
		// Arrange & Act
		
//		RestAssured.baseURI = "http://localhost:5002";
//		RestAssured.basePath = "/api/members";
//		RestAssured.authentication = RestAssured.basic("admin", "admin");
//		Header header = new Header("Accept", "application/xml");
		
		Response response = RestAssured.given()
												.header("Accept", "application/xml")
												.auth().basic("admin", "admin")
												.log().all()
		// Act
										 .when()
												.get()
												.andReturn();
		
		// Assert
		System.out.println(response.asPrettyString());
		
		
	}
	
	@Test(enabled=false)
	public void getFemaleMemberBDD() {
		
		// Arrange & Act
		
//		RestAssured.baseURI = "http://localhost:5002";
//		RestAssured.basePath = "/api/members";
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("gender", "Female");
		
		Response response = RestAssured.given()
												.header("Accept", "application/json")
//												.queryParam("gender", "Female")
												.queryParams(queryParams)
												.auth().basic("admin", "admin")
												.log().all()
	    // Act
										 .when()
												.get()
												.andReturn();
		
		// Assert
		System.out.println(response.asPrettyString());
		
		
	}
	
	@Test(enabled=false)
	public void getSpecificMemberBDD() {
		
		// Arrange & Act
		
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath += "/{id}";
		
		Response response = RestAssured.given()
												.header("Accept", "application/json")
												.pathParam("id", 3)
												.auth().basic("admin", "admin")
												.log().all()
		// Act
										 .when()
												.get()
												.andReturn();
		
		// Assert
		System.out.println(response.asPrettyString());
		
		
	}
	
	@Test(enabled=true)
	public void getSpecificMemberBDDWithStaticImport() {
		
		// Arrange & Act
		
		baseURI = "http://localhost:5002";
		basePath += "/{id}";
		
										given()
												.header("Accept", "application/json")
												.pathParam("id", 3)
												.auth().basic("admin", "admin")
		// Act
										 .when()
												.get()
		
		
		
		// Assert
										.then()
												.log().all()
												.statusCode(200)
												.statusLine("HTTP/1.1 200 OK")
												.header("X-Powered-By", "QA BOX LET'S TEST")
//												.body(containsString("Lion"));
												.body("name", equalTo("Lioness"));
		
		
	}
	

}
