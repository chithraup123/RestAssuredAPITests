package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		String responseBody = response.getBody().asString();
		int responseCode = response.getStatusCode();
		// System.out.println(responseBody);
		Assert.assertEquals(responseCode, 200);

		// using json path
		JsonPath jsonPath = response.jsonPath();
		List<String> names = jsonPath.get("name");		
		Assert.assertEquals(names.get(0), "Pankaj");
		
		for (String name : names) {
			System.out.println("Employee Name: " + name);
		}
		

		//get header from the response
		String headerContentType = response.getHeader("Content-Type");
		System.out.println("Content Type is " + headerContentType);

	}
}
