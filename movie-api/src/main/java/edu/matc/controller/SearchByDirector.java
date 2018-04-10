package edu.matc.controller;

import edu.matc.entity.Director;
import org.codehaus.jackson.map.ObjectMapper;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/searchDirector"}
)

public class SearchByDirector extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/movie-api/movieAPI/directors/all");
        String movieResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);
        Director[] directorObject = mapper.readValue(movieResponse, Director[].class);

        req.setAttribute("directors", directorObject);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchdirector.jsp");
        dispatcher.forward(req, resp);
    }
}