package basics;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Member;
import models.MemberNotFoundFailure;

public class DeserializeSimpleJsonResponse {
	
	RequestSpecification httpRequest;
	Header acceptHeader;
	
	@BeforeMethod
	public void setUp() {
		baseURI = "http://localhost:5002";
		basePath = "/api/members/";
		acceptHeader = new Header("Accept", "application/json");
	}
	
	@Test(enabled = false)
	public void deserializeSingleMember() {
		
		basePath += "{id}";
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader).pathParam("id", 4);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		Member member = response.getBody().as(Member.class);
		System.out.println(member.toString());
	}
	
	@Test(enabled = false)
	public void deserializeSingleMemberUsingTypeRef() {
		
		// TypeRef - Used to specify generic type information when de-serializing a Response.
		
		basePath += "{id}";
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader).pathParam("id", 4);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		Member member = response.getBody().as(new TypeRef<Member>() {});
		System.out.println(member.toString());
	}
	
	@Test(enabled = false)
	public void deserializeListOfMembers() {
		
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		Member[] members = response.getBody().as(Member[].class);
		
		for(Member m: members) {
			System.out.println(m.toString());
		}
	}
	
	@Test(enabled = true)
	public void deserializeListOfMembersUsingTypeRef() {
		
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		List<Member> members = response.getBody().as(new TypeRef<List<Member>>() {});
		for(Member m: members) {
			System.out.println(m.toString());
		}
	}
	
	@Test(enabled = false)
	public void deserializeSingleMemberNotFoundFailure() {
		
		basePath += "{id}";
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader).pathParam("id", 40);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		MemberNotFoundFailure memberNotFoundFailure = response.getBody().as(MemberNotFoundFailure.class);
		System.out.println(memberNotFoundFailure.toString());
	}

}
