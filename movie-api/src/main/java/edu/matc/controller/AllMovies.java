package edu.matc.controller;


import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

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
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@WebServlet(
    urlPatterns = {"/allMovies"}
)


public class AllMovies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Movies> moviesList = new ArrayList<Movies>();

        HttpSession session = request.getSession();

        URI baseURI = UriBuilder.fromUri("http://localhost:8080/movieAPI/movies/all").build();

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(baseURI);

        String allMovies = target.path("JSON/all").request().accept(MediaType.APPLICATION_JSON).get(String.class);
        Movies[] movies = mapper.readValue(allMovies, Movies[].class);


        request.setAttribute("movies", moviesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/allmovies.jsp");
        dispatcher.forward(request, response);

    }
}
