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
import models.Author;
import models.Book;

public class DeserializeComplexJsonResponse {
	
	RequestSpecification httpRequest;
	Header acceptHeader;
	
	@BeforeMethod
	public void setUp() {
		baseURI = "http://localhost:5002";
		basePath = "/api/authors/";
		acceptHeader = new Header("Accept", "application/json");
	}
	
	@Test(enabled = true)
	public void deserializeSingleAuthor() {
		
		basePath += "{id}";
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader).pathParam("id", 2);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		Author author = response.getBody().as(Author.class);
		System.out.println(author.toString());
	}
	
	@Test(enabled = false)
	public void deserializeSingleAuthorUsingTypeRef() {
		
		// TypeRef - Used to specify generic type information when de-serializing a Response.
		
		basePath += "{id}";
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader).pathParam("id", 2);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		Author author = response.getBody().as(new TypeRef<Author>() {});
		System.out.println(author.toString());
	}
	
	@Test(enabled = false)
	public void deserializeListOfAuthors() {
		
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		Author[] authors = response.getBody().as(Author[].class);
		
		for(Author a: authors) {
			System.out.println(a.toString());
		}
	}
	
	@Test(enabled = false)
	public void deserializeListOfAuthorsUsingTypeRef() {
		
		httpRequest =  given().auth().basic("admin", "admin").header(acceptHeader);

		Response response = httpRequest
										.when()
											.get()
											.andReturn();
		List<Author> authors = response.getBody().as(new TypeRef<List<Author>>() {});
		for(Author a: authors) {
			System.out.println(a.toString());
		}
	}

}
