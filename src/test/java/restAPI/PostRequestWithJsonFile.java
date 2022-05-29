package restAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithJsonFile {
	@Test
	public void test1() throws IOException {
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		byte[] jsonBytes = Files.readAllBytes(Paths.get("data.json"));

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jsonBytes).post("/create");
		// String responseBody = response.getBody().asString();
		// System.out.println(responseBody);
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get("name"), "Harris Joe");
	}
}
