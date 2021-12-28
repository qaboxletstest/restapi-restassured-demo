package basics;

import java.io.File;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Member;

public class JSONPathBasics {
	
	Response response;
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members/{id}";
		Header acceptHeader = new Header("Accept", "application/json");
		RequestSpecification httpRequest =  RestAssured
												.given()
													.auth()
														.basic("admin", "admin")
													.header(acceptHeader)
													.pathParam("id", 4);
		response = httpRequest
							.when()
								.get()
								.andReturn();
	}
	
	@Test(enabled=false)
	public void getResponseJSONPathUsingJSONPATH() {
		
		
		// JSONPath
		
		JsonPath jsonPath = response.getBody().jsonPath();
//		JsonPath jsonPath = response.jsonPath();
		
		// Get a Path
		
		System.out.println(jsonPath.getInt("id"));
		Object obj = jsonPath.get("$");
		System.out.println(obj);
		
		// Deserialize
		
		Member member = jsonPath.getObject("$", Member.class);
		System.out.println(member.toString());
		
	}
	
	@Test(enabled=false)
	public void getResponseJSONPathUsingPATH() {		
		
		// Using PATH -  call the .path() method on the response, and specify the JSONPath to the attributes

		int id = response.path("id");
		String name = response.path("name");
		String gender = response.path("gender");
		System.out.println(id + " - " + name + " - " + gender);
		
	}
	
	@Test(enabled=false)
	public void getResponseJSONPathUsingResponseAsString() {		
		
		// Grab the whole response as a string and then use JsonPath to extract the attributes from that string
		String responseAsString = response.asString();
		int id = JsonPath.from(responseAsString).getInt("id");
		String name = JsonPath.from(responseAsString).get("name");
		String gender = JsonPath.from(responseAsString).get("gender");
		System.out.println(id + " - " + name + " - " + gender);
		
	}
	
	@Test(enabled=false)
	public void getResponseJSONPathInlineAssertion() {		
		
		// Grab the whole response as a string and then use JsonPath to extract the attributes from that string
		response
				.then()
					.assertThat()
					.body("id", equalTo(4))
					.and()
					.body("name", equalTo("Shawn"))
					.and()
					.body("gender", equalTo("Male"));
		
	}
	
	@Test(enabled = false)
	public void jsonPathDictionaryDotSquareBracketNotations() {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/JSONDocs/JSONDictionary.json");
		
		JsonPath jsonPath = new JsonPath(file);
		
		// Dot Notation
		
		System.out.println("DOT NOTATION ----> " + jsonPath.getString("Person1.gender"));
		
		// Bracket Notation
		
		System.out.println("SQUARE BRACKET NOTATION ----> " + jsonPath.getString("Person1['gender']"));
		
	}
	
	@Test(enabled = false)
	public void jsonPathJSONArray() {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/JSONDocs/JSONArray.json");
		
		JsonPath jsonPath = new JsonPath(file);
		
		// LIST - ALL ITEMS
		
		System.out.println("All Items (LIST) ----> " + jsonPath.getList("$"));
		
		// INDEX - FIRST ITEM
		
		System.out.println("First Item ----> " + jsonPath.getList("[0]"));
		
		// INDEX - NAME PROPERTY OF FIRST ITEM
		
		System.out.println("First Item's Name ----> " + jsonPath.getString("[0].NAME"));
	}
	
	
}
