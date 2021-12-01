package basics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class FileDownloadBasics {
	
	RequestSpecification httpRequest;
	Response response;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost:5002";
		RestAssured.basePath = "/api/download";
		Header acceptHeader = new Header("Accept", "application/json");
		httpRequest =  RestAssured.given().auth().basic("admin", "admin").header(acceptHeader);
		 response = httpRequest
							.queryParam("name", "Yey.jpg")
							.when()
								.get()
								.andReturn();
	}
	
	@Test(enabled = false)
	public void downloadFileUsingByteArray() throws IOException {
		
		// Store the file into Byte Array
		
		byte[] downloadedFileBytes = response.asByteArray();
		
		// Using FileOutputStream write the Byte Array into Physical File and close FileOutputStream Object
		
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/test/resources/Downloads/Test.jpg");
		
		fos.write(downloadedFileBytes);
		
		fos.close();
	}
	
	@Test(enabled = false)
	public void downloadFileUsingByteArrayTryWithResource() throws FileNotFoundException, IOException {
		
		// Store the file into Byte Array
		
		byte[] downloadedFileBytes = response.asByteArray();
		
		// Using FileOutputStream write the Byte Array into Physical File and close FileOutputStream Object
		
		try(FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/test/resources/Downloads/Test.jpg")){
			fos.write(downloadedFileBytes);
		}
		
		
	}
	
	@Test(enabled = false)
	public void downloadFileUsingInputStream() throws IOException {
		
		// Store the file into Input Stream
		
		InputStream downloadedFileIS = response.asInputStream();
		
		// Create the File Object
		File targetFile = new File(System.getProperty("user.dir") + "/src/test/resources/Downloads/Test.jpg");
		
		// Using Files copy the InputStream to target file path and close InputStream Object
		
		Files.copy(downloadedFileIS, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		downloadedFileIS.close();
	}
	
	@Test(enabled = true)
	public void downloadFileUsingInputStreamTryWithResource() throws IOException {
		
		// Store the file into Input Stream
		
		try(InputStream downloadedFileIS = response.asInputStream()){
			// Create the File Object
			File targetFile = new File(System.getProperty("user.dir") + "/src/test/resources/Downloads/Test.jpg");
			
			// Using Files copy the InputStream to target file path and close InputStream Object
			
			Files.copy(downloadedFileIS, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	@AfterMethod
	public void checkHeader() {
		response.then().header("Transfer-Encoding", equalTo("chunked"));
	}

}
