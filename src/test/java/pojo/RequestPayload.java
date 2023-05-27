package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class RequestPayload {
    public boolean collaborative;
    public String description;
    public External_urls external_urls;
    public Followers followers;
    public String href;
    public String id;
    public ArrayList<Object> images;
    public String name;
    public Owner owner;
    public Object primary_color;
    @JsonProperty("public")
    public boolean mypublic;
    public String snapshot_id;
    public Tracks tracks;
    public String type;
    public String uri;

}
