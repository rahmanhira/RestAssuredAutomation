package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class TestExamples {
	
	

	@Test
	public void test1() {
		
		Response response = get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody());
		System.out.println(response.statusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	public void testGet(){
		
		 baseURI = "https://reqres.in/api";
		 
		 given().
		 get("/users?page=2").
		 then().
		 statusCode(200).body("data[4].first_name", equalTo("George"));
		 
		
	}
	
	@Test
	public void testPost() {
		
		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("name", "Mustafizur");
		//map.put("job", "QA Engineer");
		
		//System.out.println(map);
		
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Mustafizur");
		request.put("job", "QA Engineer");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		 body(request.toJSONString()).
		when().
		 post("/users").
		then().
		 statusCode(201).log().all(); //log().all() will give the Id of created json 
		
		
	}
	

}
