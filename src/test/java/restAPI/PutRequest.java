package restAPI;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";

		// create HashMap object for storing the request body
		HashMap<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("name", "Alex Marx");
		mapObj.put("salary", 1000);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(mapObj).put("/17");
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(response.getStatusCode(), 200);
		//JsonPath jsonPath = response.jsonPath();
		//Assert.assertEquals(jsonPath.get("name"), "Steves");
	}

}
