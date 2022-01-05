package chain;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetMember extends BaseTest {

	@Test
	public void getMember(ITestContext context) {
		
		// Chain (Share Data with) APIs in same Test Tag
//		int id = (int) context.getAttribute("member_id");
		
		// Chain (Share Data with) APIs in same Suite Tag	
		int id = (int) context.getSuite().getAttribute("member_id");
		
		given()
			.basePath("/api/members/{id}")
			.pathParam("id", id)
		.when()
			.get()
		.then()
			.log()
			.all();
		
	}
	
}
