package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class External_urls {
    public String spotify;

}
