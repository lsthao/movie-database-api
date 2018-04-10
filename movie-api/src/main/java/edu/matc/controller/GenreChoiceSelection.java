package edu.matc.controller;


import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.movieAPI.GenreAPI;
import edu.matc.persistence.GenericDAO;
import edu.matc.util.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "selectedGenre",
        urlPatterns = {"/selectedGenre"}
)
public class GenreChoiceSelection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonParser parser = new JsonParser();
        ObjectMapper mapper = new ObjectMapper();

        String searchId = req.getParameter("searchId");

        Client client = ClientBuilder.newClient();

        GenericDAO moviesDAO= new GenericDAO(Movies.class);
        WebTarget target = client.target("http://localhost:8080/movieAPI/genres/getByGenreId/" + searchId);
        String movieResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        Movies[] genreObject = mapper.readValue(movieResponse, Movies[].class);

        req.setAttribute("movies", genreObject);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/moviesdisplay.jsp");
        dispatcher.forward(req, resp);
    }
}