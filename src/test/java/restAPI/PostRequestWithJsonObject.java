package restAPI;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithJsonObject {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";

		//create JSON object for storing the request body
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Peter");
		jsonObj.put("salary", 1000);
		// jsonObj.put("id", "")

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jsonObj.toString())
				.post("/create");
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		System.out.println(response.getStatusCode());
		// System.out.println(respo);
	}

}
