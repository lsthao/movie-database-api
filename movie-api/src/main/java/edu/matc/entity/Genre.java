package edu.matc.entity;


import org.codehaus.jackson.annotate.JsonIgnore;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that will represent a genre
 */
@Entity(name = "Genre")
@Table(name = "Genre")
/**
 *  This class maps out the relationship between Genre and movies and is responsible for Genre's
 *  hibernate mapping
 *  @author Jeff Herrmann
 */
public class Genre {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;

    @Column(name = "genreName")
    private String genreName;


    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Movies> movies = new HashSet<>();


    /**
     * No-argument constructor
     */
    public Genre(){

    }

    /**
     * Instantiates a new genre
     */
    public Genre(String genreName){
        this.id = id;
        this.genreName = genreName;
    }

    /**
     * Get the id of a genre
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * set the Id of a genre
     * @param id the genre id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns the name of a genre
     * @return genreName
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * set the name of a genre
     * @param genreName the genre name
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * get the Movies object
     * @return movies object
     */
    public Set<Movies> getMovies() {
        return movies;
    }

    /**
     * Set the movies object
     * @param movies the movies object
     */
    public void setMovies(Set<Movies> movies) {
        this.movies = movies;
    }

    /**
     * Add a genre to the movies object
     * @param movie movies object
     */
    public void addMovie(Movies movie) {
        movies.add(movie);
        movie.setGenre(this);
    }

    /**
     * Remove a movie from the movies object
     * @param movie the movie to be removed
     */
    public void removeMovie(Movies movie) {
        movies.remove(movie);
        movie.setGenre(null);
    }
}
