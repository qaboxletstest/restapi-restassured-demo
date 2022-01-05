package chain;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.http.ContentType;
import models.Member;

public class UpdateMember {

	@Test
	public void updateMember(ITestContext context) {
		
		// Chain (Share Data with) APIs in same Test Tag
//		int id = (int) context.getAttribute("member_id");
		
		// Chain (Share Data with) APIs in same Suite Tag
		int id = (int) context.getSuite().getAttribute("member_id");
		
		Member member = new Member("Ramon", "Male");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String body = gson.toJson(member);
		
		given()
			.header("Content-Type", ContentType.JSON)
			.basePath("/api/members/{id}")
			.pathParam("id", id)
			.body(body)
		.when()
			.put()
		.then()
			.log()
			.all();
		
	}
	
}
