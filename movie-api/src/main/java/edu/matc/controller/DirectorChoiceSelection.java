package edu.matc.controller;

import edu.matc.entity.Movies;
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
        name = "selectedDirector",
        urlPatterns = {"/selectedDirector"}
)

/**
 * this servlet takes the id of the director object sent into it and returns all the movies from the movies
 * database where the director id equals the chosen director
 *
 * @author Jeff Herrmann
 */
public class DirectorChoiceSelection extends HttpServlet {
    @Override
    /**
     * This is the doGet method for our servlet
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String searchId = req.getParameter("directorId");

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/movie-api/movieAPI/directors/getByDirectorId/" + searchId);

        String movieResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        Movies[] directorObject = mapper.readValue(movieResponse, Movies[].class);

        req.setAttribute("movies", directorObject);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/moviesdisplay.jsp");
        dispatcher.forward(req, resp);
    }
}