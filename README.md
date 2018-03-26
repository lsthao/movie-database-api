# Movie Database API

## Problem Statement
The idea for this API came from the issue of not being able to find or decide on a movie. This API will be have a database of all different types of movies along with details that include: description, genre, rating, release year, director, etc. The RESTful Web Service will allow users to get movies based on things such as: 
* Searching for a movie title
* Filtering on genres, ratings, release years, directors etc
* Amount of movies they want to receive 
* Generate a random movie

## Project Objectives

## Project Plan
Week 10
[] Complete project objectives
[] Create database
[] Create entities/daos

Week 11
[] Map out relationships
[] Create API endpoints
[] Create webapp to consume api

Week 12


## Database (Tables/Columns)
* Movie
  * MovieID
  * Description
  * DirectorID
  * GenreID
  * ReleaseYear
  * AverageRating
  * Rating (R/PG/etc...)
* Genre
  * Genre ID
  * Genre Name
* Director
  * DirectorID
  * DirectorName

## API EndPoints & Usage

### Movies
#### POST - movieAPI/movies/add
* add movie to database
* return added movie in HTML or JSON

#### GET
#### movieAPI/movies/filter
* gets movies based on filters:
 * genre
 * sorted by (?)
 * director
 * release year
 * rating 
 * averageRating
 * amount of movies to return (otherwise will return all)
 
#### movieAPI/movies/search/< keyword >
* searches movie titles based on keyword

#### movieAPI/movies/< movieID >
 * if movieID is known, can get all metadata of specific movie



