package chain;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteMember {
	
	@Test
	public void deleteMember(ITestContext context) {
		// Chain (Share Data with) APIs in same Test Tag
//		int id = (int) context.getAttribute("member_id");
		
		// Chain (Share Data with) APIs in same Suite Tag
		int id = (int) context.getSuite().getAttribute("member_id");
		
		given()
			.basePath("/api/members/{id}")
			.pathParam("id", id)
		.when()
			.delete()
		.then()
			.log()
			.all();
		
	}

}
