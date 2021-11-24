package basics;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;


public class GetBasics {
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members";
		
	}

	
	@Test(enabled = false)
	public void getMember() {
		
		// Arrange
		
		// Request Line
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members";
		
		
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
		
		Headers headers = response.headers();
		for(Header hd : headers) {
			System.out.println("Key : "+ hd.getName() + " ---- " + "Value : " + hd.getValue());
		}
		
		// Body
		System.out.println(response.asPrettyString());
	}
	
	@Test(enabled = false)
	public void getfemaleMemberBDD() {
		
		// Arrange
		
		// Request Line
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members";
		
		
		// Headers
		RestAssured.authentication = RestAssured.basic("admin", "admin");

		Header header = new Header("Accept", "application/json");
		
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("gender", "Female");
		
		Response response = RestAssured.given()
						.auth()
						.basic("admin", "admin")
						.header(header)
//						.queryParam("gender", "Female")
						.queryParams(queryParams)
					.when()
						.get();
		
		
		// Body - NA
		
		// Act

		
		
		// Assert
		
		
		
		// Body
		System.out.println(response.asPrettyString());
	}
	
	@Test(enabled = false)
	public void getSpecificMemberBDD() {
		
		// Arrange
		
		// Request Line
		RestAssured.basePath += "/{id}";
		
		
		// Headers

		Header header = new Header("Accept", "application/json");
		
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("gender", "Female");
		
		Response response = RestAssured.given()
						.auth()
						.basic("admin", "admin")
						.header(header)
						.pathParam("id", 3)
						.log()
						.all()
					.when()
						.get();
		
		
		// Body - NA
		
		// Act

		
		
		// Assert
		
		
		
		// Body
		System.out.println(response.asPrettyString());
	}
	
	
	
	@Test(enabled = true)
	public void getStaticSpecificMemberBDD() {
		
		// Arrange
		
		// Request Line
		basePath += "/{id}";
		
		
		// Headers

		Header header = new Header("Accept", "application/json");
		
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("gender", "Female");
		
							given()
								.auth()
								.basic("admin", "admin")
								.header(header)
								.pathParam("id", 3)
								.log()
								.all()
							.when()
								.get()
							.then()
								.statusCode(200)
								.statusLine("HTTP/1.1 200 OK")
								.header("X-Powered-By", "QA BOX LET'S TEST")
//								.body(containsString("Lion"));
								.body("name", equalTo("Lioness"));
		
		
		// Body - NA
		
		// Act

		
		
		// Assert
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
