
# Movie Database API User Documentation

## Table of Contents
# 1.0 Introduction
# 2.0 Getting Started
# 2.1 Usage
# 3.0 Example 1 - Adding a Movie
# 4.0 Example 2 - Search by Rating, Genre, Title or Director
# Using Movies Suggestor
# Acknowledgments

## 1.0 Introduction
The idea for this API came from the issue of not being able to find or decide on a movie. This API features a database of all different types of movies along with details that include: description, genre, rating, release year, director, etc. The RESTful Web Service allows users to get movies based on things such as: 
* Searching for a movie title
* Filtering on genres, ratings, release years, directors etc
* Amount of movies they want to receive 
* Generate a random movie
This service has the unique ability to suggest movies based on related movies. 

## 2.0 Getting Started
To get started simply navigate to the homepage of Madison Movie Database. All the functionality is ready to use out of the box. 

## 2.1 Usage 
The first thing a user will notice is the homepage which contains a handful of links such as "See all Movies", "Add a movie", and "Search by rating". These will redirect the user to a web page that filters through the database based on the terms that the user searched by. For instance, if a user were to search for all movies with a rating of "G", this service will return the list of all movies that have a "G"  rating. 

Similarly, if a user wants to add a movie to the database they have the ability to do so by clicking the "Add a movie link" and entering the details of the movie that they are entering. Once these changes are entered the user will be find that the movie will be searchable by all the links on the home page. 

If a user wants to get a movie suggested to them they simple need to click on the "Get Movie Suggestion" link and enter the title of a movie that they like. The service will sort through the movies in its database and return those that have either: A) the same director, B) the same genre, or C) both. 

# 3.0 Example 1 - Adding a Movie
One of the actions a user is likely to make is to add a movie. Madison Movie Database makes this process simple with its ability to add movies. 
Step 1. Navigate to the "Add a movie" link on the homepage and click it.
Step 2. The user will see a form with fields for Title, Description, Release Year, Genre, Director, and Rating. Fill these fields out and click "submit movie".
Step 3. The user will be redirected to a confirmation page, displaying the properties (Like Genre, Director, etc) of the film that they entered. 
Step 4. The movie will now appear in all other related searches such as Search By Director, Rating, or Genre.

# 4.0 Example 2 - Search By Rating, Genre, Title or Director
A useful trait of Madison Movie Database is the ability to filter all movies by a specified Genre, Rating, or Director. 
Note: The steps to this process will demonstrate the "Search by Genre" ability. Searching by Rating or Director is the same process. 
Step 1. Navigate to the "Search by Genre" link on the homepage and click it. 
Step 2. The user will see a button that says "Choose" (or "Enter a director name" for Directors), click the button and a drop-down list of all genres will appear. Click the desired genre and click "Submit Choice".
Step 3. The service will filter through the database and return a list of all the movies that match the specified genre.

# Using Movies Suggestor
Madison Movie Database's keynote ability is the capability of suggesting movies to users. 
Step 1. Navigate to the "Get Movies Suggestions" link on the homepage and click it. 
Step 2. The user will be redirected to a page asking the user to supply the title of a movie that they like. This field attempt to aut-complete the user's movie choice based on what the user currently has entered. For instance if the user wants to search for Grapes of Wrath, the field will provide a drop down list of all movies starting with G when the user enters G. Then all movies with GR when the user adds an R. This makes finding a movie even easier!
Step 3. Once a movie is entered press the submit button. 
Step 4. If the database find a similar movie (or movies) the program will output a complete list of suggested moves. If there is no similar movie the program will inform the user that no simliar movie was found. 

# Acknowledgements
Contributors:
Leja Thao,
Ren Thao,
Jeff Herrmann








