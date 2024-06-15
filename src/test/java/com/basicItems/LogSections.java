package com.basicItems;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class LogSections {

	@Test
	public void logg() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users")
		
		.then()
			
		//.log().body()
		//.log().cookies()
		//.log().headers();
		 .log().all(); // print all details 

	}
	
	
}
