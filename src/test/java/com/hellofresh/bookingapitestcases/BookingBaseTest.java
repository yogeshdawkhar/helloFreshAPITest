package com.hellofresh.bookingapitestcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import com.hellofresh.util.PropertiesReader;

public class BookingBaseTest {

    static String baseURI;
    Response response;
    Response response2;

    static void envDetails() {
        PropertiesReader.load(System.getProperty("user.dir") + "/src/main/resources/env.properties");
        baseURI = PropertiesReader.get("url");
        System.out.println("URL"+baseURI);
    }

    @BeforeSuite
    public void setUp() {
        envDetails();
        RestAssured.baseURI = baseURI;
    }

}
