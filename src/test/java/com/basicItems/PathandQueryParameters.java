package com.basicItems;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class PathandQueryParameters {

	Response a;
	// https://reqres.in/api/users?page=2&id=5
	
		@Test
		public void testPathandQueryParameters(){
		// a =	
		given()
				.pathParam("mypath","users") //pathparameter 'users' is one pathparameter.
				.queryParam("page", 2) // this is query parameters
				.queryParam("id", 5)
			.when()
				.get("https://reqres.in/api/{mypath}")
		 
		//System.out.println("res : " + a.asString());
			
			.then()
				.statusCode(200)
				.log().all();
		
	}
}
