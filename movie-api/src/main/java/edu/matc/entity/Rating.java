package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent a rating
 */
@Entity(name = "Rating")
@Table(name = "Rating")
public class Rating {
    @Column(name = "ratingName")
    private String ratingName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy="native")
    private int rating;


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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }
}
