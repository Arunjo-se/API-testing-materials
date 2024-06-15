package com.p1;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.TestData.PojoMethod;

import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matcher.*;

import org.json.JSONObject;

public class ParsingJSONresponseData {
	
	//@Test
	public void testJSONResponse() {
		
        SoftAssert softAssert = new SoftAssert();

		
		PojoMethod data = new PojoMethod();
		data.setIdNum(5);
		data.setPageNum(2);
		data.setFirst_name("Charles");
		
		
	Response res = 	
	given()
		.contentType("application/json")	
		.pathParam("mypath","users") 
		.queryParam("page", data.getIdNum()) 
		.queryParam("id", data.getIdNum())
		
	.when()
		.get("https://reqres.in/api/{mypath}");
	
		
	Assert.assertEquals(res.getStatusCode(),200);
	
	String a = res.jsonPath().get("data.email").toString(); //  to store the email value in 'a'
	System.out.println(a);
	
	// below checking firstname, 
	//Assert.assertEquals(res.jsonPath().get("data.first_name").toString(), data.getFirst_name());  //hard assertion
	softAssert.assertEquals(res.jsonPath().get("data.first_name").toString(), data.getFirst_name());  //soft assertion
	
	}
	
	
	//{
//  "data": {
//      "id": 5,
//      "email": "charles.morris@reqres.in",
//      "first_name": "Charles",
//      "last_name": "Morris",
//      "avatar": "https://reqres.in/img/faces/5-image.jpg"
//  },
//  "support": {
//      "url": "https://reqres.in/#support-heading",
//      "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//  }
//}
	
	
	@Test
	public void dataCheck() {  // take array value 
		
		boolean status = false;
		
		Response Res =
		given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("https://reqres.in/api/users");
			
		//.then().log().all();
		
        String responseBody = Res.getBody().asString();   // Get the response body as a string
        JSONObject jo = new JSONObject(responseBody);  // Convert the response body to a JSON object
				
			
			for(int i = 0; i < jo.getJSONArray("data").length(); i++) {
				
				String firstName = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();	
				System.out.println(i +" first name = " + firstName );
				
				if(firstName.equals("Eve")) {
					
					status = true;
					break;
				}
				
			}

			Assert.assertEquals(status, true);

	}

}


