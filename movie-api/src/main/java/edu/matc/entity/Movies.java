package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Movies")
@Table(name = "Movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "directorID")
    private int directorID;

    @Column(name="genreID")
    private int genreID;

    @Column(name="releaseYear")
    private String releaseYear;

    @Column(name="ratingID")
    private int ratingID;

    @Column(name = "title")
    private String title;

    /**
     * no-argument constructor
     */
    public Movies(){

    }

    /**
     * Instantiates a new director
     */
    public Movies(int id, String description, int directorID, int genreID, String releaseYear, int ratingID, String title){
        this.id = id;
        this.description = description;
        this.directorID = directorID;
        this.genreID = genreID;
        this.releaseYear = releaseYear;
        this.ratingID = ratingID;
        this.title = title;
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

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
