package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParams {
	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		//request.param("id", 1);
		Response response = request.get();

		// getBody and getStatusCode from response obj
		String responseBody = response.getBody().asString();
		int responseCode = response.getStatusCode();
		Assert.assertEquals(responseBody.contains("Pankaj"), true);
		Assert.assertEquals(responseCode, 200);

	}
}
