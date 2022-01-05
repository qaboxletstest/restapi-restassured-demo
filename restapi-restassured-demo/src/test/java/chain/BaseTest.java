package chain;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTest {

	@BeforeClass
	public void setup() {
		
		requestSpecification = new RequestSpecBuilder()
													.setBaseUri("http://localhost:5002")
													.setBasePath("/api/members")
													.addHeader("Accept", "application/json")
													.setAuth(basic("admin", "admin"))
													.build();

		responseSpecification = new ResponseSpecBuilder()
													.expectContentType("application/json; charset=utf-8")
													.build();
		
	}
}
