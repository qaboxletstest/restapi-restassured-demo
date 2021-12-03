package basics;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteBasics {
	
/*	DELETE - DELETE is used to delete a resource
*/
	
	RequestSpecification httpRequest;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members/{id}";
		Header acceptHeader = new Header("Accept", "application/json");
		httpRequest =  RestAssured.given().auth().basic("admin", "admin").header(acceptHeader).pathParam("id", 4);
	}
	
	@Test(enabled = false)
	public void deleteMember() {

		Response response = httpRequest.delete();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = true)
	public void deleteMemberBDD() {

		Response response = httpRequest
										.when()
											.delete()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}

}
