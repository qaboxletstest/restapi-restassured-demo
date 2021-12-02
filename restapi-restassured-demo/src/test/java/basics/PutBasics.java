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

public class PutBasics {
/*	PUT - PUT is used to send data to a server to update/create a resource.
	PUT should only be used if you are replacing a resource in its entirety.
	The data sent to the server with PUT is stored in the request body of the HTTP request.
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
		RestAssured.basePath = "/api/members/{id}";
		Header acceptHeader = new Header("Accept", "application/json");
		Header contentTypeHeader = new Header("Content-Type", "application/json");
		List<Header> headers = new ArrayList<>();
		headers.add(acceptHeader);
		headers.add(contentTypeHeader);
		Headers allHeaders = new Headers(headers);
		httpRequest =  RestAssured.given().auth().basic("admin", "admin").headers(allHeaders).pathParam("id", 4);
	}
	
	@Test(enabled = false)
	public void updateMember() {
		String body = "{\r\n" + 
				"        \"name\": \"Ryan\",\r\n" + 
				"        \"gender\":\"Male\"\r\n" + 
				"}";
		httpRequest.body(body);
		Response response = httpRequest.put();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberBDD() {
		String body = "{\r\n" + 
				"        \"name\": \"Shawn\",\r\n" + 
				"        \"gender\":\"Male\"\r\n" + 
				"}";
		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberusingMapBDD() {
		Map<String, String> body = new HashMap<String, String>();
		body.put("name", "Jamie");
		body.put("gender", "Female");
		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonObjectBDD() {
		JsonObject body = new JsonObject();
		body.addProperty("name", "John");
		body.addProperty("gender", "Male");
		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonFileBDD() {
		File body = new File(System.getProperty("user.dir") + "/src/test/resources/Payloads/updateMember.json");
		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonFileStreamBDD() {
		// 
		InputStream body =  getClass().getClassLoader().getResourceAsStream("Payloads/updateMember.json");
		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonFileByteArrayBDD() throws IOException {
		// 
		byte[] body =  Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/Payloads/updateMember.json"));
		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingModelNTransientBDD() {
		// 
		Member body = new Member("Steve", "Male");
		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingModelNGsonExclusionBDD() {
		// 
		Member member = new Member("Steven", "Male");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String body = gson.toJson(member);

		Response response = httpRequest
										.body(body)
										.when()
											.put()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
}
