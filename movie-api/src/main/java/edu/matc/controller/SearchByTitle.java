package edu.matc.controller;

import edu.matc.entity.Movies;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

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
import java.net.URLEncoder;

@WebServlet(
        urlPatterns = {"/search"}
)
public class SearchByTitle extends HttpServlet {

    Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession();

        String searchTerm = request.getParameter("title");
        Client client = ClientBuilder.newClient();
        logger.info(searchTerm);
        String url = "http://localhost:8080/movie-api/movieAPI/movies/search/" + searchTerm;
        WebTarget target = client.target(URLEncoder.encode(url, "UTF-8"));

        String movieResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);
        logger.info(movieResponse);
        Movies[] movies = mapper.readValue(movieResponse, Movies[].class);
        logger.info(movies);

        request.setAttribute("movies", movies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/moviesdisplay.jsp");
        dispatcher.forward(request, response);

    }

}