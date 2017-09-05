package com.kamala.test;

import static org.hamcrest.Matchers.equalTo;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

/**
 * Using the groupKT API for testing
 */
public class CountryApiTest extends RestAssured {
	// Test Case 1 The below test is checking the country code US and response
	 @Test
	public void testISO() throws Exception {

		String jsonRes = get("http://services.groupkt.com/country/get/iso2code/US").getBody().asString();
		System.out.println("Test Case 1=" + jsonRes);
		// assertThat(jsonRes, "RestResponse.result.name", equalTo("India"));
		get("http://services.groupkt.com/country/get/iso2code/IN").then().assertThat().body("RestResponse.result.name",
				equalTo("India"));
	}

	// Test Case 2 the below test case for checking the all states returned by
	// response.
	 @Test
	public void testUSA() throws Exception {
		get("http://services.groupkt.com/country/get/all").then().assertThat().body("RestResponse.result[235].name",
				equalTo("United States of America"));
		System.out.println("Test Case 2 = " + get("http://services.groupkt.com/country/get/all").getBody().asString());
	}

	// Test Case 3 The below test is for checking the status code
	 @Test
	public void testReturnCode() throws Exception {
		get("http://services.groupkt.com/country/get/iso2code/IN").then().assertThat().statusCode(200);
		System.out.println("Test Case 3: STATUS = " + get("http://services.groupkt.com/country/get/iso2code/IN").then()
				.assertThat().statusCode(200).toString());
	}

	// Test case 4 Negtive test to check the invalid country
	 @Test
	public void testUK() throws Exception {
		get("http://services.groupkt.com/country/get/all").then().assertThat().body("RestResponse.result[234].name",
				equalTo("United Kingdom of Great Britain"));
		// System.out.println("Test Case 4:="
		// +get("http://services.groupkt.com/country/get/all").getBody().asString());
	}

	// Test case 5 : validating the http headers
	 @Test
	public void httpscodevalidation() throws Exception {
		get("http://services.groupkt.com/country/").then().assertThat().statusCode(404);
		System.out.println("Test Case 5:  validating the http headers = "
				+ get("http://services.groupkt.com/country/").then().assertThat().statusCode(404).toString());
	}

	// Test case 6:The below test for validating the no matching country.
	 @Test
	public void noMatchcountyFound() throws Exception {
		String jsonRes1 = get("http://services.groupkt.com/country/get/iso2code/XX").getBody().asString();
		System.out.println("Test Case67=" + jsonRes1);
	}

	// Test case 7:The below test case for passing the parameters with text
	// search by un
	 @Test
	public void parametersTest() throws Exception {
		get("http://services.groupkt.com/country/search?text=un").getBody().asString();
		System.out.println(
				"Test Case 7=" + get("http://services.groupkt.com/country/search?text=un").getBody().asString());
	}

	// Test case 8:The below test case for checking the length
	 @Test
	public void checkLength() throws Exception {
		get("http://services.groupkt.com/country/search?text=un").then().assertThat()
				.body("RestResponse.result.alpha3_code*.length().sum()", equalTo(36));
		System.out.println(
				"Test Case 8=" + get("http://services.groupkt.com/country/search?text=un").getBody().asString());
	}

	// Test case 9:The below test case for state
	@Test
	public void searchByState() throws Exception {
		String response = get("http://services.groupkt.com/country/search?text=un").asString();
		// System.out.println("RES= " + response);
		List<String> ls = get("http://services.groupkt.com/country/search?text=un").jsonPath()
				.getList("RestResponse.result.name");
		//System.out.println("ELE = " + ls);
		System.out.println("ELE = " + ls.size());
		for(int i = 0; i < ls.size(); i++) {
			if (ls.get(i).startsWith("United")) {
				System.out.println("Found place starts with United");
			}
		}
		
	}

	// Test case 10
	 @Test
	public void restTest() throws Exception {

		// Call GET and compare the response.
		given().accept(ContentType.JSON).when().get(new URI("http://services.groupkt.com/country/get/iso2code/US"))
				.then().assertThat().statusCode(HttpStatus.SC_OK);
		// Test with Response
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		String body = given().headers(headers).when()
				.get(new URI("http://services.groupkt.com/country/get/iso2code/US")).thenReturn().asString();
		System.out.println("Test Case 9=" + body);
		// Verify the content..
		JsonPath json = new JsonPath(body);
		Assert.assertEquals("United States of America", json.getString("RestResponse.result.name"));
		Assert.assertEquals("US", json.getString("RestResponse.result.alpha2_code"));
		Assert.assertEquals("USA", json.getString("RestResponse.result.alpha3_code"));
		Assert.assertTrue(json.getList("RestResponse.messages")
				.contains("More webservices are available at http://www.groupkt.com/post/f2129b88/services.htm"));

	}

	// Test case 11
	@Test
	public void restTestall() throws Exception {
		when().get("http://services.groupkt.com/country/get/iso2code/US");
		expect().body("RestResponse.result.alpha2_code", Matchers.equalTo("US"), "RestResponse.result.alpha3_code",
				Matchers.equalTo("USA"), "RestResponse.result.name", Matchers.equalTo("United States of America"));
		System.out.println("Test Case 11=" + get("http://services.groupkt.com/country/get/iso2code/US"));
	}

}
