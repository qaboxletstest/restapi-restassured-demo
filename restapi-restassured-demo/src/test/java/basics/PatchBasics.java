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
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatchBasics {
/*	PATCH - PATCH is used to send data to a server to update a resource.
	PATCH is used when we want to update specific data related to that id.
	The data sent to the server with PATCH is stored in the request body of the HTTP request.
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
		String body = "{\"name\":\"Alee\"}";
		httpRequest.body(body);
		Response response = httpRequest.patch();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberBDD() {
		String body = "{\"name\":\"Susain\"}";
		Response response = httpRequest
										.body(body)
										.when()
											.patch()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberusingMapBDD() {
		Map<String, String> body = new HashMap<String, String>();
		body.put("name", "Jamie");
		Response response = httpRequest
										.body(body)
										.when()
											.patch()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonObjectBDD() {
		JsonObject body = new JsonObject();
		body.addProperty("name", "Julia");
		Response response = httpRequest
										.body(body)
										.when()
											.patch()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonFileBDD() {
		File body = new File(System.getProperty("user.dir") + "/src/test/resources/Payloads/patchMember.json");
		Response response = httpRequest
										.body(body)
										.when()
											.patch()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonFileStreamBDD() {
		// 
		InputStream body =  getClass().getClassLoader().getResourceAsStream("Payloads/patchMember.json");
		Response response = httpRequest
										.body(body)
										.when()
											.patch()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void updateMemberUsingJsonFileByteArrayBDD() throws IOException {
		// 
		byte[] body =  Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/Payloads/patchMember.json"));
		Response response = httpRequest
										.body(body)
										.when()
											.patch()
											.andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
}
