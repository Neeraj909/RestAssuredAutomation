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

public class PayLoad {
    private  String name;
    private  String job;

    public PayLoad(String name, String job) {
        this.name = name;
        this.job = job;
    }


}
