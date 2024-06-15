package com.basicItems;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Map;

public class Cookies {
	
	String cookie;
	
	@Test
	
	public void AllHeaders() {
		
		Response res = given()  // res store the the all respones. 
				
						.when()
							.get("https://www.google.com/");
		
								Headers headres = res.getHeaders();
		
									for(Header h : headres) {
			
											System.out.println(h + " : value =: " + h.getValue());
													
															}
		
	
			
	}
	
	@Test
	public void GetcookieValue() {
		
		Response res = given()
		
						.when()
							.get("https://www.google.com/");
		
								String cookie = res.getCookie("AEC");
								System.out.println("cookiessss : " + cookie);
		
									Map<String, String> allCookiesKey = res.getCookies();	// getCookies = for all cookies 
		
										for(String k : allCookiesKey.keySet()) {
			
												//	System.out.println("Cookie Key : " + k);
			
												String cookies = res.getCookie(k);
													System.out.println(k + ": cookiessss Value : = " + cookies);
			
			
																				}
		
	}

//	@Test
	public void cookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC",cookie) // just we compare cookies, but cookie alwys dffernt. 
			
			.log().all();
		 
	}
	
	
}
