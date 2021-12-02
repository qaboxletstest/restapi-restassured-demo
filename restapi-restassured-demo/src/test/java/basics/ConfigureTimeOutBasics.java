package basics;

//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.HttpClientBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConfigureTimeOutBasics {
	
	RequestSpecification httpRequest;
	
	@BeforeMethod
	public void setUp() {
		
		
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/lag";
		Header acceptHeader = new Header("Accept", "application/json");
		httpRequest = RestAssured.given().auth().basic("admin", "admin").header(acceptHeader).queryParam("delay", 3000);
		HttpClientConfig httpClientConfig = HttpClientConfig
												.httpClientConfig()
													.setParam("http.socket.timeout",5000)
													.setParam("http.connection.timeout", 5000);
		RestAssured.config = RestAssuredConfig.config()
													.httpClient(httpClientConfig);
	}
	
	@Test
	public void configureTimeOut() {
		
		Response response = httpRequest.when().get().andReturn();
		
		System.out.println(response.asPrettyString());
		
	}
	
	public void knownIssue() {
		// Restassured doesn't support uses of external httpClient. You currently have to produce an AbstractHttpClient
		
//		RequestConfig requestConfig = RequestConfig.custom()
//													    .setConnectTimeout(5000)
//													    .setConnectionRequestTimeout(5000)
//													    .setSocketTimeout(5000)
//													    .build();
//
//			HttpClientConfig httpClientFactory = HttpClientConfig.httpClientConfig()
//																	    .httpClientFactory(() -> HttpClientBuilder.create()
//																	        .setDefaultRequestConfig(requestConfig)
//																	        .build());
//
//			RestAssured.config = RestAssured
//										    .config()
//										    .httpClient(httpClientFactory);
	}

}
