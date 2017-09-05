package com.kamala.test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
/**
 * Using the ThomasBayer API for testing
 */
public class CustomerApiTest {
	@Test
			public void xmlResponsesbody() throws Exception {
				get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").then().assertThat().body("CUSTOMER.ID",equalTo("10")).
				body("CUSTOMER.FIRSTNAME",equalTo("Sue"),"CUSTOMER.LASTNAME", equalTo("Fuller"),"CUSTOMER.STREET",equalTo("135 Upland Pl."), "CUSTOMER.CITY",equalTo("Dallas"));
				System.out.println("Test Case 1 validating the XML response = " + get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").getBody().asString());
		}
}
