package com.rest.spotify;

import com.rest.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.RequestPayload;
import static com.rest.api.PlaylistApi.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class SpotifyPlaylist {

    public RequestPayload getPlaylistRootPayload(String name, String description, boolean _public ){
        RequestPayload requestPayload =new RequestPayload();
        requestPayload.setName(name);
        requestPayload.setDescription(description);
        requestPayload.setMypublic(_public);
        return requestPayload;

    }
    public void assertResponse(RequestPayload responsePayload, RequestPayload requestPayload){
        assertThat(responsePayload.getName(),equalTo(requestPayload.getName()));
        assertThat(responsePayload.getDescription(),equalTo(requestPayload.getDescription()));
    }
    public void assertStatusCode(int actualCode,int expectedCode){
        assertThat(actualCode,equalTo(expectedCode));
    }

    @Test(priority = 1)
    public void createPlaylist(){
        RequestPayload requestPayload =getPlaylistRootPayload("Neeraj DJ","New Playlist",false);
        Response response=postCall(requestPayload);
        assertStatusCode(response.statusCode(),201);
        assertResponse(response.as(RequestPayload.class), requestPayload);

    }
    @Test(priority = 2)
    public void getPlaylist(){
        Response response=getCall(DataLoader.getInstance().get_playlist_id());
        assertStatusCode(response.statusCode(),200);
    }

    @Test(priority = 3)
    public void updatePlaylist(){
        RequestPayload requestPayload =getPlaylistRootPayload("Ankit DJ","New Playlist",false);
        Response response=updateCall(requestPayload,DataLoader.getInstance().get_update_playlist_id());
        assertStatusCode(response.statusCode(),200);
        //assertResponse(response.as(GetPlaylistRootPayload.class),getPlaylistRootPayload);

    }

}
