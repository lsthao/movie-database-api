package edu.matc.controller;


import edu.matc.entity.Genre;
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
import java.util.List;

@WebServlet(
        name = "/searchGenres",
        urlPatterns = {"/searchGenres"}
)

public class SearchGenres extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/movie-api/movieAPI/genres/all");
        String movieResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        Genre[] genreObject = mapper.readValue(movieResponse, Genre[].class);

        req.setAttribute("genreObject", genreObject);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/getallgenres.jsp");
        dispatcher.forward(req, resp);
    }
}