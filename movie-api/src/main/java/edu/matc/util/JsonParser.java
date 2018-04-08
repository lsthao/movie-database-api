package edu.matc.util;

import edu.matc.entity.Movies;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import javax.ws.rs.core.Response;
import java.io.IOException;


public class JsonParser {
    @JsonIgnore
    public String returnJson(Object movieTypeObject) throws IOException {

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(movieTypeObject);

        return json;
    }

}

