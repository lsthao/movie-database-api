package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Movies")
@Table(name = "Movies")
/**
 * This is the movies class. It is the master class of the Movie database. It is responsible for
 * all of the changes to the database as well as mapping out the relationships for them.
 * @author Leja Thao
 */
public class Movies {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name="releaseYear")
    private int releaseYear;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Rating rating;

    @ManyToOne
    private Director director;
    /**
     * no-argument constructor
     */
    public Movies(){

    }

    /**
     * Instantiates a new director
     */
    public Movies(String description,int releaseYear, String title, Genre genre,
                  Director director, Rating rating){
        this.description = description;
        this.releaseYear = releaseYear;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.rating = rating;
    }

    /**
     * Get the id of a movie
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of the Movie
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the description of the movie
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the movie
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the release year of the movie
     * @return releaseYear
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the movie
     * @param releaseYear the release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the title of the movie
     * @return title the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the genre
     * @return genre the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Set the genre
     * @param genre the genre to be set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Sets the rating
     * @return rating the rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Sets the rating
     * @param rating the rating to be set
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     * Gets the director
     * @return director the director
     */
    public Director getDirector() {
        return director;
    }

    /**
     * Sets the direct
     * @param director the director
     */
    public void setDirector(Director director) {
        this.director = director;
    }

}
