package edu.matc.controller;


import edu.matc.entity.Director;
import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.entity.Rating;
import edu.matc.persistence.GenericDAO;
import org.apache.log4j.Logger;
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
        urlPatterns = {"/addMovieForm"}
)

public class AddMovieForm extends HttpServlet{
    Logger logger = Logger.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession();

        Genre[] genres = mapper.readValue(getResponse("http://localhost:8080/movie-api/movieAPI/genres/all"),
                Genre[].class);
        Director[] directors = mapper.readValue(getResponse("http://localhost:8080/movie-api/movieAPI/directors/all"),
                Director[].class);
        Rating[] ratings = mapper.readValue(getResponse("http://localhost:8080/movie-api/movieAPI/ratings/all"),
                Rating[].class);

        request.setAttribute("genres", genres);
        request.setAttribute("directors", directors);
        request.setAttribute("ratings", ratings);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/addmovie.jsp");
        dispatcher.forward(request, response);
    }

    public String getResponse(String endpointURL) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(endpointURL);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        return response;
    }

}
