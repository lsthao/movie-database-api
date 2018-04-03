package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Director")
@Table(name = "Director")
public class Director {

    @Column(name = "directorName")
    private String directorName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    public Set<Movies> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movies> movies) {
        this.movies = movies;
    }

    public void addMovie(Movies movie) {
        movies.add(movie);
        movie.setDirector(this);
    }

    public void removeMovie(Movies movie) {
        movies.remove(movie);
        movie.setDirector(null);
    }

}
