package com.kamala.test;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
/**
 * Using the Weather API for testing
 */
public class WeatherApiTest extends RestAssured {
	// The below test is for checking the frisco city weather. Passed the parameters to the body.
	@Test
	public void testAppISO() throws Exception {
		String jsonRes = get(
				"http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31")
						.getBody().asString();
		System.out.println("Test Case 1=" + jsonRes);
		get("http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31").then()
				.assertThat().body("name", equalTo("Frisco"));
	}
	// The below test for checking the description returned by Json
	@Test
	public void checkDescription() throws Exception {
		String jsonRes = get(
				"http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31")
						.getBody().asString();
		System.out.println("Test Case 2=" + jsonRes);
		get("http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31").then()
				.assertThat().body("weather[0].description", equalTo("clear sky"));
	}
	// The below test is for checking the sky
	@Test
	public void checkMainSky() throws Exception {
		String jsonRes = get(
				"http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31")
						.getBody().asString();
		System.out.println("Test Case 3=" + jsonRes);
		get("http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31").then()
				.assertThat().body("weather[0].main", equalTo("Clear"));
	}
	// The below test is for validating the status code.
	@Test
	public void checkStaus() throws Exception {
		get("http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31").then().assertThat().statusCode(200);
		System.out.println("Test Case 4=" + get("http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31").then().assertThat().statusCode(200));
		
	}
	
}
