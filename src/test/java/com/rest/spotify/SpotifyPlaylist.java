package com.rest.spotify;

import com.rest.api.StatusCode;
import com.rest.utils.DataLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.RequestPayload;
import static com.rest.api.PlaylistApi.*;
import static com.rest.utils.FakerUtils.generateDescription;
import static com.rest.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
@Epic("Spotify Oauth 2.0")
@Feature("Spotify Playlist Api Test cases")
public class SpotifyPlaylist {

    @Step
    public RequestPayload getPlaylistRootPayload(String name, String description, boolean _public ){
        RequestPayload requestPayload =new RequestPayload();
        requestPayload.setName(name);
        requestPayload.setDescription(description);
        requestPayload.setMypublic(_public);
        return requestPayload;

    }
    @Step
    public void assertResponse(RequestPayload responsePayload, RequestPayload requestPayload){
        assertThat(responsePayload.getName(),equalTo(requestPayload.getName()));
        assertThat(responsePayload.getDescription(),equalTo(requestPayload.getDescription()));
    }
    @Step
    public void assertStatusCode(int actualCode,int expectedCode){
        assertThat(actualCode,equalTo(expectedCode));
    }

    @Description
    @Test(priority = 1,description = "user able to create the playlist")
    public void createPlaylist(){
        RequestPayload requestPayload =getPlaylistRootPayload(generateName(),generateDescription(),false);
        Response response=postCall(requestPayload);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201.code);
        assertResponse(response.as(RequestPayload.class), requestPayload);

    }
    @Test(priority = 2)
    public void getPlaylist(){
        Response response=getCall(DataLoader.getInstance().get_playlist_id());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.code);
    }

    @Test(priority = 3)
    public void updatePlaylist(){
        RequestPayload requestPayload =getPlaylistRootPayload(generateName(),generateDescription(),false);
        Response response=updateCall(requestPayload,DataLoader.getInstance().get_update_playlist_id());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.code);

    }

}
