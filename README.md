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
- [x] Complete project objectives
- [x] Create database
- [x] Create entities/daos

Week 11
- [x] Map out relationships
- [x] Create API endpoints
- [x] Create webapp to consume api

Week 12
- [ ] team project presentations!


## Database (Tables/Columns)
* Movie
  * id
  * description
  * directorID
  * genreID
  * releaseYear
  * rating 
* Genre
  * id
  * genreName
* Director
  * id
  * directorName
* Rating
 * id
 * ratingName

## API EndPoints & Usage

### Movies
#### POST - movieAPI/movies/add
* add movie to database
* return added movie in JSON

#### GET

#### movieAPI/movies/search/< keyword >
* searches movie titles based on keyword

#### movieAPI/movies/< movieID >
 * if movieID is known, can get all metadata of specific movie


***
[Development Journal](DevelopmentJournal.md)

