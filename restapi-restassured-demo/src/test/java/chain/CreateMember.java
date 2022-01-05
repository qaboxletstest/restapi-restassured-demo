package chain;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Member;

public class CreateMember extends BaseTest {
	
	RequestSpecification httpRequest;

	@Test
	public void createMember(ITestContext context) {
		
		Member member = new Member("Steven", "Male");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String body = gson.toJson(member);
		
		int id = given()
					.header("Content-Type", ContentType.JSON)
					.body(body)
				.when()
					.post()
					.jsonPath()
					.getInt("id");
		
		// Chain (Share Data with) APIs in same Test Tag
//		context.setAttribute("member_id", id);
		
		// Chain (Share Data with) APIs in same Suite Tag
		context.getSuite().setAttribute("member_id", id);
		
	}
	
}
