package edu.matc.controller;


import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
    urlPatterns = {"/allMovies"}
)


public class AllMovies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletContext context = req.getServletContext();

        //String searchTerm = req.getParameter("searchTerm");


        GenericDAO movieDAO= new GenericDAO(Movies.class);

        List<Movies> moviesList = movieDAO.getAll();
        req.setAttribute("movies", moviesList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/allmovies.jsp");
        dispatcher.forward(req, resp);

    }
}
