package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Followers {
    public Object href;
    public int total;
}
