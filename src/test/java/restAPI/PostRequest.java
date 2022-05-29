package restAPI;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON)
				.body("{\r\n" + "    \"name\": \"Samuel\",\r\n" + "    \"salary\": \"67000\"\r\n" + "}")
				.post("/create");
		String responseBody = response.getBody().asString();
		
		JsonPath jPath = response.jsonPath();
		String id = jPath.get("id");
		System.out.println(responseBody);

	}

}
