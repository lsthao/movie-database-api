package edu.matc.movieAPI;

import edu.matc.movieAPI.HelloWorld;
import edu.matc.movieAPI.MovieAPI;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/movieAPI")

//The java class declares root resource and provider classes
public class UserApplication extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(HelloWorld.class);
        h.add(MovieAPI.class);
        h.add(RatingAPI.class);

        h.add(GenreAPI.class);

        h.add(DirectorAPI.class);

        return h;
    }
}