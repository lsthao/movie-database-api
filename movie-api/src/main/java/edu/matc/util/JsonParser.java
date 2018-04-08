package edu.matc.util;

import com.google.gson.Gson;
import edu.matc.entity.Movies;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import javax.ws.rs.core.Response;
import java.io.IOException;


public class JsonParser {

    public String returnJson(Object movieTypeObject) throws IOException {

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(movieTypeObject);

        return json;
    }

}

