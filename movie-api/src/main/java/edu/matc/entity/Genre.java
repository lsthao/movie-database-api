package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class that will represent a genre
 */
@Entity(name = "Genre")
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    @Column(name = "genreName")
    private String genreName;

    /**
     * No-argument constructor
     */
    public Genre(){

    }

    /**
     * Instantiates a new genre
     */
    public Genre(int id, String genreName){
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
}
