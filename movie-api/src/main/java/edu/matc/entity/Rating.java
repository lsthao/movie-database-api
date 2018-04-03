package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a rating
 */
@Entity(name = "Rating")
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy="native")
    private int id;

    @Column(name = "ratingName")
    private String ratingName;

    @OneToMany(mappedBy = "rating", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Movies> movies = new HashSet<>();

    /**
     * No argument constructor
     */
    public Rating(){

    }

    /**
     * Instantiates a new Rating
     */
    public Rating(String ratingName){
        this.ratingName = ratingName;
    }

    public int getId() {
        return id;
    }

    public void setId(int rating) {
        this.id = rating;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    public Set<Movies> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movies> movies) {
        this.movies = movies;
    }

    public void addMovie(Movies movie) {
        movies.add(movie);
        movie.setRating(this);
    }

    public void removeMovie(Movies movie) {
        movies.remove(movie);
        movie.setRating(null);
    }
}
