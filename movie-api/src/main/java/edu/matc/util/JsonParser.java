package edu.matc.util;

import edu.matc.entity.Movies;
import edu.matc.entity.ResponseMessage;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import javax.ws.rs.core.Response;
import java.io.IOException;


public class JsonParser {
    Logger logger = Logger.getLogger(this.getClass());

    @JsonIgnore
    public String returnJson(Object movieTypeObject) throws IOException {

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(movieTypeObject);

        return json;
    }

    public String returnJsonResponseMessage(String message) {

        String output = "";
        ResponseMessage responseMessage = new ResponseMessage(message);
        try {
            output += returnJson(responseMessage);
        } catch(IOException ioException) {
            logger.error(ioException.getMessage());
        }
        return output;
    }

}

