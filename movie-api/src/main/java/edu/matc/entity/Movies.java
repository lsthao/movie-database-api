package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Movies")
@Table(name = "Movies")
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

}
