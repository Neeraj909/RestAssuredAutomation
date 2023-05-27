package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Builder
public class Owner {
    public String display_name;
    public External_urls external_urls;
    public String href;
    public String id;
    public String type;
    public String uri;

}
