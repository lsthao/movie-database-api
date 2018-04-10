package edu.matc.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Director")
@Table(name = "Director")
/**
 * This entity maps out the relationships between the Director, and the movies that the director filmed
 * @author Ren Thao
 */
public class Director {

    @Column(name = "directorName")
    private String directorName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Movies> movies = new HashSet<>();
    /**
     * No argument constructor
     */
    public Director(){

    }

    public Director(String directorName){
        this.id = id;
        this.directorName = directorName;
    }

    /**
     * Gets id.
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets id.
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets directorName
     * @return directorName
     */
    public String getDirectorName() {
        return directorName;
    }

    /**
     * Sets name.
     * @param directorName the director name
     */
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    /**
     * Returns all movies
     * @return movies
     */
    public Set<Movies> getMovies() {
        return movies;
    }

    /**
     * Sets the movies object
     * @param movies the movies object
     */
    public void setMovies(Set<Movies> movies) {
        this.movies = movies;
    }

    /**
     * Adds a movie to the movies object
     * @param movie the movie to be added
     */
    public void addMovie(Movies movie) {
        movies.add(movie);
        movie.setDirector(this);
    }

    /**
     * Removies a movie from the movies object
     * @param movie the movie to be removed
     */
    public void removeMovie(Movies movie) {
        movies.remove(movie);
        movie.setDirector(null);
    }

}
