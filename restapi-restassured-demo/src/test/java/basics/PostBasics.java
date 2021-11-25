package basics;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Member;

public class PostBasics {
	
/*	POST - POST is used to send data to a server to create/update a resource.
	The data sent to the server with POST is stored in the request body of the HTTP request.
	Client has to inform server about the type of request body using Content-Type Header; eg application/json for json payload
 	Request Object
		1. Request Line - URL, PORT and PATH Param
		2. Headers - Content-Type is must
		3. Body
*/
	
	RequestSpecification httpRequest;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/members";
		Header acceptHeader = new Header("Accept", "application/json");
		Header contentTypeHeader = new Header("Content-Type", "application/json");
		List<Header> headers = new ArrayList<>();
		headers.add(acceptHeader);
		headers.add(contentTypeHeader);
		Headers allHeaders = new Headers(headers);
		httpRequest =  RestAssured.given().auth().basic("admin", "admin").headers(allHeaders);
	}
	
	@Test(enabled = false)
	public void createMember() {
		String body = "{\r\n" + 
				"        \"name\": \"Ryan\",\r\n" + 
				"        \"gender\":\"Male\"\r\n" + 
				"}";
		httpRequest.body(body);
		Response response = httpRequest.post();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void createMemberBDD() {
		String body = "{\r\n" + 
				"        \"name\": \"Ryan\",\r\n" + 
				"        \"gender\":\"Male\"\r\n" + 
				"}";
		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void createMemberusingMapBDD() {
		Map<String, String> body = new HashMap<String, String>();
		body.put("name", "Julia");
		body.put("gender", "Female");
		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void createMemberUsingJsonObjectBDD() {
		JsonObject body = new JsonObject();
		body.addProperty("name", "John");
		body.addProperty("gender", "Male");
		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void createMemberUsingJsonFileBDD() {
		File body = new File(System.getProperty("user.dir") + "/src/test/resources/Payloads/inputMember.json");
		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void createMemberUsingJsonFileStreamBDD() {
		// 
		InputStream body =  getClass().getClassLoader().getResourceAsStream("Payloads/inputMember.json");
		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = true)
	public void createMemberUsingJsonFileByteArrayBDD() throws IOException {
		// 
		byte[] body =  Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/Payloads/inputMember.json"));
		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void createMemberUsingModelNTransientBDD() {
		// 
		Member body = new Member("Steve", "Male");
		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void createMemberUsingModelNGsonExclusionBDD() {
		// 
		Member member = new Member("Steven", "Male");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String body = gson.toJson(member);

		Response response = httpRequest
										.body(body)
										.when()
											.post()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
}
