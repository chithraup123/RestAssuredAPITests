package restAPI;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithMap {
	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";

		// create HashMap object for storing the request body
		HashMap<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("name", "Steves");
		mapObj.put("salary", 1000);
		
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(mapObj).post("/create");
		//String responseBody = response.getBody().asString();
		//System.out.println(responseBody);
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get("name"), "Steves");
	}

}
