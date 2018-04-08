package edu.matc.entity;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that will represent a genre
 */
@Entity(name = "Genre")
@Table(name = "Genre")
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }


    public Set<Movies> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movies> movies) {
        this.movies = movies;
    }

    public void addMovie(Movies movie) {
        movies.add(movie);
        movie.setGenre(this);
    }

    public void removeMovie(Movies movie) {
        movies.remove(movie);
        movie.setGenre(null);
    }
}
