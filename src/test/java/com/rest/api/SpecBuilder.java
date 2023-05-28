package com.rest.api;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.rest.api.Route.BASH_PATH;


public class SpecBuilder {
    public static RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder().
                //setBaseUri("https://api.spotify.com").
                setBaseUri(System.getProperty("BASE_URI")).
                setBasePath(BASH_PATH).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).build();
    }
    public static ResponseSpecification getResponseSpecBuilder(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }

    public static RequestSpecification getAccountRequestSpec(){
        return new RequestSpecBuilder().
               // setBaseUri("https://accounts.spotify.com").
                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).build();
    }

}
