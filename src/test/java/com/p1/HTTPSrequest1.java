package com.p1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPSrequest1 {
	
	int id;
	
	@Test(priority = 1)
	void getUser() {
			
		given()
		
		.when()
        	.get("https://reqres.in/api/users?page=2")

        .then()
        	.statusCode(200)
        	.body("page", equalTo(2))
        	.body("per_page", equalTo(6))
        	.body("data[0].email", equalTo("michael.lawson@reqres.in"))
        	.log().all();
	}
	
	@Test(priority = 2)
	void CreateUser() {
		
		HashMap data = new HashMap();
		
		data.put("name", "testName");
		data.put("job", "leader");
		
		
	id = given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			System.out.println("id is : " + id);
		
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test(priority = 3, dependsOnMethods = {"CreateUser"})
	void updateUser() {
		
		HashMap data1 = new HashMap();
		
		data1.put("name", "testName2");
		data1.put("job", "leader2");
		
		given()
			.contentType("application/json")
			.body(data1)
		.when()
			.put("https://reqres.in/api/users/" + id)
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority = 4, dependsOnMethods = {"updateUser"})
	void deleteUser() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/" + id)
			
		.then()
			.statusCode(204)
			.log().all();
	}
}
