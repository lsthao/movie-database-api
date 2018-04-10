package edu.matc.controller;


import edu.matc.entity.Movies;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;


import javax.servlet.RequestDispatcher;
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

@WebServlet(
        urlPatterns = {"/movieSuggester"}

)
public class MovieSuggester extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ObjectMapper mapper = new ObjectMapper();

        URI baseURI = UriBuilder.fromUri("http://localhost:8080/movie-api/movieAPI/movies/").build();

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(baseURI);

        String allMovies = target.path("all").request().accept(MediaType.APPLICATION_JSON).get(String.class);

        Movies[] movies = mapper.readValue(allMovies, Movies[].class);

        if (session.getAttribute("movie") != null) {
            session.setAttribute("movies", null);
        }

        session.setAttribute("movies", movies);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/moviesuggester.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
