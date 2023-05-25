package com.rest.api;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetTest {
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
    public void validateStatusCode(){

        given()
                .baseUri("https://reqres.in/api/")
                .when()
                .get("users?page=2")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test(priority = 2)
    public void validateResponse(){
        given()
                .baseUri("https://reqres.in/api/")
                .when()
                .get("users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.first_name",hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel")
                ,"data.size()",is(equalTo(6))
                ,"data.first_name",hasItem("Michael"));
    }
    @Test(priority = 3)
    public void extractResponse(){
       Response res= given()
                .baseUri("https://reqres.in/api/")
                .when()
                .get("users?page=2")
                .then()
                .assertThat()
                .statusCode(200).extract().response();
        JsonPath path=new JsonPath(res.asString());
       System.out.println(res.asString());
    }
    @Test(priority = 4)
    public void hamrcrast_single_response(){
        String name= given()
                .baseUri("https://reqres.in/api/")
                .log().headers()
                .when()
                .get("users?page=2")
                .then()
                .assertThat()
                .statusCode(200).extract().response().path("data[0].first_name");
        System.out.println(name);
        Assert.assertEquals(name,"Michael");
    }

    @Test(priority = 5)
    public void hamrcrast_single_QueryParam(){
        String name= given()
                .baseUri("https://reqres.in/api/")
                //.params("page","2")
                .queryParam("page","2")
                .log().headers()
                .when()
                .get("users")
                .then()
                .assertThat()
                .statusCode(200).extract().response().path("data[0].first_name");
        System.out.println(name);
        Assert.assertEquals(name,"Michael");
    }

    @Test(priority = 6)
    public void hamrcrast_single_PathParam(){
        String name= given()
                .baseUri("https://reqres.in/api/")
                //.params("page","2")
                .pathParam("pageid","2")
                .log().all()
                .when()
                .get("users/{pageid}")
                .then()
                .assertThat()
                .statusCode(200).extract().response().path("data.first_name");
        System.out.println(name);
        Assert.assertEquals(name,"Janet");
    }

    @Test(priority = 7)
    public void jsonSchemaValidation() throws FileNotFoundException {
        given(requestSpecification)
                .baseUri("https://reqres.in/api/")
                //.params("page","2")
                .pathParam("pageid","2")
                .log().all()
                .when()
                .get("users/{pageid}")
                .then().spec(responseSpecification)
                        .assertThat()
                                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("echo.json"));

    }

}
