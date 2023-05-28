package com.rest.api;
import com.rest.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.RequestPayload;

import static com.rest.api.Route.PLAYLIST;
import static com.rest.api.Route.USERS;
import static com.rest.spotify.TokenManager.getToken;

public class PlaylistApi {
    @Step
    public static Response postCall(RequestPayload request){
        return PlaylistApiMethodReusable.post(USERS+"/"+ConfigLoader.getInstance().get_user_id()+PLAYLIST,getToken(),request);

    }
    @Step
    public static Response getCall(String playlistId){
        return  PlaylistApiMethodReusable.get(PLAYLIST+"/"+playlistId,getToken());
    }

    @Step
    public static Response updateCall(RequestPayload request, String playlistId){
        return PlaylistApiMethodReusable.put(PLAYLIST+"/"+playlistId,getToken(),request);
    }
}
