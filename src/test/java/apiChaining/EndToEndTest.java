package apiChaining;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {

	Response response;
	String baseURI = "http://localhost:3000/employees";

	ResponseBody reponseBody;

	@Test
	public void test1() {

		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);

		response = PostMethod("Joshua", 12000);
		Assert.assertEquals(response.getStatusCode(), 201);
		reponseBody = response.getBody();
		System.out.println(reponseBody.asString());

		JsonPath jsonPath = response.jsonPath();
		int respId = jsonPath.get("id");
		System.out.println("**************" + respId);
		response = PutMethod(respId, "Joshua Peter", 15000);
		reponseBody = response.getBody();
		System.out.println(reponseBody.asString());
		// Assert.assertEquals(jsonPath.get("salary"), 15000);
		Assert.assertEquals(jsonPath.get("name"), "Joshua");

		response = DeleteMethod(respId);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	public Response GetMethodAll() {

		RestAssured.baseURI = baseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).get();
		return response;
	}

	public Response PostMethod(String name, int salary) {

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", name);
		jsonObj.put("salary", salary);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jsonObj.toString())
				.post("/create");
		return response;
	}

	public Response PutMethod(int id, String name, int salary) {
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("name", name);
		jsonObj.put("salary", salary);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jsonObj.toString())
				.put("/" + id);
		return response;

	}

	public Response DeleteMethod(int empId) {

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).delete("/" + empId);
		return response;
	}
}
