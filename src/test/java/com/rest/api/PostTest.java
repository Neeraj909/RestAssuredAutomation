package com.rest.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class PostTest {
    String id="";
    @Test(priority = 1,enabled = false)
    public void validatePostRequest(){
     String payload="{\n" +
             "    \"name\": \"morpheus\",\n" +
             "    \"job\": \"leader\"\n" +
             "}";
        Response response =given()
                .baseUri("https://reqres.in/api/")
                .body(payload)
                .when()
                .post("users")
                .then()
                .assertThat()
                .statusCode(201).extract().response();
        JsonPath path=new JsonPath(response.asString());
        id=path.getString("id");
        System.out.println(id);

    }
    @Test(priority = 2,enabled = false)
    public void sendASFile(){
File file =new File("src/test/resources/payload.json");
        Response response =
          given()
                .baseUri("https://reqres.in/api/")
                .body(file)
          .when()
                .post("users")
          .then()
                  .log().all()
                  .assertThat()
                  .statusCode(201).extract().response();
    }

    @Test(priority = 3,enabled = false)
    public void uploadFile(){
        File file =new File("src/test/resources/payload.json");
        Response response =
                given()
                        .baseUri("https://reqres.in/api/")
                        .when()
                        .post("users")
                        .then()
                        .log().all()
                        .assertThat()
                        .statusCode(201).extract().response();
    }

}
