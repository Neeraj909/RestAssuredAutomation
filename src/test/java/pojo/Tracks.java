package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Builder
public class Tracks {
    public String href;
    public ArrayList<Object> items;
    public int limit;
    public Object next;
    public int offset;
    public Object previous;
    public int total;

}
