package com.rest.spotify;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.GetPlaylistRootPayload;
import static com.rest.api.PlaylistApi.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class SpotifyPlayList {
    @Test(priority = 1)
    public void createPlaylist(){
        GetPlaylistRootPayload getPlaylistRootPayload=new GetPlaylistRootPayload();
        getPlaylistRootPayload.setName("Neeraj DJ");
        getPlaylistRootPayload.setDescription("New Playlist");
        getPlaylistRootPayload.setMypublic(false);
        Response response=postCall(getPlaylistRootPayload);
        assertThat(response.statusCode(),equalTo(201));
        GetPlaylistRootPayload resPlaylistRootPayload=response.as(GetPlaylistRootPayload.class);
        assertThat(resPlaylistRootPayload.getName(),equalTo("Neeraj DJ"));

    }
    @Test(priority = 2)
    public void getPlaylist(){
        Response response=getCall("4NQWNXnHjweHKNBTUBkeJZ");
        assertThat(response.statusCode(),equalTo(200));
    }

    @Test(priority = 3)
    public void updatePlaylist(){
        GetPlaylistRootPayload getPlaylistRootPayload=new GetPlaylistRootPayload();
        getPlaylistRootPayload.setName("ANKIT DJ");
        getPlaylistRootPayload.setDescription("New Playlist");
        getPlaylistRootPayload.setMypublic(false);
        Response response=updateCall(getPlaylistRootPayload,"4NQWNXnHjweHKNBTUBkeJZ");
        assertThat(response.statusCode(),equalTo(200));

    }

}
