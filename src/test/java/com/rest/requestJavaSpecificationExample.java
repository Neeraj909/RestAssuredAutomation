package com.rest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class requestJavaSpecificationExample {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    @BeforeClass()
    public void requestSpecification(){
//        requestSpecification =with()
//                .baseUri("https://reqres.in/api/");
        RequestSpecBuilder builder=new RequestSpecBuilder();
        builder.setBaseUri("https://reqres.in/api/");
       //for 1st test caseds
        requestSpecification=builder.build();
        //this is for 2nd test cases
        RestAssured.requestSpecification=builder.build();
        ResponseSpecBuilder responseBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.responseSpecification=responseBuilder.build();

    }
    @Test(priority = 1)
    public void validateStatusCode()
    {
       given().spec(requestSpecification)
               .when()
               .get("users?page=2")
               .then()
               .assertThat()
               .statusCode(200);
    }
    @Test(priority = 2)
    public void defaultRequestSpecification(){
                get("users?page=2")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
