package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayLoad {
    private  String name;
    private  String job;

    public PayLoad(String name, String job) {
        this.name = name;
        this.job = job;
    }


}
