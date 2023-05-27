package com.rest.api;
import com.rest.utils.ConfigLoader;
import io.restassured.response.Response;
import pojo.GetPlaylistRootPayload;

import static com.rest.api.Route.PLAYLIST;
import static com.rest.api.Route.USERS;
import static com.rest.spotify.TokenManager.getToken;

public class PlaylistApi {
    public static Response postCall(GetPlaylistRootPayload request){
        return PlaylistApiMethodReusable.post(USERS+"/"+ConfigLoader.getInstance().get_user_id()+PLAYLIST,getToken(),request);

    }
    public static Response getCall(String playlistId){
        return  PlaylistApiMethodReusable.get(PLAYLIST+"/"+playlistId,getToken());
    }

    public static Response updateCall(GetPlaylistRootPayload request,String playlistId){
        return PlaylistApiMethodReusable.put(PLAYLIST+"/"+playlistId,getToken(),request);
    }
}
