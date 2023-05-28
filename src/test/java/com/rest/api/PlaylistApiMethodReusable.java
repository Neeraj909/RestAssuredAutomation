package com.rest.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.rest.api.Route.API_PATH;
import static com.rest.api.Route.TOKEN;
import static com.rest.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;


public class PlaylistApiMethodReusable {
    public static Response post(String path,String token,Object request){
       return given(getRequestSpecification()).
                body(request).
               auth().oauth2(token).
                when().
                post(path).
                then().
                spec(getResponseSpecBuilder()).
                assertThat().
                statusCode(201).
                extract().response();
    }
    public static Response get(String path,String token){
        return  given(getRequestSpecification()).
                auth().oauth2(token).
                when().
                get(path).
                then().
                spec(getResponseSpecBuilder()).extract().response();
    }

    public static Response put(String path,String token,Object request){
        return given(getRequestSpecification()).
                body(request).
                auth().oauth2(token).
                when().
                put(path).
                then().
                spec(getResponseSpecBuilder()).extract().response();
    }
    public static Response postAccount(HashMap<String,String> formParam){
        return given(getAccountRequestSpec()).
                formParams(formParam).
                when().
                post(API_PATH+TOKEN).
                then().spec(getResponseSpecBuilder()).extract().response();
    }
}
