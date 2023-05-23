package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.PayLoad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SerializeAndDeSerialize {
   ResponseSpecification responseSpecification;
      RequestSpecification requestSpecification;


    @BeforeClass
    public void logFilter() throws FileNotFoundException {
        PrintStream stream=new PrintStream(new File("restAssured.log "));
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder().
                addFilter(new ResponseLoggingFilter(stream)).
                addFilter(new RequestLoggingFilter(stream));
        requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecification=  responseSpecBuilder.build();
    }
    @Test(priority = 1)
    public void serializedMapToJson() throws JsonProcessingException {
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("name","morpheus");
        map.put("job","leader");
        ObjectMapper objectMapper=new ObjectMapper();
        String mainObject=objectMapper.writeValueAsString(map);
        given(requestSpecification)
                .baseUri("https://reqres.in/api/")
                .body(mainObject)
                .when()
                .post("users")
                .then().spec(responseSpecification)
                .log().all()
                .assertThat()
                .statusCode(201);

    }

    @Test(priority = 2)
    public void serializedPojo() throws JsonProcessingException {
        PayLoad load=new PayLoad("Neeraj","Java");
        given(requestSpecification)
                .baseUri("https://reqres.in/api/")
                .body(load)
                .when()
                .post("users")
                .then().spec(responseSpecification)
                .log().all()
                .assertThat()
                .statusCode(201);

    }

}
