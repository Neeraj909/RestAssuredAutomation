package com.rest.googleauth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetGmailProfile {
    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    public  static String  accessToken="tokenId";
    @BeforeClass
    public void logFilter() throws FileNotFoundException {
        PrintStream stream=new PrintStream(new File("GetGmailProfile.log "));
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder().
        setBaseUri("https://gmail.googleapis.com").
                addHeader("Authorization","Bearer "+ accessToken).
                setContentType(ContentType.JSON).
                addFilter(new ResponseLoggingFilter(stream)).
                addFilter(new RequestLoggingFilter(stream)).
                log(LogDetail.ALL);
                ;
        requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification=  responseSpecBuilder.build();
    }
    @Test(priority = 1)
    public void getProfile(){
        Response response=given(requestSpecification).
                basePath("/gmail/v1/").
                pathParam("userid","mailId").
        when().
                get("users/{userid}/profile").
        then()
                .spec(responseSpecification).extract().response();
        String cookie= response.getCookies().toString();
        System.out.println(cookie);
      }
      @Test(priority = 2)
    public void sendMail(){
        String msg="From : nsharma2596@gmail.com\n" +
                "To : prahan63@gmail.com\n" +
                "Subject: Rest Assured Test\n" +
                "Sending for Rest Assured\n";
        String bash64EncodedMessage= Base64.getUrlEncoder().encodeToString(msg.getBytes());
          HashMap<String,String> map=new HashMap<>();
          map.put("raw",bash64EncodedMessage);
          given(requestSpecification).
                  basePath("/gmail/v1/").
                  pathParam("userid","mailId").
                  body(map).
          when().
                  post("users/{userid}/messages/send")
          .then()
                  .spec(responseSpecification);
      }

}
