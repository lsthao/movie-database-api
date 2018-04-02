package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Director")
@Table(name = "Director")
public class Director {

    @Column(name = "directorName")
    private String directorName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    /**
     * No argument constructor
     */
    public Director(){

    }

    public Director(String directorName){
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

}
