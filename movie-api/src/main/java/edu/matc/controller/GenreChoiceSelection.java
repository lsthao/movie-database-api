package edu.matc.controller;

import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;
import edu.matc.util.JsonParser;
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
        name = "selectedGenre",
        urlPatterns = {"/selectedGenre"}
)

public class GenreChoiceSelection extends HttpServlet {

    /**
     * This is the doGet method for our servlet
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String searchId = req.getParameter("searchId");

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/movie-api/movieAPI/genres/getByGenreId/" + searchId);
        String movieResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        Movies[] genreObject = mapper.readValue(movieResponse, Movies[].class);

        req.setAttribute("movies", genreObject);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/moviesdisplay.jsp");
        dispatcher.forward(req, resp);
    }
}