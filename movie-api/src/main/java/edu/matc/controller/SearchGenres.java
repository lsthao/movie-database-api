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
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = {"/searchGenres"}
)

public class SearchGenres extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        GenericDAO genreDAO= new GenericDAO(Genre.class);
        genreDAO.getAll();

        List<Movies> genresList = genreDAO.getAll();
        JsonParser parser = new JsonParser();
        String returnedJson = parser.returnJson(genresList);

        Genre[] genreObject = mapper.readValue(returnedJson, Genre[].class);

        req.setAttribute("genreObject", genreObject);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/getallgenres.jsp");
        dispatcher.forward(req, resp);

    }
}