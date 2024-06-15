package com.basicItems;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.TestData.PojoMethod;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;



public class dataPassing {

	
	//1. post request body using hashmap
	@Test(priority = 1)
	void hashMapMethod() {
		
		
		HashMap data = new HashMap();
		
		data.put("name", "testName");
		data.put("job", "leader");
		
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
		
		.then()
			.statusCode(201)
			.log().all();		
	}
	
	//2. post request body using org.json libary
	@Test(priority = 2)
	void josonORG() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "testName");
		data.put("job", "leader");
		
		
		given()
		.contentType("application/json")
		.body(data.toString())
	
	.when()
		.post("https://reqres.in/api/users")
	
	.then()
		.statusCode(201)
		.log().all();
		
	}
	
	//3. POJO method(Plain old java object)
	@Test(priority = 3)
	void pojoMethod() {
		
		PojoMethod data = new PojoMethod();
		
		data.setName("arun");
		data.setJob("qa");
		
		System.out.println("testttttttttttt:  " + data.getJob());
		
		given()
			.contentType("application/json")
			.body(data)

		
		.when()
			.post("https://reqres.in/api/users")

		
		.then()
			.statusCode(201)
			.log().all();
		

	}
	
	//4. external json file
	@Test(priority = 4)
	void externalJsonFile() throws FileNotFoundException {
		
		File f = new File(".\\body.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data =  new JSONObject(jt);
		
		given()
		
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")

		
		.then()
		
			.log().all();
		
	}

	
}
