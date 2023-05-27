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

public class ResponseRoot {
    public Data data;
    public Support support;
}
