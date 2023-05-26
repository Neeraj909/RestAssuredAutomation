package com.rest.api;

import io.restassured.response.Response;
import pojo.GetPlaylistRootPayload;

import static com.rest.api.SpecBuilder.getRequestSpecification;
import static com.rest.api.SpecBuilder.getResponseSpecBuilder;
import static io.restassured.RestAssured.given;


public class PlaylistApi {
    public  static  String  accessToken="BQAB7mk_Ks_l3jEfhPq-0JUnZO6CRFNtJSY7begF9tuv2QocvH6d8uN6y1MktNDVUpxs5U68w36wM8Y7BzBc9o3uvHUwxapbvsBCqL_G6-vHbX-V1Up-y2AYQsyiG0r5-q77YN8WjwnsJgusXSe2bkooxxDOEOcCBIHuZ_7qNLBCmpQLyiAg3c2LPwagLIZrls5jCRz7BHnqCv0HICSeoslH-x8z7XBPl5TIp5erTOtAeOqsV8TKolQfhgVfu9hYfG01nmnvHAgoS5a8";
    public static Response postCall(GetPlaylistRootPayload request){
        return PlaylistApiMethodReusable.post("/users/31llmcs5y2d3tnleaoywdontpad4/playlists",accessToken,request);

    }
    public static Response getCall(String playlistId){
        return  PlaylistApiMethodReusable.get("/playlists/"+playlistId,accessToken);
    }

    public static Response updateCall(GetPlaylistRootPayload request,String playlistId){
        return PlaylistApiMethodReusable.put("/playlists/"+playlistId,accessToken,request);
    }
}
