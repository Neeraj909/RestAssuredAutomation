package com.rest.spotify;

import com.rest.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.rest.api.PlaylistApiMethodReusable.postAccount;
import static com.rest.api.SpecBuilder.getResponseSpecBuilder;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){
        try{
            if(access_token==null || Instant.now().isAfter(expiry_time)){
                Response response=renewToken();
                access_token=response.path("access_token");
                int expireDurationSecond=response.path("expires_in");
                expiry_time=Instant.now().plusSeconds(expireDurationSecond - 300);
            }
            else {
                System.out.println("Token is live");
            }
        }catch (Exception e){
            throw new RuntimeException("ABORT !!! RENEW The Token");
        }
        return access_token;

    }
    private static Response renewToken(){
        HashMap<String,String> formParam=new HashMap<String,String>();
        formParam.put("client_id", ConfigLoader.getInstance().getClientId());
        formParam.put("client_secret",ConfigLoader.getInstance().get_client_secret());
        formParam.put("refresh_token",ConfigLoader.getInstance().get_refresh_token());
        formParam.put("grant_type",ConfigLoader.getInstance().get_grant_type());
        Response response= postAccount(formParam);
          if(response.statusCode()!=200){
              throw new RuntimeException("ABORT !!! RENEW The Token");

          }
          else {
              return response;
          }


    }
}
