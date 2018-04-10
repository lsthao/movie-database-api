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
        name = "selectedRating",
        urlPatterns = {"/selectedRating"}
)
public class RatingChoiceSelection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String searchId = req.getParameter("ratingId");

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/movie-api/movieAPI/ratings/getByRatingId/" + searchId);

        String movieResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        Movies[] ratingObject = mapper.readValue(movieResponse, Movies[].class);

        req.setAttribute("movies", ratingObject);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/moviesdisplay.jsp");
        dispatcher.forward(req, resp);
    }
}